package tamaized.aov.common.core.skills.caster.tier2;

import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.common.core.skills.AoVSkill;
import tamaized.aov.common.core.skills.SkillIcons;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class CasterSkillT2S2 extends AoVSkill {

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {

	}

	public CasterSkillT2S2() {
		super(spells,

				TextFormatting.AQUA + "Charges I",

				TextFormatting.RED + "Requires: 4 Points Spent in Tree",

				"",

				TextFormatting.GREEN + "+1 Charge"

		);
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(1, 0, 0, 0, false);
	}

	@Override
	public ResourceLocation getIcon() {
		return SkillIcons.charges;
	}

	public String getName() {
		return "CasterSkillT2S2";
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
		return 4;
	}

}