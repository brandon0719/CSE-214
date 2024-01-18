/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 */
public class TwoWayRoad {
    public static final int FORWARD_WAY = 0;
    public static final int BACKWARD_WAY = 1;
    public final static int NUM_WAYS = 2;
    public static final int LEFT_LANE = 0;
    public static final int MIDDLE_LANE = 1;
    public static final int RIGHT_LANE = 2;
    public final static int NUM_LANES = 3;

    private String name = null;
    private int greenTime = 0;
    private int leftSignalGreenTime = 0;
    private VehicleQueue lanes[][];
    private LightValue lightValue;

    /**
     * Constructor intializes the name of the road, the green time of the road, the left signal 
     * time of the road, the current light value of the road, and intializes the VehicleQueue array.
     * @param initName
     *  The name of the road.
     * @param initGreenTime
     *  The green time of the road.
     */
    public TwoWayRoad(String initName, int initGreenTime) {
        if(initName == null) throw new IllegalArgumentException("Name can not be null.");
        if(initGreenTime <= 0) throw new IllegalArgumentException("Time can not be less than or equal to 0.");
        name = initName;
        greenTime = initGreenTime;
        leftSignalGreenTime = (int) Math.floor(1.0 / NUM_LANES * initGreenTime);
        lanes = new VehicleQueue[NUM_WAYS][NUM_LANES];
        for(int i = 0; i < NUM_WAYS; i++) {                                     //for each lane, creates a Vehicle Queue in each lane
            for(int j = 0; j < NUM_LANES; j++) {
                lanes[i][j] = new VehicleQueue();
            }
        }
        lightValue = LightValue.RED;                                            //red by default
    }
    /**
     * @return
     *  Returns the current light value of the road.
     */
    public LightValue getLightValue() {
        return lightValue;
    }
    /**
     * @return
     *  Returns the green time of the road.
     */
    public int getGreenTime() {
        return greenTime;
    }
    /**
     * @return
     *  Returns the name of the road.
     */
    public String getName() {
        return name;
    }
    /**
     * @return
     *  Returns the left signal green time of the road.
     */
    public int getLeftSignalGreenTime() {
        return leftSignalGreenTime;
    }
    /**
     * This method proceeds the lanes depending on the lightValue.
     * @param timerVal
     *  The timer counting the green time.
     * @return
     *  Returns an array of the vehicles dequeued.
     */
    public Vehicle[] proceed(int timerVal) {
        if(timerVal <= 0) throw new IllegalArgumentException("Time can not be less than or equal to 0.");
        Vehicle[] returnArr = new Vehicle[4];   //MAX 4 VEHICLES CAN PASS THROUGH INTERSECTION
        int k = 0;
        /** 
        for(int i = 0; i < NUM_WAYS; i++) {                         //If there are no vehicles left, light set to RED
            for(int j = 0; j < NUM_LANES; j++) {
                if(!(lanes[i][j].isEmpty())) {
                    lightValue = LightValue.GREEN;
                    break;
                }
                else
                    lightValue = LightValue.RED;
            }
        }
        */
        if(timerVal > leftSignalGreenTime){         //Dequeues middle and right lane
            lightValue = LightValue.GREEN;
            for(int i = FORWARD_WAY; i <= BACKWARD_WAY; i++) {                                        //FORWARD AND BACKWARD
                for(int j = MIDDLE_LANE; j <= RIGHT_LANE; j++) {
                    if(!(lanes[i][j].isEmpty())) {
                        returnArr[k] = lanes[i][j].dequeue();
                        k++;
                    }
                }
            }                      
        }
        if(timerVal <= leftSignalGreenTime) {                           //Dequeues left lane
            lightValue = LightValue.LEFT_SIGNAL;
            if(!(lanes[FORWARD_WAY][LEFT_LANE].isEmpty())) {
                returnArr[k] = lanes[FORWARD_WAY][LEFT_LANE].dequeue();
                k++;
            }
            if(!(lanes[BACKWARD_WAY][LEFT_LANE].isEmpty())) {
                returnArr[k] = lanes[BACKWARD_WAY][LEFT_LANE].dequeue();
                k++;
            }
        }
        if(timerVal == 1)
            lightValue = LightValue.RED;
        return returnArr;
    }
    /**
     * This method enqueues a vehicle to the desired lane.
     * @param wayIndex
     *  Forward or Backward index.
     * @param laneIndex
     *  Left, Middle, or Right lane idnex.
     * @param vehicle
     *  The vehicle being added.
     */
    public void enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle) {
        if(vehicle == null) throw new IllegalArgumentException();
        if(wayIndex > 1 || wayIndex < 0 || laneIndex < 0 || laneIndex > 2) throw new IllegalArgumentException();
        lanes[wayIndex][laneIndex].enqueue(vehicle);
    }
    /**
     * Checks if the inputted lane is empty.
     * @param wayIndex
     *  Forward or Backward index.
     * @param laneIndex
     *  Left, Middle, or Right lane idnex.
     * @return
     *  Returns True or False.
     */
    public boolean isLaneEmpty(int wayIndex, int laneIndex) {
        if(wayIndex > 1 || wayIndex < 0 || laneIndex < 0 || laneIndex > 2) throw new IllegalArgumentException();
        return lanes[wayIndex][laneIndex].isEmpty();
    }
    /**
     * References the lane that is desired.
     * @param way
     *  Forward or Backward index.
     * @param lane
     *  Left, Middle, or Right lane idnex.
     * @return
     *  Reutrns the reference to the desired lane.
     */
    public VehicleQueue getLane(int way, int lane) {
        return lanes[way][lane];
    }
}
