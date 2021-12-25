/**
* Class LinkedStack models a stack of a generic type.
* It provides the user with methods to add, remove, vizualize items, and
* check the status of the stack.
*/
public class LinkedStack<T> implements Stack<T> {
    private Node firstNode = null;
    /**
    * Class node models a linked data structure.
    */
    private class Node {
        private T    data; 
        private Node next; 
        /**
         * Creates a node which holds data of a generic type,
         * and sets the next node in series as null
         * @param dataPortion an object of a generic type
         */
        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor
        /**
         * Creates a node which holds data of a generic type,
         * and sets the next node in series.
         * @param dataPortion an object of a generic type
         * @param nextNode a reference of type node, representing the next node in the series
         */
        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node

    /**
     * Adds an item to the top of this stack.
     * @param item The item to add.
     */
    public void push(T item) {
        if(this.firstNode == null){
            Node addNode = new Node(item);
            firstNode = addNode;
        } else{
            Node addNode = new Node(item, firstNode);
            firstNode = addNode;
        } //end if/else chain
    } //end push
    /**
     * Removes and returns the item from the top of this stack.
     * @return the item at the top of the stack. Returns null if empty.
     */
    public T pop() {
        T nodeToReturn = null;
        if(firstNode != null){
            nodeToReturn = firstNode.data;
            firstNode = firstNode.next;
        } //end if
        return nodeToReturn;
    } //end pop
    
    /**
     * Returns the item on top of the stack, without removing it.
     * @return the item at the top of the stack. Returns null if empty.
     */
    public T peek() {
        T dataTop = null;
        if(firstNode != null){
            dataTop = firstNode.data;
        } //end if
        return dataTop;
    } //end peek
    
    /** 
     * Returns whether the stack is empty. 
     * @return true if the stack is empty; false otherwise
     */
    public boolean isEmpty() {
        boolean stateStack = true;
        if (firstNode != null){
            stateStack = false;
        } //end if
        return stateStack;
    } //end isEmpty
    
    /** 
     * Removes all items from the stack. 
     */
    public void clear() {
        firstNode = null;
    } //end clear
    /**
     * Tests the stack and its main methods.
     */
    public static void main(String[] args) {
        LinkedStack<String> test = new LinkedStack<>();
        System.out.println(test.isEmpty());
        System.out.println(test.peek());
        System.out.println(test.pop());
        test.push("a");
        System.out.println(test.isEmpty());
        System.out.println(test.peek());
        test.push("b");
        System.out.println(test.peek());
        System.out.println(test.pop());
        System.out.println(test.peek());
        test.push("c");
        test.push("d");
        test.push("e");
        test.push("f");
        test.push("g");
        test.push("h");
        test.push("i");
        test.push("j");
        test.push("k");
        test.push("l");
        System.out.println(test.isEmpty());
        test.clear();
        System.out.println(test.isEmpty());
    }
}
