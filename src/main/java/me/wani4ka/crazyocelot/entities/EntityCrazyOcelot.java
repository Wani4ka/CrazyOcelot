package me.wani4ka.crazyocelot.entities;

import lombok.val;
import me.wani4ka.crazyocelot.util.Utils;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;

import javax.annotation.Nullable;

public class EntityCrazyOcelot extends EntityOcelot {
	public EntityCrazyOcelot(World world) {
		super(world);
		setCustomName(new ChatComponentText(Utils.randomString(5)));
	}
	public EntityCrazyOcelot(org.bukkit.World world) {
		this(((CraftWorld) world).getHandle());
	}

	@Override
	protected void n() {
		this.goalSit = new PathfinderGoalSit(this);
		this.goalSelector.a(1, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, this.goalSit);
		this.goalSelector.a(6, new PathfinderGoalJumpOnBlock(this, 0.8));
		this.goalSelector.a(7, new PathfinderGoalLeapAtTarget(this, 0.3f));
		this.goalSelector.a(8, new PathfinderGoalCrazyOcelotAttack(this));
		this.goalSelector.a(10, new PathfinderGoalRandomStrollLand(this, 0.8, 1.0000001E-5f));
		this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0f));
		this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
	}

	@Override
	public boolean a(EntityHuman entityhuman, EnumHand enumhand) {
		return false; // zapobiegaj oswajaniu oraz karmieniu
	}

	@Override
	protected void dz() { } // zapobiegaj ucieczce od gracza

	@Nullable
	@Override
	public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
		return groupdataentity; // zapobiegaj pojawianiu się kilku ocelotów
	}

	public static class PathfinderGoalCrazyOcelotAttack extends PathfinderGoalOcelotAttack {

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
			if (var2 > var0 || d > 0) return;
			Utils.setField(PathfinderGoalOcelotAttack.class, this, "d", 20);
			b.B(c);
		}
	}
}
