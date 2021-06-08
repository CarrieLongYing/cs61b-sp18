public class ArrayDeque<chooseType> {
    private chooseType[] array;
    private int nextFirst = 0;
    private int nextLast = 1;
    private int size = 0;
    private int capacity = 8;

    public ArrayDeque() {
        array = (chooseType[]) new Object[capacity];
    }

    private void copyTo(chooseType[] b) {
        int bIndex = 0;
        int index = (nextFirst + 1) % capacity;
        int tempSize = size;
        while ((tempSize--) > 0) {
            b[bIndex++] = array[index];
            index = (index + 1) % capacity;
        }
    }

    private void resize() {
        chooseType[] largerArray = (chooseType[]) new Object[size * 2];
        copyTo(largerArray);
        array = largerArray;
        capacity = size * 2;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private void shrink() {
        chooseType[] smallerArray = (chooseType[]) new Object[capacity / 2];
        copyTo(smallerArray);
        array = smallerArray;
        capacity = capacity / 2;
        nextFirst = capacity - 1;
        nextLast = size;
    }


    public void addFirst(chooseType item) {
        if (size == capacity) {
            resize();
        }
        array[nextFirst] = item;
        if (nextFirst - 1 < 0) {
            nextFirst = capacity - 1; //nextFirst is then the end of the array: recursive array!
        } else {
            nextFirst = nextFirst - 1;
        }
        size++;

        printDeque();
    }

    public void addtLast(chooseType item) {
        if (size == capacity) {
            resize();
        }
        if (size == 0) {
            array[nextFirst] = item;
            nextFirst = nextFirst - 1 < 0 ? capacity - 1 : nextFirst - 1;
        } else {
            array[nextLast] = item;
            nextLast = (nextLast + 1) % capacity;
        }
        size++;
        printDeque();


    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
    }

    public chooseType removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        nextFirst = (nextFirst + 1) % capacity;
        chooseType res = array[nextFirst];
        array[nextFirst] = null;
        if ((size * 1.0 / capacity) < 0.251) {
            shrink();
        }
        printDeque();
        return res;
    }

    public chooseType  removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        nextLast = nextLast - 1 < 0 ? capacity - 1 : nextLast - 1;
        chooseType res = array[nextLast];
        array[nextLast] = null;
        if ((size * 1.0 / capacity) < 0.251) {
            shrink();
        }
        printDeque();
        return res;
    }

    public chooseType get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return array[(nextFirst + index + 1) % capacity];
    }


    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addtLast(5);
        a.addtLast(6);
        a.addtLast(7);
        a.addFirst(4);
        a.addtLast(8);
        a.addtLast(9);
        a.addtLast(10);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.addFirst(0);
        a.addFirst(-1);
        a.addFirst(-2);
        a.addtLast(11);
        a.addtLast(12);
        a.addtLast(13);
        a.addtLast(14);
        a.addtLast(15);
        a.removeFirst();
        a. removeLast();
        a. removeLast();
        a. removeLast();
        a. removeLast();
        a. removeLast();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        System.out.println("this is your a: ");
        a.printDeque();
    }
}