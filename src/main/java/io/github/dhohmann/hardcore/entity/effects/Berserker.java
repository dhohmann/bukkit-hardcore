package io.github.dhohmann.hardcore.entity.effects;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.entity.OnHitEffect;

public class Berserker implements OnHitEffect {

	public static final String MODIFIER = "berserker";
	public static final double DAMAGE_MOFIDIER = 1.25;

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	@Override
	public void onHit(EntityDamageByEntityEvent event) {
		event.setDamage(event.getDamage() * DAMAGE_MOFIDIER);
	}

}
