package tamaized.aov.common.core.skills.caster.tier3;

import tamaized.aov.AoV;
import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.common.core.skills.AoVSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class CasterSkillT3S4 extends AoVSkill {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid + ":textures/skills/CasterT3S4.png");

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {

	}

	public CasterSkillT3S4() {
		super(spells,

				TextFormatting.AQUA + "null",

				TextFormatting.RED + "Requires: 8 Points Spent in Tree"

		);
	}

	@Override
	public String getName() {
		return "CasterSkillT3S4";
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(0, 0, 0, 0, false);
	}

	@Override
	public ResourceLocation getIcon() {
		return icon;
	}

	@Override
	public boolean isClassCore() {
		return false;
	}

	@Override
	public AoVSkill getParent() {
		return AoVSkill.caster_core_1;
	}

	@Override
	public int getCost() {
		return 1;
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public int getSpentPoints() {
		return 8;
	}

}