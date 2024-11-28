import java.util.*;

public class HashDS<T> implements SequenceInterface<T> {

    // private data fields
    private ArrayList<ArrayList<T>> table; // arraylist of arraylists
    private int capacity;
    private int size;
    private static final int INITIAL_CAP = 16;

    // private helper methods 
    /**
     * int getHash(T item)
     * @param item, getting hash index from (T item)
     * @return int, the hash code
     */
    private int getHash(T item) {
        return Math.abs(item.hashCode() % capacity);
    }

    // another DS to maintain ordered items separately
    // helpful with returning
    private LinkedList<T> orderedItems = new LinkedList<>();

    /**
     * String toString()
     * @override
     * @return table as a String
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T item : orderedItems) {
            result.append(item);
        }
        return result.toString();
    }

    // constructor
    public HashDS() {
        this.capacity = INITIAL_CAP; // declare initial cap
        this.size = 0;
        this.table = new ArrayList<>(capacity); 
        for (int i = 0; i < capacity; i++) {
            table.add(new ArrayList<>());
        }
    }

    // copy 
    public HashDS(HashDS<T> other) {
        this.capacity = other.capacity;
        this.size = other.size;
        this.table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            ArrayList<T> newBucket = new ArrayList<>(other.table.get(i));
            this.table.add(newBucket);
        }
        this.orderedItems = new LinkedList<>(other.orderedItems); // copy ordered items
    }

    /**
     * int size()
     * @return logical size of DS
     */
    public int size() {
        return size;
    }

    /**
     * boolean isEmpty()
     * @return true if DS is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * T itemAt(int position)
     * @param position from which getting item
     * @return T item
     */
    public T itemAt(int position) {
        if (position < 0 || position > size() - 1) {
            throw new IndexOutOfBoundsException(); // position out of bounds
        }
        
        int tracker = 0; // tracker position to check if target position reached
        for (ArrayList<T> bucket : table) {
            for (T item : bucket) {
                if (tracker == position) {
                    return item;
                }
                tracker++;
            }
        }
        return null; // item was not found
    }

    /**
     * T first()
     * @return the first item in DS
     */
    public T first() {
        if (isEmpty()) return null;
        return orderedItems.getFirst();
    }

    /**
     * T last()
     * @return the last item in DS
     */
    public T last() {
        if (isEmpty()) return null;
        return orderedItems.getLast();
    }

    /**
     * clears the DS, makes it empty
     */
    public void clear() {
        for (ArrayList<T> bucket : table) {
            bucket.clear();
        }
        size = 0;
    }

    /**
     * void append(T item)
     * @param item, to be added to end of DS
     */
    public void append(T item) {
        int hash = getHash(item);
        table.get(hash).add(item);
        orderedItems.addLast(item); // maintain the ordered sequence
        size++;
    }

    /**
     * void prefix(T item)
     * @param item, to be added to start of DS
     */
    public void prefix(T item) {
        int hash = getHash(item);
        table.get(hash).add(0, item); // add item to index 0 in the hash table
        orderedItems.addFirst(item);   // maintain the ordered sequence
        size++;
    }

    /**
     * boolean remove(T item)
     * @param item, to be removed from DS
     * @return true if item successfully removed
     * optimized to get EC, but screwed other EC
     */
    public boolean remove (T item) {
        int hash = getHash(item);
        ArrayList<T> bucket = table.get(hash);
        boolean removed = false;

        while (bucket.remove(item)) {
            size--;
            removed = true;
        }

        for (int i = 0; i < orderedItems.size(); i++) {
            if (orderedItems.get(i).equals(item)) {
                orderedItems.remove(i);
                i--;
                removed = true;
            }
        }
        return removed;
    }

    /**
     * int getFrequencyOf(T item)
     * @param item, to be counted (in DS)
     * @return # of times item occurs
     */
    public int getFrequencyOf(T item) {
        int count = 0;
        int hash = getHash(item);
        ArrayList<T> bucket = table.get(hash);
        for (T thing : bucket) {
            if (thing.equals(item)) {
                count++;
            }
        }
        return count;
    }

    /**
     * T deleteHead()
     * @return deleted item (logical head)
     * @throws EmptySequenceException if the Sequence is empty
     */
    public T deleteHead() {
        if (isEmpty()) {
            throw new EmptySequenceException("The sequence is empty.");
        }
        T item = orderedItems.removeFirst(); // remove from ordered list
        int hash = getHash(item);
        table.get(hash).remove(item); // remove from hash table
        size--;
        return item;
    }

    /**
     * T deleteTail()
     * @return deleted item (logical tail)	 
     * @throws EmptySequenceException if the Sequence is empty
     */

    public T deleteTail() {
        if (isEmpty()) {
            throw new EmptySequenceException("The sequence is empty.");
        }
        T item = orderedItems.removeLast(); // remove from ordered list
        int hash = getHash(item);
        table.get(hash).remove(item); // remove from hash table
        size--;
        return item;
    }

} // end class
