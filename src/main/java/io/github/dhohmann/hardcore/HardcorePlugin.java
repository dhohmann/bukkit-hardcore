package io.github.dhohmann.hardcore;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dhohmann.hardcore.command.ForceGamemode;
import io.github.dhohmann.hardcore.effects.Enhancer;
import io.github.dhohmann.hardcore.player.PlayerManager;

public class HardcorePlugin extends JavaPlugin implements Listener {

	private Map<Command, CommandExecutor> commands;
	private PlayerManager playerManager;
	private Enhancer enhancer;

	@Override
	public void onEnable() {
		playerManager = new PlayerManager();
		enhancer = new Enhancer();
		saveDefaultConfig();

		Bukkit.getWorlds().forEach((world) -> {
			Utils.getWorldPreparator().setupWorld(world);
		});

		playerManager.load();

		Bukkit.getPluginManager().registerEvents(playerManager, this);
		Bukkit.getPluginManager().registerEvents(enhancer, this);

		commands.put(getCommand("forcegamemode"), new ForceGamemode(playerManager));
	}

	@Override
	public void onDisable() {
		playerManager.save();
		playerManager.shutdown();

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CommandExecutor executor = commands.get(command);
		if (executor != null) {
			executor.onCommand(sender, command, label, args);
		}
		return false;
	}

}
