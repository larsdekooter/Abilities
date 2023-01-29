package net.kooter.abilities.item.custom;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import net.kooter.abilities.veinmine.PlayerVeinmineProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

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

                while(!queue.isEmpty()) {
                    Tuple<BlockPos, Integer> tuple = queue.poll();
                    BlockPos blockpos = tuple.getA();

                    for(int vert = 0; vert < RADIUS; vert++) {
                        BlockPos vertPos = blockPos.below(vert);
                        for(int hor = 0; hor < RADIUS; hor++) {
                            BlockPos horPos = vertPos.east(hor);
                            if(level.getBlockState(horPos).getBlock().equals(state.getBlock())) {
                                level.destroyBlock(horPos, true, livingEntity);
                            }
                            for(int i = -1; i < RADIUS; i++) {
                                BlockPos iPos = horPos.north(i);
                                if(level.getBlockState(iPos).getBlock().equals(state.getBlock())) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                            for(int i = -1; i < RADIUS; i++) {
                                BlockPos iPos = horPos.south(i);
                                if(level.getBlockState(iPos).getBlock().equals(state.getBlock())) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                        }
                    }
                    for(int vert = 0; vert < RADIUS; vert++) {
                        BlockPos vertPos = blockPos.below(vert);
                        for(int hor = 0; hor < RADIUS; hor++) {
                            BlockPos horPos = vertPos.west(hor);
                            if(level.getBlockState(horPos).getBlock().equals(state.getBlock())) {
                                level.destroyBlock(horPos, true, livingEntity);
                            }
                            for(int i = -1; i < RADIUS; i++) {
                                BlockPos iPos = horPos.south(i);
                                if(level.getBlockState(iPos).getBlock().equals(state.getBlock())) {
                                    level.destroyBlock(iPos, true, livingEntity);
                                }
                            }
                            for(int i = -1; i < RADIUS; i++) {
                                BlockPos iPos = horPos.north(i);
                                if(level.getBlockState(iPos).getBlock().equals(state.getBlock())) {
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
}
