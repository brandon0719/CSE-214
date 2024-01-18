/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#2
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * This class acts as wraps a Slide object to allow it to be inserted into a doubly linked-list data structure.
 */
public class SlideListNode {
    private Slide data;
    private SlideListNode next;
    private SlideListNode prev;
    /**
     * This is a constructor to intialize the doubbly-linked list.
     * @param initData
     *  A Slide class variable that is added to the linked list.
     * @throws IllegalArgumentException
     *  Throws an exception when the data is null.
     */
    public SlideListNode(Slide initData) throws IllegalArgumentException {
        if(initData == null)
            throw new IllegalArgumentException("Data can not be null");
        this.data = initData;
    }
    /**
     * This method returns data
     * @return
     *  Returns the data of the slide on the object is called on
     */
    public Slide getData() {
        return data;
    }
    /**
     * This method sets the object's data that it is called on.
     * @param newData
     *  A slide class variable
     * @throws IllegalArgumentException
     *  Throws an exception when the data is null
     */
    public void setData(Slide newData) throws IllegalArgumentException {
        if(newData == null)
            throw new IllegalArgumentException("Data can not be null");
        this.data = newData;
    }
    /**
     * This method returns the reference variable next.
     * @return
     *  The next index of the linked list.
     */
    public SlideListNode getNext() {
        return next; 
    }
    /**
     * This method sets the object's next reference variable.
     * @param newNext
     *  The next reference.
     */
    public void setNext(SlideListNode newNext) {
        this.next = newNext;
    }
    /**
     * This method returns the reference variable prev.
     * @return
     *  Returns prev.
     */
    public SlideListNode getPrev() {
        return prev;
    }
    /**
     * This method sets the object's prev reference variable.
     * @param newPrev
     *  The prev reference.
     */
    public void setPrev(SlideListNode newPrev) {
        this.prev = newPrev;
    }
}
