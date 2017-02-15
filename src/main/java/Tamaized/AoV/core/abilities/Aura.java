package Tamaized.AoV.core.abilities;

import Tamaized.AoV.capabilities.CapabilityList;
import net.minecraft.entity.player.EntityPlayer;

public final class Aura {

	private final Ability spell;
	private int life;

	public Aura(Ability ability, int duration) {
		spell = ability;
		life = duration*20;
	}

	/**
	 * If the caster dies or is null or does not have the AOV capability, the aura dies.
	 */
	public final void update(EntityPlayer caster) {
		if (caster == null || caster.isDead || !caster.hasCapability(CapabilityList.AOV, null)) life = 0;
		if (life > 0) {
			spell.castAsAura(caster, caster.getCapability(CapabilityList.AOV, null), life);
		}
		life--;
	}

	public final boolean isDead() {
		return life <= 0;
	}

}
