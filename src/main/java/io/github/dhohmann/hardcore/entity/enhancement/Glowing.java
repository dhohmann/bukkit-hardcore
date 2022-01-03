package io.github.dhohmann.hardcore.entity.enhancement;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.enhancement.SpawnEffect;

public class Glowing implements SpawnEffect {

	public static final String MODIFIER = "glowing";

	@Override
	public void applyOn(LivingEntity entity) {
		entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, Integer.MAX_VALUE));
	}

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

}
