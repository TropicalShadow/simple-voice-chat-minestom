package dev.lu15.voicechat.network.minecraft.packets.clientbound;

import dev.lu15.voicechat.VoiceChat;
import dev.lu15.voicechat.network.minecraft.Group;
import dev.lu15.voicechat.network.minecraft.Packet;
import net.kyori.adventure.key.Key;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;

public record GroupCreatedPacket(@NotNull Group group) implements Packet<GroupCreatedPacket> {

    public static final @NotNull Key IDENTIFIER = VoiceChat.key("add_group");
    public static final @NotNull NetworkBuffer.Type<GroupCreatedPacket> SERIALIZER = NetworkBufferTemplate.template(
            Group.NETWORK_TYPE, GroupCreatedPacket::group,
            GroupCreatedPacket::new
    );

    @Override
    public @NotNull Key id() {
        return IDENTIFIER;
    }

    @Override
    public NetworkBuffer.@NotNull Type<GroupCreatedPacket> serializer() {
        return SERIALIZER;
    }

}
