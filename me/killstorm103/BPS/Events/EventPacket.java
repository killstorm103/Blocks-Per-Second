package me.killstorm103.BPS.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

import me.killstorm103.BPS.Main.BPS;
import me.killstorm103.BPS.Main.User;

public class EventPacket implements PacketListener
{
	@Override
	public void onPacketReceive(PacketReceiveEvent e)
	{
		if (e.getPacketType() == PacketType.Play.Client.PLAYER_POSITION || e.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION)
		{
			Player player = Bukkit.getPlayer(e.getUser().getUUID());
			if (player == null || !player.isOnline()) return;
			
			User user = BPS.getUser (player);
			if (user == null) return;
			
			user.lastTickPosX = user.getPlayer().getLocation().getX();
			user.lastTickPosY = user.getPlayer().getLocation().getY();
			user.lastTickPosZ = user.getPlayer().getLocation().getZ();
		}
	}
}
