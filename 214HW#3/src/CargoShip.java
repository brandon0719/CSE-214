public class CargoShip {
    private CargoStack[] stacks;
    private int maxHeight;
    private int maxWeight;
    protected int currentWeight = 0;
    CargoStrength fragile = CargoStrength.F;
    CargoStrength moderate = CargoStrength.M;
    CargoStrength sturdy = CargoStrength.S;
    public CargoShip(int numStacks, int initMaxHeight, int initMaxWeight) throws IllegalArgumentException {
        if(numStacks <= 0) {throw new IllegalArgumentException("Not valid size");}
        if(initMaxHeight <= 0) {throw new IllegalArgumentException("Not a valid height");}
        if(initMaxWeight <= 0) {throw new IllegalArgumentException("Not a valid weight");}
        stacks = new CargoStack[numStacks];
        for(int i = 0; i < numStacks; i++) {
            CargoStack newStacks = new CargoStack();
            stacks[i] = newStacks;
        }
        maxHeight = initMaxHeight;
        maxWeight = initMaxWeight;
    }
    public void pushCargo(Cargo cargo, int stack) throws FullStackException, ShipOverweightException, CargoStrengthException, EmptyStackException {
        if(cargo == null) {throw new IllegalArgumentException("Cargo can not be null");}
        if(stack < 1 || stack > stacks.length) {throw new IllegalArgumentException("Index given by user is out of range");}
        if(stacks[stack-1].size() +1 > this.maxHeight) {throw new FullStackException("Stack size can not exceed max height");}
        if(currentWeight + cargo.getWeight() > maxWeight) {throw new ShipOverweightException("Adding cargo will exceed max weight");}
        if(stacks[stack-1].size() > 1) {checkStrength(cargo, stack);}            //Throws CargoStrengthException
        stacks[stack-1].push(cargo);
        currentWeight += cargo.getWeight();
    }
    public Cargo popCargo(int stack) throws EmptyStackException {
        try {
            if(stacks[stack-1].size() > 0 && (stack > 0 && stack < stacks.length))
                currentWeight -= stacks[stack-1].peek().getWeight();
            return stacks[stack-1].pop();
        } catch (Exception e) {
            if(stack < 1 || stack > stacks.length) {throw new IllegalArgumentException("Index given by user is out of range");}
            if(stacks[stack-1].isEmpty()) {throw new EmptyStackException("The stack is empty");}
            return null;
        }
    }
    public void findAndPrint(String name) {
        double combinedWeight = 0;
        int numFound = 0;
        for(int i = 0; i < stacks.length; i++) {
            CargoStack temp = (CargoStack) stacks[i].clone();
            for(int j = 0; j < temp.size();) {          //doesnt increment because when stack pops, it looses size(index goes down)
                try {
                    Cargo poppedVal = temp.pop();
                    if(poppedVal.getName().toLowerCase().equals(name.toLowerCase())) {
                        if(numFound == 0) {                                             //Prints once
                            System.out.println("\nCargo \'" + name + "\' found at the following locations: ");
                            System.out.printf("\n\n%6s%8s%9s%11s","Stack", "Depth", "Weight", "Strength");
                            System.out.print("\n=======+=======+========+==========");
                        }
                        System.out.printf("\n%4d%4s%4d%4s%6d%3s%8s", i+1, "|", stacks[i].size() -1, "|", poppedVal.getWeight(), "|", poppedVal.printStrength());
                        combinedWeight += poppedVal.getWeight();
                        numFound++;
                    }
                } catch (EmptyStackException e) {
                    System.out.println("Every stack is empty!");
                }
            }
        }
        if(numFound > 0) {
            System.out.println("\n\nTotal Count: " + numFound);
            System.out.println("Total Weight: " + combinedWeight);
        }
        else
            System.out.println("\nCargo \'" + name + "\' could not be found on the ship.\n");
    }
    public int size(int index) {
        return stacks[index-1].size();
    }
    public CargoStack getCargoStack(int index) {
        return stacks[index-1];
    }
    public void stackOverview() {
        for(int i = 1; i < stacks.length + 1; i++) {
            System.out.print("Stack " + i + ": ");
            if(stacks[i-1] != null)                //PROBLEM
                stacks[i-1].displayStackStrength();
            System.out.println();
        }
    }
    public int getCurrentWeight() {
        return currentWeight;
    }
    public void checkStrength(Cargo cargo, int stack) throws CargoStrengthException, EmptyStackException {
        if(stacks[stack-1].peek().getStrength() == fragile && (cargo.getStrength() == moderate || cargo.getStrength() == sturdy)){
            throw new CargoStrengthException();
        }
        else if(stacks[stack-1].peek().getStrength() == moderate && (cargo.getStrength() == sturdy)) {
            throw new CargoStrengthException();
        }
    }
}
