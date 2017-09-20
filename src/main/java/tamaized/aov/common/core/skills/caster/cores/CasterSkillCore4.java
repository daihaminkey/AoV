package tamaized.aov.common.core.skills.caster.cores;

import tamaized.aov.common.core.abilities.AbilityBase;
import tamaized.aov.common.core.skills.AoVSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class CasterSkillCore4 extends AoVSkill {

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {
		spells.add(AbilityBase.bladeBarrier);
	}

	public CasterSkillCore4() {
		super(spells,

				TextFormatting.AQUA + "Caster Core 4",

				TextFormatting.RED + "Requires: Caster Core 3",

				TextFormatting.RED + "Requires: Level 12",

				"",

				TextFormatting.GREEN + "+15 Spell Power",

				TextFormatting.GREEN + "+1 Charge",

				"",

				TextFormatting.YELLOW + "Added Spell: Blade Barrier"

		);
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(1, 15, 0, 0, false);
	}

	@Override
	public ResourceLocation getIcon() {
		return AbilityBase.bladeBarrier.getIcon();
	}

	@Override
	public boolean isClassCore() {
		return false;
	}

	@Override
	public String getName() {
		return "CasterSkillCore4";
	}

	@Override
	public AoVSkill getParent() {
		return AoVSkill.caster_core_3;
	}

	@Override
	public int getCost() {
		return 1;
	}

	@Override
	public int getLevel() {
		return 12;
	}

	@Override
	public int getSpentPoints() {
		return 0;
	}

}