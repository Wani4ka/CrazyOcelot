package me.wani4ka.crazyocelot;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import lombok.Getter;
import lombok.val;
import me.wani4ka.crazyocelot.ocelot.EntityCrazyOcelot;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;
import java.util.function.Function;

public final class CrazyOcelotPlugin extends JavaPlugin implements Listener {

	public static EntityTypes<EntityCrazyOcelot> CRAZY_OCELOT;

	@Getter
	private OcelotKillLogger killLogger;

	@Override
	public void onLoad() {
		CRAZY_OCELOT = injectNewEntity("crazy_ocelot", "ocelot", EntityCrazyOcelot.class, EntityCrazyOcelot::new);
	}

	@Override
	public void onEnable() {
		if (!getDataFolder().exists())
			getDataFolder().mkdir();
		killLogger = new OcelotKillLogger(this, new File(getDataFolder(), "kills.db").getPath());
		getServer().getPluginManager().registerEvents(new DeathsWatcher(this), this);
	}

	@Override
	public void onDisable() {
		killLogger.close();
	}

	public Entity spawnOcelot(Location loc) {
		val nmsEntity = CRAZY_OCELOT.a(
				((CraftWorld) loc.getWorld()).getHandle(),
				null,
				null,
				null,
				new BlockPosition(loc.getX(), loc.getY(), loc.getZ()),
				true,
				false);
		return nmsEntity == null ? null : nmsEntity.getBukkitEntity();
	}

	private <T extends net.minecraft.server.v1_13_R2.Entity> EntityTypes<T> injectNewEntity(String name, String parent, Class<T> clazz, Function<? super World, T> spawnFunc) {
		val dataTypes = (Map<String, Type<?>>) DataConverterRegistry.a().getSchema(DataFixUtils.makeKey(1631)).findChoiceType(DataConverterTypes.n).types();
		dataTypes.put("minecraft:" + name, dataTypes.get("minecraft:" + parent));
		return EntityTypes.a(name, EntityTypes.a.a(clazz, spawnFunc));
	}

}
