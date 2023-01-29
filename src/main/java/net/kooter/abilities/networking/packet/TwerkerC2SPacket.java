package net.kooter.abilities.networking.packet;

import net.kooter.abilities.twerker.PlayerTwerkerProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TwerkerC2SPacket {
    public TwerkerC2SPacket() {

    }

    public TwerkerC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            player.getCapability(PlayerTwerkerProvider.PLAYER_TWERKER).ifPresent(twerker -> {
                twerker.inverseTwerker();
                player.sendSystemMessage(Component.literal("Turned Twerker " + (twerker.getTwerker() ? "on" : "off")).withStyle(ChatFormatting.AQUA));
            });
        });
        return true;
    }
}
