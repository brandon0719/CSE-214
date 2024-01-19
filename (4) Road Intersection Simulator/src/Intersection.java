/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 */
import java.util.Queue;
public class Intersection {
    private TwoWayRoad[] roads;
    private int lightIndex;
    private int countdownTimer;
    public static final int MAX_ROADS = 4;
    /**
     * This is a constructor that intializes the array of two way roads, initializes the number of roads,
     * the light index, and the countdown timer.
     * @param initRoads
     *  The array of roads.
     * @throws
     *  Throws an exception if the array of roads is null. 
     */
    public Intersection(TwoWayRoad[] initRoads) {
        if(initRoads == null) throw new IllegalArgumentException();
        for(int i = 0; i < initRoads.length; i++) {
            if(initRoads[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        if(initRoads.length > MAX_ROADS) throw new IllegalArgumentException();
        roads = initRoads;
        lightIndex = 0;
        countdownTimer = roads[lightIndex].getGreenTime();
    }
    /**
     * Advances the lanes depending on the countdown timer. Countdown timer tracks if the light should be green,
     * left signal, or go to the next road.
     * @return
     *  Returns the vehicles dequeued.
     */
    public Vehicle[] timeStep() {
        Vehicle[] result = new Vehicle[4];          //MAX 4 VEHICLES CAN PASS THROUGH INTERSECTION
        if(countdownTimer > 0) {
            result = roads[lightIndex].proceed(countdownTimer);
            countdownTimer--;
        }
        else if((lightIndex+1) % roads.length == 0) {
            lightIndex = 0;
            countdownTimer = roads[lightIndex].getGreenTime();
            result = roads[lightIndex].proceed(countdownTimer);
            countdownTimer--;
        }
        else {
            lightIndex++;
            countdownTimer = roads[lightIndex].getGreenTime();
            result = roads[lightIndex].proceed(countdownTimer);
            countdownTimer--;
        }
        return result;
    }
    /**
    * This method enqueues a vehicle to the desired lane.
     * @param roadIndex
     *  The index of the road.
     * @param wayIndex
     *  Forward or Backward.
     * @param laneIndex
     *  Left, Middle, or RIght lane index.
     * @param vehicle
     *  The vehicle being added to the road.
     */
    public void enqueueVehicle(int roadIndex, int wayIndex, int laneIndex, Vehicle vehicle) {
        if(vehicle == null) throw new IllegalArgumentException("Invalid vehicle");
        if((roadIndex < 0 || roadIndex > roads.length) || 
        (wayIndex < 0 || wayIndex > TwoWayRoad.NUM_WAYS) ||
        (laneIndex < 0 || laneIndex > TwoWayRoad.NUM_LANES)) 
        {throw new IllegalArgumentException("Invalid road index, way index, or lane index");};
        roads[roadIndex].enqueueVehicle(wayIndex, laneIndex, vehicle);
    }

    /**
     * Returns a current visual representation of the lanes.
     */
    public void display() {
        System.out.println("\n################################################################################\n");
        System.out.println("Time step: " + IntersectionSimulator.timeStepCount);
        System.out.println("\n\tGreen light for " + roads[lightIndex].getName() + ".");
        System.out.println("\tTimer = " + (countdownTimer+1) + "\n");
        if(IntersectionSimulator.timeStepCount > IntersectionSimulator.simulationTime) {
            System.out.println("\tCars no longer arriving.");
        }
        System.out.println("\n\tARRIVING CARS:");
        for(int i = 0; i < IntersectionSimulator.numVehiclesAdded; i++) {
            System.out.printf("\t    Car[%03d]%8s %s, going %s in %s lane.\n", IntersectionSimulator.enqueuedVehicles[i].getSerialId(),
            " entered",IntersectionSimulator.roadOn[i], IntersectionSimulator.backOrForward[i], IntersectionSimulator.lane[i]);
        }
        System.out.println("\n");
        System.out.println("\n\tPASSING CARS:");
        for(int i = 0; i < IntersectionSimulator.sizeOfVehicleArray(IntersectionSimulator.vehiclesDequeued); i++) {
            int waitTime = (IntersectionSimulator.timeStepCount - IntersectionSimulator.vehiclesDequeued[i].getTimeArrived());
            System.out.printf("\t    Car[%03d]%s%d\n", IntersectionSimulator.vehiclesDequeued[i].getSerialId(), 
            " passes through. Wait time of ", waitTime);
        }
        System.out.println("\n");       //SKIPS A LINE
        //DISPLAY LANES
        for(int i = 0; i < roads.length; i++) {
            System.out.printf("\n\t%s:\n", roads[i].getName()); 
            System.out.println("                               FORWARD               BACKWARD ");
            System.out.println("\t==============================               ===============================");
            String forwardLeft, forwardMiddle, forwardRight, backwardLeft, backwardMiddle, backwardRight;          //GO or X
            forwardLeft = "  ";
            forwardMiddle = "  ";
            forwardRight = "  ";
            backwardLeft = "  ";
            backwardMiddle = "  ";
            backwardRight = "  ";
            if(i != lightIndex) {
                forwardLeft = " x";
                forwardMiddle = " x";
                forwardRight = " x";
                backwardLeft = "x ";
                backwardMiddle = "x ";
                backwardRight = "x ";
            }
            else if(countdownTimer > roads[lightIndex].getLeftSignalGreenTime()) {
                forwardMiddle = " x";
                forwardRight = " x";
                backwardMiddle = "x ";
                backwardRight = "x ";
            }
            else if(countdownTimer <= roads[lightIndex].getLeftSignalGreenTime()) {
                forwardLeft = " x";
                backwardLeft = "x ";
            }
            System.out.println(String.format("%38s%4s%s   %s%-4s%s",
            printForwardLane(roads[i], TwoWayRoad.FORWARD_WAY, TwoWayRoad.LEFT_LANE)," [L]", forwardLeft, backwardRight, "[R]",
            printBackwardLane(roads[i], TwoWayRoad.BACKWARD_WAY, TwoWayRoad.RIGHT_LANE)));
            System.out.println("\t------------------------------               -------------------------------");
            System.out.println(String.format("%38s%4s%s   %s%-4s%s",
            printForwardLane(roads[i], TwoWayRoad.FORWARD_WAY, TwoWayRoad.MIDDLE_LANE)," [M]", forwardMiddle, backwardMiddle, "[M]",
            printBackwardLane(roads[i], TwoWayRoad.BACKWARD_WAY, TwoWayRoad.MIDDLE_LANE)));
            System.out.println("\t------------------------------               -------------------------------");
            System.out.println(String.format("%38s%4s%s   %s%-4s%s",
            printForwardLane(roads[i], TwoWayRoad.FORWARD_WAY, TwoWayRoad.RIGHT_LANE)," [R]", forwardRight, backwardLeft, "[L]",
            printBackwardLane(roads[i], TwoWayRoad.BACKWARD_WAY, TwoWayRoad.LEFT_LANE)));
            System.out.println("\t==============================               ===============================");
        }
        System.out.println("\n\n\tSTATISTICS: ");
        System.out.printf("\t    %-25s%s\n", "Cars currently waiting:", numOfCars() + " cars");
        System.out.printf("\t    %-25s%s\n", "Total cars passed:", IntersectionSimulator.numCarsPassedThrough + " cars");
        System.out.printf("\t    %-25s%s\n","Total wait time:", IntersectionSimulator.totalWaitTime + " turns");
        System.out.printf("\t    %-25s%-5.2f%s\n", "Average wait time", 
        ((float) IntersectionSimulator.totalWaitTime / (float) IntersectionSimulator.numCarsPassedThrough), " turns");
    }
    /**
     * Prints the forward lane of the desired road, depending on the way index, and lane index.
     * @param road
     *  The road that is being printed.
     * @param way
     *  The index deciding if it should be forward or backward.
     * @param lane
     *  The index deciding if it should be left, right, or middle lane.
     * @return
     *  Returns the string of the lanes.
     */
    public String printForwardLane(TwoWayRoad road, int way, int lane) {
        String str = "";
        Queue<Vehicle> copy = road.getLane(way, lane).cloneQueue();
        for(int i = 0; i < road.getLane(way, lane).size(); i++) {        //FOR how many cars are in the lane
            str = "[" + String.format("%03d", copy.remove().getSerialId()) + "]" + str; // + str to keep order of a queue
        }
        return str;
    }
    /**
     * Prints the forward lane of the desired road, depending on the way index, and lane index.
     * @param road
     *  The road that is being printed.
     * @param way
     *  The index deciding if it should be forward or backward.
     * @param lane
     *  The index deciding if it should be left, right, or middle lane.
     * @return
     *  Returns the string of the lanes.
     */
    public String printBackwardLane(TwoWayRoad road, int way, int lane) {
        String str = "";
        Queue<Vehicle> copy = road.getLane(way, lane).cloneQueue();
        for(int i = 0; i < road.getLane(way, lane).size(); i++) {
            str = str + "[" + String.format("%03d", copy.remove().getSerialId()) + "]";
        }
        return str;
    }
    /**
     * @return
     *  Returns true or false if the intersection is empty.
     */
    public boolean isIntersectionEmpty() {
        boolean ans = true;
        for(int i = 0; i < roads.length; i++) {
            for(int j = 0; j < TwoWayRoad.NUM_WAYS; j++) {
                for(int k = 0; k < TwoWayRoad.NUM_LANES; k++) {
                    if(!(roads[i].isLaneEmpty(j, k)))
                        ans = false;
                }
            }
        }
        return ans;
    }
    /**
     * @return
     *  Returns the number of cars in the intersection.
     */
    public int numOfCars() {
        int numOfCars = 0;
        for(int i = 0; i < roads.length; i++) {
            for(int j = 0; j < TwoWayRoad.NUM_WAYS; j++) {
                for(int k = 0; k < TwoWayRoad.NUM_LANES; k++) {
                    if(!(roads[i].isLaneEmpty(j, k)))
                        numOfCars++;
                }
            }
        }
        return numOfCars;
    }
 }
