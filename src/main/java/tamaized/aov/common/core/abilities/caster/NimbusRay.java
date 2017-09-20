package tamaized.aov.common.core.abilities.caster;

import tamaized.aov.AoV;
import tamaized.aov.common.capabilities.CapabilityList;
import tamaized.aov.common.capabilities.aov.IAoVCapability;
import tamaized.aov.common.core.abilities.Ability;
import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.common.entity.ProjectileNimbusRay;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class NimbusRay extends AbilityBase {

	private static final int damage = 4;
	private static final int charges = 6;
	private static final int distance = 20;

	public NimbusRay() {
		super(

				TextFormatting.YELLOW + getStaticName(),

				"",

				TextFormatting.AQUA + "Charges: " + charges,

				TextFormatting.AQUA + "Range: " + distance,

				TextFormatting.AQUA + "Base Damage: " + damage,

				"",

				TextFormatting.DARK_PURPLE + "Shoots a small ray of light",

				TextFormatting.DARK_PURPLE + "to deal damage.",

				TextFormatting.DARK_PURPLE + "This damage is doubled on",

				TextFormatting.DARK_PURPLE + "Undead targets."

		);
	}

	@Override
	public ResourceLocation getIcon() {
		return new ResourceLocation(AoV.modid, "textures/spells/nimbus.png");
	}

	@Override
	public String getName() {
		return getStaticName();
	}

	public static String getStaticName() {
		return "Nimbus Ray";
	}

	@Override
	public int getCoolDown() {
		return 2;
	}

	@Override
	public int getMaxCharges() {
		return charges;
	}

	@Override
	public int getChargeCost() {
		return 1;
	}

	@Override
	public double getMaxDistance() {
		return distance;
	}

	@Override
	public boolean usesInvoke() {
		return false;
	}

	@Override
	public void cast(Ability ability, EntityPlayer caster, EntityLivingBase target) {
		IAoVCapability cap = caster.getCapability(CapabilityList.AOV, null);
		if (cap == null)
			return;
		int a = (int) (damage * (1f + (cap.getSpellPower() / 100f)));
		ProjectileNimbusRay ray = new ProjectileNimbusRay(caster.world, caster, caster.posX, caster.posY, caster.posZ);
		ray.setSpell(this);
		ray.setColor(0xFFFF66FF);
		ray.setDamage(a);
		ray.setMaxRange(distance);
		caster.world.spawnEntity(ray);
	}

}