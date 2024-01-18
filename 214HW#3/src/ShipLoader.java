import java.util.Scanner;
public class ShipLoader {
    private static Scanner sc = new Scanner(System.in);
    static int numStacks;
    static int maxHeight;
    static int maxWeight;
    static CargoShip cargoShip;
    static CargoStack dock;
    static CargoStrength fragile = CargoStrength.F;
    static CargoStrength moderate = CargoStrength.M;
    static CargoStrength sturdy = CargoStrength.S;
    public static void main(String[] args) {
        String choice = "";
        System.out.println("Welcome to ShipLoader!\n\n" + 
        "Cargo Ship Parameters\n" + 
        "--------------------------------------------------");
        System.out.print("Number of stacks: ");
        numStacks = nextIntLine();
        System.out.print("Maximum height of stacks: ");
        maxHeight = nextIntLine();
        System.out.print("Maximum total cargo weight: ");
        maxWeight = nextIntLine();
        cargoShip = new CargoShip(numStacks,maxHeight,maxWeight);     //create cargoShip
        dock = new CargoStack();                                     //the dock
        System.out.println("\nCargo ship created.\n" +
        "Pulling ship in to dock...\n" + "Cargo ship ready to be loaded.\n");
        while(!(choice.equalsIgnoreCase("Q"))) {
            printMenu();
            choice = sc.nextLine().toUpperCase();
            switch(choice) {
                case "C":
                    try {
                        Cargo newCargo;
                        System.out.print("Enter the name of the cargo: ");
                        String cargoName = sc.nextLine();
                        System.out.print("Enter the weight of the cargo: ");
                        int cargoWeight = nextIntLine();
                        System.out.print("Enter the container strength (F/M/S): ");
                        String cargoStrength = sc.nextLine();
                        if(cargoStrength.equals("F"))
                            newCargo = new Cargo(cargoName,cargoWeight, CargoStrength.F);
                        else if(cargoStrength.equals("M"))
                            newCargo = new Cargo(cargoName, cargoWeight, CargoStrength.M);
                        else if(cargoStrength.equals("S"))
                            newCargo = new Cargo(cargoName, cargoWeight, CargoStrength.S);
                        else 
                            throw new IllegalArgumentException();
                        if(dock.size() > 0) {
                            if(cargoStrength.equals("S") && (dock.peek().getStrength() == fragile
                            || dock.peek().getStrength() == moderate))
                                System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                            else if(cargoStrength.equals("M") && (dock.peek().getStrength() == fragile))
                                System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                            else
                                dock.push(newCargo);
                        }
                        else
                            dock.push(newCargo);
                        if(dock.peek() == newCargo) {
                            System.out.println("\nCargo \'" + cargoName + "\' pushed onto the dock.\n");
                            fullStackOverview();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Choose between F/M/S");
                    } catch (EmptyStackException e) {       // for line 50
                    }
                    break;
                case "L":
                    Cargo dockVal = null;
                    try {
                        System.out.print("\nSelect the load destination stack index: ");
                        int stackIndex = nextIntLine();
                        if(dock.size() > 0) dockVal = dock.peek();                          //for the print statement at the end
                        if(cargoShip.getCargoStack(stackIndex).size() > 0) {
                            CargoStack temp = cargoShip.getCargoStack(stackIndex);
                            if(dock.peek().getStrength() == sturdy && (temp.peek().getStrength() == fragile
                            || temp.peek().getStrength() == moderate))
                                System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                            else if(dock.peek().getStrength() == moderate && (temp.peek().getStrength() == fragile))
                                System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                            else
                                cargoShip.pushCargo(dock.pop(), stackIndex);
                        }
                        else
                            cargoShip.pushCargo(dock.pop(), stackIndex);
                        if(cargoShip.getCargoStack(stackIndex).peek() == dockVal) {
                            System.out.println("\nCargo \'" + cargoShip.getCargoStack(stackIndex).peek().getName()+
                            "\' moved from dock to stack " + stackIndex + ".\n");
                            fullStackOverview();
                        }
                    } catch (EmptyStackException e) {
                        System.out.println("\nOperation failed! Dock is null.");
                    } 
                    catch (FullStackException e) {
                        System.out.println("\nOperation failed! Cargo stack is at maximum height.");
                        dock.push(dockVal);
                    } catch (ShipOverweightException e) {
                        System.out.println("\nOperation failed! Cargo would put ship overweight.");
                        dock.push(dockVal);
                    } catch (CargoStrengthException e) {                        //covered
                    } 
                    break;
                case "U":
                    try {
                        System.out.print("\nSelect the unload source stack index: ");
                        int stackIndex = nextIntLine();
                        Cargo original = null;
                        CargoStack source = cargoShip.getCargoStack(stackIndex);
                        if(source.size() > 0) original = source.peek();
                        if(source.size() > 0 && dock.size() > 0) {
                            if(source.peek().getStrength() == sturdy && (dock.peek().getStrength() == fragile
                            || dock.peek().getStrength() == moderate))
                                System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                            else if(source.peek().getStrength() == moderate && (dock.peek().getStrength() == fragile))
                                System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                            else
                                dock.push(cargoShip.popCargo(stackIndex));
                        }
                        else {
                            dock.push(cargoShip.popCargo(stackIndex));
                        }
                        if(dock.peek() == original) {
                            System.out.println("\nCargo \'" + original.getName() + "\' moved from stack " + stackIndex + " to dock.\n");
                            fullStackOverview();
                        }
                    } catch (EmptyStackException e) {
                        System.out.println("The cargo stack is empty!");
                    }
                    break;
                case "M":
                Cargo original = null;
                CargoStack source = null;
                    try {
                        System.out.print("Source stack index: ");
                        int sourceIndex = nextIntLine();
                        System.out.print("Destination stack index: ");
                        int destinationIndex = nextIntLine();
                        source = cargoShip.getCargoStack(sourceIndex);
                        CargoStack destination = cargoShip.getCargoStack(destinationIndex);
                        if(source.size() > 0) original = source.peek();
                        if(cargoShip.getCargoStack(sourceIndex).size() > 0 && cargoShip.getCargoStack(destinationIndex).size() > 0) {
                            if(source.peek().getStrength() == sturdy && (destination.peek().getStrength() == fragile
                            || destination.peek().getStrength() == moderate))
                                System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                            else if(source.peek().getStrength() == moderate && (destination.peek().getStrength() == fragile))
                                System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.");
                            else {
                                cargoShip.pushCargo(cargoShip.getCargoStack(sourceIndex).pop(), destinationIndex);
                                cargoShip.currentWeight -= cargoShip.getCargoStack(destinationIndex).peek().getWeight();    //readjust weight
                            }
                        }
                        else {
                            cargoShip.pushCargo(cargoShip.getCargoStack(sourceIndex).pop(), destinationIndex);
                            cargoShip.currentWeight -= cargoShip.getCargoStack(destinationIndex).peek().getWeight();        //readjust weight
                        }
                        if(destination.peek() == original) {
                            System.out.println("\nCargo \'" + original.getName()+"\' moved from stack " + sourceIndex + " to stack " + destinationIndex + ".\n");
                            fullStackOverview();
                        }
                    } catch (FullStackException e) {
                        System.out.println("\nOperation failed! Cargo stack is at maximum height.");
                        source.push(original);
                    } catch (EmptyStackException e) {
                        System.out.println("\nOperation failed! The stack is empty");
                    } catch (ShipOverweightException e) {
                        System.out.println("\nOperation failed! Cargo would put ship overweight weight.");
                        source.push(original);
                    } catch (CargoStrengthException e) {    //covered
                    } 
                    break;
                case "K":
                    boolean initiallyEmpty = dock.isEmpty();
                    while(!(dock.isEmpty())) {
                        try {
                            dock.pop();
                        } catch (EmptyStackException e) {
                            System.out.println("\nOperation failed! The stack is empty");
                        }
                    }
                    if(!(initiallyEmpty)) {
                        System.out.println("\nDock cleared.\n");
                        fullStackOverview();
                    }
                    else
                        System.out.println("\nOperation failed! The stack is empty");
                    break;
                case "P":
                    fullStackOverview();
                    break;
                case "S":
                    System.out.print("Enter the name of the cargo: ");
                    String name = sc.nextLine();
                    cargoShip.findAndPrint(name);
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
    public static void printMenu() {
        System.out.println("\nPlease select an option:\nC) Create new cargo\n"
        + "L) Load Cargo from dock\nU) Unload Cargo from dock\nM) Move cargo between stacks" +
         "\nK) Clear dock\nP) Print ship stacks" +
         "\nS) Search for cargo\nQ) Quit\n");
        System.out.print("\nSelect a menu option: ");
    }
    public static void fullStackOverview() {
        cargoShip.stackOverview();
        System.out.print("Dock: ");                 // need to reverse the order of the dock
        dock.displayStackStrength();
        System.out.println("\n\nTotal Weight = " + cargoShip.getCurrentWeight() + " / " + maxWeight + "\n");
    }
    public static void printStrength() {

    }
    public static int nextIntLine() {
        int number = sc.nextInt();  //input declared at the top of the program as a static variable
        sc.nextLine();
        return number;
    }
}
