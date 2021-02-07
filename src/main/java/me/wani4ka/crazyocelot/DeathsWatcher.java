package me.wani4ka.crazyocelot;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

@RequiredArgsConstructor
public class DeathsWatcher implements Listener {

	private final CrazyOcelotPlugin plugin;

	@EventHandler
	public void onZombieDied(EntityDeathEvent e) {
		if (e.getEntityType() != EntityType.ZOMBIE) return;
		val lastDmg = e.getEntity().getLastDamageCause();
		if (!(lastDmg instanceof EntityDamageByEntityEvent)) return;
		if (((EntityDamageByEntityEvent) lastDmg).getDamager().getType() == EntityType.PLAYER)
			plugin.spawnOcelot(e.getEntity().getLocation());
	}

}
