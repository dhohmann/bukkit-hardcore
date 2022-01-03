package io.github.dhohmann.hardcore.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.dhohmann.hardcore.enhancement.DeathEffect;
import io.github.dhohmann.hardcore.enhancement.Effect;
import io.github.dhohmann.hardcore.enhancement.OnHitEffect;
import io.github.dhohmann.hardcore.enhancement.SpawnEffect;

public class EnhancedEffects {
	private Collection<Effect> effects;

	public EnhancedEffects() {
		effects = new ArrayList<>();
	}

	public void addEffect(Effect effect) {
		effects.add(effect);
	}

	public <T extends Effect> Collection<T> getEffects(Class<T> type) {
		List<T> result = new ArrayList<>();
		effects.stream().forEach(e -> {
			if (type.isAssignableFrom(e.getClass())) {
				result.add(type.cast(e));
			}
		});
		return result;
	}
	
	public Collection<Effect> getEffects(){
		return effects;
	}

	public Collection<SpawnEffect> getSpawnEffects() {
		return getEffects(SpawnEffect.class);
	}

	public Collection<OnHitEffect> getOnHitEffects() {
		return getEffects(OnHitEffect.class);
	}

	public Collection<DeathEffect> getDeathEffects() {
		return getEffects(DeathEffect.class);
	}

}
