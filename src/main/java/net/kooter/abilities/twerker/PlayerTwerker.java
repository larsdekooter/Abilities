package net.kooter.abilities.twerker;

import net.minecraft.nbt.CompoundTag;

public class PlayerTwerker {
    private boolean twerker;

    public boolean getTwerker() {
        return twerker;
    }

    public void inverseTwerker() {
        this.twerker = !twerker;
    }

    public void copyFrom(PlayerTwerker source) {
        this.twerker = source.twerker;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("twerker", twerker);
    }

    public void loadNBTData(CompoundTag nbt) {
        twerker = nbt.getBoolean("twerker");
    }
}
