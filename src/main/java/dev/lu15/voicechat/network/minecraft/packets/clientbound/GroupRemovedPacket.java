package dev.lu15.voicechat.network.minecraft.packets.clientbound;

import dev.lu15.voicechat.VoiceChat;
import dev.lu15.voicechat.network.minecraft.Packet;
import java.util.UUID;
import net.kyori.adventure.key.Key;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;

public record GroupRemovedPacket(@NotNull UUID group) implements Packet<GroupRemovedPacket> {

    public static final @NotNull Key IDENTIFIER = VoiceChat.key("remove_group");
    public static final @NotNull NetworkBuffer.Type<@NotNull GroupRemovedPacket> SERIALIZER = NetworkBufferTemplate.template(
            NetworkBuffer.UUID, GroupRemovedPacket::group,
            GroupRemovedPacket::new
    );

    @Override
    public @NotNull Key id() {
        return IDENTIFIER;
    }

    @Override
    public NetworkBuffer.@NotNull Type<@NotNull GroupRemovedPacket> serializer() {
        return SERIALIZER;
    }

}
