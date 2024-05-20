import java.util.*;

public class Standart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();

        // Stack
        Stack<Integer> stack = new Stack<>();
        float stackTime = measureStackPerformance(stack, n);
        System.out.println("Stack time: " + stackTime/1000000 + " ms");

        // 70
        Queue<Integer> queue = new LinkedList<>();
        float queueTime = measureQueuePerformance(queue, n);
        System.out.println("Queue time: " + queueTime/1000000 + " ms");

        // Deque
        Deque<Integer> deque = new ArrayDeque<>();
        float dequeTime = measureDequePerformance(deque, n);
        System.out.println("Deque time: " + dequeTime/1000000 + " ms");

        // Array
        float arrayTime = measureArrayPerformance(n);
        System.out.println("Array time: " + arrayTime/1000000 + " ms");

        // Singly Linked List
        float singlyLinkedListTime = measureSinglyLinkedListPerformance(n);
        System.out.println("Singly Linked List time: " + singlyLinkedListTime/1000000 + " ms");

    }

    private static long measureStackPerformance(Stack<Integer> stack, int n) {
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureQueuePerformance(Queue<Integer> queue, int n) {
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }
        for (int i = 0; i < n; i++) {
            queue.remove();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureDequePerformance(Deque<Integer> deque, int n) {
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < n; i++) {
            deque.removeFirst();
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureArrayPerformance(int n) {
        long startTime = System.nanoTime();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int temp = array[i];
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureSinglyLinkedListPerformance(int n) {
        class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        Node head = null;
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            Node newNode = new Node(i);
            if (head == null) {
                head = newNode;
            } else {
                Node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
            }
        }
        while (head != null) {
            head = head.next;
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}

