package Tamaized.AoV.gui.client;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.AoV.AoV;
import Tamaized.AoV.common.handlers.ServerPacketHandler;
import Tamaized.AoV.core.AoVData;
import Tamaized.AoV.gui.GuiHandler;
import Tamaized.AoV.gui.buttons.SkillButton;

public class AoVSkillsGUI extends GuiScreen {
	
	public static AoVSkillsGUI instance;
	
	private static final int BUTTON_CLOSE = 0;
	private static final int BUTTON_SKILL_CHECK = 1;
	private static final int BUTTON_SPELLBOOK = 2;
	private static final int BUTTON_RESET = 3;
	private static final int BUTTON_CHECKSTATS = 4;
	
	public static boolean doRefresh = false;
	
	public static ArrayList<SkillButton> skillButtonList = new ArrayList<SkillButton>();

	public AoVSkillsGUI() {
		super();
	}
	
	@Override
	public void initGui(){
		int margin = 20;
		int padding = 100;
		float workW = width-padding;
		int loc1 = (int) (workW*.0) + margin*1;
		int loc2 = (int) (workW*.25) + margin*2;
		int loc3 = (int) (workW*.50) + margin*3;
		int loc4 = (int) (workW*.75) + margin*4;
		buttonList.add(new GuiButton(BUTTON_CLOSE, loc1, height-25, 80, 20, "Close"));
		buttonList.add(new GuiButton(BUTTON_SPELLBOOK, loc2, height-25, 80, 20, "Spell Book"));
		buttonList.add(new GuiButton(BUTTON_CHECKSTATS, loc3, height-25, 80, 20, "Check Stats"));
		buttonList.add(new GuiButton(BUTTON_RESET, loc4, height-25, 80, 20, "Reset Skills"));
		if(AoV.clientAoVCore != null){
			skillButtonList.clear();
			
			SkillButton sbI = new SkillButton(BUTTON_SKILL_CHECK, 148, height-48, "HealerSkillCore");
			buttonList.add(sbI);
			skillButtonList.add(sbI);
			
			sbI = new SkillButton(BUTTON_SKILL_CHECK, 13, height-48, "CasterSkillCore");
			buttonList.add(sbI);
			skillButtonList.add(sbI);
		}else{
			mc.displayGuiScreen((GuiScreen)null);
		}
		instance = this;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException{
		if (button.enabled){
			switch(button.id){
				case BUTTON_CLOSE:
					mc.displayGuiScreen((GuiScreen)null);
					break;
				case BUTTON_SKILL_CHECK:
					if(!(button instanceof SkillButton)) break;
					SkillButton skillButton = (SkillButton) button;
					skillButton.enabled = false;
					boolean flag = beginChecks(skillButton);
					if(flag){
						skillButton.isObtained = true;
					}else{
						skillButton.enabled = true;
					}
					break;
				case BUTTON_SPELLBOOK:
					GuiHandler.openGUI(GuiHandler.GUI_SPELLBOOK);
					break;
				case BUTTON_RESET:
					GuiHandler.openGUI(GuiHandler.GUI_RESET);
					break;
				case BUTTON_CHECKSTATS:
					break;
				default:
					break;
			}
		}
	}
	
	private boolean beginChecks(SkillButton b) throws IOException{
		boolean flag = false;
		AoVData data = AoV.clientAoVCore.getPlayer(null);
		if(b.skill.parent == null) if(data.getCurrentSkillPoints() >= b.skill.pointCost) flag = true;
		else if(data.hasSkill(b.skill.parent) && data.getCurrentSkillPoints() >= b.skill.pointCost) flag = true;
		if(flag){
			int pktType = ServerPacketHandler.TYPE_SKILLEDIT_CHECK_CANOBTAIN;
			ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
			DataOutputStream outputStream = new DataOutputStream(bos);
			outputStream.writeInt(pktType);
			outputStream.writeUTF(b.skill.skillName);
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), AoV.networkChannelName);
			AoV.channel.sendToServer(packet);
			System.out.println("Sent Server Packet");
			outputStream.close();
			bos.close();
		}
		return true;
	}
	
	@Override
	public boolean doesGuiPauseGame(){
        return false;
    }

	@Override
	public void onGuiClosed(){
		instance = null;
    }
	
	@Override
	public void updateScreen(){
		if(doRefresh){
			for(SkillButton button : skillButtonList){
				button.updateVar();
			}
			doRefresh = false;
		}
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		AoVData data = AoV.clientAoVCore.getPlayer(null);
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "Angel of Vengeance: Skills", this.width / 2, 15, 16777215);
		this.drawRect(10+(135*0), 25, 135*1, height-27, 0x88000000);
		this.drawRect(10+(135*1), 25, 135*2, height-27, 0x88000000);
		this.drawRect(10+(135*2), 25, 135*3, height-27, 0x88000000);
		super.drawScreen(mouseX, mouseY, partialTicks);
		for(SkillButton b : skillButtonList){
			if(!b.isMouseOver()) continue;
			if(b.skill != null && b.skill.description != null) this.drawHoveringText(b.skill.description, mouseX, mouseY);	
		}
		this.drawString(fontRendererObj, "Skill Points: "+data.getCurrentSkillPoints(), 5, 5, 0xFFFFFF00);
		this.drawString(fontRendererObj, "Spent: "+(data.getMaxSkillPoints()-data.getCurrentSkillPoints())+" out of "+data.getMaxSkillPoints(), 5, 15, 0xFFFFFF00);
	}
}
