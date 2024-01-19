/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Throws an exception if the tree is full.
 */
public class TreeFullException extends Exception{
    public TreeFullException() {}
    public TreeFullException(String message) {
        super(message);
    }
}
