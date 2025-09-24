package dev.lu15.voicechat.network.minecraft;

import dev.lu15.voicechat.VoiceChat;
import dev.lu15.voicechat.network.minecraft.packets.clientbound.CategoryAddedPacket;
import dev.lu15.voicechat.network.minecraft.packets.clientbound.VoiceStateRemovedPacket;
import dev.lu15.voicechat.network.minecraft.packets.serverbound.CreateGroupPacket;
import dev.lu15.voicechat.network.minecraft.packets.clientbound.GroupCreatedPacket;
import dev.lu15.voicechat.network.minecraft.packets.clientbound.GroupChangedPacket;
import dev.lu15.voicechat.network.minecraft.packets.clientbound.GroupRemovedPacket;
import dev.lu15.voicechat.network.minecraft.packets.serverbound.JoinGroupPacket;
import dev.lu15.voicechat.network.minecraft.packets.serverbound.LeaveGroupPacket;
import dev.lu15.voicechat.network.minecraft.packets.clientbound.VoiceStateUpdatedPacket;
import dev.lu15.voicechat.network.minecraft.packets.clientbound.HandshakeAcknowledgePacket;
import dev.lu15.voicechat.network.minecraft.packets.serverbound.HandshakePacket;
import dev.lu15.voicechat.network.minecraft.packets.serverbound.UpdateStatePacket;
import dev.lu15.voicechat.network.minecraft.packets.clientbound.VoiceStatesUpdatedPacket;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.key.Key;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.packet.server.common.PluginMessagePacket;
import org.intellij.lang.annotations.Subst;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MinecraftPacketHandler {

    private final @NotNull Map<Key, NetworkBuffer.Type<@NotNull Packet<?>>> serializers = new HashMap<>();

    public MinecraftPacketHandler() {
        // clientbound
        this.register(HandshakeAcknowledgePacket.IDENTIFIER, HandshakeAcknowledgePacket.SERIALIZER);
        this.register(VoiceStateRemovedPacket.IDENTIFIER, VoiceStateRemovedPacket.SERIALIZER);
        this.register(VoiceStateUpdatedPacket.IDENTIFIER, VoiceStateUpdatedPacket.SERIALIZER);
        this.register(VoiceStatesUpdatedPacket.IDENTIFIER, VoiceStatesUpdatedPacket.SERIALIZER);
        this.register(GroupCreatedPacket.IDENTIFIER, GroupCreatedPacket.SERIALIZER);
        this.register(GroupChangedPacket.IDENTIFIER, GroupChangedPacket.SERIALIZER);
        this.register(GroupRemovedPacket.IDENTIFIER, GroupRemovedPacket.SERIALIZER);
        this.register(CategoryAddedPacket.IDENTIFIER, CategoryAddedPacket.SERIALIZER);
        this.register(CategoryAddedPacket.IDENTIFIER, CategoryAddedPacket.SERIALIZER);

        // serverbound
        this.register(HandshakePacket.IDENTIFIER, HandshakePacket.SERIALIZER);
        this.register(UpdateStatePacket.IDENTIFIER, UpdateStatePacket.SERIALIZER);
        this.register(JoinGroupPacket.IDENTIFIER, JoinGroupPacket.SERIALIZER);
        this.register(LeaveGroupPacket.IDENTIFIER, LeaveGroupPacket.SERIALIZER);
        this.register(CreateGroupPacket.IDENTIFIER, CreateGroupPacket.SERIALIZER);
    }

    @SuppressWarnings("unchecked")
    public <T extends Packet<T>> void register(@NotNull Key id, @NotNull NetworkBuffer.Type<@NotNull T> serializer) {
        if (!id.namespace().equals(VoiceChat.NAMESPACE)) throw new IllegalArgumentException("id with incorrect namespace used");
        this.serializers.put(id, (NetworkBuffer.Type<@NotNull Packet<?>>) serializer);
    }

    public @Nullable Packet<?> read(@Subst("minecraft") @NotNull String identifier, byte[] data) {
        Key key = Key.key(identifier);
        NetworkBuffer.Type<@NotNull Packet<?>> serializer = this.serializers.get(key);
        if (serializer == null) return null;

        NetworkBuffer buffer = NetworkBuffer.wrap(data, 0, data.length);
        return serializer.read(buffer);
    }

    public <T extends Packet<T>> @NotNull PluginMessagePacket write(@NotNull T packet) {
        NetworkBuffer.Type<@NotNull T> serializer = packet.serializer();
        NetworkBuffer buffer = NetworkBuffer.resizableBuffer();
        buffer.write(serializer, packet);

        byte[] data = new byte[(int) buffer.writeIndex()];
        buffer.copyTo(0, data, 0, data.length);

        return new PluginMessagePacket(packet.id().asString(), data);
    }

}
