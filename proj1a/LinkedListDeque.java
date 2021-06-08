public class LinkedListDeque<chooseType> {

    /** nested class: Node
     * @param <chooseType>
     */
    private class Node<chooseType> {
        private chooseType item;
        private Node<chooseType> prev;
        private Node<chooseType> next;

        /** constructor for non-empty Node
         * @param item
         * @param prev
         * @param next
         */
        public Node(chooseType item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        /** constructor for empty Node
         */
        public Node() {
            this.next = null;
            this.prev = null;
        }
    }

    /** sentinal is a instance vairable
     * @param <chooseType>
     */
    private Node<chooseType> sentinel;

    /** totalSize is a instance vairable so that the time to caculate the totalSize is O(1)
     */
    private int totalSize;

    public LinkedListDeque() {
        sentinel = new Node<chooseType>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        totalSize = 0;
    }

    public void addLast(chooseType item) {
        sentinel.prev.next = new Node<chooseType>(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        this.totalSize++;
    }


    public void addFirst(chooseType item) {
        Node<chooseType> first = new Node<chooseType>(item, sentinel, sentinel.next);
        sentinel.next = first;
        first.next.prev = first;
        this.totalSize++;
    }

    public boolean isEmpty() {
        if (this.totalSize == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return totalSize;
    }

    public void printDeque() {
        if (totalSize == 0) {
            System.out.println("empty deque!");
            return;
        }
        Node<chooseType> p = sentinel.next;
        System.out.print(p.item);
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(" " + p.item);
        }
        System.out.print("\n");
    }




    public chooseType removeFirst() {
        if (totalSize == 0) {
            return null;
        }
        totalSize--;
        chooseType item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return item;
    }

    public chooseType removeLast() {
        if (totalSize == 0) {
            return null;
        }
        totalSize--;
        chooseType item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return item;
    }

    public chooseType get(int index) {
        if (totalSize <= index) {
            return null;
        }
        Node<chooseType> p = sentinel.next;
        while ((index--) > 0) {
            p = p.next;
        }
        return p.item;
    }

    private chooseType getRecursiveHelper(int index, Node<chooseType> p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }

    public chooseType getRecursive(int index) {
        if (index >= totalSize || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
}