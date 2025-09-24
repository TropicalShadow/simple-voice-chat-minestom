package dev.lu15.voicechat;

import dev.lu15.voicechat.network.minecraft.VoiceState;
import java.net.SocketAddress;
import java.util.UUID;

import dev.lu15.voicechat.network.voice.encryption.Secret;
import net.minestom.server.tag.Tag;
import org.jetbrains.annotations.NotNull;

public final class Tags {

    public static final @NotNull Tag<@NotNull SocketAddress> VOICE_CLIENT = Tag.Transient("voicechat:voice-client");
    public static final @NotNull Tag<@NotNull VoiceState> PLAYER_STATE = Tag.Transient("voicechat:player-state");
    public static final @NotNull Tag<@NotNull Long> LAST_KEEP_ALIVE = Tag.Long("voicechat:last-keep-alive");
    public static final @NotNull Tag<@NotNull Secret> SECRET = Tag.Transient("voicechat:secret"); // transient because we don't ever want to save this to disk

    private Tags() {}

}
