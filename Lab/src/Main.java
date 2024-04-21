import java.util.LinkedList;
import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        // Пример использования хэш-таблицы с линейным пробированием
        HashTableLinearProbing linearProbingTable = new HashTableLinearProbing(10); // Создаем хэш-таблицу размером 10

        // Добавляем пары ключ-значение в хэш-таблицу с линейным пробированием
        linearProbingTable.put("apple", "red");
        linearProbingTable.put("banana", "yellow");
        linearProbingTable.put("grape", "purple");

        // Получаем значения по ключам
        System.out.println("Color of apple: " + linearProbingTable.get("apple"));
        System.out.println("Color of banana: " + linearProbingTable.get("banana"));
        System.out.println("Color of grape: " + linearProbingTable.get("grape"));

        // Попытка получить значение для ключа, который отсутствует в таблице
        System.out.println("Color of cherry: " + linearProbingTable.get("cherry")); // Должно вернуться null

        // Пример использования хэш-таблицы с методом цепочек
        HashTableSeparateChaining separateChainingTable = new HashTableSeparateChaining(10); // Создаем хэш-таблицу размером 10

        // Добавляем пары ключ-значение в хэш-таблицу с методом цепочек
        separateChainingTable.put("apple", "red");
        separateChainingTable.put("banana", "yellow");
        separateChainingTable.put("grape", "purple");

        // Получаем значения по ключам
        System.out.println("Color of apple: " + separateChainingTable.get("apple"));
        System.out.println("Color of banana: " + separateChainingTable.get("banana"));
        System.out.println("Color of grape: " + separateChainingTable.get("grape"));

        // Попытка получить значение для ключа, который отсутствует в таблице
        System.out.println("Color of cherry: " + separateChainingTable.get("cherry")); // Должно вернуться null

        // Пример использования фильтра Блума
        BloomFilter bloomFilter = new BloomFilter(10); // Создаем фильтр Блума с ожидаемым количеством элементов 10

        // Добавляем элементы в фильтр Блума
        bloomFilter.add("apple");
        bloomFilter.add("banana");
        bloomFilter.add("grape");

        // Проверяем наличие элементов в фильтре Блума
        System.out.println("Contains apple: " + bloomFilter.contains("apple")); // Должно вернуться true
        System.out.println("Contains banana: " + bloomFilter.contains("banana")); // Должно вернуться true
        System.out.println("Contains grape: " + bloomFilter.contains("grape")); // Должно вернуться true
        System.out.println("Contains cherry: " + bloomFilter.contains("cherry")); // Может вернуться false
    }

    static class HashTableLinearProbing {
        private int size;
        private String[] keys;
        private String[] values;

        public HashTableLinearProbing(int size) {
            this.size = size;
            keys = new String[size];
            values = new String[size];
        }

        private int hash(String key) {
            return Math.abs(key.hashCode() % size);
        }

        public void put(String key, String value) {
            int index = hash(key);
            while (keys[index] != null) {
                if (keys[index].equals(key)) {
                    values[index] = value; // если ключ уже существует, обновляем значение
                    return;
                }
                index = (index + 1) % size; // переходим к следующему элементу
            }
            keys[index] = key;
            values[index] = value;
        }

        public String get(String key) {
            int index = hash(key);
            while (keys[index] != null) {
                if (keys[index].equals(key)) {
                    return values[index];
                }
                index = (index + 1) % size; // переходим к следующему элементу
            }
            return null; // ключ не найден
        }
    }

    static class HashTableSeparateChaining {
        private int size;
        private LinkedList<Entry>[] table;

        public HashTableSeparateChaining(int size) {
            this.size = size;
            table = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                table[i] = new LinkedList<>();
            }
        }

        private int hash(String key) {
            return Math.abs(key.hashCode() % size);
        }

        public void put(String key, String value) {
            int index = hash(key);
            LinkedList<Entry> list = table[index];
            for (Entry entry : list) {
                if (entry.key.equals(key)) {
                    entry.value = value; // если ключ уже существует, обновляем значение
                    return;
                }
            }
            list.add(new Entry(key, value));
        }

        public String get(String key) {
            int index = hash(key);
            LinkedList<Entry> list = table[index];
            for (Entry entry : list) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
            return null; // ключ не найден
        }

        private static class Entry {
            String key;
            String value;

            Entry(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    static class BloomFilter {
        private int size;
        private BitSet bitSet;
        private int[] hashes;

        public BloomFilter(int size) {
            this.size = size;
            bitSet = new BitSet(size);
            hashes = new int[]{7, 11, 13, 31}; // Пример хеш-функций
        }

        public void add(String element) {
            for (int hash : hashes) {
                int index = Math.abs(hash * element.hashCode() % size);
                bitSet.set(index, true);
            }
        }

        public boolean contains(String element) {
            for (int hash : hashes) {
                int index = Math.abs(hash * element.hashCode() % size);
                if (!bitSet.get(index)) {
                    return false;
                }
            }
            return true;
        }
    }
}
