package me.killstorm103.BPS.Main;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Task implements Runnable
{
	
	private static Task task = new Task();
	public Task () {}

	public static Task getMainTask() 
	{
		return task;
	}
	
	@Override
	public void run() 
	{
		if (Bukkit.getOnlinePlayers().isEmpty()) return;
		
		for (Player player : Bukkit.getOnlinePlayers())
		{
			User user = BPS.getUser(player);
			if (user != null)
			{
				final double distX = user.getLocation().getX() - user.lastTickPosX;
				final double distY = user.getLocation().getY() - user.lastTickPosY;
				final double distZ = user.getLocation().getZ() - user.lastTickPosZ;
				final double bpsXZ = Math.sqrt(distX * distX + distZ * distZ) * 20.0;
				final double bpsY = Math.sqrt(distY * distY) * 20.0;  
				user.getPlayer().sendMessage("Your BPS is: " + String.valueOf(new DecimalFormat("#.##").format(bpsXZ)) + "/xz " + String.valueOf(new DecimalFormat("#.##").format(bpsY)) + "/y"); 
			}
		}
	}
}
