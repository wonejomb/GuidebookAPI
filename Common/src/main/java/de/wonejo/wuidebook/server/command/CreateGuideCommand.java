package de.wonejo.wuidebook.server.command;

import com.mojang.brigadier.CommandDispatcher;
import de.wonejo.wuidebook.core.ModItems;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CreateGuideCommand {

    public static void registerCommand (@NotNull CommandDispatcher<CommandSourceStack> pDispatcher) {
        pDispatcher.register(
                Commands.literal("CreateGuide")
                        .requires((permission) -> permission.hasPermission(2))
                        .then(
                                Commands.argument("target", EntityArgument.player())
                                        .executes((source) ->
                                                giveItem(source.getSource(),
                                                EntityArgument.getPlayer(source, "target")))
                        )
        );
    }

    private static int giveItem (CommandSourceStack pSource, @NotNull ServerPlayer pTarget ) {
        ItemStack guideBaseStack = new ItemStack(ModItems.GUIDE_BASE, 1);

        if ( !pTarget.addItem(guideBaseStack)) {
            pTarget.drop(guideBaseStack, false);
            pSource.sendSuccess(() -> Component.translatable("commands.wapi.createguide.success", pTarget.getDisplayName()), true);
        }

        return 0;
    }

}
