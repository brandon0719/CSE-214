/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#2
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * This class contains the the title, array of bullet points, and duration of a slide. Each slide is limited to 5 bullet points.
 */
public class Slide {
    public static final int MAX_BULLETS = 5;
    private String title;
    private String[] bullets;
    private double duration;
    private int numBullets = 0;
    /**
     * This is a default constructor to create a new Slide Object. It defaults the values of bullets, title, and duration.
     */
    public Slide() {
        this.title = null;
        this.bullets = null;
        this.duration = 0.0;
    }
    /**
     * This is a constructor to create a new Slide object. It intializes the slide with the inputted values.
     * @param title
     *  This is the title of the slide.
     * @param bullets
     *  The String array of bullets.
     * @param duration
     *  The duration of the slide.
     */
    public Slide(String title, String[] bullets, double duration) {
        this.title = title;
        this.bullets = bullets;
        this.duration = duration;
    }
    /**
     * This method returns the title of the slide.
     * @return
     *  The title of the slide.
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * This method sets the title of the slide to whatever it is called to.
     * @param newTitle
     *  The new title.
     * @throws IllegalArgumentException
     *  Throws and exception when the newTitle is null.
     */
    public void setTitle(String newTitle) throws IllegalArgumentException {
        if(newTitle == null)
            throw new IllegalArgumentException("Null is not a valid title");
        this.title = newTitle;
    }
    /**
     * This method returns the duration of the slide.
     * @return
     *  The duration of the slide.
     */
    public double getDuration() {
        return this.duration;
    }
    /**
     * This method sets the new duration of the slide.
     * @param newDuration
     *  The new duration that the slide will be set to.
     * @throws IllegalArgumentException
     *  Throws an exception when tiem is less than 0.
     */
    public void setDuration(double newDuration) throws IllegalArgumentException{
        if(newDuration <= 0)
            throw new IllegalArgumentException("Time can not be less than 0");
        this.duration = newDuration;
    } 
    /**
     * This returns the number of bullets in the slide.
     * @return
     *  Returns number of bullets.
     */
    public int getNumBullets() {                            //DEBUG NEED TO
        numBullets = 0;
        for(int i = 0; i < MAX_BULLETS; i++) {
            if(bullets[i] != null)
                numBullets += 1;
        }
        return numBullets;
    }
    /**
     * This method gets value of the bullet.
     * @param i
     *  The index of the bullet.
     * @return
     *  Returns the string of the bullet.
     * @throws IllegalArgumentException
     *  Throws and exception when the index is invalid.
     */
    public String getBullet(int i) throws IllegalArgumentException {
        if(i < 0 || i > MAX_BULLETS)
            throw new IllegalArgumentException("Not a valid index");
        return bullets[i];
    }
    /**
     * This method creates a bullet to the desired index.
     * @param bullet
     *  The contents of the bullet.
     * @param i
     *  The desired index.
     * @throws IllegalArgumentException
     *  Throws and exception when the index is invalid or the max number of bullets is reached.
     */
    public void setBullet(String bullet, int i) throws IllegalArgumentException {
        if(i < 1 || i > MAX_BULLETS)
            throw new IllegalArgumentException("Not a valid index");
        if(this.getNumBullets() >= MAX_BULLETS)
            throw new IllegalArgumentException("Max number of bullets reached");
        if(bullet == null) {                                //if bullet is null 
            for(int j = i-1; j < MAX_BULLETS-1; j++) {  
                bullets[j] = bullets[j+1]; 
            }
            bullets[4] = null;
        }
        else {
            for(int k = MAX_BULLETS; k < i-1; k--) {
                bullets[k] = bullets[k-1];
            }
            bullets[i-1] = bullet;
        }
    }
}
