/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#7
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Interactive class that allows you to interact with the API and pull information.
 */
import java.util.Scanner;
public class NeoViewer {
    private static Scanner sc = new Scanner(System.in);
    private static NeoDatabase db = new NeoDatabase();
    public static void main(String[] args) {
        System.out.println("Welcome to NEO Viewer!\n");
        String choice = "";
        boolean quit = false;
        while(!(quit)) {
            printMenu();
            choice = sc.nextLine();
            switch(choice.toUpperCase()) {
                case "A":
                    try {
                        System.out.print("\nEnter the page to load: ");
                        int page = sc.nextInt();
                        sc.nextLine();
                        String queryUrl = db.buildQueryURL(page);
                        db.addAll(queryUrl);
                        System.out.println("\nPage loaded successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid page number!\n");
                    }
                    break;
                case "S":
                    boolean sorted = false;
                    String sortChoice = "";
                    while(!(sorted)) {
                        System.out.println("\nR) Sort by referenceID\nD) Sort by diameter\nA) Sort by approach date\nM) Sort by miss distance");
                        System.out.print("Select a menu option: ");
                        sortChoice = sc.nextLine();
                        switch(sortChoice.toUpperCase()) {
                            case "R":
                                ReferenceIDComparator compR = new ReferenceIDComparator();
                                db.sort(compR);
                                System.out.println("Table sorted on referenceID.\n");
                                sorted = true;
                                break;
                            case "D":
                                DiameterComparator compD = new DiameterComparator();
                                db.sort(compD);
                                System.out.println("Table sorted on diameter.\n");
                                sorted = true;
                                break;
                            case "A":
                                ApproachDateComparator compA = new ApproachDateComparator();
                                db.sort(compA);
                                System.out.println("Table sorted on approach date.\n");
                                sorted = true;
                                break;
                            case "M":
                                MissDistanceComparator compM = new MissDistanceComparator();
                                db.sort(compM);
                                System.out.println("Table sorted on miss distance.\n");
                                sorted = true;
                                break;
                            default:
                                System.out.println("Invalid choice.\n");
                                break;
                        }
                    }
                    break;
                case "P":
                    db.printTable();
                    break;
                case "Q":
                    quit = true;
                    System.out.println("\nProgram terminating normally");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
    /**
     * Prints the interactive menu.
     */
    public static void printMenu() {
        System.out.println("\nOption Menu:");
        System.out.println("    A) Add a page to the database\n    S) Sort the database\n    P) Print the database as a table.\n    Q) Quit\n");
        System.out.print("Select a menu option: ");
    }
}
