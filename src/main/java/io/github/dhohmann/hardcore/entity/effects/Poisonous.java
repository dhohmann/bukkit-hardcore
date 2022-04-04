package io.github.dhohmann.hardcore.entity.effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.entity.OnHitEffect;

public class Poisonous implements OnHitEffect {

	private static final int DURATION = 3;

	@Override
	public String getModifier() {
		return Utils.getTranslation("poisonous");
	}

	@Override
	public void onHit(EntityDamageByEntityEvent event) {
		LivingEntity damager = (LivingEntity) event.getDamager();
		PotionEffect effect = new PotionEffect(PotionEffectType.POISON, DURATION * TICKS_PER_SECOND, 5);
		damager.addPotionEffect(effect);
		
	}

}
