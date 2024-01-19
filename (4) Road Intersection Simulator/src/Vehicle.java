/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * This class is the class that creates a vehicle and documents the time the vehicle was arrived.
 */
public class Vehicle {
    private static int serialCounter = 0;
    private int serialId;
    private int timeArrived;

    /**
     * Constructor that intializes the vehicle. It sets the time arrived to what is inputted 
     * and increases the serial counter by 1. It also sets the serial number of the car.
     * @param initTimeArrived
     *  Time the vehicle arrived on the road.
     * @throws IllegalArgumentException
     *  Throws an exception if time arrived is <= 0.
     */
    public Vehicle(int initTimeArrived) throws IllegalArgumentException {
        if(initTimeArrived <= 0) throw new IllegalArgumentException("Invalid time value.");
        this.timeArrived = initTimeArrived;
        this.serialId = ++serialCounter;
    }
    /**
     * Returns how many vehicles have arrived on the road.
     * @return
     *  returns serial counter.
     */
    public int getSerialCounter() {
        return serialCounter;
    }
    /**
     * @return
     *  Returns the serial id.
     */
    public int getSerialId() {
        return this.serialId;
    }
    /**
     * @return
     *  Returns the time the vehicle arrived.
     */
    public int getTimeArrived() {
        return this.timeArrived;
    }
}
