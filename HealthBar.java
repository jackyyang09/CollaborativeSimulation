import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Health bar widget that allows user to use a custom stand alone or following health bar that one can adjust
 * its width, height, max health, current health, and more
 * <p>
 * Additionally user's may also attach this health bar to another actor to follow
 * <p>
 * Note: This health bar will not work as intended if negative values are entered into certian methods
 * @author Ryan Huang
 * @version Feb 24th 2015
 */
public class HealthBar extends Actor
{
    private GreenfootImage hpBar; // health bar
    private int offset = -30; // offset of health bar if following another object
    private Actor owner; // object to follow
    private boolean following = false; // toggles between stand alone health bar or following health bar
    // health bar visual vars
    private int hpBarHeight = 20; // height of health bar
    private int hpBarWidth = 300; // width of health bar
    // health bar technical vars
    private double healthPerc; // health bar percentage
    private int healthPerPixel; // number of pixels per health
    private int currentHp = 100; // current health
    private int maxHp = 100; // max health
    private int dangerValue = 20; // danger once health reaches this value
    // health bar custom vars
    private boolean leftRight = true; // toggles between left to right increase of health or right to left
    private boolean isVertical = false; // toggles between vertical or horizontal health bar
    // helath bar color vars
    private int hpR = 0, hpG = 255, hpB = 0; // color for safe level health
    private int hpDR = 255, hpDG = 0, hpDB = 0; // color for danger level health
    private int hpFR = 255, hpFG = 255, hpFB = 255; // color for flashing color health
    private Color hpBarColor = new Color (hpR, hpG, hpB); // new color for safe health
    private Color hpBarDangerColor = new Color (hpDR, hpDG, hpDB); // new color for hanger health
    private Color hpBarFlashColor = new Color (hpFR, hpFG, hpFB);// new color for flash health
    //flashing vars
    private int delay = 10; // time delay for flashing tempo
    private boolean flashNow; // toggles between flashing or non flashing
    /**
     * Creates default health bar with max health of 100 and current health of 100
     * 300 pixels wide by 20 pixels high
     * Default color of health is bright green or (R=0,G=255,B=0)
     */
    public HealthBar()
    {
        update();
    }

    /**
     * User may enter the current health to be displayed in the default health bar
     * 
     * @param CurrentHp choose your current hp
     */
    public HealthBar(int CurrentHp)
    {
        currentHp = CurrentHp;
        update();
    }

    /**
     * User may enter the current health and max health to be displayed in the in the default health bar
     * 
     * @param CurrentHp set your current health
     * @param MaxHp set your max health
     */
    public HealthBar(int CurrentHp, int MaxHp)
    {
        currentHp = CurrentHp;
        maxHp = MaxHp;
        update();
    }

    /**
     * User may enter the current health, max health, width of health bar, and height of health bar
     * to be displayed in the in the default health bar
     * 
     * @param CurrentHp set your current health
     * @param MaxHp set your max health
     * @param HpBarWidth set your health bar width
     * @param HpBarHeight set your health bar height
     */
    public HealthBar(int CurrentHp, int MaxHp, int HpBarWidth, int HpBarHeight)
    {
        currentHp = CurrentHp;
        maxHp = MaxHp;
        hpBarWidth = HpBarWidth;
        hpBarHeight = HpBarHeight;
        update();
    }

    /**
     * Accepts a total Health which is the max HP that the owner has and accepts an actor to be set as the owner
     * 
     * @param CurrentHp current health of the health bar's owner
     * @param MaxHp Max Health of the Health Bar's owner
     * @param target The owner of this Health Bar
     */
    public HealthBar (int CurrentHp, int MaxHp, int HpBarWidth, int HpBarHeight, Actor target){
        hpBarWidth = HpBarWidth;
        hpBarHeight = HpBarHeight;
        hpBar = new GreenfootImage(hpBarWidth, hpBarHeight); //makes a new Health Bar
        hpBar.setColor(hpBarColor); //sets the colour for the Health Bar to be green
        hpBar.fill(); //fill the Health Bar
        currentHp = CurrentHp; //sets current health
        maxHp = MaxHp; //sets max health
        this.owner = target; //sets its owner
        this.setImage(hpBar); //sets the image defined above
        following = true;
    }

    /**
     * act method gets run every act to run update
     */
    public void act()
    {
        update();
        follow();
        flip(leftRight);
        setVertical(isVertical);
    }

    /**
     * checks to see if there is an object to follow if not deletes health bar
     */
    private void follow ()
    {
        if (following == true) // checks to see if health bar is following another object
        {
            //checks to see if there is an owner to follow
            if (owner.getWorld() == null){
                getWorld().removeObject(this); //if there is nothing to follow, remove the HPBar
            }
            else if (owner.getWorld() != null){
                setLocation(owner.getX(), owner.getY() + offset); //follow the owner of this HPBar
            }
        }
    }

