package dev.lu15.voicechat.network.voice.packets;

import dev.lu15.voicechat.network.voice.VoicePacket;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;

public record AuthenticationAcknowledgedPacket() implements VoicePacket<AuthenticationAcknowledgedPacket> {

    public static final @NotNull NetworkBuffer.Type<@NotNull AuthenticationAcknowledgedPacket> SERIALIZER = NetworkBufferTemplate.template(
            AuthenticationAcknowledgedPacket::new
    );

    @Override
    public int id() {
        return 0x6;
    }

    @Override
    public NetworkBuffer.@NotNull Type<@NotNull AuthenticationAcknowledgedPacket> serializer() {
        return SERIALIZER;
    }

}
