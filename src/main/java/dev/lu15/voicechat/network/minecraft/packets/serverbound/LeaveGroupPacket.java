package dev.lu15.voicechat.network.minecraft.packets.serverbound;

import dev.lu15.voicechat.VoiceChat;
import dev.lu15.voicechat.network.minecraft.Packet;
import net.kyori.adventure.key.Key;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;

public record LeaveGroupPacket() implements Packet<LeaveGroupPacket> {

    public static final @NotNull Key IDENTIFIER = VoiceChat.key("leave_group");
    public static final @NotNull NetworkBuffer.Type<@NotNull LeaveGroupPacket> SERIALIZER = NetworkBufferTemplate.template(
            LeaveGroupPacket::new
    );

    @Override
    public @NotNull Key id() {
        return IDENTIFIER;
    }

    @Override
    public NetworkBuffer.@NotNull Type<@NotNull LeaveGroupPacket> serializer() {
        return SERIALIZER;
    }

}
