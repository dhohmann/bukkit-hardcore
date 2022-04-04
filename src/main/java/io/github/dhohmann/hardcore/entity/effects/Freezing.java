package io.github.dhohmann.hardcore.entity.effects;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dhohmann.hardcore.HardcorePlugin;
import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.entity.DeathEffect;
import io.github.dhohmann.hardcore.effects.entity.OnHitEffect;
import io.github.dhohmann.hardcore.effects.entity.SpawnEffect;

/**
 * Applies a freezing effect for {@value #FREEZING_DURATION} seconds to all
 * players in close range when it gets hit.
 */
public class Freezing implements SpawnEffect, DeathEffect, OnHitEffect {

	private static final HardcorePlugin PLUGIN = JavaPlugin.getPlugin(HardcorePlugin.class);
	private static final int FREEZING_DURATION = 3;
	private static final double CLOSE_RANGE_DISTANCE = 3;
	private int task = -1;
	private LivingEntity entity;

	@Override
	public String getModifier() {
		return Utils.getTranslation("freezing");
	}

	/**
	 * Provides a sound effect when the entity spawns until the death of the entity.
	 */
	@Override
	public void onSpawn(CreatureSpawnEvent event) {
		this.entity = event.getEntity();
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(PLUGIN, () -> {
			this.entity.getWorld().playEffect(this.entity.getLocation(), Effect.SMOKE, 1, 2);
		}, 0, 3 * TICKS_PER_SECOND);

	}

	@Override
	public void onHit(EntityDamageByEntityEvent event) {
		if (entity == null) {
			return;
		}

		entity.getNearbyEntities(CLOSE_RANGE_DISTANCE, CLOSE_RANGE_DISTANCE, CLOSE_RANGE_DISTANCE).stream()
				.filter((e) -> e instanceof LivingEntity).forEach(e -> {
					e.setFreezeTicks(FREEZING_DURATION * TICKS_PER_SECOND);
				});
	}

	@Override
	public void onDeath(EntityDeathEvent event) {
		if (task != -1) {
			Bukkit.getScheduler().cancelTask(task);
		}
	}

}
