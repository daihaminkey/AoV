package Tamaized.AoV.core.skills.defender.tier3;

import java.util.ArrayList;
import java.util.List;

import Tamaized.AoV.AoV;
import Tamaized.AoV.core.abilities.AbilityBase;
import Tamaized.AoV.core.skills.AoVSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class DefenderSkillT3S4 extends AoVSkill {

	private static final ResourceLocation icon = new ResourceLocation(AoV.modid + ":textures/skills/DefenderT3S4.png");

	private static final List<AbilityBase> spells = new ArrayList<AbilityBase>();

	static {

	}

	public DefenderSkillT3S4() {
		super(spells,

				TextFormatting.AQUA + "DoubleStrike I",

				TextFormatting.RED + "Requires: 8 Points Spent in Tree",
				
				"",
				
				TextFormatting.GREEN + "+5% DoubleStrike"

		);
	}

	@Override
	public String getName() {
		return "DefenderSkillT3S4";
	}

	@Override
	protected Buffs setupBuffs() {
		return new Buffs(0, 0, 0, 5, false);
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
		return AoVSkill.defender_core_1;
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