    /**
     * creates the health bar and updates it every act as inputs are given
     */
    private void update()
    {
        //Next lines determines how many pixels to adjust the health by according to the health bar's width
        healthPerc = (double)currentHp/(double)maxHp;
        healthPerPixel = (int)(healthPerc*hpBarWidth);
        // if true then turns health bar by 90 degrees
        if (isVertical == false){ 
            setImage(new GreenfootImage(hpBarWidth + 2, hpBarHeight + 2)); //creates new health bar
            hpBar = getImage(); // sets image
            hpBar.setColor(Color.BLACK); // set color to black
            hpBar.drawRect(0, 0, hpBarWidth + 1, hpBarHeight + 1); // draws health bar
            if (currentHp <= dangerValue && flashNow == false) // checks if the health is lower or equal to danger and flash is false
            {
                hpBar.setColor(hpBarDangerColor); // changes color to danger color
                hpBar.fillRect(1, 1, healthPerPixel, hpBarHeight); // draws updated color on to health bar
            }else if (currentHp <= dangerValue && flashNow == true) // checks if the health is lower or equal to danger and flash is true
            {
                flash();
            } else
            {
                hpBar.setColor(hpBarColor);// changes color to safe color
                hpBar.fillRect(1, 1, healthPerPixel, hpBarHeight);// draws updated color on to health bar
            }
        } else // if vertical is true then make this code drawa vertical health bar
        {
            setImage(new GreenfootImage(hpBarHeight + 2, hpBarWidth + 2));
            hpBar = getImage();
            hpBar.setColor(Color.BLACK);
            hpBar.drawRect(0, 0, hpBarHeight + 1, hpBarWidth + 1);
            if (currentHp <= dangerValue)
            {
                hpBar.setColor(hpBarDangerColor);// changes color to danger color
                hpBar.fillRect(1, 1, hpBarHeight, healthPerPixel);// draws updated color on to health bar
            } else
            {
                hpBar.setColor(hpBarColor);// changes color to safe color
                hpBar.fillRect(1, 1, hpBarHeight, healthPerPixel);// draws updated color on to health bar
            }
        }
    }

    /**
     * Increases current health by user's set number
     * @param increaseBy set how much you would like to increase your current health
     */
    public void increaseHp(int increaseBy)
    {
        if(currentHp < maxHp)
        {
            currentHp += increaseBy;
        }
    }

    /**
     * Decreases current health by user's set number
     * @param decreaseBy set how much you would like to decrease your current health
     */
    public void decreaseHp(int decreaseBy)
    {
        if(currentHp > 0)
        {
            currentHp -= decreaseBy;
        }
    }

    /**
     * sets current hp of healthBar
     * @param current sets the current health of your health bar
     */
    public void setCurrentHp(int current)
    {
        currentHp = current;
    }

    /**
     * sets max health of health bar
     * @param max sets the max health of your health bar
     */
    public void setMaxHp(int max)
    {
        maxHp = max;
    }

    /**
     * By default your health bar will increase from left to right, this allows the reverse
     * @param LR If true health bar increases from left to right. If false helaht bar increases from right to left
     */
    public void flip(boolean LR)
    {
        leftRight = LR;
        if(leftRight == false && isVertical == false) //checks if veritcal health bar is true if not then flips horizontally
        {
            hpBar.mirrorHorizontally();
        } else if (leftRight == true && isVertical == true)//checks if veritcal health bar is true if ture then flips vertically
        {
            hpBar.mirrorVertically();
        }
    }

    /**
     * set width of health bar
     * @param width the width of your health bar values must be above 0
     */
    public void setHpBarWidth(int width)
    {
        hpBarWidth = width;
    }

    /**
     * set height of health bar
     * @param height the height of your health bar values must be above 0
     */
    public void setHpBarHeight(int height)
    {
        hpBarHeight = height;
    }

    /**
     * Changes color of health safe color using RGB colors
     * @param R your red value from 0-255
     * @param G your green value from 0-255
     * @param B your blue value from 0-255
     */
    public void setHealthColor(int R, int G, int B)
    {
        hpR = R;
        hpG = G;
        hpB = B;
    }

    /**
     * Changes color of health bar once it gets to a specific value that is less than or equal to
     * @param R your red value from 0-255
     * @param G your green value from 0-255
     * @param B your blue value from 0-255
     * @param danger when the health bar should turn into the color stated above
     */
    public void setDangerColor(int R, int G, int B, int danger)
    {
        hpR = R;
        hpG = G;
        hpB = B;
        dangerValue = danger;
    }

    /**
     * Turns health bar vertically, note that the stated width and height of the health bar will become the vertical health bar's
     * new height and width respectively
     * @param vertical if true rotates health bar by 90 degrees
     * <p>
     * if false does nothing
     */
    public void setVertical(boolean vertical)
    {
        isVertical = vertical;
    }

    /**
     * flashs health bar once it gets to a specific value that is less than or equal to
     */
    private void flash()
    {
        hpBar.setColor(hpBarFlashColor); //sets color to flash color
        hpBar.fillRect(1, 1, healthPerPixel, hpBarHeight); //updates health bar with flash color
        Greenfoot.delay(delay); //delays the color before changing to danger color

        hpBar.setColor(hpBarDangerColor); //sets color to danger color
        hpBar.fillRect(1, 1, healthPerPixel, hpBarHeight);//updates health bar with danger color
        Greenfoot.delay(delay); //delays the color before changing to flash color
    }

    /**
     * true to flash
     * choose flash color
     * @param flash if true health bar will flash to flash color when in danger value
     * @param d set delay between flashes (1-10) If any higher will cause a lot of jitter
     * @param FR your red value for flash color from 0-255
     * @param FG your green value for flash color from 0-255
     * @param FB your blue value for flash color from 0-255
     */
    public void setFlash(boolean flash, int d, int FR, int FG, int FB)
    {
        flashNow = flash;
        delay = d;
        hpFR = FR;
        hpFG = FG;
        hpFB = FB;
    }
}
