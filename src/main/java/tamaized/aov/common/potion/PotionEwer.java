package tamaized.aov.common.potion;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.aov.AoV;
import tamaized.aov.common.capabilities.CapabilityList;
import tamaized.aov.common.capabilities.aov.IAoVCapability;

import javax.annotation.Nonnull;

public class PotionEwer extends Potion {

	private final ResourceLocation iconTexture;

	public PotionEwer(String name) {
		super(false, 0x00FFFF);
		iconTexture = new ResourceLocation(AoV.MODID, "textures/potions/" + name + ".png");
		setRegistryName(AoV.MODID, name);
		setPotionName("effect." + AoV.MODID + "." + name);
		setBeneficial();
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return duration % 5 == 0;
	}

	@Override
	public void performEffect(@Nonnull EntityLivingBase entityLivingBaseIn, int p_76394_2_) {
		if (entityLivingBaseIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
			if (player.hasCapability(CapabilityList.AOV, null)) {
				IAoVCapability cap = player.getCapability(CapabilityList.AOV, null);
				if (cap != null)
					cap.restoreCharges(entityLivingBaseIn, 1);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) {
		mc.getTextureManager().bindTexture(iconTexture);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.enableBlend();
		net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x + 7, y + 8, 0, 0, 16, 16, 16, 16);
		GlStateManager.disableBlend();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha) {
		mc.getTextureManager().bindTexture(iconTexture);
		GlStateManager.enableBlend();
		net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture(x + 4, y + 4, 0, 0, 16, 16, 16, 16);
		GlStateManager.disableBlend();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}

}
