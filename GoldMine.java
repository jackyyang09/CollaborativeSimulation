import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A goldmine for units to collect gold.
 * 
 * @author (Kajamugesh Raneethran) 
 * @version (February 29th, 2015)
 */
public class GoldMine extends Buildings
{
    protected int amount;//current amount of gold in the mine
    protected boolean propertyOne;//true if controlled by player one
    protected boolean propertyTwo;//true if controlled by player two
    protected int maxAmount;//maximum amount of gold that can be in the mine
    protected int regen;//amount of time required for gold to be added
    protected int time = 0;//time left until gold is added to mine
    public GoldMine()
    {
        maxAmount= 5000;
        amount = 3000;
        propertyOne = false; 
        propertyTwo = false;
        regen = 2;
    }

    /**
     * Act - do whatever the Gold wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * regenerates gold if enough acts have passed
     */
    public void act() 
    {
        if (time == regen)
        {
            time = 0;
            subAmount(-1);
        }
        else
        {
            time++;   
        }
    } 

    /**
     * returns the Y co-ordinate of the gold mine
     */
    public int getY()
    {
        return getY();
    }

    /**
     * returns the X co-ordinate of the gold mine
     */
    public int getX()
    {
        return getX();
    }

    /**
     * sets amount of gold in the gold mine
     * @param amount     amount
     */
    public void setAmount(int amount)
    {
        if (amount<0)
        { 
            amount = this.amount;
        }
    }

    /**
     * subtracts amount of gold, or adds if negative, in the gold mine
     * doesn't allow negative  current amounts or past max amount
     * @param amount     amount taken
     */
    public boolean subAmount(int amount)
    {
        int request = amount - this.amount;
        if (request < 0)
        {
            return false;
        }
        else if (request > maxAmount)
        {
            amount = maxAmount;
            return true;
        }
        else
        {
            amount = request;
            return true;
        }
    }

    /**
     * returns amount of gold in the gold mine
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * sets regeneration rate
     * @param rate     the larger the number the longer
     */
    public void regenRate(int rate)
    {
        regen = rate;
    }

    /**
     * decides which player gets control
     * over the gold mine
     * @param player     player one gets control if 1, player two gets control if 2
     */
    public void control(int player)
    {
        if (player == 1)
        {
            propertyOne = true;
            propertyTwo = false;
        }

        if (player == 2)
        {
            propertyOne = false;
            propertyTwo = true;
        }
    }
}
