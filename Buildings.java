import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Super class buildings, abstract
 * 
 * @authorJacky Yang, and Kajamugesh Raneethran
 * @version Final
 */
public abstract class Buildings extends Actor
{
    protected int health;
    protected int maxHealth;
    protected int buildTime;
    protected int rate = 0;
    protected int graphic;
    /**
     * Returns private variable "health"
     */
    public int getHealth()
    {   
        return health;
    }

    /**
     * Regenerates one health every 100 acts
     */
    public void regen()
    {
        int rate = 0;
        if ((health + 1)<=maxHealth && rate == 750)
        {
            health++;
            rate = 0;
        }
        else if(health!=maxHealth)
        {
            rate++;
        }
    }

    /**
     * Returns private variable "maxHealth"
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * Sets the private variable "health" to the inputted value
     * @param newVal The new value for hp
     */
    public void setHealth(int newVal)
    {
        health = newVal;
    }
}
