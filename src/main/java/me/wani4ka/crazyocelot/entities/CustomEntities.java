package me.wani4ka.crazyocelot.entities;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import lombok.val;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.entity.Entity;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CustomEntities {

	private static final Map<String, EntityTypes<? extends net.minecraft.server.v1_13_R2.Entity>> map = new HashMap<>();

	private final Map<String, Type<?>> dataTypes = (Map<String, Type<?>>) DataConverterRegistry.a().getSchema(DataFixUtils.makeKey(1631)).findChoiceType(DataConverterTypes.n).types();

	public <T extends net.minecraft.server.v1_13_R2.Entity> void register(Class<T> clazz, Function<? super World, T> spawnFunc) {
		CustomEntity annotation = clazz.getAnnotation(CustomEntity.class);
		if (annotation == null)
			throw new IllegalArgumentException("Entity class without @CustomEntity annotation: " + clazz.getSimpleName());
		String name = annotation.name();
		if (dataTypes.containsKey("minecraft:" + name))
			throw new IllegalArgumentException("Entity with such a name is already registered: " + name);
		dataTypes.put("minecraft:" + name, dataTypes.get("minecraft:" + annotation.parent()));
		map.put(name, EntityTypes.a(name, EntityTypes.a.a(clazz, spawnFunc)));
	}

	@Nullable
	public Entity spawn(String name, Location loc) {
		if (!map.containsKey(name))
			throw new IllegalArgumentException("Tried to spawn entity I'm not responsible for: " + name);
		val nmsEntity = map.get(name).a(
				((CraftWorld) loc.getWorld()).getHandle(),
				null,
				null,
				null,
				new BlockPosition(loc.getX(), loc.getY(), loc.getZ()),
				true,
				false
		);
		return nmsEntity == null ? null : nmsEntity.getBukkitEntity();
	}
}
