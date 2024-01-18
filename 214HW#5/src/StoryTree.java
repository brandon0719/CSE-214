/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#5
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * The tree class.
 */

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
public class StoryTree {
    private StoryTreeNode root;
    private StoryTreeNode cursor;
    private GameState state;
    private static Scanner file;
    /**
     * A constructor that creates a new Story Tree with the default root.
     */
    public StoryTree() {
        root = new StoryTreeNode("root", "root", "Hello, and welcome to Zork!");
        cursor = root;
        state = GameState.GAME_NOT_OVER;
    }
    /**
     * Reads a file and creates a tree. 
     * @param filename
     *  The name of the file.
     * @return
     *  Returns the created tree.
     * @throws TreeFullException
     *  Throws an exception if there are more than 3 children.
     */
    public static StoryTree readTree(String filename) {
        try {
            File theFile = new File(filename);
            file = new Scanner(theFile);
            if(filename.equals(null) || !(file.hasNextLine())) {
                throw new IllegalArgumentException();
            }
            StoryTree story = new StoryTree();
            while(file.hasNextLine()) {
                String[] values = ((file.nextLine()).split(" \\|\\ "));
                if(values.length != 3) {
                    throw new DataFormatException();
                }
                story.cursor = story.root;
                String[] pos = values[0].split("-");
                for(int i = 0; i < pos.length; i++) {
                    if(i != pos.length-1) {
                        if(pos[i].equals("1")) {
                            story.cursor = story.cursor.getLeftChild();
                        }
                        else if(pos[i].equals("2")) {
                            story.cursor = story.cursor.getMiddleChild();
                        }
                        else if(pos[i].equals("3")) {
                            story.cursor = story.cursor.getRightChild();
                        }
                    }
                    else {
                        story.addChild(values[1], values[2]);
                    }
                }
            }
            file.close();
            story.cursor = story.root;
            return story;
        } catch (IllegalArgumentException e) {
            System.out.println("File is empty.");
        } catch (DataFormatException e) {
            System.out.println("Format error.");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
        }
        StoryTree empty = new StoryTree();
        return empty;
    }
    /**
     * Recursive function that saves the read tree to a textfile or creates a new file based on the read tree.
     * @param filename
     * @param tree
     * @throws FileNotFoundException
     */
    public static void saveTree(String filename, StoryTree tree) throws FileNotFoundException {
        if(filename.equals(null) || tree == null) throw new IllegalArgumentException();
        PrintWriter fileOut = new PrintWriter(filename);
        fileOut.write(tree.root.preorder(tree.root));
        fileOut.close();
    }
    public StoryTreeNode getRoot() {
        return root;
    }
    public StoryTreeNode getCursor() {
        return cursor;
    }
    public GameState getGameState() {
        return state;
    }
    public String getCursorPosition() {
        return cursor.getPosition();
    }
    public String getCursorMessage() {
        return cursor.getMessage();
    }
    /**
     * @return
     * Returns the options of the current cursor node based on how many children there are.
     */
    public String[][] getOptions() {
        String[][] options = new String[3][2];
        for(int i = 0; i < getNumChildren(); i++) {
            if(i == 0) {
                options[i][0] = cursor.getLeftChild().getPosition();
                options[i][1] = cursor.getLeftChild().getOption();
            }
            else if(i == 1) {
                options[i][0] = cursor.getMiddleChild().getPosition();
                options[i][1] = cursor.getMiddleChild().getOption();
            }
            else if(i == 2) {
                options[i][0] = cursor.getRightChild().getPosition();
                options[i][1] = cursor.getRightChild().getOption();
            }
        }
        return options;
    }
    public void setCursorMessage(String message) {
        cursor.setMessage(message);
    }
    public void setCursorOption(String option) {
        cursor.setOption(option);
    }
    public void setCursor(StoryTreeNode newCursor) {
        this.cursor = newCursor;
    }
    public void setGameState(GameState state) {
        this.state = state;
    }
    /**
     * Resets the cursor, and brings it to the root of the tree.
     */
    public void resetCursor() {
        if(root.getLeftChild() != null)
            cursor = root.getLeftChild();   //BRINGS TO POSITION 1 (WHICH IS TECHNICALLY ROOT)
        else
            cursor = root;          //IF THE TEXT FILE IS EMPTY
    }
    /**
     * Selects a child from the cursor.
     * @param position
     *  The position of the child that is being selected.
     */
    public void selectChild(String position) {
        try {
            if(position == null) throw new IllegalArgumentException();
            if(position.substring(0, position.length()-2).equals(cursor.getPosition())) {
                if(position.charAt((position.length() - 1)) == '1') {
                    if(cursor.getLeftChild() == null) throw new NodeNotPresentException();
                    cursor = cursor.getLeftChild();
                }
                else if(position.charAt(position.length()-1) == '2') {
                    if(cursor.getMiddleChild() == null) throw new NodeNotPresentException();
                    cursor = cursor.getMiddleChild();
                }
                else {
                    if(cursor.getRightChild() == null) throw new NodeNotPresentException();
                    cursor = cursor.getRightChild();
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Position can not be empty.");
        } catch (NodeNotPresentException e) {
            System.out.println("Error. No child " + position.charAt(position.length()-1)+ " for the current node.");
        }
    }
    /**
     * Adds a child to the tree at the given cursor.
     * @param option
     *  The option message of the new child.
     * @param message
     *  The message of the new child.
     * @throws TreeFullException
     *  Throws an exception if there are already 3 children.
     */
    public void addChild(String option, String message) {
        if(option == null || message == null) throw new IllegalArgumentException();
        if(cursor.getLeftChild() != null && cursor.getMiddleChild() != null && cursor.getRightChild() != null) {
            try {
                throw new TreeFullException();
            } catch (TreeFullException e) {
                System.out.println("Error");
            }
        }
        if(cursor == root) {    //BASE first node
            StoryTreeNode newNode = new StoryTreeNode("1", option, message);
            cursor.setLeftChild(newNode);
        }
        else if(cursor.getLeftChild() == null) {
            StoryTreeNode newNode = new StoryTreeNode(cursor.getPosition() + "-1", option, message);
            cursor.setLeftChild(newNode);
        }
        else if(cursor.getMiddleChild() == null) {
            StoryTreeNode newNode = new StoryTreeNode(cursor.getPosition() + "-2", option, message);
            cursor.setMiddleChild(newNode);
        }
        else if(cursor.getRightChild() == null) {
            StoryTreeNode newNode = new StoryTreeNode(cursor.getPosition() + "-3", option, message);
            cursor.setRightChild(newNode);
        }
    }
    /**
     * Removes a child at the given position.
     * @param position
     *  The position of the child that is going to be removed.
     * @return
     *  Returns a references to the child that was removed.
     */
    public StoryTreeNode removeChild(String position) {
        try {
            StoryTreeNode temp = null;
            if(position == null) throw new IllegalArgumentException();
            if(position.substring(0,position.length()-2).equals(cursor.getPosition())) {
                if(position.charAt((position.length() - 1)) == '1') {
                    if(cursor.getLeftChild() == null) throw new NodeNotPresentException();
                    temp = cursor.getLeftChild();
                    cursor.setLeftChild(null);
                }
                else if(position.charAt(position.length()-1) == '2') {
                    if(cursor.getMiddleChild() == null) throw new NodeNotPresentException();
                    temp = cursor.getMiddleChild();
                    cursor.setMiddleChild(null);
                }
                else {
                    if(cursor.getRightChild() == null) throw new NodeNotPresentException();
                    temp = cursor.getRightChild();
                    cursor.setRightChild(null);
                }
                return temp;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Position can not be empty.");
        } catch (NodeNotPresentException e) {
            System.out.println("Error. No child " + position.charAt(position.length()-1)+ " for the current node.");
        }
        return null;
    }
    /**
     * @return
     *  Returns the number of children that the cursor has.
     */
    public int getNumChildren() {
        int numChildren = 0;
        if(cursor.getLeftChild() != null) {
            numChildren++;
        }
        if(cursor.getMiddleChild() != null) {
            numChildren++;
        }
        if(cursor.getRightChild() != null) {
            numChildren++;
        }
        return numChildren;
    }
} 
