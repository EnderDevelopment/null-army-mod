package com.enderdevelopment.nullarmymod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item.Properties;

public class NullArmyMod implements ModInitializer {
    public static final String MOD_ID = "null_army_mod";
    public static final Item TOTEM_OF_NULL = new Item(new Properties().stacksTo(1));

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "totem_of_null"), TOTEM_OF_NULL);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(MOD_ID, "null_army_tab"), CreativeModeTabs.builder()
            .title(Component.translatable("itemGroup.null_army_tab"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> new ItemStack(TOTEM_OF_NULL))
            .displayItems((parameters, output) -> {
                output.accept(TOTEM_OF_NULL);
            }).build());
    }
}