package io.github.dhohmann.hardcore.command;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.dhohmann.hardcore.player.PlayerManager;

public class ForceGamemode implements CommandExecutor {

	private PlayerManager playerManager;

	public ForceGamemode(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ("forcegamemode".equals(label)) {
			if (args.length != 2) {
				sender.sendMessage("You are missing some arguments.");
				return false;
			}
			try {
				GameMode gm = GameMode.valueOf(args[0].toUpperCase());
				Player p = Bukkit.getPlayer(args[1]);
				if(p == null) {
					sender.sendMessage("Player "+args[1] + " not found.");
					return true;
				}
				playerManager.authorizeGMChange(p);
				p.setGameMode(gm);
				return true;
			} catch (IllegalArgumentException e) {
				sender.sendMessage("Game " + args[0] + " mode not found");
			}
		}
		return false;
	}


}
