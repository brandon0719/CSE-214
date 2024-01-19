public class CargoStack {
    private class Node {
        private Cargo data;
        private Node next;
        public Node(Cargo data) {
            if(data == null) throw new IllegalArgumentException("Cargo can not be null");
            this.data = data;
        }
        public Cargo getData() {
            return data;
        }
        public void setData(Cargo data) {
            if(data == null) throw new IllegalArgumentException("Data can not be null");
            this.data = data;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }
    private Node head;
    private int numOfCargo;
    public CargoStack() {
        head = null;
        numOfCargo = 0;
    }
    public void push(Cargo data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
        numOfCargo++;
    }
    public Cargo pop() throws EmptyStackException{
        if(isEmpty()) {throw new EmptyStackException("Can not pop an empty stack");}
        Node nodePtr = head;
        head = head.getNext();
        nodePtr.setNext(null);
        numOfCargo--;
        return nodePtr.getData();
    }
    public Cargo peek() throws EmptyStackException {
        if(isEmpty()) {throw new EmptyStackException("Can not peek an empty stack");}
        return head.getData();
    }
    public int size() {
        return numOfCargo;
    }
    public boolean isEmpty() {
        return numOfCargo == 0;
    }
    public Object clone() {
        CargoStack temp = new CargoStack();
        Node nodePtr = head;
        while(nodePtr != null) {
            temp.push(nodePtr.getData());
            nodePtr = nodePtr.getNext();
        }
        return temp;
    }
    public void reverse() {
        Node nodePtr = head;
        Node previous = null, current = null;
        while (nodePtr != null) {
            current = nodePtr;
            nodePtr = nodePtr.getNext();
            // reverse the link
            current.setNext(previous);
            previous = current;
            head = current;
        }
    }
    public void displayStackStrength() {
        reverse(); //REVERSE THE LINKED LIST
        Node nodePtr = head;
        while(nodePtr != null) {
            System.out.print(nodePtr.getData().getStrength());
            if(nodePtr.getNext() != null)
                System.out.print(", ");
            nodePtr = nodePtr.getNext();
        }
        reverse();
    }
}
