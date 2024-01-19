/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * Throws an exception if adding cargo would put the weight limit overweight.
 */


public class ShipOverweightException extends Exception{
    public ShipOverweightException() {}
    public ShipOverweightException(String message) {
        super(message);
    }
}
