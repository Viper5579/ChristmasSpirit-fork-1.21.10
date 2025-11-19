package com.tm.cspirit.entity.data;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.item.ItemStack;

public class CSDataSerializers {

    public static final EntityDataSerializer<NonNullList<ItemStack>> ITEMSTACK_ARRAY_4 = new EntityDataSerializer<NonNullList<ItemStack>>() {

        @Override
        public void write(FriendlyByteBuf buf, NonNullList<ItemStack> value) {

            for (ItemStack stack : value) {
                buf.writeItem(stack);
            }
        }

        @Override
        public NonNullList<ItemStack> read(FriendlyByteBuf buf) {

            NonNullList<ItemStack> list = NonNullList.withSize(4, ItemStack.EMPTY);

            for (int i = 0; i < 4; i++) {
                list.set(i, buf.readItem());
            }

            return list;
        }

        @Override
        public NonNullList<ItemStack> copy(NonNullList<ItemStack> value) {

            NonNullList<ItemStack> list = NonNullList.withSize(4, ItemStack.EMPTY);

            for (int i = 0; i < 4; i++) {
                list.set(i, value.get(i).copy());
            }

            return list;
        }
    };
}
