/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#2
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * 
 * 
 */
public class SlideList {
    private SlideListNode head;
    private SlideListNode tail;
    private SlideListNode cursor;
    private int numSlides;
    /**
     * This is a default constructor that intializes the slidelist.
     */
    public SlideList() {
        head = null;
        tail = null;
        cursor = null;
    }
    /**
     * This method returns the number of slides in the slidelist.
     * @return
     *  The number of slides in the slidelist.
     */
    public int size() {
        return numSlides;
    }
    /**
     * This method returns the totalDuration of all of the slides.
     * @return
     *  The total duration of the slidelist.
     */
    public double duration() {
        SlideListNode nodePtr = head;
        double totalDuration = 0;
        while(nodePtr != null) {
            totalDuration += nodePtr.getData().getDuration();
            nodePtr = nodePtr.getNext();
        }
        return totalDuration;
    }
    /**
     * The method returns the number of bullets in the slidelist.
     * @return
     *  The number of bullets in the slides.
     */
    public int numBullets() {
        SlideListNode nodePtr = head;
        int numBullets = 0;
        while(nodePtr != null) {
            numBullets += nodePtr.getData().getNumBullets();
            nodePtr = nodePtr.getNext();
        }
        return numBullets;
    }
    /**
     * Returns the slide that the cursor is at.
     * @return
     *  A slide where the cursor is.
     */
    public Slide getCursorSlide() {
        return cursor.getData();
    }
    /**
     * Resets the cursor to the head of the linkedlist.
     */
    public void resetCursorToHead() {
        cursor = head;
    }
    /**
     * Moves the cursor forward in the linked list.
     * @throws EndOfListException
     *  Throws an exception when the cursor is already at the tail of the list.
     */
    public void cursorForward() throws EndOfListException {
        if(cursor == tail)
            throw new EndOfListException("Cursor is already at the end of the list");
        if(cursor != tail) {
            cursor = cursor.getNext();
        }
    }
    /**
     * Moves the cursor backwards in the linked list.
     * @throws EndOfListException
     *  Throws an exception when the cursor is already at the head of the list.
     */
    public void cursorBackward() throws EndOfListException {
        if(cursor == head)
            throw new EndOfListException("Cursor is already at the front of the list");
        if(cursor != head) {
            cursor = cursor.getPrev();                
        }
    }
    /**
     * This method adds another slide in the linked list before the cursor.
     * @param newSlide
     *  The new slide being added.
     * @throws IllegalArgumentException
     *  Throws an exception if the new slide being added is null.
     */
    public void insertBeforeCursor(Slide newSlide) throws IllegalArgumentException {
        if(newSlide == null)
            throw new IllegalArgumentException("The slide can not be null");
        SlideListNode newNode = new SlideListNode(newSlide);
        if(cursor == null) {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        else {
            if(cursor.getPrev() == null) {
                newNode.setNext(cursor);
                cursor.setPrev(newNode);
                head = newNode;
                cursor = head;
            }
            else {
                newNode.setNext(cursor);
                cursor.getPrev().setNext(newNode);
                newNode.setPrev(cursor.getPrev());
                cursor.setPrev(newNode);
                cursor = newNode;
            }
        }
        numSlides++;
    }
    /**
     * This method appends a value to the linked list and becomes the new tail.
     * @param newSlide
     *  The new slide being added.
     * @throws IllegalArgumentException
     *  Throws an exception when the new slide is null.
     */
    public void appendToTail(Slide newSlide) throws IllegalArgumentException {
        if(newSlide == null)
            throw new IllegalArgumentException("The slide can not be null");
        SlideListNode newNode = new SlideListNode(newSlide);
        if(tail == null) {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
            cursor = tail;
        }
        numSlides++;
    }
    /**
     * Removes the slide at the cursor.
     * @return
     *  The slide that was removed.
     * @throws EndOfListException
     *  Throws an exception when the cursor is null.
     */
    public Slide removeCursor() throws EndOfListException {
        SlideListNode temp = cursor;
        if(cursor == null)
            throw new EndOfListException("Cursor is nullPointerException");
        if(cursor == head) {
            head = head.getNext();
            cursor = head;
        }
        else if(cursor == tail) {
            tail = tail.getPrev();
            cursor = tail;
        }
        else {
            cursor.getPrev().setNext(cursor.getNext());
            cursor = cursor.getPrev();
        }
        numSlides--;
        return temp.getData();
    }
    public SlideListNode getCursor() {
        return cursor;
    }
    public void setCursor(SlideListNode newCursor) {
        cursor = newCursor;
    }
    public SlideListNode getHead() {
        return head;
    }
}
