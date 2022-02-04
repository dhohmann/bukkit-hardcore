package io.github.dhohmann.hardcore.entity.enhancement;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.dhohmann.hardcore.HardcorePlugin;
import io.github.dhohmann.hardcore.enhancement.DeathEffect;
import io.github.dhohmann.hardcore.enhancement.Utils;

public class Exploding implements DeathEffect {

	public static final String MODIFIER = "exploding";
	public static final int DELAY = 3;

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	@Override
	public void execute(LivingEntity dying, LivingEntity killer) {
		Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(HardcorePlugin.class), () -> {
			dying.getWorld().createExplosion(dying.getLocation(), 3, true);
		}, 3 * 20);

	}

}
