import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author Ryan Huang, Kris Leung, Kajamugesh Raneethran, Jacky Yang
 * @version Day 1
 */
public class Map extends World
{
    private int location; // location of buildings
    GoldMine goldMineOne = new GoldMine();
    GoldMine goldMineTwo = new GoldMine();
    Nexus nexusOne = new Nexus(1);
    Nexus nexusTwo = new Nexus(2);
    Player playerOne;
    Player playerTwo;
    ScoreBar score = new ScoreBar();
    boolean toggle = true;// removes score bar if false
    int lag = 10;// prevents flashing by having a 10 act wait
    /**
     * Constructor for objects of class Map.
     * 
     */
    public Map(int gold1, int gold2)
    {    
        // Create a new world with  960x640 cells
        super(960, 640, 1);
        playerOne = new Player(1, gold1);
        addObject(playerOne,0,0);
        playerTwo = new Player(2, gold2);
        addObject(playerTwo,0,0);
        addObject(goldMineOne,480,520);
        addObject(goldMineTwo,480,120);
        addObject(nexusOne,100,320);
        addObject(nexusTwo,860,320);
        addObject(score, 480,15);
        spawnUnit(0, true);
        spawnUnit(0, false);
        spawnUnit(1, true);
        spawnUnit(1, false);
    }

    /**
     * 
     */
    public void act(){
        toggleStats();
    }

    /**
     * prevents score bar from bing overlapped as a side effect
     */
    private void toggleStats()
    { 
        if (!toggle && Greenfoot.isKeyDown("space") && !("space".equals(Greenfoot.getKey()))){
            removeObject(score);
            toggle = true;
        }
        if (toggle && !Greenfoot.isKeyDown("space") && ("space".equals(Greenfoot.getKey()))){
            addObject(score, 480,15);// added here so building doesn't block stats
            toggle = false;
        }
        score.update(playerOne.getSoldiers(), playerTwo.getSoldiers(), playerOne.getGold(), playerTwo.getGold());
    }

    /**
     * enter a number to spawn in a certian building at a specfic location
     * <p>
     * Barrack = 0
     * @param buildingCode code that correpsonds to building
     */
    public void spawnBuilding(int buildingCode)
    {

    }

    /**
     * sets the location of a building
     */
    public int setLocBuilding(int loc)
    {
        switch (loc)
        {
            case 0:
            return 128;
            case 1:
            return 256;
            case 2:
            return 384;
            case 3:
            return 512;
            case 4:
            return 640;
        }
        return 0;
    }

    /**
     * Static method that gets the distance between the x,y coordinates of two Actors
     * using Pythagorean Theorum.
     * 
     * @param a     First Actor
     * @param b     Second Actor
     * @return float
     */
    public static float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }

    /**
     * Method that spawns units in the world
     * @param code     0 if miner and soldier otherwise
     * @param side     true if player one and player two otherwise
     */
    public void spawnUnit (int code, boolean side){
        if(code == 0){
            if(side == true){
                //true for player 1
                addObject(new PlayerOneMiner(goldMineOne, goldMineTwo, nexusOne, playerOne), 225, 320);
            }
            else{
                addObject(new PlayerTwoMiner(goldMineOne, goldMineTwo, nexusTwo, playerTwo), 735, 320);
            }
        }
        else if(code == 1){
            if(side == true){
                //true for player 1
                addObject(new PlayerOneSoldier(nexusTwo, playerOne), 251, 320);
                playerOne.addSoldiers(1);
            }
            else{
                addObject(new PlayerTwoSoldier(nexusOne, playerTwo), 709, 320);
                playerTwo.addSoldiers(1);
            }   
        }
    }
    
    public int getPopOpponentSoldier(int p){
        return 4;
    }
}
