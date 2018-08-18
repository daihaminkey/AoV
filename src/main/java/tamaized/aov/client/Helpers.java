package tamaized.aov.client;

import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import tamaized.tammodized.common.helper.RayTraceHelper;

import java.util.HashSet;

public final class Helpers {

	private static final HashSet<Entity> BLANK_SET = Sets.newHashSet();

	private Helpers() {

	}


	public static Entity getTargetOverMouse(Minecraft mc, int range) {
		BLANK_SET.clear();
		BLANK_SET.add(mc.player);
		RayTraceResult ray = RayTraceHelper.tracePath(mc.world, mc.player, range, 1, BLANK_SET);
		BLANK_SET.clear();
		return ray == null ? null : ray.entityHit;
	}

}