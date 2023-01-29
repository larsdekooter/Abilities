package net.kooter.abilities.networking.packet;

import com.mojang.logging.LogUtils;
import net.kooter.abilities.veinmine.PlayerVeinmineProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class VeinmineC2SPacket {
    private static final String MESSAGE_VEINMINE_ENABLE = "message.abilities.veinmine_enable";
    private static final String MESSAGE_VEINMINE_DISABLE = "message.abilities.veinmine_disable";

    public VeinmineC2SPacket() {

    }

    public VeinmineC2SPacket(FriendlyByteBuf buf) {}

    public void toBytes(FriendlyByteBuf buf) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player  = context.getSender();
            ServerLevel level = player.getLevel();

            LogUtils.getLogger().info("Handle Function");
           // Enable / Disable veinmining
            player.getCapability(PlayerVeinmineProvider.PLAYER_VEINMINE).ifPresent(veinmine -> {
                veinmine.inverseVeinmine();

                player.sendSystemMessage(Component.literal("Turned Vein Mine " + (veinmine.getVeinmine() ? "on" : "off")).withStyle(ChatFormatting.AQUA));
            });
        });
        return true;
    }

}
