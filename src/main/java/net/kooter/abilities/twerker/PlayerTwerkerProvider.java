package net.kooter.abilities.twerker;

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

public class PlayerTwerkerProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerTwerker> PLAYER_TWERKER = CapabilityManager.get(new CapabilityToken<PlayerTwerker>() { });

    private PlayerTwerker twerker = null;
    private final LazyOptional<PlayerTwerker> optional = LazyOptional.of(this::createPlayerTwerker);

    private PlayerTwerker createPlayerTwerker() {
        if(this.twerker == null) {
            this.twerker = new PlayerTwerker();
        }
        return this.twerker;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_TWERKER) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerTwerker().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerTwerker().loadNBTData(nbt);
    }
}
