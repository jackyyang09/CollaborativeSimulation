import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Unit is a unit.
 * 
 * @author Kris Leung, (some code cited from Mr.Cohen's bug example)
 * @version Mar 2015
 */
public abstract class Unit extends Actor
{
    protected int currentHp; //current hp of the units
    protected int maxHp; //max hp of the unit
    protected int speed; //speed of which unit moves
    protected int startSpeed;
    protected int damage; //damage unit can deal
    protected int range; //the range where the unit can attack other units
    protected boolean dead; //sees if unit is dead
    protected boolean side; //true if playerOne side and false if playerTwo side
    protected int counter;
    Buildings building;
    Buildings building2;
    private Unit targetUnit;
    private ArrayList<Unit> unit;
    private Buildings targetBuildings;
    private ArrayList<Buildings> buildings;
    
    /**
     * Act - do whatever the Bug wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        counter++;
        if (currentHp > 0)
        {
            if (counter == 5) // Only run every 5 acts to avoid lag
            {
                targetClosestUnit (this.side);
                //hpBar.update(hp);
            }
            if (targetUnit != null && targetUnit.getWorld() != null)
            {
                moveTowardOrAttackUnit();
            }
            else
            {
                moveForward();
            }
        }
        // Death:
        else
        {
            Map m = (Map)getWorld();
            getWorld().removeObject(this);
        }
    }  
    
    /**
     * Private method, called by act(), that constantly checks for closer targets
     */
    private void targetClosestUnit (boolean whichSide)
    {
        double closestTargetDistance = 0;
        double distanceToActor;
        int numUnit;
        // Get a list of all Units in the World, cast it to ArrayList for easy management

        numUnit = getWorld().getObjects(Unit.class).size();
        // If any units are found
        if (numUnit > 50)
        {
            unit = (ArrayList)getObjectsInRange(100, Unit.class);
        }
        else if (numUnit > 20)
        {
            unit = (ArrayList)getObjectsInRange(200, Unit.class);
        }
        else
            unit = (ArrayList)getWorld().getObjects(Unit.class);

        if (unit.size() > 0)
        {
            // set the first one as my target
            do{
                int whichUnit = 0;
                targetUnit = unit.get(whichUnit);
                if (targetUnit.getSide() == this.side)
                {
                    whichUnit++;
                }
                else{
                    break;
                }
            }
            while(true);
            // Use method to get distance to target. This will be used
            // to check if any other targets are closer
            closestTargetDistance = Map.getDistance (this, targetUnit);

            // Loop through the objects in the ArrayList to find the closest target
            for (Unit o : unit)
            {
                // Cast for use in generic method
                Actor a = (Actor) o;
                // Measure distance from me
                distanceToActor = Map.getDistance(this, a);
                // If I find a Unit closer than my current target, I will change
                // targets
                if (distanceToActor < closestTargetDistance)
                {
                    targetUnit = o;
                    closestTargetDistance = distanceToActor;
                }
            }
        }
    }

    /**
     * Private method, calle by act(), that moves toward the target,
     * or eats it if within range.
     */
    private void moveTowardOrAttackUnit ()
    {
        turnTowards(targetUnit.getX(), targetUnit.getY());

        if (this.getNeighbours (30, true, Unit.class).size() > 0)
        {
            // Deal damage to enemy unit
            targetUnit.dealDamage(damage);

            //attacking animation
        }
        else
        {
            move (speed);
        }
    }

    private void moveForward ()
    {
        if (this.side == true)
        {
            move (speed);
        }
        else
            move (-speed);
    }
    
    public void pathFinding(int x, int y){
        turnTowards(x, y);
        move(speed);
        shootRange();
    }
    
    public boolean shootRange(){
        building = (Buildings)getOneObjectAtOffset(10, 0, Buildings.class);
        building2 = (Buildings)getOneObjectAtOffset(-10, 0, Buildings.class);
        if (building != null || building2 != null){
            speed = 0;
            return true;
        }
        else{
            return false;   
        }
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
     * @param dmg Damage of the unit
     * @return boolean Returns true if value is changed and false otherwise
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
     * @param rng Speed of the unit
     * @return boolean Returns true if value is changed and false otherwise
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
     * @param hasDied Unit is dead or not
     */
    public void setDead (boolean hasDied){
        dead = hasDied;
    }
    
    /**
     * Returns unit's current hp
     * @return int Returns current hp of unit
     */
    public int getCurrentHp(){
        return currentHp;
    }
    
    /**
     * Returns unit's max hp
     * @return int Returns max hp of unit
     */
    public int getMaxHp(){
        return maxHp;
    }
    
        /**
     * Returns unit's damage
     * @return int Returns unit damage that it can deal
     */
    public int getDamage(){
        return damage;
    }
    
        /**
     * Returns unit's range
     * @return int Returns unit range of attack
     */
    public int getRange(){
        return range;
    }
    
    /**
     * Returns if unit is dead or not
     * @return boolean Returns true if unit is dead and false otherwise
     */
    public boolean getDead (){
        return dead;
    }
    
    /**
     * Returns what side the unit is on
     * @return boolean Returns true if unit is on playerOne side and false if playerTwo
     */
    public boolean getSide(){
        return side;
    }
}
