package dev.lu15.voicechat.network.minecraft.packets.serverbound;

import dev.lu15.voicechat.VoiceChat;
import dev.lu15.voicechat.network.NetworkTypes;
import dev.lu15.voicechat.network.minecraft.Group;
import dev.lu15.voicechat.network.minecraft.Packet;
import net.kyori.adventure.key.Key;
import net.minestom.server.network.NetworkBuffer;
import net.minestom.server.network.NetworkBufferTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record CreateGroupPacket(
        @NotNull String name,
        @Nullable String password,
        @NotNull Group.Type type
) implements Packet<CreateGroupPacket> {

    public static final @NotNull Key IDENTIFIER = VoiceChat.key("create_group");
    public static final @NotNull NetworkBuffer.Type<@NotNull CreateGroupPacket> SERIALIZER = NetworkBufferTemplate.template(
            NetworkBuffer.STRING, CreateGroupPacket::name,
            NetworkBuffer.STRING.optional(), CreateGroupPacket::password,
            NetworkTypes.ShortEnum(Group.Type.class), CreateGroupPacket::type,
            CreateGroupPacket::new
    );

    @Override
    public @NotNull Key id() {
        return IDENTIFIER;
    }

    @Override
    public NetworkBuffer.@NotNull Type<@NotNull CreateGroupPacket> serializer() {
        return SERIALIZER;
    }

}
