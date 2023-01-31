package net.kooter.abilities.event;


import net.kooter.abilities.AbilitiesMod;
import net.kooter.abilities.twerker.PlayerTwerker;
import net.kooter.abilities.twerker.PlayerTwerkerProvider;
import net.kooter.abilities.veinmine.PlayerVeinmine;
import net.kooter.abilities.veinmine.PlayerVeinmineProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AbilitiesMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerVeinmineProvider.PLAYER_VEINMINE).isPresent()) {
                event.addCapability(new ResourceLocation(AbilitiesMod.MOD_ID, "properties"), new PlayerVeinmineProvider());
            }
            if(!event.getObject().getCapability(PlayerTwerkerProvider.PLAYER_TWERKER).isPresent()) {
                event.addCapability(new ResourceLocation(AbilitiesMod.MOD_ID, "properties1"), new PlayerTwerkerProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerVeinmineProvider.PLAYER_VEINMINE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerVeinmineProvider.PLAYER_VEINMINE)
                        .ifPresent(newStore -> { newStore.copyFrom(oldStore);});
            });

            event.getOriginal().getCapability(PlayerTwerkerProvider.PLAYER_TWERKER).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerTwerkerProvider.PLAYER_TWERKER)
                        .ifPresent(newStore -> { newStore.copyFrom(oldStore);});
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerVeinmine.class); event.register(PlayerTwerker.class);
    }

    @SubscribeEvent
    public static void onCrouch(TickEvent.PlayerTickEvent event) {
        boolean isSneaking = event.player.isCrouching();
        event.player.getCapability(PlayerTwerkerProvider.PLAYER_TWERKER).ifPresent(twerker -> {
            if(!twerker.getTwerker()) return;
            if (isSneaking) {
                BlockPos pos = event.player.blockPosition();
                for (int i = -1; i <= 1; i++) {
                    BlockPos newPos = pos.north(i);
                    bonemeal(newPos.above(), event);
                    bonemeal(newPos, event);
                    for (int j = -1; j <= 1; j++) {
                        BlockPos newNewPos = newPos.west(j);
                        bonemeal(newNewPos.above(), event);
                        bonemeal(newNewPos, event);
                    }
                }
            }
        });

    }

    public static void bonemeal(BlockPos pos, TickEvent.PlayerTickEvent event) {
        if(event.player.level.isClientSide()) return;
        Block block = event.player.level.getBlockState(pos).getBlock();
        if(block instanceof BonemealableBlock) {
            if(block instanceof GrassBlock) return;
            ServerLevel level = (ServerLevel) event.player.level;
            BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), level, pos, event.player);
        }
    }
}


