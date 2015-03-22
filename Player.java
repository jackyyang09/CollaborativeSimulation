import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Manager class
 * 
 * @author Jacky Yang, Ryan Huang
 * @version Day X WIP
 */
public class Player extends Actor
{
    private int gold;
    private boolean prepare = false;
    private int space;
    private int player;
    private int buyCount;
    private int buildPerc;
    private int barracksRoll, factoryRoll;
    private int barracksCount, factoryCount;
    private int decision;
    private Boolean[] occupied = new Boolean[5];
    private Nexus nexus;
    /**
     * Instantiates Player 
     */
    public Player(int AI)
    {
        player = AI;
        nexus = new Nexus();
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
        buyCount = 0;
        buildPerc = 0;
        decision = 3;
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (prepare == false){prepare();}
        gold++;
        if (buyCount < 4 && decision == 3){buyBuildings();}
        if (decision == 1 && gold >= 1000){buyBarracks();}
        if (decision == 2 && gold >= 1500){buyFactory();}
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
        do{
            space = Greenfoot.getRandomNumber(5);
        }while(occupied[space] == true);
        if (occupied[space] == false){
            if (player == 1){getWorld().addObject(new Barracks(), 100, slot(space));}
            if (player == 2){getWorld().addObject(new Barracks(), 860, slot(space));}
            occupied[space] = true;
            buyCount++;
            barracksCount++;
            gold -= 1000;
            decision = 3;
        }
    }

    /**
     * Buys a War Factory and places it on the appropriate area and decreases gold
     */
    private void buyFactory()
    {
        do{
            space = Greenfoot.getRandomNumber(5);
        }while(occupied[space] == true);
        if (occupied[space] == false){
            if (player == 1){getWorld().addObject(new WarFactory(), 100, slot(space));}
            if (player == 2){getWorld().addObject(new WarFactory(), 860, slot(space));}
            occupied[space] = true;
            buyCount++;
            factoryCount++;
            gold -= 1500;
            decision = 3;
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
        if (buyCount == 0){decision = 1;}
        if (buyCount == 1){buildPerc = 50;}
        if (buyCount == 2){buildPerc = 25;}
        if (buyCount == 3){buildPerc = 0;}
        if (factoryCount > barracksCount){buildPerc = 100 - buildPerc;}
        do{
            barracksRoll = Greenfoot.getRandomNumber(100);
            factoryRoll = Greenfoot.getRandomNumber(100);
            if (factoryRoll < buildPerc){decision = 2;}
            if (barracksRoll < buildPerc){decision = 1;}
        }while(factoryRoll == barracksRoll && barracksRoll >= buildPerc && factoryRoll >= buildPerc);
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
