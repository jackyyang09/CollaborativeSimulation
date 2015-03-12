import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author Ryan Huang
 * @version Day 1
 */
public class Map extends World
{
    private int location; // location of buildings
    /**
     * Constructor for objects of class Map.
     * 
     */
    public Map()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(960, 640, 1);
        addObject(new Nexus(),100,setLocBuilding(2));
        GoldMine goldMineTwo = new GoldMine();
        addObject(goldMineTwo,480,320);
         GoldMine goldMineOne = new GoldMine();
        addObject(goldMineOne,480,31);
         GoldMine goldMineThree = new GoldMine();
        addObject(goldMineThree,480,608);
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
}
