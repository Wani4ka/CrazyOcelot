package me.wani4ka.crazyocelot;

import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;
import me.wani4ka.crazyocelot.entities.EntityOcelotLeather;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CrazyOcelotPlugin extends JavaPlugin implements Listener {

	@Getter
	private OcelotKillLogger killLogger;

	@Override
	public void onEnable() {
		if (!getDataFolder().exists())
			getDataFolder().mkdir();
		killLogger = new OcelotKillLogger(this, new File(getDataFolder(), "kills.db").getPath());
		getServer().getPluginManager().registerEvents(new DeathsWatcher(this), this);
		ProtocolLibrary.getProtocolManager().addPacketListener(new EntityOcelotLeather.OcelotLeatherPacketAdapter(this));
	}

	@Override
	public void onDisable() {
		killLogger.close();
	}

}
