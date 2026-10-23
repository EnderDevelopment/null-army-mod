package com.enderdevelopment.nullarmymod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NullArmyCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("null")
            .then(Commands.literal("army")
                .executes(context -> {
                    summonNullArmy(context.getSource(), 1);
                    return 1;
                })
                .then(Commands.argument("count", IntegerArgumentType.integer(1))
                    .executes(context -> {
                        int count = IntegerArgumentType.getInteger(context, "count");
                        summonNullArmy(context.getSource(), count);
                        return 1;
                    })))
            .then(Commands.literal("army").then(Commands.literal("commander")
                .executes(context -> {
                    summonNullCommander(context.getSource(), 1);
                    return 1;
                })
                .then(Commands.argument("count", IntegerArgumentType.integer(1))
                    .executes(context -> {
                        int count = IntegerArgumentType.getInteger(context, "count");
                        summonNullCommander(context.getSource(), count);
                        return 1;
                    })))));
    }

    private static void summonNullArmy(CommandSourceStack source, int count) {
        ServerLevel level = source.getLevel();
        Player player = source.getPlayer();
        if (player != null) {
            for (int i = 0; i < count; i++) {
                NullEntity nullEntity = new NullEntity(EntityType.PLAYER, level);
                nullEntity.setPos(player.getX(), player.getY(), player.getZ());
                level.addFreshEntity(nullEntity);
            }
            source.sendSuccess(() -> Component.literal("Summoned " + count + " Null Army members."), true);
        }
    }

    private static void summonNullCommander(CommandSourceStack source, int count) {
        ServerLevel level = source.getLevel();
        Player player = source.getPlayer();
        if (player != null) {
            for (int i = 0; i < count; i++) {
                NullCommanderEntity nullCommander = new NullCommanderEntity(EntityType.PLAYER, level);
                nullCommander.setPos(player.getX(), player.getY(), player.getZ());
                level.addFreshEntity(nullCommander);
            }
            source.sendSuccess(() -> Component.literal("Summoned " + count + " Null Commanders."), true);
        }
    }
}