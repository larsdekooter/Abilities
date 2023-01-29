package net.kooter.abilities.veinmine;

import net.minecraft.nbt.CompoundTag;

public class PlayerVeinmine {
    private boolean veinmine = false;

    public boolean getVeinmine() {return veinmine;};

    public void enableVeinmine() {
        this.veinmine = true;
    }

    public void disableVeinmine() {
        this.veinmine = false;
    }

    public void inverseVeinmine() {
        veinmine = !veinmine;

    }

    public void copyFrom(net.kooter.abilities.veinmine.PlayerVeinmine source) {
        this.veinmine = source.veinmine;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("veinmine", veinmine);
    }

    public void loadNBTData(CompoundTag nbt) {
        veinmine = nbt.getBoolean("veinmine");
    }
}
