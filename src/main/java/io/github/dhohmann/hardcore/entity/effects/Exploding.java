package io.github.dhohmann.hardcore.entity.effects;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dhohmann.hardcore.HardcorePlugin;
import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.entity.DeathEffect;

/**
 * On death the entity creates an explosion that explodes after {@value #DELAY}
 * seconds.
 */
public class Exploding implements DeathEffect {

	public static final String MODIFIER = "exploding";
	public static final int DELAY = 3;

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	@Override
	public void onDeath(EntityDeathEvent event) {
		final LivingEntity dying = event.getEntity();
		dying.getWorld().playSound(dying.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1, 1);
		Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(HardcorePlugin.class), () -> {
			dying.getWorld().createExplosion(dying.getLocation(), 3, true);
		}, DELAY * TICKS_PER_SECOND);
	}

}
