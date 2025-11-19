package com.tm.cspirit.effect;

import net.minecraft.world.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.List;

public class NaughtyEffect extends Effect {

    public NaughtyEffect() {
        super(EffectType.HARMFUL, 0);
    }

    @Override
    public String getName() {
        return "Naughty";
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
