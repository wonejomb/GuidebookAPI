package de.wonejo.wuidebook.core;

import com.mojang.brigadier.CommandDispatcher;
import de.wonejo.wuidebook.server.command.CreateGuideCommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ModCommands {

    public static void setupCommands (CommandDispatcher<CommandSourceStack> pDispatcher, Commands.CommandSelection pSelection, CommandBuildContext pContext) {
        CreateGuideCommand.registerCommand(pDispatcher);
    }

}
