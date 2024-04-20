import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int NUM_ELEMENTS = 100000;
    private static int[] arr1 = new int[NUM_ELEMENTS]; // initial
    private static int n = 100000;
    private static int[] arr = new int[NUM_ELEMENTS]; // fillable


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Введите число: ");
//        int number = scanner.nextInt();
//        int coins [] = {1,5,10,25,50};
//        System.out.println("Количество комбинаций: " + coinChange(coins, number));

//        int[] dimensions = {10, 30, 5,60}; // Размеры матриц: A1(10x30), A2(30x5), A3(5x60)
//        System.out.println("Минимальное количество операций: " + matrixChainOrder(dimensions));
        Random random = new Random();
        int numMatrices =1000; // Количество матриц
        int[] dimensions = new int[numMatrices + 1]; // Размеры матриц

        // Генерируем случайные размеры матриц
        for (int i = 0; i < numMatrices + 1; i++) {
            dimensions[i] = random.nextInt(100) + 1; // Генерируем случайное число от 1 до 100
        }
        long startTime = System.nanoTime();
        System.out.println("Минимальное количество операций: " + matrixChainOrder(dimensions));
        long endTime = System.nanoTime();
        System.out.println("Time taken to sort array: " + ((endTime - startTime)/1000000) + " ms");

        scanner.close();


        for (int i = 0; i < NUM_ELEMENTS; i++) {
            int value = (int) (Math.random() * 100);
            arr1[i] = value;
        }

        System.out.println("\n selectionSort:");
        startTime = System.nanoTime();
        selectionSort(arr1);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort array: " + ((endTime - startTime)/1000) + " ms");

        System.out.println("\n mergeSort:");
        fillingArray();
        startTime = System.nanoTime();
        mergeSort(arr1, 0,NUM_ELEMENTS-1);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort array: " + ((endTime - startTime)/1000) + " ms");

        System.out.println("\n findMax:");
        fillingArray();
        startTime = System.nanoTime();
        findMax(arr1);
        endTime = System.nanoTime();
        System.out.println("Time taken to sort array: " + ((endTime - startTime)/1000) + " ms");


    }

    public static int coinChange(int[] coins, int amount) {
        return coinChangeHelper(coins, amount, 0);
    }

    public static int coinChangeHelper(int[] coins, int amount, int index) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || index == coins.length) {
            return 0;
        }

        int include = coinChangeHelper(coins, amount - coins[index], index);

        int exclude = coinChangeHelper(coins, amount, index + 1);

        return include + exclude;
    }

    public static int[] generateRandomDimensions(int size) {
        Random random = new Random();
        int[] dimensions = new int[size];
        for (int i = 0; i < size; i++) {
            dimensions[i] = random.nextInt(100) + 1; // Случайное число от 1 до 100
        }
        return dimensions;
    }

    public static int matrixChainOrder(int[] dimensions) {
        int n = dimensions.length - 1; // Количество матриц
        int[][] dp = new int[n][n]; // Массив для хранения минимального количества операций

        for (int len = 2; len < n + 1; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        return dp[0][n - 1];


    }

    private static void fillingArray() {
        System.arraycopy(arr1, 0, arr, 0, n);
    }


    //Сложность O(n^2): Сортировка выбором (Selection Sort)
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    //Сложность O(n*log(n)): Сортировка слиянием (Merge Sort)
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //Сложность O(n): Поиск максимального элемента в массиве
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

}
