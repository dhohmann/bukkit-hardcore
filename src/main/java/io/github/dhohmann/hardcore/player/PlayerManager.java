package io.github.dhohmann.hardcore.player;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dhohmann.hardcore.HardcorePlugin;

public class PlayerManager implements Listener {

	private HardcorePlugin plugin = JavaPlugin.getPlugin(HardcorePlugin.class);
	private Map<Object, Date> reviveDates = new HashMap<>();
	private Set<Object> removeDates = new HashSet<>();
	private Set<Object> automatedGMChanges = new HashSet<>();
	private int updateTask;

	public PlayerManager() {
		updateTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, revive, 0, 1200);
	}
	
	/**
	 * Event handler for a dying player. Adds a player to the handler logic after
	 * the death.
	 * 
	 * @param e Event
	 */
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		addPlayer(e.getEntity());
	}

	/**
	 * Event handler for game mode changes. It cancels game mode changes if the
	 * change is not authorized.
	 * 
	 * @param e Event
	 */
	@EventHandler
	public void onPlayerGMChange(PlayerGameModeChangeEvent e) {
		Player p = e.getPlayer();
		Object key = Bukkit.getOnlineMode() ? p.getUniqueId() : p.getName();
		if (!automatedGMChanges.contains(key)) {
			plugin.getLogger().info("Gamemode change for " + p.getName() + " canceled");
			e.setCancelled(true);
		} else {
			automatedGMChanges.remove(key);
		}
	}

	/**
	 * Sends a message to the player when he re-spawns.
	 * 
	 * @param e Event
	 */
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		Object key = Bukkit.getOnlineMode() ? p.getUniqueId() : p.getName();
		if (reviveDates.containsKey(key)) {
			String format = "You will be revived at %tF %tH:%tM";
			Date d = reviveDates.get(key);
			p.sendMessage(String.format(format, d, d, d));
		}
	}

	/**
	 * Adds a player to the restriction logic. If the world has no hard core
	 * enabled, the player gets set to a spectator.
	 * 
	 * @param p Player
	 */
	public void addPlayer(Player p) {
		if (p == null) {
			return;
		}
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.HOUR, plugin.getConfig().getInt("restricted_hours", 48));
		Object key = Bukkit.getOnlineMode() ? p.getUniqueId() : p.getName();
		reviveDates.put(key, cal.getTime());
		automatedGMChanges.add(key);
		if (!Bukkit.isHardcore()) {
			p.setGameMode(GameMode.SPECTATOR);
		}
	}

	/**
	 * Task that revives all entitled players. All players that will be revived in
	 * the next 5 minutes will receive a message.
	 */
	private Runnable revive = new Runnable() {

		@Override
		public void run() {
			Calendar cal = new GregorianCalendar();
			reviveDates.forEach((key, date) -> {
				Player p = Bukkit.getOnlineMode() ? Bukkit.getPlayer((UUID) key) : Bukkit.getPlayer((String) key);
				if (cal.getTime().before(date)) {
					return;
				}
				revivePlayer(p);
			});
			removeDates.forEach(key -> {
				reviveDates.remove(key);
			});
			removeDates.clear();

			Set<Object> notified = new HashSet<>();
			int minute = 5;
			while (minute >= 1) {
				Calendar future = (Calendar) cal.clone();
				future.add(Calendar.MINUTE, minute);
				String message = String.format("You will be revived in %d minutes.", minute);
				reviveDates.forEach((key, date) -> {
					Player p = Bukkit.getOnlineMode() ? Bukkit.getPlayer((UUID) key) : Bukkit.getPlayer((String) key);
					if (p == null) {
						return;
					}
					if (future.getTime().before(date)) {
						return;
					}
					if (notified.contains(key)) {
						return;
					}
					p.sendMessage(message);
					notified.add(key);
				});
				minute--;
			}

		}

	};

	/**
	 * Revives a player at their spawn location.
	 * 
	 * @param p Player to remove
	 */
	public void revivePlayer(Player p) {
		if (p == null) {
			return;
		}
		Object key = Bukkit.getOnlineMode() ? p.getUniqueId() : p.getName();
		removeDates.add(key);
		automatedGMChanges.add(key);
		Location location = p.getBedSpawnLocation();
		if (location == null) {
			location = p.getWorld().getSpawnLocation();
		}
		p.teleport(location, TeleportCause.PLUGIN);
		p.setGameMode(GameMode.SURVIVAL);
		p.sendMessage("You were revived.");
		plugin.getLogger().info("Player " + p.getName() + " revived");
	}

	public void save() {
		YamlConfiguration playerstore = new YamlConfiguration();
		for (Object key : reviveDates.keySet()) {
			long time = reviveDates.get(key).getTime();
			playerstore.set(key.toString(), time);
		}
		try {
			playerstore.save(new File(plugin.getDataFolder(), "players.yml"));
		} catch (IOException e) {
			plugin.getLogger().severe("Could not store player data");
		}
	}

	public void authorizeGMChange(Player p) {
		Object key = Bukkit.getOnlineMode() ? p.getUniqueId() : p.getName();
		automatedGMChanges.add(key);
	}

	public void load() {
		YamlConfiguration store = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "players.yml"));
		store.getKeys(false).forEach((ymlkey) -> {
			Object key = Bukkit.getOnlineMode() ? UUID.fromString(ymlkey) : ymlkey;
			reviveDates.put(key, new Date(store.getLong(ymlkey)));
		});

	}

	public void shutdown() {
		Bukkit.getScheduler().cancelTask(updateTask);		
	}

}
