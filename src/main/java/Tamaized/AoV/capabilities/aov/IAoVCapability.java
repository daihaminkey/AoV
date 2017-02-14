package Tamaized.AoV.capabilities.aov;

import java.io.IOException;
import java.util.List;

import Tamaized.AoV.AoV;
import Tamaized.AoV.core.abilities.Ability;
import Tamaized.AoV.core.abilities.AbilityBase;
import Tamaized.AoV.core.abilities.Aura;
import Tamaized.AoV.core.skills.AoVSkill;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public interface IAoVCapability {
	
	public static final ResourceLocation ID = new ResourceLocation(AoV.modid, "AoVCapabilityHandler");

	void reset(boolean b);

	void update(EntityPlayer player);

	void resetCharges();

	List<Ability> getAbilities();

	boolean canUseAbility(Ability ability);

	void addAbility(Ability ability);

	void removeAbility(Ability ability);

	void castAbility(Ability ability, EntityPlayer caster, EntityLivingBase target);

	void addAura(Aura aura);

	void addExp(int amount, AbilityBase spell);

	void addObtainedSkill(AoVSkill skill);

	boolean hasSkill(AoVSkill skill);

	boolean hasCoreSkill();

	AoVSkill getCoreSkill();

	List<AoVSkill> getObtainedSkills();

	void removeSkill(AoVSkill skill);

	int getLevel();

	int getMaxLevel();
	
	void setMaxLevel(int amount);

	int getExp();
	
	void setExp(int amount);

	int getExpNeededToLevel();

	int getSkillPoints();
	
	void setSkillPoints(int amount);

	int getSpentSkillPoints();

	float getSpellPower();

	int getExtraCharges();

	boolean hasSelectiveFocus();

	void toggleInvokeMass(boolean b);

	void toggleInvokeMass();

	boolean getInvokeMass();

	boolean hasInvokeMass();

	void setSlot(Ability ability, int slot);

	Ability getSlot(int slot);
	
	/**
	 * Returns -1 if slots dont contain ability
	 */
	int getSlotFromAbility(Ability ability);
	
	default Ability getAbilityFromSlots(Ability ability){
		return getSlot(getSlotFromAbility(ability));
	}

	int getCurrentSlot();
	
	void setCurrentSlot(int index);

	boolean slotsContain(Ability ability);

	void addToNearestSlot(Ability ability);

	void removeSlot(int slot);

	void clearAllSlots();

	void copyFrom(IAoVCapability cap);

	void decodePacket(ByteBufInputStream stream) throws IOException;

}
