import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Nexus, does a bunch of things
 * 
 * @Jacky Yang
 * @Day 1
 */
public class Nexus extends Buildings
{
    private HealthBar healthBar = new HealthBar(50000, 50000, 10000, -75, 30, 5, this);
    public void addedToWorld (World w)
    {
        getWorld().addObject (healthBar, getX(), getY());
    }

    public Nexus(int player)
    {
        graphic = player;
        health = 50000;
        maxHealth = 50000;
    }

    public void act()
    {   
        if(health > 0){
            healthBar.setCurrentHp(health);
        }
        else{
            getWorld().removeObject(this);
        }
        if (graphic == 1){setImage("golding2.png");}
        regen();  
    }

    /**
     * Deal amount of damage to the Nexus
     * @param dmg      Damage dealt to the unit
     * @return boolean   Returns true if value is changed and false otherwise
     */
    public boolean dealDamage (int dmg){
        if (dmg >= 0){
            health -= dmg;
            return true;
        }
        else{
            return false;
        }
    }
}
