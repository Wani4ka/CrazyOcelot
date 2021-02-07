package me.wani4ka.crazyocelot.util;

import net.minecraft.server.v1_13_R2.Entity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;

// trzy funkcje zmniejszające ilość kodu
public class CustomEntities {

	// spawn z dodatkową instalacją lokalizacji (używana ocelotem)...
	public static org.bukkit.entity.Entity spawn(Entity ent, Location loc) {
		ent.setPosition(loc.getX(), loc.getY(), loc.getZ());
		return spawn(ent);
	}

	// ...i bez instalacji lokalizacji
	public static org.bukkit.entity.Entity spawn(Entity ent) {
		ent.world.addEntity(ent);
		return ent.getBukkitEntity();
	}

	// dzięki tej funkcji DeathsWatcher nie ma zależności od NMS
	public static <T extends Entity> boolean isInstanceOf(org.bukkit.entity.Entity ent, Class<T> clazz) {
		return clazz.isInstance(((CraftEntity) ent).getHandle());
	}
}
