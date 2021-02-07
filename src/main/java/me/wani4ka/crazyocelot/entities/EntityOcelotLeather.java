package me.wani4ka.crazyocelot.entities;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import lombok.val;
import me.wani4ka.crazyocelot.util.CustomEntities;
import me.wani4ka.crazyocelot.util.Utils;
import net.minecraft.server.v1_13_R2.*;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.UUID;

public class EntityOcelotLeather extends EntityItem {

	public EntityOcelotLeather(World world) {
		super(world);
		initialize();
	}

	public EntityOcelotLeather(World world, double d0, double d1, double d2) {
		super(world, d0, d1, d2);
		initialize();
	}

	@SuppressWarnings("unused")
	public EntityOcelotLeather(World world, double d0, double d1, double d2, ItemStack itemstack) {
		this(world, d0, d1, d2);
	}

	public EntityOcelotLeather(org.bukkit.World world, double d0, double d1, double d2) {
		this(((CraftWorld) world).getHandle(), d0, d1, d2);
	}

	private void initialize() {
		setItemStack(new ItemStack(Items.LEATHER));
		val tag = new NBTTagCompound();
		tag.a("Random", UUID.randomUUID()); // nie można łączyć z innymi itemstackami
		getItemStack().setTag(tag);
		this.pickupDelay = 10;
		setCustomName(new ChatComponentText(Utils.randomString(5)));
		setCustomNameVisible(true);
	}

	public static class OcelotLeatherPacketAdapter extends PacketAdapter {

		private final WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.getChatComponentSerializer(true);

		public OcelotLeatherPacketAdapter(Plugin plugin) {
			super(plugin, PacketType.Play.Server.ENTITY_METADATA);
		}

		@Override
		public void onPacketSending(PacketEvent event) {
			val packet = event.getPacket();
			val entity = packet.getEntityModifier(event).read(0);
			if (entity == null) return;
			if (!CustomEntities.isInstanceOf(entity, EntityOcelotLeather.class)) return;
			val dataWatcher = WrappedDataWatcher.getEntityWatcher(entity);
			dataWatcher.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(2, serializer),
					Optional.of(WrappedChatComponent.fromText(event.getPlayer().getName()).getHandle()));
			packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
			event.setPacket(packet);
		}
		@Override
		public void onPacketReceiving(PacketEvent event) {
			// po prostu aby zapobieć wyjątkom
		}
	}
}
