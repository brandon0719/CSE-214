/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * This class is the Vehicle queue used in each lane of the TwoWayRoad.
 */
import java.util.LinkedList;
import java.util.Queue;
public class VehicleQueue {
    private Queue<Vehicle> queue;
    private int size = 0;
    /**
     * Constructor that intializes the vehicle queue.
     */
    public VehicleQueue() {
        queue = new LinkedList<>();
    }
    /**
     * @return
     *  Returns if the queue is empty.
     */
    public boolean isEmpty() {
        return queue.size() == 0;
    }
    /**
     * Adds a vehicle to the queue.
     * @param v
     *  The vehicle that is being added.
     */
    public void enqueue(Vehicle v) {
        queue.add(v);
        size++;
    }
    /**
     * Removes the vehicle that was first in. (Queues follow FIFO)
     * @return
     *  The removed vehicle.
     */
    public Vehicle dequeue(){
        Vehicle removed = queue.remove();
        size--;
        return removed;
    }
    /**
     * @return
     *  The size of the queue.
     */
    public int size() {
        return size;
    }
    /**
     * @return
     *  A clone of the queue. 
     */
    public Queue<Vehicle> cloneQueue() {
        Queue<Vehicle> temp = new LinkedList<>();
        for(Vehicle q: queue) {
            temp.add((q));
        }
        return temp;
    }
}
