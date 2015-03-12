import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Gold here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoldMine extends Buildings
{
    protected int amount;
    protected boolean propertyOne;
    protected boolean propertyTwo;
    protected int maxAmount;
    protected int regen;
    protected int time = 0;
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

    public int getY()
    {
        return getY();
    }

    public int getX()
    {
        return getX();
    }

    public void setAmount(int amount)
    {
        if (amount<0)
        { 
            amount = this.amount;
        }
    }

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

    public int getAmount()
    {
        return amount;
    }

    public void regenRate(int rate)
    {
        regen = rate;
    }

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
