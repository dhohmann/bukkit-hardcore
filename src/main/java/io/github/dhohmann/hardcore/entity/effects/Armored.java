package io.github.dhohmann.hardcore.entity.effects;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import io.github.dhohmann.hardcore.Utils;
import io.github.dhohmann.hardcore.effects.entity.SpawnEffect;

/**
 * Equips the entity with diamond gear. Only use on entities with equipment to
 * prevent side effects.
 */
public class Armored implements SpawnEffect {

	private static final String MODIFIER = "armored";

	@Override
	public String getModifier() {
		return Utils.getTranslation(MODIFIER);
	}

	protected void applyOn(LivingEntity entity) {
		EntityEquipment equipment = entity.getEquipment();
		if (equipment == null) {
			return;
		}
		equipment.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1), true);
		equipment.setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1), true);
		equipment.setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1), true);
		equipment.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1), true);
	}

	@Override
	public void onSpawn(CreatureSpawnEvent event) {
		applyOn(event.getEntity());
	}

}
