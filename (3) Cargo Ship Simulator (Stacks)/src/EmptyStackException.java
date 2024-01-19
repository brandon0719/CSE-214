/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * Throws an exception if some methods are called on an empty stack
 */


public class EmptyStackException extends Exception{
    public EmptyStackException() {}
    public EmptyStackException(String message) {
        super(message);
    }
}
