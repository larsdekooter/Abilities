package net.kooter.abilities.networking;

import net.kooter.abilities.AbilitiesMod;
import net.kooter.abilities.networking.packet.TwerkerC2SPacket;
import net.kooter.abilities.networking.packet.VeinmineC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(AbilitiesMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.o")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(VeinmineC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(VeinmineC2SPacket::new)
                .encoder(VeinmineC2SPacket::toBytes)
                .consumerMainThread(VeinmineC2SPacket::handle)
                .add();
        net.messageBuilder(TwerkerC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TwerkerC2SPacket::new)
                .encoder(TwerkerC2SPacket::toBytes)
                .consumerMainThread(TwerkerC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
