/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#1
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * This class represents a Team of players which have statistics and a name. 
 * There are methods utilizing the statistics of each player, and methods to add 
 * and remove players from the Team class. 
 */
public class Team implements Cloneable{
    public static final int MAX_PLAYERS = 40;
    private int numPlayers;
    private Player[] team;
    /**
     * The constructor intializes an empty Player array.
     * post condition:
     *  Team intialized to an empty list of Players
     */
    public Team() {
        team = new Player[MAX_PLAYERS];
    }
    /**
     * Clones a Team object.
     * pre condition:
     *  intialized clonedTeam 
     * @return
     *  A clone of a Team object instance.
     */
    @Override
    public Object clone() {
        Team clonedTeam = new Team();
        for(int i = 0; i < this.size(); i++) {
            try {
                clonedTeam.addPlayer((Player)team[i].clone(), i + 1);
            } catch (FullTeamException e) {
                System.out.println("Something went wrong while cloning"); 
            }
        }
        return clonedTeam;
        }
    /**
     * Checks to see if two objects (team) are equal.
     * @param obj
     *  the reference object with which to compare.
     * @return 
     *  if this object is the same as the object in the argument.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Team))
            return false;
        Team object = (Team) obj;
        if (object.size() != this.size()) 
            return false;
        for(int i = 0; i < this.team.length; i++) {
            if(team[i].equals(object.team[i]))
                return true;
        }
        return false;
    }
    /**
     * Returms the size of the team.
     * pre conditions: 
     *  This team object has been intialized.
     * @return
     *  the number of players in the team.
     */
    public int size() {
        return numPlayers;
    }
    /**
     * Adds a player to the team, which is a Player array.
     * pre conditions: 
     *  This team object has been instantiated.
     *  position is >= 1 and position is <= numPlayers + 1
     *  The number of Player objects in this Team is less than MAX_PLAYERS.
     * @param p
     *  The Player object that will be added to the team.
     * @param position
     *  The position the Player object will be added in the team.
     * @throws IllegalArgumentException
     *  Indicates when the position is out of range.
     * @throws FullTeamException
     *  Indicates when the team is full.
     */
    public void addPlayer(Player p, int position) throws IllegalArgumentException, FullTeamException {
        if(position > MAX_PLAYERS || position < 1){
            throw new IllegalArgumentException("The position is not in range");
        }
        if(this.size() > MAX_PLAYERS) {
            System.out.println("C");
            throw new FullTeamException("There is no more room inside of the Team");
        }
        numPlayers++;
        for(int i = numPlayers; i > position; i--) {
            this.team[i-1] = this.team[i-2];
        }
        this.team[position - 1] = p;
    }
    /**
     * Removes a player at the given position.
     * pre conditions:
     *  This Team object has been instantiated.
     *  1 ≤ position ≤ players_currently_in_team.
     * @param position
     *  The position the Player object will be added in the team.
     * @throws IllegalArgumentException
     *  Indicates when the position does not exist/ is out of range.
     */
    public void removePlayer(int position) throws IllegalArgumentException {            //position is not the same as the index
        if(team[position-1] == null)
            throw new IllegalArgumentException("Position does not exist");
        numPlayers--;
        for(int i = position-1; i < this.size(); i++) {
            Player temp = this.team[i + 1];
            this.team[i] = temp;
        }
    }
    /**
     * Returns a Player at the given position.
     * pre conditions:
     *  This Team object has been instantiated.
     * @param position
     *  The position the Player object will be added in the team.
     * @return
     *  the Player at the specified position in the team.
     * @throws IllegalArgumentException
     *  Indicates when the position does not exist/ is out of range.
     */
    public Player getPlayer(int position) throws IllegalArgumentException {
        if(this.team[position-1] == null)
            throw new IllegalArgumentException("Position does not exist");
        return this.team[position-1];
    }
    /**
     * Returns the leader in errors or hits.
     * pre conditions:
     *  This Team object has been instantiated.
     * @param stat
     *  The error or hits stat for the player.
     * @return
     *  The Player with the most hits or least errors.
     * @throws IllegalArgumentException
     *  Indicates if stat was neither hits or errors.
     */
    public Player getLeader(String stat) throws IllegalArgumentException {
        if(stat.toLowerCase().equalsIgnoreCase("hits") || stat.toLowerCase().equalsIgnoreCase("errors")) {
            if(stat.toLowerCase().equalsIgnoreCase("hits")) {
                int greatestHits = 0;
                Player hitsLeader = new Player();
                for(int i = 0; i < this.size(); i++) {
                    if(this.team[i].getNumHits() > greatestHits) {
                        greatestHits = this.team[i].getNumHits();
                        hitsLeader = this.team[i];
                    }
                }
                return hitsLeader;
            }
            if(stat.toLowerCase().equalsIgnoreCase("errors")) {
                int LowestErrors = 999999;
                Player errorsLeader = new Player();
                for(int i = 0; i < this.size(); i++) {
                    if(this.team[i].getNumErrors() < LowestErrors) {
                        LowestErrors = this.team[i].getNumErrors();
                        errorsLeader = this.team[i];
                    }
                }
                return errorsLeader;
            }
        }
        else {
            throw new IllegalArgumentException("stat was neither hits or errors");
        }
        return null;
    }

    /**
     * Prints all of the players in a team.
     * pre conditions:
     *  This Team object has been instantiated.
     *  A neatly formatted table of each Player in the Team on its own 
     *  line with its position number has been displayed to the user.
     * Prints all players in the team/
     */
    public void printAllPlayers() {
        System.out.println(this.toString());
    }
    /**
     * Returns a string format for teams.
     * @return
     *  A string representation used in printAllPlayers() to print all the Players in the team.
     */
    public String toString() {
        String string = "";
        string +=
            "Player#    Name                Hits        Errors\n" +
            "-------------------------------------------------\n";
        for(int i = 0; i < this.size(); i++) {
            string += i+1 + "     " + this.team[i].toString() + "\n";
        }
        return string;
    }
}
