package net.kooter.abilities.veinmine;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerVeinmineProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<net.kooter.abilities.veinmine.PlayerVeinmine> PLAYER_VEINMINE = CapabilityManager.get(new CapabilityToken<net.kooter.abilities.veinmine.PlayerVeinmine>() { });

    private net.kooter.abilities.veinmine.PlayerVeinmine veinmine = null;
    private final LazyOptional<net.kooter.abilities.veinmine.PlayerVeinmine> optional = LazyOptional.of(this::createPlayerVeinmine);

    private net.kooter.abilities.veinmine.PlayerVeinmine createPlayerVeinmine() {
        if(this.veinmine == null) {
           this.veinmine = new net.kooter.abilities.veinmine.PlayerVeinmine();
        }

        return this.veinmine;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_VEINMINE) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerVeinmine().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerVeinmine().loadNBTData(nbt);
    }
}
