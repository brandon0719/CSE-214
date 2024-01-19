/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#5
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Interactive main class that runs the game.
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Zork {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello and welcome to Zork!\n");
        System.out.print("Please enter the file name: ");
        String filename = sc.nextLine();
        System.out.println("\nLoading game from file... \n");//   /Users/brandonwong/VSCode Projects/214HW#5/src/SampleStory.txt
        System.out.println("File loaded!\n");
        String choice = "";
        while(!(choice.equals("Q"))) {
            StoryTree story = StoryTree.readTree(filename);             //STORY TREE
            System.out.print("Would you like to edit (E), play (P) or quit (Q)?  ");
            choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case "P":
                    playTree(story);
                    break;
                case "E":
                    editTree(story);
                    StoryTree.saveTree(filename, story);
                    break;
                case "Q":
                    sc.close();
                    System.out.println("Program terminating normally...");
                    System.exit(0);
                    break;
            }
        }
    }
    /**
     * Edits the tree by reading the tree after the edits have been made and saving the tree to the textfile.
     * @param tree
     *  The story tree being edited.
     */
    public static void editTree(StoryTree tree) {
        if(tree.getRoot().getLeftChild() != null)               //IF THE FILE IS NOT EMPTY
            tree.setCursor(tree.getRoot().getLeftChild());       
        else
            tree.setCursor(tree.getRoot());                 //IF THE FILE IS EMPTY
        String editChoice = "";
        String children;
        String chosenChild;
        String newMessage;
        String newOption;
        boolean edit = true;
        while(edit) {
            zorkEditorMenu();
            System.out.print("Please select an option: ");
            editChoice = sc.nextLine();
            switch(editChoice.toUpperCase()) {
                case "V":
                    System.out.print("\nPosition: " + tree.getCursorPosition() + "\nOption: " + tree.getCursor().getOption());
                    System.out.println("\nMessage: " + tree.getCursor().getMessage() + "\n");
                    break;
                case "S":
                    if(tree.getNumChildren() != 0) {
                        System.out.print("\nPlease select a child: ");
                        System.out.print("[");
                        children = "";
                        for(int i = 0; i < tree.getNumChildren(); i++) {
                            System.out.print(i+1);
                            children += (i+1);
                            if(i != tree.getNumChildren() -1) System.out.print(",");
                        }
                        System.out.print("] ");
                        chosenChild = sc.nextLine();
                        if(children.contains(chosenChild)) {
                            tree.selectChild(tree.getCursorPosition() + "-" + chosenChild);
                        }
                        else {
                            System.out.println("\nError. No child " + chosenChild + " for the current node.");
                        }
                    }
                    else {
                        System.out.println("There are no children to select.");
                    }
                    break;
                case "O":
                    System.out.print("Please enter a new option: ");
                    newOption = sc.nextLine();
                    tree.setCursorOption(newOption);
                    System.out.println("\nOption set.");
                    break;
                case "M":
                    System.out.print("Please enter a new Message: ");
                    newMessage = sc.nextLine();
                    tree.setCursorMessage(newMessage);
                    System.out.println("\nMessage set.");
                    break;
                case "A":
                    System.out.print("Enter an option: ");
                    newOption = sc.nextLine();
                    System.out.print("Enter a message: ");
                    newMessage = sc.nextLine();
                    tree.addChild(newOption, newMessage);
                    break;
                case "D":
                    if(tree.getNumChildren() != 0) {
                        System.out.print("\nPlease select a child: ");
                        System.out.print("[");
                        children = "";
                        for(int i = 0; i < tree.getNumChildren(); i++) {
                            System.out.print(i+1);
                            children += (i+1);
                            if(i != tree.getNumChildren() -1) System.out.print(",");
                        }
                        System.out.print("] ");
                        chosenChild = sc.nextLine();
                        if(children.contains(chosenChild)) {
                            tree.removeChild(tree.getCursorPosition() + "-" + chosenChild);
                            System.out.println("\nSubtree deleted.");
                        }
                        else {
                            System.out.println("\nError. No child " + chosenChild + " for the current node.");
                        }
                    }
                    else {
                        System.out.println("There are no children to delete.");
                    }
                    break;
                case "R":
                    tree.resetCursor();
                    System.out.println("Cursor moved to root.");
                    break;
                case "Q":
                    edit = false;
                    System.out.println("Game being saved to SampleStory.txt...\n\nSave successful!\n\nProgram terminating normally.");
                    break;
                
            }
        }
    }
    /**
     * Runs the tree based on the user's choices.
     * @param tree
     *  The story tree being played.
     */
    public static void playTree(StoryTree tree) {
        tree.setCursor(tree.getRoot());
        while(tree.getGameState() == GameState.GAME_NOT_OVER) {
            if(tree.getCursor() == tree.getRoot()) {
                tree.setCursor(tree.getCursor().getLeftChild());
            }
            System.out.println("\n" + tree.getCursorMessage());
            String[][] options = tree.getOptions();
            for(int i = 0; i < tree.getNumChildren(); i++) {
                if(!(options[i][1].equals(null))) {
                    System.out.println(i+1 + ") " + options[i][1]);
                }
            }
            System.out.print("Please make a choice. ");
            String choice = sc.nextLine();
            boolean validChoice = false;
            while(validChoice == false) {
                if(choice.equals("1")) {
                    tree.setCursor(tree.getCursor().getLeftChild());
                    validChoice = true;
                }
                else if(choice.equals("2")) {
                    tree.setCursor(tree.getCursor().getMiddleChild());
                    validChoice = true;
                }
                else if(choice.equals("3")) {
                    tree.setCursor(tree.getCursor().getRightChild());
                    validChoice = true;
                }
                else {
                    System.out.println("Invalid choice! ");
                    System.out.print("Please make a choice. ");
                    choice = sc.nextLine();
                }
                if(validChoice == true) {
                    if(tree.getCursor().isLosingNode()) {
                        tree.setGameState(GameState.GAME_OVER_LOSE);
                        System.out.println("\n" + tree.getCursorMessage());
                        System.out.println("\nThanks for playing.");
                    } 
                    else if(tree.getCursor().isWinningNode()) {
                        tree.setGameState(GameState.GAME_OVER_WIN);
                        System.out.println("\n" + tree.getCursorMessage());
                        System.out.println("\nThanks for playing.");
                    }
                }
            }
        }
    }
    /**
     * Prints the zork editor menu.
     */
    public static void zorkEditorMenu() {
        System.out.println("\nZork Editor:\n\tV: View the cursor's position, option and message.\n" +
        "\tS: Select a child of this cursor (options are 1, 2, and 3).\n\tO: Set the option of the cursor.\n" +
        "\tM: Set the message of the cursor.\n\tA: Add a child StoryNode to the cursor.\n" +
        "\tD: Delete one of the cursor's children and all its descendants.\n\tR: Move the cursor to the root of the tree." +
        "\n\tQ: Quit editing and return to main menu.\n");
    }
}
