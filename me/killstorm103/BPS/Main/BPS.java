package me.killstorm103.BPS.Main;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;

import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.killstorm103.BPS.Events.EventJoinAndLeave;
import me.killstorm103.BPS.Events.EventPacket;

public class BPS extends JavaPlugin
{
	public static final HashMap<UUID, User> USERS = new HashMap<>();
	public BukkitTask OneSecondUpdateTask;
	private static BPS Plugin;
	public static BPS getPlugin ()
	{
		return Plugin;
	}
	
	@Override
	public void onEnable ()
	{
		Plugin = this;
		PacketEvents.getAPI().getEventManager().registerListener(new EventPacket(), PacketListenerPriority.HIGHEST);
        PacketEvents.getAPI().init();
        
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventJoinAndLeave(), this);
        
        OneSecondUpdateTask = getServer().getScheduler().runTaskTimer(this, Task.getMainTask(), 0, 20);
	}
	
	@Override
	public void onDisable () 
	{
		if (OneSecondUpdateTask != null)
			OneSecondUpdateTask.cancel();
			
		PacketEvents.getAPI().terminate();
	}
	@Override
	public void onLoad () 
	{
		PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this)); // Your server builder: Spigot, Velocity etc
        PacketEvents.getAPI().getSettings().reEncodeByDefault(false).checkForUpdates(true).reEncodeByDefault(false).bStats(false);
        PacketEvents.getAPI().load();
	}
	public static User getUser(Player player) 
	{
        for (User user : USERS.values()) 
        {
        	if (user.getPlayer() != player && !user.getPlayer().getUniqueId().equals(player.getUniqueId())) continue;
        	
        	return user;
        }
        return null;
    }
}
