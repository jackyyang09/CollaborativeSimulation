import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manager class
 * 
 * @Jacky Yang, Ryan Huang 
 * @Day 3 WIP
 */
public class Player extends Actor
{
    private int gold;
    private boolean prepare = false;
    private int player;
    private int buyCount = 0;
    private Boolean[] occupied = new Boolean[5];
    private Nexus nexus;
    private Buildings barracks;
    public Player(int AI)
    {
        player = AI;
        nexus = new Nexus();
    }

    /**
     * Prepare things
     */
    public void prepare()
    {
        for (int i = 0; i < 5; i++)
        {
            occupied[i] = false;
        }
        occupied[2] = true;
        if (player == 1){getWorld().addObject(nexus, 100, slot(2));}
        if (player == 2){getWorld().addObject(nexus, 860, slot(2));}
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
        if (buyCount < 5){buy();}
    }

    public void buy()
    {
        if (gold >= 1000)
        {
            int val = Greenfoot.getRandomNumber(5);
            if (occupied[val] == false){
                if (player == 1){getWorld().addObject(new Barracks(), 100, slot(val));}
                if (player == 2){getWorld().addObject(new Barracks(), 860, slot(val));}
                occupied[val] = true;
                buyCount++;
                gold -= 1000;
            }
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
     * @param loc is the slot number; 0 is slot 1 and so on
     */
    public int slot(int loc)
    {
        switch (loc)
        {
            case 0: // slot 1
            return 64;
            case 1: // slot 2
            return 192;
            case 2: // slot 3
            return 320;
            case 3: //slot 4
            return 448;
            case 4: //slot 5
            return 576;
        }
        return 0; //outside the world
    }
}
