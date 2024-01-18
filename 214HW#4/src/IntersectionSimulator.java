/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 */
import java.util.Scanner;
public class IntersectionSimulator {
    private static int numStreets;     //numStreets
    protected static int totalWaitTime = 0;      //total wait time in the simulation
    protected static int numCarsPassedThrough = 0;
    protected static Vehicle[] enqueuedVehicles;         //LIST OF VEHICLES FOR DISPLAY()
    protected static int numVehiclesAdded;              //NUMBER OF VEHICLES FOR DISPLAY()
    protected static String[] roadOn;                //LIST OF THE ROADS THE VEHICLES ARE ON FOR DISPLAY()
    protected static String[] backOrForward;        //FOR DISPLAY
    protected static String[] lane;                 //FOR DISPLAY
    protected static Vehicle[] vehiclesDequeued;        //FOR TIME STEP
    protected static int timeStepCount = 1;         //FOR DISPLAY
    public static final int MAX_ARRIVAL = 6;
    private static int greatestWaitTime = 0;
    protected static int simulationTime;
    public static void main(String[] args) {
        //COMMAND PROMPT (GIVEN)
        if (args.length > 1) {
            int simTime    = Integer.parseInt(args[0]);
            double prob    = Double.parseDouble(args[1]);
            int numRoads   = Integer.parseInt(args[2]);
            String[] names = new String[numRoads];
            int[] times = new int[numRoads];
            for (int i = 0; i < numRoads; ++i) {
                names[i] = args[3 + i];
                times[i] = Integer.parseInt(args[3 + numRoads + i]);
            }      
            // process args in simTime, prob, numRoads, names, times.
            simulate(simTime, prob, names, times);
        }
        //INTERACTIVE
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to InterSectionSimulator 2023 \n");
        System.out.print("Input the simulation time: ");
        simulationTime = nextIntLine();
        System.out.print("Input the arrival probability: ");
        double arrivalProb = sc.nextDouble();
        sc.nextLine();
        try {                                                                                   //NUMBER OF STREETS
            System.out.print("Input number of Streets: ");
            numStreets = nextIntLine();
            if(numStreets > Intersection.MAX_ROADS) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid! Number of streets must be less than 4");
            System.out.print("Input number of Streets: ");
            numStreets = nextIntLine();
        }
        String[] names = new String[numStreets];                     //string of names
        int[] greenTime = new int[numStreets];                    //string of green time
        for(int i = 0; i < numStreets; i++) {
            try {
                System.out.print("Input Street " + (i+1) + " name: ");
                names[i] = sc.nextLine();
                for(int j = 0; j < i; j++) {
                    if(names[i].equals(names[j])) 
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Duplicate detected.");
                System.out.print("Input Street " + (i+1) + " name: ");
                names[i] = sc.nextLine();
            }
        }
        for(int i = 0; i < numStreets; i++) {
            System.out.print("Input max green time for " + names[i] + ": ");
            greenTime[i] = nextIntLine();
        }
        System.out.println("\nStarting Simulation... \n");
        sc.close();
        simulate(simulationTime, arrivalProb, names, greenTime);
    }
    /**
     * Simulates the program.
     * @param simulationTime
     *  The desired simulation time.
     * @param arrivalProbability
     *  The probability of the arrival of cars.
     * @param roadNames
     *  An array of strings of the road names.
     * @param maxGreenTimes
     *  An array of integers of the green times for each road.
     */
    public static void simulate(int simulationTime, double arrivalProbability, String[] roadNames, int[] maxGreenTimes) {
        TwoWayRoad[] roads = new TwoWayRoad[numStreets];                //array of TwoWayRoads
        for(int i = 0; i < numStreets; i++) {                           //filling the array with values
            TwoWayRoad road = new TwoWayRoad(roadNames[i], maxGreenTimes[i]);
            roads[i] = road;
        }
        Intersection intersection = new Intersection(roads);            //intersection of twoWayRoads
        BooleanSourceHW4 source = new BooleanSourceHW4(arrivalProbability);
        //START OF THE SIMULATION CODE
        for(int t = 0; t < simulationTime; t++) {       //CARS STOP COMING AT INPUTTED SIMULATION TIME
            enqueuedVehicles = new Vehicle[MAX_ARRIVAL];            //max num of cars that can be added every timestep is 6.
            lane = new String[MAX_ARRIVAL];                   //FOR DISPLAY
            backOrForward= new String[MAX_ARRIVAL];           //FOR DISPLAY
            roadOn= new String[MAX_ARRIVAL];                  //FOR DISPLAY
            numVehiclesAdded = 0;
            for(int i = 0; i < numStreets; i++) {         //for every street in the intersection
                for(int j = 0; j < TwoWayRoad.NUM_WAYS; j++) {              //for every WAY (forward or backward) in the road
                    for(int k = 0; k < TwoWayRoad.NUM_LANES; k++) {         //for every LANE in the street
                        if(numVehiclesAdded == MAX_ARRIVAL) {
                            break;
                        }
                        else if(source.occursHW4()) {                  //enqueue
                            Vehicle vehicle = new Vehicle(timeStepCount);
                            intersection.enqueueVehicle(i, j, k, vehicle);
                            enqueuedVehicles[numVehiclesAdded] = vehicle;   //FOR DISPLAY();
                            roadOn[numVehiclesAdded] = roads[i].getName();   //FOR DISPLAY()
                            //TO FIND IF GOING BACKWARDS OR FORWARD
                            if(j == 0)
                                backOrForward[numVehiclesAdded] = "FORWARD";
                            else if(j == 1)
                                backOrForward[numVehiclesAdded] = "BACKWARD";
                            //TO FIND IF IN LEFT RIGHT OR MIDDLE LANE
                            if(k == 0)
                                lane[numVehiclesAdded] = "LEFT";
                            else if(k == 1)
                                lane[numVehiclesAdded] = "MIDDLE";
                            else if(k == 2)
                                lane[numVehiclesAdded] = "RIGHT";
                            numVehiclesAdded+=1;                             //The num of vehicles added for each time step; FOR DISPLAY()
                        }//else no vehicle arrived
                    }
                }
            }
            //dequeue vehicles through intersection
            vehiclesDequeued = intersection.timeStep();
            for(int i = 0; i < sizeOfVehicleArray(vehiclesDequeued); i++) {
                totalWaitTime += timeStepCount - vehiclesDequeued[i].getTimeArrived();     //Wait time
                numCarsPassedThrough++;
                if(greatestWaitTime < timeStepCount - vehiclesDequeued[i].getTimeArrived())
                    greatestWaitTime = timeStepCount - vehiclesDequeued[i].getTimeArrived();
            }
            intersection.display();
            timeStepCount++;
        }
        //IF THERE ARE STILL CARS ON THE STREET AFTER SIMULATION TIME - 
        while(!(intersection.isIntersectionEmpty())) {
            numVehiclesAdded = 0;
            vehiclesDequeued = intersection.timeStep();       //dequeue
            for(int i = 0; i < sizeOfVehicleArray(vehiclesDequeued); i++) {
                totalWaitTime += timeStepCount - vehiclesDequeued[i].getTimeArrived();
                numCarsPassedThrough++;
                if(greatestWaitTime < timeStepCount - vehiclesDequeued[i].getTimeArrived())
                    greatestWaitTime = timeStepCount - vehiclesDequeued[i].getTimeArrived();
            }
            intersection.display();
            timeStepCount++;
        }
        System.out.println("\n\n################################################################################\n"+
        "################################################################################\n" + 
        "################################################################################\n" + "\nSIMULATION SUMMARY:\n");
        System.out.printf("    %-23s%s\n", "Total Time:", (timeStepCount-1) + " steps");
        System.out.printf("    %-23s%s\n", "Total vehicles:", numCarsPassedThrough + " cars");
        System.out.printf("    %-23s%s\n", "Longest wait time:", greatestWaitTime + " turns");
        System.out.printf("    %-23s%s\n","Total wait time:", totalWaitTime + " turns");
        System.out.printf("    %-23s%-5.2f%s\n", "Average wait time", 
        ((float) totalWaitTime / (float) numCarsPassedThrough), " turns");
        System.out.println("\nEnd simulation.\n");
    }
    public static int nextIntLine() {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();  //input declared at the top of the program as a static variable
        sc.nextLine();
        return number;
    }
    /**
     * Returns the size of the vehicle array.
     * @param arr
     *  The vehicle array.
     * @return
     *  The size of the inputted vehicle array.
     */
    public static int sizeOfVehicleArray(Vehicle[] arr){
        int size = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != null)
                size++;
        } 
        return size;
    }
}
