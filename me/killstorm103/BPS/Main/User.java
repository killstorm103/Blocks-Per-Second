package me.killstorm103.BPS.Main;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class User 
{
	private final Player player;
	private static User user;
	public double lastTickPosX, lastTickPosY, lastTickPosZ;
	
	public Player getPlayer ()
	{
		return this.player;
	}
	
	public User (Player player)
	{
		setUser(this);
		this.player = player;
	}
	public static void setUser (User userr) 
	{
		user = userr;
	}
	public static User getUser () 
    {
        return user;
    }

	public Location getLocation() 
	{
		return getPlayer().getLocation();
	}
}
