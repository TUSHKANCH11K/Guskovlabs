import java.util.Scanner;

public class Custom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();

        // Custom Stack
        float stackTime = measureStackPerformance(n);
        System.out.println("Custom Stack time: " + stackTime/1000000 + " ms");

        // Custom Queue
        float queueTime = measureQueuePerformance(n);
        System.out.println("Custom Queue time: " + queueTime/1000000 + " ms");

        // Custom Deque
        float dequeTime = measureDequePerformance(n);
        System.out.println("Custom Deque time: " + dequeTime/1000000 + " ms");

        // Custom Array
        float arrayTime = measureArrayPerformance(n);
        System.out.println("Custom Array time: " + arrayTime/1000000 + " ms");

        // Custom Singly Linked List
        float singlyLinkedListTime = measureSinglyLinkedListPerformance(n);
        System.out.println("Custom Singly Linked List time: " + singlyLinkedListTime/1000000 + " ms");

    }

    private static long measureStackPerformance(int n) {
        int[] stack = new int[n];
        int top = -1;
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            stack[++top] = i;
        }
        for (int i = 0; i < n; i++) {
            int value = stack[top--];
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureQueuePerformance(int n) {
        int[] queue = new int[n];
        int front = 0, rear = -1;
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            queue[++rear] = i;
        }
        for (int i = 0; i < n; i++) {
            int value = queue[front++];
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureDequePerformance(int n) {
        int[] deque = new int[n];
        int front = 0, rear = -1;
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            deque[++rear] = i;
        }
        for (int i = 0; i < n; i++) {
            int value = deque[front++];
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureArrayPerformance(int n) {
        int[] array = new int[n];
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int value = array[i];
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureSinglyLinkedListPerformance(int n) {
        Node head = null;
        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            head = addSingly(head, i);
        }
        for (int i = 0; i < n; i++) {
            searchSingly(head, i);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static Node addSingly(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null) {
            return newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        return head;
    }

    private static boolean searchSingly(Node head, int data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
