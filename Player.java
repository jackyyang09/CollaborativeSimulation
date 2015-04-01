import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class that manages and makes decisions for the two teams
 * 
 * @author Jacky Yang, Ryan Huang, Kajamugesh Raneethran
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
    private int cooldown;
    private int popSoldier = 0;
    private int popMiner = 0;
    private Boolean[] occupied = new Boolean[5];
    private Boolean panic = false;
    private Nexus nexus;
    private Map m;
    /**
     * Instantiates Player 
     * 
     * @param AI An integer value of either 1 or 2, decides which side to make decisions for
     */
    public Player(int AI)
    {
        player = AI;
        nexus = new Nexus(player);
        buyCount = 0;
        buildPerc = 0;
        decision = 3;
        if (player == 1){cooldown = 2000;}
        if (player == 2){cooldown = 2000;}
    }

    /**
     * Prepares some values that will be used later
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
        if (cooldown%50 == 0){gold++;}
        cooldown--;
        if (buyCount < 4 && decision == 3){buyBuildings();}
        if (decision == 1 && gold >= 1000){buyBarracks();}
        if (decision == 2 && gold >= 1500 && panic == false){buyFactory();}
        if (barracksCount > 0 && cooldown <= 0 && gold >= 200){buyUnits();}
        if (((Map)getWorld()).getPopOpponentSoldier(player) - getSoldiers() >= 4){panic = true;}
        else{panic = false;}
    }

    /**
     * Adds the given amount of gold to current gold
     * 
     * @param amtgold amount of gold to be added
     */
    public void addGold(int amtgold)
    {
        gold += amtgold;    
    }

    /**
     * Adds the given amount of soldiers to current population
     * @param soldiers amount of soldiers to be added
     */
    public void addSoldiers(int soldiers)
    {
        popSoldier += soldiers;
    }

    /**
     * Returns current amount of gold
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * Returns current amount of soldiers of this player
     */
    public int getSoldiers()
    {
        return popSoldier;
    }

    /**
     * Returns current amount of miners of this player
     */
    public int getMiners()
    {
        return popMiner;
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
            if (player == 1){getWorld().addObject(new Barracks(1), 100, slot(space));}
            if (player == 2){getWorld().addObject(new Barracks(2), 860, slot(space));}
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
            if (player == 1){getWorld().addObject(new WarFactory(1), 100, slot(space));}
            if (player == 2){getWorld().addObject(new WarFactory(2), 860, slot(space));}
            occupied[space] = true;
            buyCount++;
            factoryCount++;
            gold -= 1500;
            decision = 3;
        }
    }

    /**
     * Spawns a miner or a soldier at the expense of gold
     */
    private void buyUnits()
    {
        if(Greenfoot.getRandomNumber(2) == 0){
            gold -= 200; 
            if(player==1){
                ((Map)getWorld()).spawnUnit(1, true);
                if (panic == false){cooldown = 2000;}
                else{cooldown = 1200;}
            }
            if(player==2){
                ((Map)getWorld()).spawnUnit(1, false);
                if (panic == false){cooldown = 2000;}
                else{cooldown = 1200;}
            }
        }
        else{
            if (gold >= 500){
                gold -= 500;
                if (factoryCount > 0){
                    if(player==1){
                        ((Map)getWorld()).spawnUnit(0, true);
                        cooldown = 2000;
                    }
                    if(player==2){
                        ((Map)getWorld()).spawnUnit(0, false);
                        cooldown = 2000;
                    }
                }
            }
        }
    }

    /**
     * Decides to buy the buildings according to the player AI and how many buildings its bought before
     */
    private void buyBuildings()
    {
        if (buyCount == 0){decision = 1;}
        if (buyCount == 1){buildPerc = 50;}
        if (buyCount == 2){buildPerc = 25;}
        if (buyCount == 3){buildPerc = 1;}
        if (factoryCount > barracksCount){buildPerc = 100 - buildPerc;}
        do{
            barracksRoll = Greenfoot.getRandomNumber(100);
            factoryRoll = Greenfoot.getRandomNumber(100);
            if (factoryCount > barracksCount || factoryCount == barracksCount){
                if (barracksRoll < buildPerc){decision = 1;}
                if (factoryRoll < buildPerc){decision = 2;}
            }else{
                if (barracksRoll < (100 - buildPerc)){decision = 1;}
                if (factoryRoll < (100 - buildPerc)){decision = 2;}
            }
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
