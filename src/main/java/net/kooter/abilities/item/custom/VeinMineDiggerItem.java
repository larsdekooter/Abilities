package net.kooter.abilities.item.custom;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import net.kooter.abilities.veinmine.PlayerVeinmineProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.List;
import java.util.Queue;

public class VeinMineDiggerItem extends DiggerItem {
    public static int RADIUS = 4;

    private static Logger LOGGER = LogUtils.getLogger();
    public VeinMineDiggerItem(float v, float v1, Tier tier, TagKey<Block> blockTagKey, Properties properties) {
        super(v, v1, tier, blockTagKey, properties);
    }

    @Override
    public boolean mineBlock(ItemStack tool, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        super.mineBlock(tool, level, blockState, blockPos, livingEntity);
        livingEntity.getCapability(PlayerVeinmineProvider.PLAYER_VEINMINE).ifPresent(veinmine -> {
            if(veinmine.getVeinmine()) {

                Block blockBeingMined = level.getBlockState(blockPos).getBlock();
                Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
                queue.add(new Tuple<>(blockPos, 0));
                BlockState state = level.getBlockState(blockPos);

                while (!queue.isEmpty()) {
                    Tuple<BlockPos, Integer> tuple = queue.poll();
                    BlockPos blockpos = tuple.getA();

                    // Loops 3 times
                    for(int vert = 0; vert < RADIUS; vert++) {
                        BlockPos verPos = blockPos.below(vert);
                        for(int hor = 0; hor < RADIUS; hor++) {
                            BlockPos horPos = verPos.north(hor);
                            if(level.getBlockState(horPos).getBlock().equals(blockBeingMined)) {
                                level.destroyBlock(horPos, true, livingEntity);
                            }
                            // 0 may need to change to -1
                            for(int i = 0; i < RADIUS; i++) {
                                BlockPos iPos = horPos.west(i);
                                if(level.getBlockState(iPos).getBlock().equals(blockBeingMined)) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                            // 0 may need to change to -1
                            for(int i = 0; i < RADIUS; i++) {
                                BlockPos iPos = horPos.east(i);
                                if(level.getBlockState(iPos).getBlock().equals(blockBeingMined)) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                        }
                        for(int hor = 0; hor < RADIUS; hor++) {
                            BlockPos horPos = verPos.south(hor);
                            if(level.getBlockState(horPos).getBlock().equals(blockBeingMined)) {
                                level.destroyBlock(horPos, true, livingEntity);
                            }
                            // 0 may need to change to -1
                            for(int i = 0; i < RADIUS; i++) {
                                BlockPos iPos = horPos.west(i);
                                if(level.getBlockState(iPos).getBlock().equals(blockBeingMined)) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                            // 0 may need to change to -1
                            for(int i = 0; i < RADIUS; i++) {
                                BlockPos iPos = horPos.east(i);
                                if(level.getBlockState(iPos).getBlock().equals(blockBeingMined)) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                        }
                    }


                    for(int vert = 0; vert < RADIUS; vert++) {
                        BlockPos verPos = blockPos.above(vert);
                        for(int hor = 0; hor < RADIUS; hor++) {
                            BlockPos horPos = verPos.north(hor);
                            if(level.getBlockState(horPos).getBlock().equals(blockBeingMined)) {
                                level.destroyBlock(horPos, true, livingEntity);
                            }
                            // 0 may need to change to -1
                            for(int i = 0; i < RADIUS; i++) {
                                BlockPos iPos = horPos.west(i);
                                if(level.getBlockState(iPos).getBlock().equals(blockBeingMined)) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                            // 0 may need to change to -1
                            for(int i = 0; i < RADIUS; i++) {
                                BlockPos iPos = horPos.east(i);
                                if(level.getBlockState(iPos).getBlock().equals(blockBeingMined)) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                        }
                        for(int hor = 0; hor < RADIUS; hor++) {
                            BlockPos horPos = verPos.south(hor);
                            if(level.getBlockState(horPos).getBlock().equals(blockBeingMined)) {
                                level.destroyBlock(horPos, true, livingEntity);
                            }
                            // 0 may need to change to -1
                            for(int i = 0; i < RADIUS; i++) {
                                BlockPos iPos = horPos.west(i);
                                if(level.getBlockState(iPos).getBlock().equals(blockBeingMined)) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                            // 0 may need to change to -1
                            for(int i = 0; i < RADIUS; i++) {
                                BlockPos iPos = horPos.east(i);
                                if(level.getBlockState(iPos).getBlock().equals(blockBeingMined)) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                        }
                    }
                }
            }
        });


        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Mines blocks in an 7x7x7 area around the player").withStyle(ChatFormatting.AQUA));
        super.appendHoverText(stack, level, components, flag);
    }
}
