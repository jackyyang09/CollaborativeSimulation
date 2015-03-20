import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manager class
 * 
 * @Jacky Yang, Ryan Huang 
 * @Day 4 WIP
 */
public class Player extends Actor
{
    private int gold;
    private boolean prepare = false;
    private int player;
    private int buyCount;
    private int buildPerc;
    private int barracksRoll, factoryRoll;
    private Boolean[] occupied = new Boolean[5];
    public Player(int AI)
    {
        player = AI;
        buyCount = 0;
        buildPerc = 0;
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
        if (buyCount < 5){
            buyBuildings();
        }
        System.out.println(gold);
    }

    /**
     * Buys a Barrack and places it on the appropriate area and decreases gold
     */
    public void buyBarracks()
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
     * Buys a War Factory and places it on the appropriate area and decreases gold
     */
    public void buyFactory()
    {
        if (gold >= 1500)
        {
            int val = Greenfoot.getRandomNumber(5);
            if (occupied[val] == false){
                if (player == 1){getWorld().addObject(new Barracks(), 100, slot(val));}
                if (player == 2){getWorld().addObject(new Barracks(), 860, slot(val));}
                occupied[val] = true;
                buyCount++;
                gold -= 1500;
            }
        }
    }

    /**
     * Decides to buy the buildings according to the player AI and how many buildings its bought before
     */
    public void buyBuildings()
    {
        if (buyCount == 0){buyBarracks();}
        if (buyCount == 1){
            buildPerc = 50;
            do{
                int barracksRoll = Greenfoot.getRandomNumber(100);
                int factoryRoll = Greenfoot.getRandomNumber(100);
            }while(factoryRoll == barracksRoll);
            do{
                if (barracksRoll < buildPerc){buyBarracks();}
                if (factoryRoll < buildPerc){buyFactory();}
            }while(barracksRoll >= buildPerc && factoryRoll >= buildPerc);
        }
    }

    public void buyUnits()
    {
        if (buyCount == 0){}
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
