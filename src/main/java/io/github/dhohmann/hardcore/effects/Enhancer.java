package io.github.dhohmann.hardcore.effects;

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
import io.github.dhohmann.hardcore.Utils;
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
			e.onSpawn(event.getSpawnEvent());
		});

		plugin.getLogger().info("Enhanced entity " + entity.getName());

	}

	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent event) {
		if (Math.random() >= 0.2) {
			return;
		}

		EnhancedEffects effects = Utils.getEnhancements(event.getEntity());
		Bukkit.getPluginManager().callEvent(new EntityEnhanceEvent(event, effects));
	}

	/**
	 * Applies on hit and thorn effects when an entity is damaged.
	 * 
	 * If the damager is not enhanced, this method does not apply on hit effects.
	 * 
	 * If the entity is not enhanced, this method does not apply thorn effects.
	 * 
	 * @param event Damage event
	 */
	@EventHandler
	public void onEntityHitsPlayer(EntityDamageByEntityEvent event) {
		if (!entities.keySet().contains(event.getDamager())) {
			return;
		}
		if (!(event.getDamager() instanceof LivingEntity)) {
			return;
		}

		EnhancedEffects damagerEffects = entities.get(event.getDamager());
		damagerEffects.getOnHitEffects().forEach(e -> e.onHit(event));

		EnhancedEffects entityEffects = entities.get(event.getEntity());
		entityEffects.getThornEffects().forEach(e -> e.onReceiveHit(event));
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();
		if (!entities.keySet().contains(entity)) {
			return;
		}

		EnhancedEffects effects = entities.get(entity);
		effects.getDeathEffects().forEach(e -> e.onDeath(event));
		entities.remove(entity);
	}

}
