/**
 * @author Brandon Wong
 * SOLARID: 115006519
 * brandon.r.wong@stonybrook.edu
 * Hw#5
 * CSE 214: Recitation R01 (Mihir Mad, Steven Secreti)
 * Tree node class.
 */
public class StoryTreeNode {
    protected static final String WIN_MESSAGE = "YOU WIN";
    protected static final String LOSE_MESSAGE = "YOU LOSE";
    private String position;
    private String option;
    private String message;
    private StoryTreeNode leftChild;
    private StoryTreeNode middleChild;
    private StoryTreeNode rightChild;
    /**
     * Constructor that creates a node in story Tree.
     * @param position
     *  The position of the node.
     * @param option
     *  The option message of the node.
     * @param message
     *  The message of the node.
     */
    public StoryTreeNode(String position, String option, String message) {
        this.position = position;
        this.option = option;
        this.message = message;
    }
    /**
     * Checks to see if the node is a leaf.
     * @return
     *  Returns boolean of whether the node is a leaf or not.
     */
    public boolean isLeaf() {
        if(leftChild == null && middleChild == null && rightChild == null)
            return true;
        else
            return false;
    }
    /**
     * Checks to see if the node has the winning message.
     * @return
     *  Returtns boolean of whether the node is a winning node or not.
     */
    public boolean isWinningNode() {
        if(isLeaf()) {
            if(message.contains(WIN_MESSAGE)) {
                return true;
            }
            else
                return false;
        }
        else return false;
    }
    /**
     * Checks to see if the node has the losing message.
     * @return
     *  Returns boolean of whetehr the node is a losing node or not.
     */
    public boolean isLosingNode() {
        if(isLeaf()) {
            if(message.contains(LOSE_MESSAGE)) {
                return true;
            }
            else
                return false;
        }
        else return false;
    }
    /**
     * @return
     *  The position of the node.
     */
    public String getPosition() {
        return position;
    }
    /**
     * @return
     *  The option message of the node.
     */
    public String getOption() {
        return option;
    }
    /**
     * @return
     *  The message of the node.
     */
    public String getMessage() {
        return message;
    }
    /**
     * Sets a new position of the node.
     * @param position
     *  The new position.
     */
    public void setPosition(String position) {
        this.position = position;
    }
    /**
     * Sets a new option of the node.
     * @param option
     *  The new option.
     */
    public void setOption(String option) {
        this.option = option;
    }
    /**
     * Sets a new message of the node.
     * @param message
     *  THe new message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Sets the left child of the node.
     * @param leftChild
     *  A StoryTreeNode.
     */
    public void setLeftChild(StoryTreeNode leftChild) {
        this.leftChild = leftChild;
    }
    /**
     * Sets the middle child of the node.
     * @param middleChild
     *  A StoryTreeNode.
     */
    public void setMiddleChild(StoryTreeNode middleChild) {
        this.middleChild = middleChild;
    }
    /**
     * Sets the right child of the node.
     * @param rightChild
     *  A storyTreeNode.
     */
    public void setRightChild(StoryTreeNode rightChild) {
        this.rightChild = rightChild;
    }
    /**
     * @return
     *  Returns the left child of the current node.
     */
    public StoryTreeNode getLeftChild() {
        return leftChild;
    }
    /**
     * @return
     *  Returns the middle child of the current node.
     */
    public StoryTreeNode getMiddleChild() {
        return middleChild;
    }
    /**
     * @return
     *  Returns the right child of the current node.
     */
    public StoryTreeNode getRightChild() {
        return rightChild;
    }
    /**
     * Goes through the tree by pre-order recursively. 
     * @param node 
     *  A StoryTreeNode.
     * @return
     *  Returns a string of the node position, option and message.
     */
    public String preorder(StoryTreeNode node) {
        String storyString = "";
        if (node != null) {
            storyString += (node.getPosition() + " | " + node.getOption() + " | " + node.getMessage() + "\n");
            storyString += this.preorder(node.getLeftChild());
            storyString += this.preorder(node.getMiddleChild());
            storyString += this.preorder(node.getRightChild());
        }
        return storyString;
    }
}
