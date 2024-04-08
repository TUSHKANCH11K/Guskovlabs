import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        int number = scanner.nextInt();
        int coins [] = {1,5,10,25,50};
        System.out.println("Количество комбинаций: " + coinChange(coins, number));
        int[] dimensions = {10, 30, 5, 60}; // Размеры матриц: A1(10x30), A2(30x5), A3(5x60)
        System.out.println("Минимальное количество операций: " + matrixChainOrder(dimensions));
        scanner.close();
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
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
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
