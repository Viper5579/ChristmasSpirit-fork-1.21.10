package com.tm.cspirit.item.base;

import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemSpawnEggBase extends SpawnEggItem {

    protected static final List<ItemSpawnEggBase> UNADDED_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<? extends Mob>> entityTypeSupplier;

    public ItemSpawnEggBase(final RegistryObject<? extends EntityType<? extends Mob>> entityTypeSupplier) {
        super((EntityType<? extends Mob>) null, 0xFFFFFF, 0xFFFFFF, new Item.Properties());
        this.entityTypeSupplier = Lazy.of((Supplier<? extends EntityType<? extends Mob>>) entityTypeSupplier);
        UNADDED_EGGS.add(this);
    }

    public static void initSpawnEggs() {
        DispenseItemBehavior dispenseBehavior = new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack execute(net.minecraft.core.dispenser.BlockSource source, ItemStack stack) {
                Direction direction = source.state().getValue(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack);

                try {
                    type.spawn(source.level(), stack, null, source.pos().relative(direction),
                        MobSpawnType.DISPENSER, direction != Direction.UP, false);
                } catch (Exception e) {
                    // Spawn failed, return stack unchanged
                }

                stack.shrink(1);
                return stack;
            }
        };

        for (final SpawnEggItem spawnEgg : UNADDED_EGGS) {
            DispenserBlock.registerBehavior(spawnEgg, dispenseBehavior);
        }

        UNADDED_EGGS.clear();
    }

    @Override
    public EntityType<? extends Mob> getType(ItemStack stack) {
        return (EntityType<? extends Mob>) this.entityTypeSupplier.get();
    }
}
