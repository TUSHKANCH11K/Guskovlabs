import java.util.Arrays;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

class Main {
    private static final int NUM_ELEMENTS = 100000;
    private static int[] arr1 = new int[NUM_ELEMENTS]; // initial
    private static int n = 100000;
    private static int[] arr = new int[NUM_ELEMENTS]; // fillable

    public static void main(String[] args) {

        for (int i = 0; i < NUM_ELEMENTS; i++) {
            int value = (int) (Math.random() * 100);
            arr1[i] = value;
        }

        fillingArray();
        System.out.println("\n Shaker Sorting:");
        System.out.println("Original array:");
//        printElements();
        long startTime = System.nanoTime();
        long memoryBeforeArray = getMemoryUsed();
        shekerSort();
        long endTime = System.nanoTime();
        System.out.println("Time taken to sort array: " + ((endTime - startTime)/1000000) + " ms");
        long memoryAfterArray = getMemoryUsed();
        System.out.println("Memory used by array: " + (memoryAfterArray - memoryBeforeArray) + " bytes");

        System.out.println("Sorted array:");
//        printElements();
        fillingArray();
        System.out.println("\n Quick Sorting:");
        System.out.println("Original array:");
//        printElements();
        startTime = System.nanoTime();
        memoryBeforeArray = getMemoryUsed();
        quickSort(arr, 0, n - 1);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort array: " + ((endTime - startTime)/1000000) + " ms");
        memoryAfterArray = getMemoryUsed();
        System.out.println("Memory used by array: " + (memoryAfterArray - memoryBeforeArray) + " bytes");
        System.out.println("Sorted array:");
        System.out.println("\n");

        // Example usage
        ShoppingListStack stack = new ShoppingListStack();
        startTime = System.nanoTime();
        memoryBeforeArray = getMemoryUsed();
        stack.push("Хлеб", 1.5, "Вова");
        memoryAfterArray = getMemoryUsed();
        System.out.println("Memory used by array: " + (memoryAfterArray - memoryBeforeArray) + " bytes");
        endTime = System.nanoTime();
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
        stack.push("Молоко", 2.0, "Маша");
        stack.push("Яйца", 1.0, "Катя");
        System.out.println("\n");

        System.out.println("Top element of the stack: " + stack.peek().name);

        startTime = System.nanoTime();
        memoryBeforeArray = getMemoryUsed();

        stack.pop();
        memoryAfterArray = getMemoryUsed();
        System.out.println("Memory used by array: " + (memoryAfterArray - memoryBeforeArray) + " bytes");

        endTime = System.nanoTime();
        System.out.println("Time taken: " + ((endTime - startTime) / 1000000000) + " sec");
        stack.pop();
        stack.pop();

        stack.pop(); // Attempt to pop from an empty stack
    }
    private static long getMemoryUsed() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        return memoryBean.getHeapMemoryUsage().getUsed();
    }

    private static void printElements() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    private static void fillingArray() {
        System.arraycopy(arr1, 0, arr, 0, n);
    }

    private static void shekerSort() {
        int left = 0;
        int right = n - 1;
        boolean swapped = true;

        while (left < right && swapped) {
            swapped = false;
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (arr[i] < arr[i - 1]) {
                    int temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
            left++;
        }
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        return i + 1;
    }

    static class Item {
        String name;
        double price;
        String recipient;
        Item next;
        Item prev;

        Item(String name, double price, String recipient) {
            this.name = name;
            this.price = price;
            this.recipient = recipient;
            this.next = null;
            this.prev = null;
        }
    }

    static class ShoppingListStack {
        private Item top;

        ShoppingListStack() {
            this.top = null;
        }

        boolean isEmpty() {
            return top == null;
        }

        void push(String name, double price, String recipient) {
            Item newItem = new Item(name, price, recipient);
            if (isEmpty()) {
                top = newItem;
            } else {
                newItem.next = top;
                top.prev = newItem;
                top = newItem;
            }
            System.out.println("Added item \"" + name + "\" to the shopping list in the name \"" + recipient + "\". ");
        }

        void pop() {
            if (isEmpty()) {
                System.out.println("Stack is empty.");
                return;
            }
            Item temp = top;
            top = top.next;
            temp.next = null;
        }

        Item peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty.");
                return null;
            }
            return top;
        }

        void clear() {
            while (!isEmpty()) {
                pop();
            }
            System.out.println("Shopping list cleared.");
        }

        protected void finalize() {
            clear();
        }
    }
}
