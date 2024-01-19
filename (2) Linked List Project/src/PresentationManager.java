/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#2
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * This class is the menu to use the slide classes. It is used to run the code, and uses input and output.
 */
import java.util.Scanner;
public class PresentationManager {
    private static Scanner sc = new Scanner(System.in);
    //The main function
    public static void main(String[] args) {
        SlideList presentation = new SlideList();
        String newSlideTitle = "";
        Double newSlideDuration = 0.0;
        String choice = "";
        int addBulletNum;
        String addedBullet = "";
        String anotherBulletPoint = "";                                                 // y or n (another bullet point?)
        String[] bullets = new String[5];                                               // string array of new bullets
        System.out.println("Welcome to PresentationManager!\n\n");
        while(!(choice.equalsIgnoreCase("Q"))) {
            printMenu();
            choice = sc.nextLine().toUpperCase();
            switch(choice) {
                case "F":
                    try {
                        presentation.cursorForward();
                        System.out.println("\nThe cursor moved forward to slide \""
                        + presentation.getCursorSlide().getTitle() + "\"");
                    } catch (EndOfListException e) {
                        System.out.println("End of list cannot move forward");
                    }
                    break;
                case "B":
                    try {
                        presentation.cursorBackward();
                        System.out.println("\nThe cursor moved backwards to slide \""
                        + presentation.getCursorSlide().getTitle() + "\"");
                    } catch (EndOfListException e) {
                        System.out.println("End of list cannot move backward");
                    }
                    break;
                case "D":
                    if(presentation.getCursorSlide() == null) {
                        System.out.println("Empty slideshow");
                        break;
                    }
                    System.out.println("\n==============================================\n" + "  " +
                      presentation.getCursorSlide().getTitle() + "\n==============================================");
                    for(int i = 0; i < presentation.getCursorSlide().getNumBullets(); i++) {
                        System.out.printf("%3d.%2s\n",i+1, presentation.getCursorSlide().getBullet(i));
                    }
                    System.out.println("==============================================\n");
                    break;
                case "E":
                    if(presentation.getCursorSlide() == null) {
                        System.out.println("Empty slideshow");
                        break;
                    }
                    System.out.print("Edit title, duration, or bullets? (t/d/b) ");
                    String input = sc.nextLine();
                    if(input.toLowerCase().equals("t")) {
                        System.out.print("Enter a new title: ");
                        presentation.getCursorSlide().setTitle(sc.nextLine());
                    }
                    else if(input.toLowerCase().equals("d")) {
                        System.out.print("Enter a new duration: ");
                        presentation.getCursorSlide().setDuration(sc.nextDouble());
                    }
                    else if(input.toLowerCase().equals("b")) {
                        System.out.print("Bullet index: ");
                        int newBulletNum = nextIntLine();
                        System.out.print("Delete or edit? (d/e) ");
                        String deleteOrEdit = sc.nextLine();
                        if(deleteOrEdit.toLowerCase().equals("e")) {
                            System.out.print("Bullet " + newBulletNum + ": ");
                            String newBulletString = sc.nextLine();
                            presentation.getCursorSlide().setBullet(newBulletString, newBulletNum);
                        }
                        else if(deleteOrEdit.toLowerCase().equals("d")) {
                            presentation.getCursorSlide().setBullet(null, newBulletNum);
                            System.out.println("\nBullet " + newBulletNum + " has been deleted.\n");
                        }
                    }
                    break;
                case "P":
                    System.out.println("\nSlideshow Summary:\n==============================================");
                    System.out.printf("%7s%9s%17s%10s\n","Slide","Title","Duration","Bullets");
                    System.out.println("----------------------------------------------");
                    int slideNum = 1;
                    SlideListNode original = presentation.getCursor();
                    SlideListNode temp = presentation.getHead();
                    for (int i = 0; i < presentation.size(); i++) {
                        if(original.getData() == temp.getData()) {
                            System.out.printf("->%2d       %-14s%-10.2f%-10d\n", slideNum, temp.getData().getTitle(),
                            temp.getData().getDuration(), temp.getData().getNumBullets());
                            slideNum++;
                            temp = temp.getNext();
                        }
                        else {
                            System.out.printf("%4d       %-14s%-10.2f%-10d\n", slideNum, temp.getData().getTitle(),
                            temp.getData().getDuration(), temp.getData().getNumBullets());
                            slideNum++;
                            temp = temp.getNext();
                        }
                    }
                    System.out.println("==============================================");
                    System.out.println("Total: " + presentation.size() + " slide(s), "
                    + presentation.duration() + " minute(s), " + presentation.numBullets() + " bullet(s)");
                    System.out.println("==============================================\n");
                    break;
                case "A":
                    bullets = new String[5];
                    System.out.print("Enter the slide title: ");
                    newSlideTitle = sc.nextLine();
                    System.out.print("Enter the slide duration: ");
                    newSlideDuration = sc.nextDouble();
                    if(!(newSlideDuration instanceof Double)) {
                        System.out.println("Invalid duration");
                        break;
                    }
                    sc.nextLine();
                    addBulletNum = 1;
                    boolean invalid = false;
                    for(int i = 0; i < 5; i++) {
                        System.out.print("Bullet " + addBulletNum + ": ");
                        addedBullet = sc.nextLine();
                        bullets[addBulletNum-1] = addedBullet;                                 // each bullet inputed;
                        System.out.print("Add another bullet point? (y/n) ");
                        anotherBulletPoint = sc.nextLine();                                         // breaks while loop if "n"
                        if(!(anotherBulletPoint.toLowerCase().equals("y"))) {
                            if(anotherBulletPoint.toLowerCase().equals("n")) {
                                break;
                            }
                            else {
                                System.out.println("Invalid option");
                                invalid = true;
                                break;
                            }
                        }
                        addBulletNum++;
                    }
                    if(invalid == false) {
                        Slide appendSlide = new Slide(newSlideTitle,bullets,newSlideDuration);
                        presentation.appendToTail(appendSlide);
                        System.out.println("Slide \"" + appendSlide.getTitle() + "\" added to presentation");
                    }
                    break;
                case "I":
                    bullets = new String[5];
                    System.out.print("Enter the slide title: ");
                    newSlideTitle = sc.nextLine();
                    if(!(newSlideTitle instanceof String)) {
                        System.out.println("Invalid title");
                        break;
                    }
                    System.out.print("Enter the slide duration: ");
                    newSlideDuration = sc.nextDouble();
                    sc.nextLine();
                    if(!(newSlideDuration instanceof Double)) {
                        System.out.println("Invalid duration");
                        break;
                    }
                    addBulletNum = 1;
                    for(int i = 0; i < 5; i++) {
                        System.out.print("Bullet " + addBulletNum + ": ");
                        addedBullet = sc.nextLine();
                        bullets[addBulletNum-1] = addedBullet;                                 // each bullet inputed;
                        System.out.print("Add another bullet point? (y/n) ");
                        anotherBulletPoint = sc.nextLine();                                         
                        if(!(anotherBulletPoint.toLowerCase().equals("y"))) {
                            if(anotherBulletPoint.toLowerCase().equals("n")) {
                                break;
                            }
                            else {
                                System.out.println("Invalid option");
                                break;
                            }
                        }
                        addBulletNum++;
                        if(i == 4)                                                                 //when slide gets to 5th slide (ends on its own)
                            System.out.println("No more bullets allowed. Slide is full.\n");
                    }
                    Slide insertSlide = new Slide(newSlideTitle,bullets,newSlideDuration);
                    presentation.insertBeforeCursor(insertSlide);
                    System.out.println("Slide \"" + insertSlide.getTitle() + "\" added to presentation");
                    break;
                case "R":
                    if(presentation.getCursorSlide() == null) {
                        System.out.println("Empty slideshow");
                        break;
                    }
                    try {
                        System.out.println("Slide \"" + presentation.removeCursor().getTitle() + "\" has been removed");
                    } catch (EndOfListException e) {
                        System.out.println("You are at the end of the list");
                    }
                    break;
                case "H":
                    if(presentation.getCursorSlide() == null) {
                        System.out.println("Empty slideshow");
                        break;
                    }
                    presentation.resetCursorToHead();
                    System.out.println("The cursor moved to the front of the list");
                    break;
                case "Q":
                    sc.close();
                    System.out.println("Program terminating normally...");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            } 
        }
    }
    /**
     * Prints the menu of the presentation.
     */
    public static void printMenu() {
        System.out.println("\nPlease select an option:\nF) Move cursor forward\n"
        + "B) Move cursor backward\nD) Display cursor slide\nE) Edit cursor slide" +
         "\nP) Print presentation summary\nA) Append new slide to tail" +
         "\nI) Insert new slide before cursor\nR) Remove slide at cursor\n" + 
         "H) Reset cursor to head\nQ) Quit\n");
        System.out.print("\nSelect a menu option: ");
    }
    /**
     * Input formatting
     * @return
     *  Returns the inputted int.
     */
    public static int nextIntLine() {
        int number = sc.nextInt();  //input declared at the top of the program as a static variable
        sc.nextLine();
        return number;
    }
}
