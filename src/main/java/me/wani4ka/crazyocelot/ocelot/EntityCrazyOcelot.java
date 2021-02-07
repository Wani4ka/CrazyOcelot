package me.wani4ka.crazyocelot.ocelot;

import me.wani4ka.crazyocelot.Utils;
import net.minecraft.server.v1_13_R2.*;

import java.util.Set;

public class EntityCrazyOcelot extends EntityOcelot {
	public EntityCrazyOcelot(World world) {
		super(world);
		setCustomName(new ChatComponentText(Utils.randomString(5)));
		((Set) Utils.getField(PathfinderGoalSelector.class, this.goalSelector, "b")).clear();
		((Set) Utils.getField(PathfinderGoalSelector.class, this.goalSelector, "c")).clear();
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.goalSelector.a(6, new PathfinderGoalJumpOnBlock(this, 0.8));
		this.goalSelector.a(7, new PathfinderGoalLeapAtTarget(this, 0.3f));
		this.goalSelector.a(8, new PathfinderGoalCrazyOcelotAttack(this));
		this.goalSelector.a(10, new PathfinderGoalRandomStrollLand(this, 0.8, 1.0000001E-5f));
		this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0f));
		this.targetSelector.a(1, new PathfinderGoalRandomTargetNonTamed<EntityHuman>(this, EntityHuman.class, false, null));
	}

}
