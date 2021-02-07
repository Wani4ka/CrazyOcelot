package me.wani4ka.crazyocelot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class OcelotKillLogger {
	private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `ocelot_kills` (" +
			"`player` VARCHAR(20), `ocelot` VARCHAR(5), `time` INT);";
	private static final String INSERT_KILL = "INSERT INTO `ocelot_kills` (`player`, `ocelot`, `time`) VALUES (?, ?, ?);";

	private final Plugin plugin;
	private Connection con;

	public OcelotKillLogger(Plugin plugin, String src) {
		this.plugin = plugin;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + src);
			try (Statement stmt = con.createStatement()) {
				stmt.execute(CREATE_TABLE);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void logKill(Player ply, String ocelotName) {
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_KILL)) {
				stmt.setString(1, ply.getName());
				stmt.setString(2, ocelotName);
				stmt.setInt(3, (int) (System.currentTimeMillis() / 1000));
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
