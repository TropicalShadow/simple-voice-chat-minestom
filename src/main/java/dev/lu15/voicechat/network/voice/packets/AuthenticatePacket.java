package dev.lu15.voicechat.network.voice.packets;

import dev.lu15.voicechat.network.voice.VoicePacket;
import java.util.UUID;

import dev.lu15.voicechat.network.voice.encryption.Secret;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;

public record AuthenticatePacket(
        @NotNull UUID player,
        byte[] secret
) implements VoicePacket<AuthenticatePacket> {

    public static final @NotNull NetworkBuffer.Type<@NotNull AuthenticatePacket> SERIALIZER = NetworkBufferTemplate.template(
            NetworkBuffer.UUID, AuthenticatePacket::player,
            NetworkBuffer.FixedRawBytes(Secret.SECRET_SIZE_BYTES), AuthenticatePacket::secret,
            AuthenticatePacket::new
    );

    @Override
    public int id() {
        return 0x5;
    }

    @Override
    public NetworkBuffer.@NotNull Type<@NotNull AuthenticatePacket> serializer() {
        return SERIALIZER;
    }

}
