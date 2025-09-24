package dev.lu15.voicechat.network.minecraft;

import net.kyori.adventure.key.Key;
import net.minestom.server.network.NetworkBuffer;
import org.jetbrains.annotations.NotNull;

public interface Packet<T extends Packet<T>> {

    @NotNull Key id();

    @NotNull NetworkBuffer.Type<@NotNull T> serializer();

}
