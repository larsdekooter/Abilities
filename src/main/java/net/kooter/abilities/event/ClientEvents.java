package net.kooter.abilities.event;

import com.mojang.logging.LogUtils;
import net.kooter.abilities.AbilitiesMod;
import net.kooter.abilities.networking.ModMessages;
import net.kooter.abilities.networking.packet.TwerkerC2SPacket;
import net.kooter.abilities.networking.packet.VeinmineC2SPacket;
import net.kooter.abilities.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

public class ClientEvents {
    private static final Logger LOGGER = LogUtils.getLogger();
    @Mod.EventBusSubscriber(modid = AbilitiesMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.VEINMINING_KEY.consumeClick()) {
                ModMessages.sendToServer(new VeinmineC2SPacket());
            }
            if(KeyBinding.TWERKER_KEY.consumeClick()) {
                ModMessages.sendToServer(new TwerkerC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = AbilitiesMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.VEINMINING_KEY);
            event.register(KeyBinding.TWERKER_KEY);
        }
    }

}
