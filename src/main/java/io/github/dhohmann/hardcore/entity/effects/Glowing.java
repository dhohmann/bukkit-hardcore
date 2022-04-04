package io.github.dhohmann.hardcore.entity.effects;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.VersionInfo;
import io.github.dhohmann.hardcore.effects.entity.SpawnEffect;

/**
 * Requires version 1.9
 */
public class Glowing implements SpawnEffect {

	public static final String MODIFIER = "glowing";

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	@Override
	public void onSpawn(CreatureSpawnEvent event) {
		VersionInfo info = new VersionInfo(Bukkit.getBukkitVersion());
		if (!info.isEqualOrHigherThan("1.9")) {
			return;
		}
		PotionEffect effect = new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, Integer.MAX_VALUE);
		event.getEntity().addPotionEffect(effect);

	}

}
