/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * Throws an exception if something violates the cargo strength rules
 */


public class CargoStrengthException extends Exception {
    public CargoStrengthException() {}
    public CargoStrengthException(String message) {
        super(message);
    }
}
