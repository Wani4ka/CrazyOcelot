package me.wani4ka.crazyocelot.ocelot;

import lombok.val;
import me.wani4ka.crazyocelot.Utils;
import net.minecraft.server.v1_13_R2.EntityInsentient;
import net.minecraft.server.v1_13_R2.EntityLiving;
import net.minecraft.server.v1_13_R2.PathfinderGoalOcelotAttack;

public class PathfinderGoalCrazyOcelotAttack extends PathfinderGoalOcelotAttack {

	private final EntityInsentient b;

	public PathfinderGoalCrazyOcelotAttack(EntityInsentient var0) {
		super(var0);
		this.b = var0;
	}

	// prawie skopiowany kod z PathfinderGoalOcelotAttack#e
	@Override
	public void e() {
		val c = (EntityLiving) Utils.getField(PathfinderGoalOcelotAttack.class, this, "c");
		int d = (int) Utils.getField(PathfinderGoalOcelotAttack.class, this, "d");

		b.getControllerLook().a(c, 30.0f, 30.0f);
		double var0 = b.width * 2.0f * (b.width * 2.0f);
		double var2 = b.d(c.locX, c.getBoundingBox().minY, c.locZ);
		double var4 = 0.8;
		if (var2 > var0 && var2 < 255.0)
			var4 = 1.33;
		b.getNavigation().a(c, var4);
		d = Math.max(d - 1, 0);
		Utils.setField(PathfinderGoalOcelotAttack.class, this, "d", d);
		if (var2 > var0) return;
		if (d > 0) return;
		Utils.setField(PathfinderGoalOcelotAttack.class, this, "d", 20);
		b.B(c);
	}
}

