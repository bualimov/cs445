public class LinkedDS<T extends Comparable<? super T>> implements SequenceInterface<T>, ReorderInterface, Comparable<LinkedDS<T>> {
    // Start class + helper methods
    private class Node {
        T data; 
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node getNextNode() {
            return next;
        }

        private Node getPrevNode() {
            return prev;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }

        private T getData() {
			return data;
		} 
    }

    private Node firstNode; // Private data fields
    private Node lastNode;
    private Node prev;
    private int size;

    // Constructors (reg)
    public LinkedDS() {
        this.firstNode = null; // Signifies empty LL
        this.size = 0;
    }

    // copy constructor
    public LinkedDS(LinkedDS<T> other) {
        if (other.firstNode == null) {
            this.firstNode = null;
        } else {
            this.firstNode = new Node(other.firstNode.data);
            Node curr = this.firstNode;
            Node otherCurr = other.firstNode.next; // otherCurr = the other current node which points to next (u alr copied first one)

            while (otherCurr != null) {
                curr.next = new Node(otherCurr.data);
                curr = curr.next;
                otherCurr = otherCurr.next; 
            }
        }
        this.size = other.size;
    }

    // SequenceInterface.java
    // toString()
    @Override
    public String toString() {
        String result = ""; // 
        Node current = firstNode;

        while (current != null) {
            result += current.data; 
            if (current.next != null) {
                result += ""; 
            }
            current = current.next; 
        }
        return result;
    }

    // size()
    public int size() {
        return this.size;
    }

    // isEmpty()
    public boolean isEmpty() {
        return this.size == 0;
    }

    // first()
    public T first() {
        return (firstNode != null) ? firstNode.data : null;
    }

    // last()
    public T last() {
        return (lastNode != null) ? lastNode.data : null;
    }

    // clear()
    public void clear() {
        firstNode = null;
        size = 0; // reset size
    }

    // T itemAt(int position)
    public T itemAt(int position) {
        if (position < 0 || position > size() - 1)
            throw new IndexOutOfBoundsException();

        Node current = firstNode;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.data; // just use getNodeAt 
    }

    // int, getFrequencyOf(T item)
    public int getFrequencyOf(T item) {
        Node current = firstNode;
        int counter = 0;
        while (current != null) {
            if (current.data.equals(item)) {
                counter++;
            }
            current = current.next;
        }
        return counter;
    }

    // int, lastOccurenceOf(T item)
    public int lastOccurrenceOf(T item) {
        Node current = firstNode;
        int result = -1;
        int index = 0;

        while (current != null) { // this and current = current.next -> traversing thru list
            if (current.data.equals(item)) {
                result = index;
            }
            current = current.next; // next element
            index++; // next index
        }
        return result;
    }

    // append(T item)
    public void append(T item) {
        Node newNode = new Node(item); // create a new Node of such item
        if (firstNode == null) {
            firstNode = newNode; // if empty, make both nodes <- (item)
            lastNode = newNode;
        } else {
            lastNode.next = newNode; // update the lastNode.next's data
            lastNode = newNode; // lastNode should point to newNode
        }
        size++; // don't forget to increment size
    }

    // prefix(T item)
    public void prefix(T item) {
        Node newNode = new Node(item);
        if (firstNode == null) {
            firstNode = newNode;
            lastNode = newNode;
        } else {
            newNode.next = firstNode; // newNode is now BEFORE firstNode, what we want
            firstNode = newNode;
        }
        size++;
    }

    // insert(T item, int position)
    public void insert(T item, int position) {
        if (position < 0 || position > size)
            throw new IndexOutOfBoundsException();

        Node newNode = new Node(item);
        if (position == 0) {
            newNode.next = firstNode; // insert at head
            firstNode = newNode;
        } else {
            Node prev = firstNode;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.next;
            }
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++; // size incremenet at the end
    }

    // T, deleteHead()
    public T deleteHead() {
        if (isEmpty())
            throw new EmptySequenceException("List is empty");

        T result = firstNode.data; // preserve data to be removed
        firstNode = firstNode.next; // move head to next node

        size--;
        return result;
    }


    // T, deleteTail()
    public T deleteTail() {
        if (isEmpty())
            throw new EmptySequenceException("List is empty");

        T result = lastNode.data;
        if (firstNode == lastNode) { // only one Node
            firstNode = null;
            lastNode = null; // make list empty
        } else {
            Node current = firstNode;
            while (current.next != lastNode) {
                current = current.next; // find the 2nd to last node
            }
            current.next = null;
            lastNode = current; // this is now the tail    
        }
        size--;
        return result;
    }

    // boolean, trim(int numItems)
    public boolean trim(int numItems) {
        if (numItems > size) 
            return false;
        
        if (numItems == size) {
            firstNode = null; // Make sure the list is empty if all items are trimmed
            lastNode = null;
            size = 0;
            return true;
        }

        Node current = firstNode;
        for (int i = 0; i < size - 1 - numItems; i++) {
            current = current.next; // iterate thru the list, find cutoff point
        }
        current.next = null; // disconnect list
        lastNode = current;
        size -= numItems;

        return true;
    }

    // boolean, cut(int start, int numItems)
    public boolean cut(int start, int numItems) {
        if (start < 0 || start + numItems > size || start >= size)
            return false;

        if (start == 0) {
            Node current = firstNode;
            for (int i = 0; i < numItems; i++) {
                current = current.next;
            }
            firstNode = current;
            if (firstNode == null) {
                lastNode = null;
            }
            size -= numItems;
            return true;
        }

        Node current = firstNode;
        for (int i = 0; i < start - 1; i++) {
            current = current.next;
        }

        Node beginCut = current.next;
        for (int i = 0; i < numItems; i++) {
            beginCut = beginCut.next;
        }

        current.next = beginCut;
        if (beginCut == null) {
            lastNode = current;
        }

        size -= numItems; // Update the size
        return true;
    }

    // T, predecessor(T item)
    public T predecessor(T item){
        Node pointer = lastNode;
        for (int i = size; i > 0; i--) {
            if (pointer.getData().equals(item)) {
                pointer = pointer.getPrevNode();
                try {
                    return pointer.getData();
                } catch (Exception e) {
                    return null;
                }
            }
            pointer = pointer.getPrevNode();
        }

        if (pointer == null)
            return null;

        return pointer.getData();
    }
    

    // SequenceInterface<T> slice (T item) extra credit
    public SequenceInterface<T> slice(T item) {
        LinkedDS<T> result = new LinkedDS<>();
        Node current = firstNode; // empty slice of "result"

        while (current != null) { // start traversing
            if (current.data.compareTo(item) <= 0) { // the checks
                result.append(current.data);
            }
            current = current.next; // iterate
        }
        return result;
    }

    
    // SequenceInterface<T> slice (int start, int numItems) extra credit
    public SequenceInterface<T> slice(int start, int numItems) {
        if (start < 0 || start + numItems > size || start >= size || numItems < 0) {
            return null;
        }

        LinkedDS<T> result = new LinkedDS<>();
        Node current = firstNode;

        for (int i = 0; i < start; i++) { // find the start position
            current = current.next;
        }

        for (int i = 0; i < numItems; i++) {
            result.append(current.data);
            current = current.next;
        }
        return result;
    }

    // int compareTo()
    @Override
    public int compareTo(LinkedDS<T> other){
        Node pointer1 = this.firstNode;
        Node pointer2 = other.firstNode;
        int number;
    
        for (int i = 0; i < this.size() && i < other.size(); i++) {
            int result = pointer1.getData().compareTo(pointer2.getData());
            if (result != 0) {
                return result;  
            }
            pointer1 = pointer1.getNextNode();
            pointer2 = pointer2.getNextNode();
        }
        
        if(this.size() == other.size())
            return 0;
        
        if (this.size() > other.size()) {
            number = 1;
        } else {
            number = -1;
        }
        
        return number;
    }

    // ReorderInterface.java
    // reverse()
    public void reverse() {
        Node previous = null;
        Node current = firstNode;
        Node next = null;

        lastNode = firstNode;

        while (current != null) {
            next = current.getNextNode();
            current.setNextNode(previous);
            previous = current;
            current = next;
        }

        firstNode = previous;
    }

    // rotateRight()
    public void rotateRight() {
        prefix(lastNode.data);
        deleteTail();
    }


    // rotateLeft()
    public void rotateLeft() { // front to last <-
        append(firstNode.data);
        deleteHead();
    }

    @SuppressWarnings("unchecked")
    // shuffle(int[] oldPositions, int[] newPositions)
    public void shuffle(int[] oldPositions, int[] newPositions) {
        if (oldPositions.length != newPositions.length) {
            throw new IllegalArgumentException("Position arrays must have the same length.");
        }

        T[] temp = (T[]) new Comparable[newPositions.length];
        for (int i = 0; i < oldPositions.length; i++) {
            temp[i] = itemAt(oldPositions[i]);
        }

        for (int i = 0; i < newPositions.length; i++) {
            insert(temp[i], newPositions[i]);
        }
    }

}