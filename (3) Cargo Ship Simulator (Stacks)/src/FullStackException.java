/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * Throws an exception if the stack is full and more are trying to be added.
 */


public class FullStackException extends Exception {
    public FullStackException() {}
    public FullStackException (String message) {
        super(message);
    }
}
