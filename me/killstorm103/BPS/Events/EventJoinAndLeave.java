package me.killstorm103.BPS.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.killstorm103.BPS.Main.BPS;
import me.killstorm103.BPS.Main.User;

public class EventJoinAndLeave implements Listener
{
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onJoin (PlayerJoinEvent e)
	{
		BPS.USERS.put(e.getPlayer().getUniqueId(), new User(e.getPlayer()));
	}
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onLeave (PlayerQuitEvent e)
	{
		User user = BPS.getUser(e.getPlayer());
		if (user == null) return;
		
		BPS.USERS.remove(e.getPlayer().getUniqueId(), user);
	}
}
