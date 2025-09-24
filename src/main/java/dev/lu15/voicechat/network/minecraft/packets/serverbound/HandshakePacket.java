package dev.lu15.voicechat.network.minecraft.packets.serverbound;

import dev.lu15.voicechat.VoiceChat;
import dev.lu15.voicechat.network.minecraft.Packet;
import net.kyori.adventure.key.Key;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;

public record HandshakePacket(int version) implements Packet<HandshakePacket> {

    public static final @NotNull Key IDENTIFIER = VoiceChat.key("request_secret");
    public static final @NotNull NetworkBuffer.Type<@NotNull HandshakePacket> SERIALIZER = NetworkBufferTemplate.template(
            NetworkBuffer.INT, HandshakePacket::version,
            HandshakePacket::new
    );

    @Override
    public @NotNull Key id() {
        return IDENTIFIER;
    }

    @Override
    public NetworkBuffer.@NotNull Type<@NotNull HandshakePacket> serializer() {
        return SERIALIZER;
    }

}
