/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#1
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * this class represents a person which has a name, and their statistics 
 */
public class Player {
    private String name;
    private int numHits, numErrors;
    /**
     * This is a Constructor used to create a new Player object.
     * @param name
     *  the name of the Player.
     * @param numHits
     *  the number of Hits a player has.
     * @param numErrors
     *  the number of Errors a player has.
     */
    public Player(String name, int numHits, int numErrors) {            //constructors
        this.name = name;
        this.numHits = numHits;
        this.numErrors = numErrors;
    }
    /**
     *  This is a default Constructor used to create a new Player Object.
     */ 
    public Player() {
        this.name = null;
        this.numHits = 0;
        this.numErrors = 0;
    }
    /** 
     * This method compares two objects to see if they are equal.
     * @param obj
     *  the reference object with which to compare.
     * @return 
     *  if this object is the same as the object in the argument.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Player)) {
            return false;
        }
        Player object = (Player) obj;
        if(this.getName().equalsIgnoreCase(object.getName()) && this.getNumHits() == object.getNumHits()
        && this.getNumErrors() == object.getNumErrors()) {
            return true;
        }
        else
            return false;
    }
    
    /** 
     * This method clones an object.
     * @return 
     *  returns a clone called by the Player object.
     */
    @Override
    public Object clone() {
        Player copy = new Player(this.getName(), this.getNumHits(),this.getNumErrors());
        return copy;
    }
    /**
     * This getter returns the name.
     * @return
     *  the name of the Player.
     */
    public String getName() {                                           //accessor and mutator methods
        return name;
    }
    /**
     * This setter changes the name of the Player.
     * @param name
     *  Desired name to change to.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This getter returns the number of hits
     * @return
     *  The number of hits by the Player
     */
    public int getNumHits() {
        return numHits;
    }
    /**
     * The setter changes the number of hits
     * @param numHits
     *  The number of hits a player has.
     * @throws IllegalArgumentException
     *  Throws if number of hits is negative.
     */
    public void setNumHits(int numHits) throws IllegalArgumentException {
        if (numHits < 0)
            throw new IllegalArgumentException("Number of hits cannot be negative.");
        this.numHits = numHits;
    }
    /**
     * This getter returns the number of errors.
     * @return
     *  Number of errors.
     */
    public int getNumErrors() {
        return numErrors;
    }
    /**
     * Sets the number of errors a Player has.
     * @param numErrors
     *  The number of errors.
     * @throws IllegalArgumentException
     *  Throws if the number of errors is negative.
     */
    public void setNumErrors(int numErrors) throws IllegalArgumentException {
        if (numErrors < 0)
            throw new IllegalArgumentException("Number of errors cannot be negative.");
        this.numErrors = numErrors;
    }
    /**
     * Returns a string of the player.
     * @return 
     *  returns the Player's name, number of hits, and number of errors.
     */
    @Override
    public String toString() {
        return name + " - " + numHits + " hits, "  + numErrors + " errors";
    }
}
