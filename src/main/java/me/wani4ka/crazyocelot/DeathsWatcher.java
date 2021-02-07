package me.wani4ka.crazyocelot;

import lombok.RequiredArgsConstructor;
import lombok.val;
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
			val ocelot = plugin.spawnOcelot(e.getEntity().getLocation());
			if (((Zombie) e.getEntity()).isBaby() && ocelot != null)
				((Ocelot) ocelot).setBaby();
		}
	}

}
