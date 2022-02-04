package io.github.dhohmann.hardcore.entity.enhancement;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.dhohmann.hardcore.VersionInfo;
import io.github.dhohmann.hardcore.enhancement.SpawnEffect;
import io.github.dhohmann.hardcore.enhancement.Utils;

/**
 * Requires version 1.9
 */
public class Glowing implements SpawnEffect {

	public static final String MODIFIER = "glowing";

	@Override
	public void applyOn(LivingEntity entity) {
		VersionInfo info = new VersionInfo(Bukkit.getBukkitVersion());
		if (!info.isEqualOrHigherThan("1.9")) {
			return;
		}
		entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, Integer.MAX_VALUE));
	}

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

}
