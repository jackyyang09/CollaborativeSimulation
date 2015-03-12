
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Unit is a unit.
 * 
 * @author Kris Leung
 * @version Mar 2015
 */
public abstract class Unit extends Actor
{
    protected int currentHp; //current hp of the units
    protected int maxHp; //max hp of the unit
    protected int speed; //speed of which unit moves
    protected int damage; //damage unit can deal
    protected int range; //the range where the unit can attack other units
    protected boolean dead; //sees if unit is dead
    protected int xValue; //x value of the unit
    protected int yValue; //y value of the unit
    protected boolean side; //true if playerOne side and false if playerTwo side
    Building building = new Building();
    Building building2 = new Building();
    public void pathFinding(int x, int y){
        turnTowards(x, y);
        move(speed);
        shootRange();
    }
    
    public void shootRange(){
        building = (Building)getOneObjectAtOffset(30, 0, Building.class);
        building2 = (Building)getOneObjectAtOffset(-30, 0, Building.class);
        if (building != null || building2 != null){
            stop();
        }
    }
    
    public void stop(){
        speed = 0;
    }
    /**
     * Deal amount of damage to a unit
     * @param dmg      Damage dealt to the unit
     * @return boolean   Returns true if value is changed and false otherwise
     */
    public boolean dealDamage (int dmg){
        if (dmg >= 0){
            currentHp -= dmg;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Sets unit's current Hp
     * @param crtHp      Current Hp of the unit
     * @return boolean   Returns true if value is changed and false otherwise
     */
    public boolean setCurrentHp (int crtHp){
        if (crtHp >= 0){
            currentHp = crtHp;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Sets unit's max Hp
     * @param mxHp      Max Hp of the unit
     * @return boolean   Returns true if value is changed and false otherwise
     */
    public boolean setMaxHp (int mxHp){
        if (mxHp >= 0){
            maxHp = mxHp;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Sets unit's speed
     * @param spd      Speed of the unit
     * @return boolean   Returns true if value is changed and false otherwise
     */
    public boolean setSpeed (int spd){
        if (spd >= 0){
            speed = spd;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Sets unit's damage
     * @param dmg      Damage of the unit
     * @return boolean   Returns true if value is changed and false otherwise
     */
    public boolean setDamage (int dmg){
        if (dmg >= 0){
            damage = dmg;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Sets unit's range
     * @param rng      Speed of the unit
     * @return boolean   Returns true if value is changed and false otherwise
     */
    public boolean setRange (int rng){
        if (rng >= 0){
            range = rng;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Sets if the unit is dead or not
     * @param hasDied   Unit is dead or not
     */
    public void setDead (boolean hasDied){
        dead = hasDied;
    }
    
    /**
     * Returns unit's current hp
     * @return int   Returns current hp of unit
     */
    public int getCurrentHp(){
        return currentHp;
    }
    
    /**
     * Returns unit's max hp
     * @return int   Returns max hp of unit
     */
    public int getMaxHp(){
        return maxHp;
    }
    
        /**
     * Returns unit's damage
     * @return int   Returns unit damage that it can deal
     */
    public int getDamage(){
        return damage;
    }
    
        /**
     * Returns unit's range
     * @return int   Returns unit range of attack
     */
    public int getRange(){
        return range;
    }
    
    /**
     * Returns if unit is dead or not
     * @return boolean   Returns true if unit is dead and false otherwise
     */
    public boolean getDead (){
        return dead;
    }
    
    /**
     * Returns what side the unit is on
     * @return boolean   Returns true if unit is on playerOne side and false if playerTwo
     */
    public boolean getSide(){
        return side;
    }
}
