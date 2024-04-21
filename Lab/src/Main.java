import java.util.*;

public class Main {
    private static final int MAX_SIZE = 100;
    private int[] heap;
    private int size;

    public Main() {
        heap = new int[MAX_SIZE];
        size = 0;
    }

    public void insert(int value) {
        if (size == MAX_SIZE) {
            System.out.println("Куча заполнена!");
            return;
        }
        int index = size;
        heap[index] = value;
        size++;
        siftUp(index);
    }

    public int extractMin() {
        if (size == 0) {
            System.out.println("Куча пуста!");
            return -1; // или бросить исключение
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return min;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[parent] <= heap[index]) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap[left] < heap[smallest]) {
                smallest = left;
            }
            if (right < size && heap[right] < heap[smallest]) {
                smallest = right;
            }
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    // Обход в глубину для графа
    private static class DepthFirstSearch {
        private int V; // Количество вершин
        private LinkedList<Integer> adj[]; // Списки смежности

        public DepthFirstSearch(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }

        // Добавление ребра в граф
        void addEdge(int v, int w) {
            adj[v].add(w);
        }

        // Рекурсивная функция для обхода в глубину
        void DFSUtil(int v, boolean visited[]) {
            // Помечаем текущую вершину как посещенную и выводим ее
            visited[v] = true;
            System.out.print(v + " ");

            // Рекурсивно обходим все вершины, смежные с данной вершиной
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n])
                    DFSUtil(n, visited);
            }
        }

        // Функция для обхода в глубину из заданной вершины
        void DFS(int v) {
            // Массив для отслеживания посещенных вершин
            boolean visited[] = new boolean[V];

            // Вызываем рекурсивную вспомогательную функцию для обхода в глубину
            DFSUtil(v, visited);
        }
    }

    // Алгоритм Дейкстры для матрицы смежности
    private static class Dijkstra {
        private int V;
        private int[][] graph;

        public Dijkstra(int v) {
            V = v;
            graph = new int[V][V];
        }

        void addEdge(int source, int destination, int weight) {
            graph[source][destination] = weight;
            graph[destination][source] = weight; // Если граф неориентированный
        }

        int minDistance(int dist[], boolean visited[]) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && dist[v] < min) {
                    min = dist[v];
                    minIndex = v;
                }
            }

            return minIndex;
        }

        void dijkstra(int src) {
            int dist[] = new int[V];
            boolean visited[] = new boolean[V];

            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
            }

            dist[src] = 0;

            for (int count = 0; count < V - 1; count++) {
                int u = minDistance(dist, visited);
                visited[u] = true;

                for (int v = 0; v < V; v++) {
                    if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                            dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }

            System.out.println("\nАлгоритм Дейкстры:");
            System.out.println("Вершина \t Расстояние от источника");
            for (int i = 0; i < V; i++) {
                System.out.println(i + " \t\t " + dist[i]);
            }
        }
    }

    public static void main(String[] args) {
        Main heap = new Main();

        heap.insert(5);
        heap.insert(10);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);

        System.out.println("Извлечен минимальный элемент: " + heap.extractMin());
        System.out.println("Извлечен минимальный элемент: " + heap.extractMin());

        // Создаем граф и добавляем ребра
        DepthFirstSearch graph = new DepthFirstSearch(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("\nОбход в глубину, начиная с вершины 2:");
        graph.DFS(2);

        // Создаем взвешенный граф и добавляем ребра
        Dijkstra dijkstraGraph = new Dijkstra(4);
        dijkstraGraph.addEdge(0, 1, 10);
        dijkstraGraph.addEdge(0, 2, 5);
        dijkstraGraph.addEdge(1, 2, 3);
        dijkstraGraph.addEdge(1, 3, 1);
        dijkstraGraph.addEdge(2, 3, 8);

        dijkstraGraph.dijkstra(0);
    }
}
