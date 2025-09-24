package dev.lu15.voicechat.network.minecraft;

import java.util.UUID;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record VoiceState(
        boolean disabled,
        boolean disconnected,
        @NotNull UUID uuid,
        @NotNull String name,
        boolean hasGroup,
        @Nullable UUID group
) {

    public static final @NotNull NetworkBuffer.Type<@NotNull VoiceState> NETWORK_TYPE = NetworkBufferTemplate.template(
            NetworkBuffer.BOOLEAN, VoiceState::disabled,
            NetworkBuffer.BOOLEAN, VoiceState::disconnected,
            NetworkBuffer.UUID, VoiceState::uuid,
            NetworkBuffer.STRING, VoiceState::name,
            NetworkBuffer.BOOLEAN, VoiceState::hasGroup,
            NetworkBuffer.OPT_UUID, VoiceState::group,
            VoiceState::new
    );

}
