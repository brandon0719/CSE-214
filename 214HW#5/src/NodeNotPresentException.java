/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#3
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Throws an exception if there is no node present.
 */

public class NodeNotPresentException extends Exception {
    public NodeNotPresentException() {}
    public NodeNotPresentException(String message) {
        super(message);
    }
}
