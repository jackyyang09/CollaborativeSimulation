import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Nexus, does a bunch of things
 * 
 * @Jacky Yang
 * @Day 1
 */
public class Nexus extends Buildings
{
    public Nexus(int player)
    {
        graphic = player;
    }

    public void act()
    {   
        if (graphic == 1){setImage("golding2.png");}
        regen();  
    }
}
