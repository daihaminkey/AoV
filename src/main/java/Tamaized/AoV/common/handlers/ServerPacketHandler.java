package Tamaized.AoV.common.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import Tamaized.AoV.AoV;
import Tamaized.AoV.core.AoVCore;
import Tamaized.AoV.core.AoVData;
import Tamaized.AoV.core.skills.AoVSkill;

public class ServerPacketHandler {
	
	public static final int TYPE_SKILLEDIT_CHECK_CANOBTAIN = 0;
	
	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {
		try{
			EntityPlayerMP player = ((NetHandlerPlayServer)event.handler).playerEntity;
			ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
			
			processPacketOnServer(event.packet.payload(), Side.SERVER, player);
			
			bbis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void processPacketOnServer(ByteBuf parBB, Side parSide, EntityPlayerMP player) throws IOException{
	  
		if(parSide == Side.SERVER){
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType = bbis.readInt();
			switch(pktType){
				
				case TYPE_SKILLEDIT_CHECK_CANOBTAIN:
					String theName = bbis.readUTF();
					AoVCore core = AoV.serverAoVCore;
					AoVData data = core.getPlayer(player);
					AoVSkill skillToCheck = AoVSkill.getSkillFromName(theName);
					boolean flag = false;
					if(skillToCheck == null){
						sendCanObtainSkillPacket(player, theName, false);
						break;
					}
					if(skillToCheck.parent == null) 
						if(data.getCurrentSkillPoints() >= skillToCheck.pointCost) flag = true;
						else if(data.hasSkill(skillToCheck.parent) && data.getCurrentSkillPoints() >= skillToCheck.pointCost) flag = true;
					if(flag){
						System.out.println("Server passed on canObtain, sending data to client");
						data.addObtainedSkill(skillToCheck);
						data.setCurrentSkillPoints(data.getCurrentSkillPoints()-skillToCheck.pointCost);
						data.updateVariables();
						sendAovDataPacket(player, data);
						sendCanObtainSkillPacket(player, theName, true);
					}
					break;
					
				default:
					break;
			}
			bbis.close();
		}
	}
	
	private static void sendCanObtainSkillPacket(EntityPlayerMP player, String theName, boolean b) throws IOException{
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		outputStream.writeInt(ClientPacketHandler.TYPE_SKILL_CHECK_CANOBTAIN);
		outputStream.writeBoolean(b);
		outputStream.writeUTF(theName);
		FMLProxyPacket pkt = new FMLProxyPacket(new PacketBuffer(bos.buffer()), AoV.networkChannelName);
		if(AoV.channel != null && pkt != null) AoV.channel.sendTo(pkt, player);
		bos.close();
	}
	
	public static void sendAovDataPacket(EntityPlayerMP player, AoVData packet){
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.TYPE_COREDATA);
			outputStream.writeUTF(packet.toPacket());
			FMLProxyPacket pkt = new FMLProxyPacket(new PacketBuffer(bos.buffer()), AoV.networkChannelName);
			if(AoV.channel != null && pkt != null) AoV.channel.sendTo(pkt, player);
			bos.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
}