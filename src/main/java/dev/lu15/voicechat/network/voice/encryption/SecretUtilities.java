package dev.lu15.voicechat.network.voice.encryption;

import dev.lu15.voicechat.Tags;

import java.util.UUID;
import java.util.WeakHashMap;

import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SecretUtilities {

    private static final @NotNull WeakHashMap<UUID, Secret> secrets = new WeakHashMap<>();

    private SecretUtilities() {}

    public static @Nullable Secret getSecret(@NotNull UUID player) {
        return secrets.getOrDefault(player, null);
    }

    public static @Nullable Secret getSecret(@NotNull Player player) {
        return player.getTag(Tags.SECRET);
    }

    public static boolean hasSecret(@NotNull Player player) {
        return player.hasTag(Tags.SECRET);
    }

    public static void setSecret(@NotNull Player player, @Nullable Secret secret) {
        if (secret == null) {
            secrets.remove(player.getUuid());
            player.removeTag(Tags.SECRET);
            return;
        }
        secrets.put(player.getUuid(), secret);
        player.setTag(Tags.SECRET, secret);
    }

    public static @NotNull Secret generateSecret() {
        return Secret.generateNewRandomSecret();
    }

}
