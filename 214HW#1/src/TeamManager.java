/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#1
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * This class is the menu to utilize the Team class. It is used to run the code, and uses input and output.
 */
import java.util.Scanner;
public class TeamManager {
    public static final int MAX_TEAMS = 5;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String choice = "";
        Team[] teams = new Team[MAX_TEAMS];
        for(int i = 0; i < teams.length; i++){
            teams[i] = new Team();
        }
        Team pointerTeam = teams[0];      //default pointer
        System.out.println("Welcome to Team Manager!\nTeam 1 is currently selected.");
        while(!(choice.equalsIgnoreCase("Q"))) {
            printMenu();
            choice = sc.nextLine().toUpperCase();
            switch(choice) {
                case "A":
                    try {
                        System.out.print("Enter the player's name: ");
                        String playerName = sc.nextLine();
                        System.out.print("Enter the number of hits: ");
                        int numHits = nextIntLine(); 
                        System.out.print("Enter the number of errors: ");
                        int numErrors = nextIntLine();
                        System.out.print("Enter the position: ");
                        int pos = nextIntLine();
                        Player p = new Player(playerName,numHits,numErrors);
                        pointerTeam.addPlayer(p, pos);
                        System.out.println("Player added: " + playerName + " - " + numHits + " hits, " + numErrors + " errors");
                    } catch (IllegalArgumentException e) {
                        System.out.println("The position is not in range");
                    } catch(FullTeamException e) {
                        System.out.println("There is no more room inside of the Team");
                    }
                    break;
                case "G":
                    try {
                        System.out.print("Enter the position: ");
                        int statsPos = nextIntLine();
                        System.out.print(pointerTeam.getPlayer(statsPos).toString() + "\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Position does not exist");
                    }
                    break;
                case "L":
                    System.out.print("(hits or errors)\nEnter the stat: ");
                    String leaderStat = sc.nextLine();
                    System.out.println(leaderStat);
                    System.out.println((pointerTeam.getLeader(leaderStat)).toString());
                    break;
                case "R":
                    System.out.print("Enter the position: ");
                    int removePos = nextIntLine();
                    pointerTeam.removePlayer(removePos);
                    System.out.println("Player removed at position " + removePos);
                    break;
                case "P":
                    System.out.print("Enter team index: ");
                    int teamIndex = nextIntLine();
                    teams[teamIndex-1].printAllPlayers();
                    break;
                case "S":
                    System.out.print("There are " + pointerTeam.size() + " player(s) in the current Team.");
                    break;
                case "T":
                    System.out.print("Enter team index to select: ");
                    int index = nextIntLine();
                    pointerTeam = teams[index-1];
                    System.out.println("Team " + index + " has been selected.");
                    break;
                case "C":
                    System.out.print("Select team to clone from: ");
                    int cloneFrom = sc.nextInt()-1;
                    sc.nextLine();
                    System.out.print("Select team to clone to: ");
                    int cloneTo = sc.nextInt()-1;
                    sc.nextLine();
                    teams[cloneTo] = (Team) teams[cloneFrom].clone();
                    System.out.println("Team " + cloneFrom + 1 + " has been copied to Team " + cloneTo + 1);
                    break;
                case "E":
                    System.out.print("Select first team index: ");
                    int firstTeam = sc.nextInt()-1;
                    sc.nextLine();
                    System.out.print("Select second team index: ");
                    int secondTeam = sc.nextInt()-1;
                    sc.nextLine();
                    if(teams[firstTeam].equals(teams[secondTeam]))
                        System.out.println("These teams are equal");
                    else
                        System.out.println("These teams are not equal");
                    break;
                case "U":
                    System.out.print("Enter the player to update: ");
                    String name = sc.nextLine();
                    System.out.print("Enter the stat to update: ");
                    String updateStat = sc.nextLine();
                    if(updateStat.equalsIgnoreCase("hits") || updateStat.equalsIgnoreCase("errors")) {
                        for(int i = 1; i <= pointerTeam.size(); i++) {
                            if(pointerTeam.getPlayer(i).getName().equalsIgnoreCase(name)) {
                                if(updateStat.toLowerCase().equalsIgnoreCase("hits")) {
                                    System.out.print("Enter the new number of hits: ");
                                    int newNumHits = nextIntLine();
                                    pointerTeam.getPlayer(i).setNumHits(newNumHits);
                                    System.out.println("Updated " + name + " hits.");
                                }
                                else if(updateStat.toLowerCase().equalsIgnoreCase("errors")) {
                                    System.out.print("Enter the new number of errors: ");
                                    int newNumErrors = nextIntLine();
                                    pointerTeam.getPlayer(i).setNumErrors(newNumErrors);
                                    System.out.println("Updated " + name + " errors.");
                                }
                            }
                            else 
                                System.out.println("Player not found");
                                break;
                        }
                        break;
                    }
                    case "Q":
                        sc.close();
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                 
            }
        }
    }
    /**
     * Prints the menu interface of the code.
     */
    public static void printMenu() {
        System.out.println("\nPlease select an option:\nA) Add Player.\nG) Get Players stats.\nL) Get leader in a stat.\nR) Remove a player." +
         "\nP) Print all players.\nS) Size of team.\nT) Select team.\nC) Clone team.\nE) Team equals.\nU) Update stat." +
        "\nQ) Quit.\n");
        System.out.println("\nSelect a menu option: ");
    }
    /**
     * Method to make the input of ints simpler.
     * @return
     *  int inputted.
     */
    public static int nextIntLine() {
        int number = sc.nextInt();  //input declared at the top of the program as a static variable
        sc.nextLine();
        return number;
    }
}
