package io.github.dhohmann.hardcore.entity.enhancement;

import org.bukkit.entity.LivingEntity;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.enhancement.OnHitEffect;

public class Berserker implements OnHitEffect {

	public static final String MODIFIER = "berserker";
	public static final double DAMAGE_MOFIDIER = 1.25;

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	@Override
	public double applyHitEffect(LivingEntity damager, double damage) {
		return damage * DAMAGE_MOFIDIER;
	}

}
