import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Super class buildings, abstract
 * 
 * @Jacky Yang
 * @Day 1 WIP 
 */
public abstract class Buildings extends Actor
{
    private int health;
    private int maxHealth;
    private int buildTime;
    /**
     * Returns private variable "health"
     */
    public int getHealth()
    {   
        return health;
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
