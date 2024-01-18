/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#6
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Interactive main class that lets you add, remove, and save the storage table.
 */
import java.util.Scanner;
import java.io.*;
public class StorageManager {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        System.out.print("Hello, and welcome to Rocky Stream Storage Manager ");
        String choice = "";
        boolean notQuit = true;
        StorageTable storageTable;
        File theFile = new File("storage.obj");
        if(theFile.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(theFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                storageTable = (StorageTable) objectInputStream.readObject();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                storageTable = new StorageTable();
            }
        } else {
            storageTable = new StorageTable();
        }
        while(notQuit) {
            int id;
            String client;
            String contents;
            printMenu();
            System.out.print("Please select an option: ");
            choice = sc.nextLine().toUpperCase();
            switch(choice) {
                case "P":
                    if(storageTable.getStorageTable().size() == 0) {
                        System.out.println("\nThere are is no storage.");
                        break;
                    }
                    System.out.printf("\n%-14s%-31s%-6s", "Box#", "Contents", "Owner");
                    System.out.println("\n----------------------------------------------------------------");
                    storageTable.printTable();
                    break;
                case "A":
                    System.out.print("Please enter an id: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Please enter client: ");
                    client = sc.nextLine();
                    System.out.print("Please Enter Contents: ");
                    contents = sc.nextLine();
                    Storage box = new Storage(id,client,contents);
                    storageTable.putStorage(id, box);
                    System.out.println("\nStorage " + id + " set!");
                    break;
                case "R":
                    System.out.println("Please enter ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    if(storageTable.getStorage(id) != null) {
                        storageTable.getStorageTable().remove(id);
                        System.out.println("Box" + id + " is now removed.");
                    }
                    else {
                        System.out.println("Storage ID does not exist.");
                    }
                    break;
                case "C":
                    System.out.print("Please enter the name of the client: ");
                    client = sc.nextLine();
                    System.out.printf("\n%-14s%-31s%-6s", "Box#", "Contents", "Owner");
                    System.out.println("\n----------------------------------------------------------------");
                    storageTable.printTable(client);
                    break;
                case "F":
                    System.out.print("Please enter ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    if(storageTable.getStorage(id) == null) {
                        System.out.println("There is no box at the given ID.");
                        break;
                    }
                    else {
                        System.out.println("Box " + id);
                        System.out.println("Contents: " + storageTable.getStorage(id).getContents());
                        System.out.println("Owner: " + storageTable.getStorage(id).getClient());
                    }
                    break;
                case "Q":
                    notQuit = false;
                    FileOutputStream file = new FileOutputStream("storage.obj");
                    ObjectOutputStream outStream = new ObjectOutputStream(file);
                    // the following line will save the object in the file
                    outStream.writeObject(storageTable);
                    outStream.close();
                    System.out.println("Storage Manager is quitting, current storage is saved for next session.");
                    break;
                case "X":
                    notQuit = false;
                    FileOutputStream fileClear = new FileOutputStream("storage.obj");
                    ObjectOutputStream outStreamClear = new ObjectOutputStream(fileClear);
                    // the following line will save the object in the file
                    StorageTable newTable = new StorageTable();             //new table
                    outStreamClear.writeObject(newTable);                   //file becomes empty table
                    outStreamClear.close();
                    System.out.println("Storage Manager is quitting, all data is being erased.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
    /**
     * Prints the menu of the options.
     */
    public static void printMenu() {
        System.out.println("\n\nP - Print all storage boxes\nA - Insert into storage box\n" +
        "R - Remove contents from a storage box\nC - Select all boxes owned by a particular client\n" +
        "F - Find a box by ID and display its owner and contents\nQ - Quit and save workspace \n" +
        "X - Quit and delete workspace\n");
    }
}
