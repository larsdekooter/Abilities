package net.kooter.abilities.item;

import net.kooter.abilities.AbilitiesMod;
import net.kooter.abilities.item.custom.VeinMineDiggerItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AbilitiesMod.MOD_ID);


    public static RegistryObject<Item> VEINMINE_PICKAXE = ITEMS.register("veinmine_pickaxe",
            () -> new VeinMineDiggerItem(1, 1, Tiers.DIAMOND
            , BlockTags.MINEABLE_WITH_PICKAXE, new Item.Properties().stacksTo(1)));

    public static RegistryObject<Item> VEINMINE_AXE = ITEMS.register("veinmine_axe",
            () -> new VeinMineDiggerItem(1, 1, Tiers.DIAMOND, BlockTags.MINEABLE_WITH_AXE,
                    new Item.Properties().stacksTo(1)));

    public static RegistryObject<Item> VEINMINE_SHOVEL = ITEMS.register("veinmine_shovel",
            () -> new VeinMineDiggerItem(1, 1, Tiers.DIAMOND, BlockTags.MINEABLE_WITH_SHOVEL,
                    new Item.Properties().stacksTo(1)));

    public static RegistryObject<Item> VEINMINE_HOE = ITEMS.register("veinmine_hoe",
            () -> new VeinMineDiggerItem(1, 1, Tiers.DIAMOND, BlockTags.MINEABLE_WITH_HOE,
                    new Item.Properties().stacksTo(1)));

    public static RegistryObject<Item> VEIN_MINER = ITEMS.register("vein_miner_ingot",
            () -> new Item(new Item.Properties().stacksTo(64).rarity(Rarity.EPIC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
