package me.wani4ka.crazyocelot;

import lombok.RequiredArgsConstructor;
import lombok.val;
import me.wani4ka.crazyocelot.entities.EntityCrazyOcelot;
import me.wani4ka.crazyocelot.entities.EntityOcelotLeather;
import me.wani4ka.crazyocelot.util.CustomEntities;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

@RequiredArgsConstructor
public class DeathsWatcher implements Listener {

	private final CrazyOcelotPlugin plugin;

	@EventHandler
	public void onZombieDied(EntityDeathEvent e) {
		if (e.getEntityType() != EntityType.ZOMBIE) return;
		val killer = e.getEntity().getKiller();
		if (killer == null) return;
		val loc = e.getEntity().getLocation();
		val ocelot = CustomEntities.spawn(new EntityCrazyOcelot(loc.getWorld()), loc);
		if (((Zombie) e.getEntity()).isBaby() && ocelot != null)
			((Ocelot) ocelot).setBaby();
	}

	@EventHandler
	public void onOcelotDied(EntityDeathEvent e) {
		if (!CustomEntities.isInstanceOf(e.getEntity(), EntityCrazyOcelot.class)) return;
		e.setDroppedExp(0);
		e.getDrops().clear();
		val loc = e.getEntity().getLocation();
		CustomEntities.spawn(new EntityOcelotLeather(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
		val killer = e.getEntity().getKiller();
		if (killer != null)
			plugin.getKillLogger().logKill(killer, e.getEntity().getCustomName());
	}

}
