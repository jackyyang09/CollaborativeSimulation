import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; // Need Color and Font for drawing ScoreBar
import java.awt.Font;

/**
 * ScoreBar is a Greenfoot Actor that displays text. 
 * It has been borrowed and recoded from a different source.
 * The text is white on reddish brown.
 * <p>
 * Display any String that fits, or send
 * game info straight to customized method.
 * It has also been modified to display stats
 * It was recoded from modular code but is now less modular
 * @author (Kajamugesh Raneethran) orignially by Mr. Cohen
 * @version April 2013
 */
public class ScoreBar extends Actor
{
    // Declare Objects
    private GreenfootImage scoreBoard;
    private Color background;
    private Color foreground;
    private Font textFont;
    private String text;
    // Declare Variables
    /**
     * Constructs a ScoreBar of the appropriate size.
     * 
     * @param width     width of the World where the
     *                  ScoreBar will be placed
     */
    public ScoreBar ()
    {
        scoreBoard = new GreenfootImage (960, 30);
        background = new Color (1, 20, 23);
        foreground = new Color (255, 255, 255);
        textFont = new Font ("Courier", Font.BOLD, 24);
        scoreBoard.setColor(background);
        scoreBoard.fill();
        this.setImage (scoreBoard);
        scoreBoard.setFont(textFont);

        
    } 

    /**
     * Updates this ScoreBar with game stats - population, and deat
     * 
     * @param onePop player one's population
     * @param twoPop player two's population
     * @param oneGold      number of dead bugs
     * @param twoGold   
     */
    public void update (int onePop, int twoPop, int oneGold, int twoGold)
    {
        // In order to make uniform sizes and preceding zeros
        String text = "P1 Alive: " + onePop + " P2 Alive: " + twoPop +  "  P1 Gold: " +  oneGold + " P2 Gold: " + twoGold; 
            // this.update (String) calls the other version of update(), in this case
            // update(String) - see below
        this.update (text);
    }

    /**
     * Takes a String and displays it 
     * 
     * @param output    Text for displaying. 
     */
    public void update (String output)
    {
        // Refill the background with background color
        scoreBoard.setColor(background);
        scoreBoard.fill();

        // Write text over the solid background
        scoreBoard.setColor(foreground);  
       
        // Draw the text onto the image
        scoreBoard.drawString(output,10 , 22);
    }
}
