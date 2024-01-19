/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#2
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * This exception throws when it is at the end of the list.
 */
public class EndOfListException extends Exception {
    public EndOfListException () {}
    public EndOfListException(String message) {
        super(message);
    }
}
