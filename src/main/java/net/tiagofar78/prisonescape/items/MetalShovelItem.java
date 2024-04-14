package net.tiagofar78.prisonescape.items;

import org.bukkit.Material;

public class MetalShovelItem extends ToolItem {

    @Override
    protected int usesAmount() {
        return 11;
    }

    @Override
    public int damageToBlock() {
        return 100;
    }

    @Override
    public boolean isMetalic() {
        return true;
    }

    @Override
    public boolean isIllegal() {
        return true;
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_SHOVEL;
    }

}