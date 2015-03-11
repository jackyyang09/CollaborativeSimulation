import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manger class
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int gold;
    private boolean prepare = false;
    private Nexus nexus;
    private Barracks barracks;
    public Player()
    {
        nexus = new Nexus();
        barracks = new Barracks();
    }

    public void prepare()
    {
        getWorld().addObject(nexus, 100, slot(2));
        prepare = true;
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (prepare == false){prepare();}
        gold++;
        System.out.println(gold);
    }

    public void buy()
    {
        if (gold >= 1000)
        {
            getWorld().addObject(barracks, 100, slot(1));
        }
    }

    /**
     * enter a number to spawn in a certian building at a specfic location
     * <p>
     * Barrack = 0
     * @param buildingCode code that correpsonds to building
     */
    public void spawnBuilding(int buildingCode)
    {
        if (buildingCode == 0)
        {
            //spawn barracks
        }
    }

    /**
     * sets the location of a building
     */
    public int slot(int loc)
    {
        switch (loc)
        {
            case 0: // slot 1
            return 128;
            case 1: // slot 2
            return 256;
            case 2: // slot 3
            return 384;
            case 3: //slot 4
            return 512;
            case 4: //slot 5
            return 640;
        }
        return 0; //outside the world
    }
}
