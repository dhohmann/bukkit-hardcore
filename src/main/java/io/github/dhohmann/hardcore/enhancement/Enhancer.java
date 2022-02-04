package io.github.dhohmann.hardcore.enhancement;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dhohmann.hardcore.HardcorePlugin;
import io.github.dhohmann.hardcore.entity.event.EntityEnhanceEvent;

public class Enhancer implements Listener {

	private final Map<Entity, EnhancedEffects> entities = new ConcurrentHashMap<>();
	private final Plugin plugin = JavaPlugin.getPlugin(HardcorePlugin.class);

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityEnhance(EntityEnhanceEvent event) {
		if (event.isCancelled()) {
			return;
		}
		LivingEntity entity = event.getEntity();
		EnhancedEffects effects = event.getEffects();
		entities.put(entity, effects);

		StringBuilder customName = new StringBuilder();
		effects.getEffects().forEach((e) -> {
			String modifier = e.getModifier().toLowerCase();
			if (customName.length() == 0) {
				customName.append(modifier.substring(0, 1).toUpperCase());
				customName.append(modifier.substring(1));
			} else {
				customName.append(' ').append(modifier);
			}
		});
		customName.append(' ').append(Utils.getTranslation(entity.getType().toString()));
		entity.setCustomName(customName.toString());
		entity.setCustomNameVisible(true);

		effects.getSpawnEffects().forEach((e) -> {
			e.applyOn(entity);
		});

		plugin.getLogger().info("Enhanced entity " + entity.getName());

	}

	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent event) {
		if (Math.random() >= 0.2) {
			return;
		}

		EnhancedEffects effects = Utils.getEnhancements(event.getEntity());
		Bukkit.getPluginManager().callEvent(new EntityEnhanceEvent(event.getEntity(), effects));
	}

	/**
	 * Applies on hit effects when the player is damaged.
	 * 
	 * @param event
	 */
	@EventHandler
	public void onEntityHitsPlayer(EntityDamageByEntityEvent event) {
		if (!entities.keySet().contains(event.getDamager())) {
			return;
		}
		if (!(event.getDamager() instanceof LivingEntity)) {
			return;
		}
		EnhancedEffects effects = entities.get(event.getDamager());
		effects.getOnHitEffects().forEach(e -> {
			LivingEntity entity = (LivingEntity) event.getDamager();
			double damage = event.getDamage();
			damage = e.applyHitEffect(entity, damage);
			event.setDamage(damage);
		});

	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();
		if (!entities.keySet().contains(entity)) {
			return;
		}

		EnhancedEffects effects = entities.get(entity);
		effects.getDeathEffects().forEach(e -> {
			e.execute(entity, entity.getKiller());
		});
		entities.remove(entity);
	}

}
