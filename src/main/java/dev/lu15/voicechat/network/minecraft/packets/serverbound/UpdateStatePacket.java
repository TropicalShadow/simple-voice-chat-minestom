package dev.lu15.voicechat.network.minecraft.packets.serverbound;

import dev.lu15.voicechat.VoiceChat;
import dev.lu15.voicechat.network.minecraft.Packet;
import net.kyori.adventure.key.Key;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;

public record UpdateStatePacket(boolean disabled) implements Packet<UpdateStatePacket> {

    public static final @NotNull Key IDENTIFIER = VoiceChat.key("update_state");
    public static final @NotNull NetworkBuffer.Type<@NotNull UpdateStatePacket> SERIALIZER = NetworkBufferTemplate.template(
            NetworkBuffer.BOOLEAN, UpdateStatePacket::disabled,
            UpdateStatePacket::new
    );

    @Override
    public @NotNull Key id() {
        return IDENTIFIER;
    }

    @Override
    public NetworkBuffer.@NotNull Type<@NotNull UpdateStatePacket> serializer() {
        return SERIALIZER;
    }

}
