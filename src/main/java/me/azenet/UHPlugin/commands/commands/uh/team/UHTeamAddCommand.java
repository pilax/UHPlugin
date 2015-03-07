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
package me.azenet.UHPlugin.commands.commands.uh.team;

import me.azenet.UHPlugin.UHPlugin;
import me.azenet.UHPlugin.commands.core.annotations.Command;
import me.azenet.UHPlugin.commands.core.commands.UHCommand;
import me.azenet.UHPlugin.commands.core.exceptions.CannotExecuteCommandException;
import me.azenet.UHPlugin.i18n.I18n;
import me.azenet.UHPlugin.teams.TeamColor;
import me.azenet.UHPlugin.teams.UHTeam;
import me.azenet.UHPlugin.utils.CommandUtils;
import me.azenet.UHPlugin.utils.UHUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;


@Command(name = "add")
public class UHTeamAddCommand extends UHCommand {

	UHPlugin p;
	I18n i;

	public UHTeamAddCommand(UHPlugin plugin) {
		p = plugin;
		i = plugin.getI18n();
	}

	/**
	 * Runs the command.
	 *
	 * @param sender The sender of the command.
	 * @param args   The arguments passed to the command.
	 *
	 * @throws me.azenet.UHPlugin.commands.core.exceptions.CannotExecuteCommandException If the command cannot be executed.
	 */
	@Override
	public void run(CommandSender sender, String[] args) throws CannotExecuteCommandException {
		if(args.length == 1) { // /uh team add <color>

			TeamColor color = TeamColor.fromString(args[0]);
			UHTeam team;

			if(color == null) {
				sender.sendMessage(i.t("team.add.errorColor"));
			}
			else {
				try {
					team = p.getTeamManager().addTeam(color);
				}
				catch(IllegalArgumentException e) {
					sender.sendMessage(i.t("team.add.errorExists"));
					return;
				}

				sender.sendMessage(i.t("team.add.added", team.getDisplayName()));
			}

		}
		else if(args.length >= 2) { // /uh team add <color> <name ...>

			TeamColor color = TeamColor.fromString(args[0]);
			UHTeam team;

			if(color == null) {
				sender.sendMessage(i.t("team.add.errorColor"));
			}
			else {
				String name = UHUtils.getStringFromCommandArguments(args, 1);

				try {
					team = p.getTeamManager().addTeam(color, name);
				}
				catch(IllegalArgumentException e) {
					e.printStackTrace();
					sender.sendMessage(i.t("team.add.errorExists"));
					return;
				}

				sender.sendMessage(i.t("team.add.added", team.getDisplayName()));
			}

		}
		else {
			throw new CannotExecuteCommandException(CannotExecuteCommandException.Reason.BAD_USE, this);
		}
	}

	/**
	 * Tab-completes this command.
	 *
	 * @param sender The sender.
	 * @param args   The arguments passed to the command.
	 *
	 * @return A list of suggestions.
	 */
	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		if(args.length == 1) {
			List<String> colors = Arrays.asList("aqua", "black", "blue", "darkaqua",
					"darkblue", "darkgray", "darkgreen", "darkpurple", "darkred",
					"gold", "gray", "green", "lightpurple", "red", "white", "yellow", "?");

			return CommandUtils.getAutocompleteSuggestions(args[0], colors);
		}

		return null;
	}

	@Override
	public List<String> help(CommandSender sender) {
		return Arrays.asList(i.t("cmd.teamHelpAdd"));
	}
}