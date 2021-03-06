/**
 *  Plugin UltraHardcore Reloaded (UHPlugin)
 *  Copyright (C) 2013 azenet
 *  Copyright (C) 2014-2015 Amaury Carrade
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see [http://www.gnu.org/licenses/].
 */
package eu.carrade.amaury.UHCReloaded.commands.commands.uh;

import eu.carrade.amaury.UHCReloaded.commands.commands.categories.Category;
import eu.carrade.amaury.UHCReloaded.commands.commands.uh.spawns.UHSpawnsDumpCommand;
import eu.carrade.amaury.UHCReloaded.commands.core.exceptions.CannotExecuteCommandException;
import eu.carrade.amaury.UHCReloaded.UHCReloaded;
import eu.carrade.amaury.UHCReloaded.commands.commands.uh.spawns.*;
import eu.carrade.amaury.UHCReloaded.commands.core.AbstractCommand;
import eu.carrade.amaury.UHCReloaded.commands.core.annotations.Command;
import eu.carrade.amaury.UHCReloaded.i18n.I18n;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

@Command(name = "spawns")
public class UHSpawnsCommand extends AbstractCommand {

	private UHCReloaded p;
	private final I18n i;

	public UHSpawnsCommand(UHCReloaded plugin) {
		p = plugin;
		i = p.getI18n();

		registerSubCommand(new UHSpawnsAddCommand(p));
		registerSubCommand(new UHSpawnsGenerateCommand(p));
		registerSubCommand(new UHSpawnsListCommand(p));
		registerSubCommand(new UHSpawnsDumpCommand(p));
		registerSubCommand(new UHSpawnsRemoveCommand(p));
		registerSubCommand(new UHSpawnsResetCommand(p));
	}

	/**
	 * This will be executed if this command is called without argument,
	 * or if there isn't any sub-command executor registered.
	 *
	 * @param sender The sender.
	 * @param args   The arguments passed to the command.
	 */
	@Override
	public void run(CommandSender sender, String[] args) throws CannotExecuteCommandException {
		throw new CannotExecuteCommandException(CannotExecuteCommandException.Reason.NEED_DOC, this);
	}

	/**
	 * The result of this method will be added to the tab-complete suggestions for this command.
	 *
	 * @param sender The sender.
	 * @param args   The arguments.
	 *
	 * @return The suggestions to add.
	 */
	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return null;
	}

	@Override
	public List<String> help(CommandSender sender) {
		return Arrays.asList(i.t("cmd.spawnsHelpTitle"));
	}

	@Override
	public List<String> onListHelp(CommandSender sender) {
		return Arrays.asList(i.t("cmd.helpSpawns"));
	}

	@Override
	public String getCategory() {
		return Category.GAME.getTitle();
	}
}
