import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Does what a little War Factory does
 * 
 * @Jacky Yang
 * @Day X WIP
 */
public class WarFactory extends Buildings
{
    private HealthBar healthBar = new HealthBar(10000, 10000, 2000, 0, 50, 15, this);
    public void addedToWorld (World w)
    {
        getWorld().addObject (healthBar, getX(), getY());
    }
    
    public WarFactory(int player)
    {
        graphic = player;
        buildTime = 750;
        health = 10000;
        maxHealth = 10000;
    }
    
    /**
     * Act - do whatever the WarFactory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (buildTime == 750)
        {
            if (graphic == 1){setImage("golding3.png");}
            getWorld().addObject(new Graphics(1), getX(), getY());
        }
        if (buildTime > 0){buildTime--;}
        if (buildTime == 0){removeTouching(Graphics.class);}
        regen();
        if(health > 0){
            healthBar.setCurrentHp(health);
        }
        else{
            getWorld().removeObject(this);
        }
    }    
}
