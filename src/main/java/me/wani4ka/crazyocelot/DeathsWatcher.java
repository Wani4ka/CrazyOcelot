package me.wani4ka.crazyocelot;

import lombok.RequiredArgsConstructor;
import lombok.val;
import me.wani4ka.crazyocelot.entities.EntityCrazyOcelot;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
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
		if (killer != null) {
			val ocelot = plugin.getCustomEntities().spawn("crazy_ocelot", e.getEntity().getLocation());
			if (((Zombie) e.getEntity()).isBaby() && ocelot != null)
				((Ocelot) ocelot).setBaby();
		}
	}

	@EventHandler
	public void onOcelotDied(EntityDeathEvent e) {
		if (!(((CraftEntity) e.getEntity()).getHandle() instanceof EntityCrazyOcelot)) return;
		e.setDroppedExp(0);
		e.getDrops().clear();
		plugin.getCustomEntities().spawn("ocelot_leather", e.getEntity().getLocation());
		val killer = e.getEntity().getKiller();
		if (killer != null)
			plugin.getKillLogger().logKill(killer, e.getEntity().getCustomName());
	}

}
