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
    private boolean buying = false;
    private int player;
    private int buyCount;
    private int buildPerc;
    private int barracksRoll, factoryRoll;
    private int barracksCount, factoryCount;
    private Boolean[] occupied = new Boolean[5];
    private Nexus nexus;
    /**
     * Instantiates Player 
     */
    public Player(int AI)
    {
        player = AI;
        nexus = new Nexus();
        buyCount = 0;
        buildPerc = 0;
    }

    /**
     * Prepare things
     */
    private void prepare()
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
        buyUnits();
    }

    /**
     * adds a the given amount of gold to current gold
     * @param amtgold amount of gold to be added
     */
    public void addGold(int amtgold)
    {
        gold += amtgold;
    }

    /**
     * returns current amount of gold
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * Buys a Barrack and places it on the appropriate area and decreases gold
     */
    private void buyBarracks()
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
                buying = false;
            }
        }
    }

    /**
     * Buys a War Factory and places it on the appropriate area and decreases gold
     */
    private void buyFactory()
    {
        if (gold >= 1500)
        {
            int val = Greenfoot.getRandomNumber(5);
            if (occupied[val] == false){
                if (player == 1){getWorld().addObject(new WarFactory(), 100, slot(val));}
                if (player == 2){getWorld().addObject(new WarFactory(), 860, slot(val));}
                occupied[val] = true;
                buyCount++;
                gold -= 1500;
                buying = false;
            }
        }
    }

    private void buyUnits()
    {
        //if (buyCount == 0){}
    }

    /**
     * Decides to buy the buildings according to the player AI and how many buildings its bought before
     */
    private void buyBuildings()
    {
        if (buyCount == 0){buyBarracks();}
        if (buyCount == 1){buildPerc = 50;}
        if (buyCount == 2){buildPerc = 40;}
        if (buyCount == 3){buildPerc = 30;}
        if (buying == true){
            do{
                barracksRoll = Greenfoot.getRandomNumber(100);
                factoryRoll = Greenfoot.getRandomNumber(100);
            }while(factoryRoll == barracksRoll);
            if (barracksCount > factoryCount){buildPerc = 100 - buildPerc;}
            do{
                if (barracksRoll < buildPerc)
                {
                    barracksCount++;
                    buyBarracks();
                }
                if (factoryRoll < (100 - buildPerc))
                {
                    factoryCount++;
                    buyFactory();
                }
            }while(barracksRoll >= buildPerc && factoryRoll >= buildPerc);
        }
    }

    /**
     * sets the location of a building
     * @param loc is the slot number; 0 is slot 1 and so on
     */
    private int slot(int loc)
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
