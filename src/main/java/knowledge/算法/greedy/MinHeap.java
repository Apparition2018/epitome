package knowledge.算法.greedy;

/**
 * 最小堆
 * <p>
 *     0
 *    / \
 *   1   2
 *  / \
 * 3  4
 * <p>
 * https://blog.csdn.net/xuefeng0707/article/details/7841328
 */
@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable> {
    private Object[] data;

    public int size;

    MinHeap(int capacity) {
        data = new Object[capacity];
        size = 0;

    }

    public boolean add(T val) {
        if (size >= data.length)
            return false;
        int i = size, p;
        data[size] = val;
        size++;

        while (i > 0) {
            p = parentIndex(i);
            if (get(i).compareTo(get(p)) < 0) {
                swap(data, i, p);
            } else {
                break;
            }
            i = p;
        }

        return true;
    }

    public T remove(int index) {
        if (index >= size)
            return null;
        int i = index, left, right, p;
        T val = (T) data[index];
        data[index] = data[size - 1];
        size--;
        while (!isLeaf(i)) {
            left = leftIndex(i);
            right = rightIndex(i);
            p = i;
            i = right >= size || get(left).compareTo(get(right)) < 0 ? left
                    : right;
            if (get(i).compareTo(get(p)) < 0) {
                swap(data, i, p);
            }
        }

        return val;
    }

    public T removeMin() {
        return remove(0);
    }

    public T get(int index) {
        if (index >= size)
            throw new IllegalArgumentException("index is greater than size : "
                    + index);
        return (T) data[index];
    }

    private static int leftIndex(int index) {
        return 2 * index + 1;
    }

    private static int rightIndex(int index) {
        return 2 * index + 2;
    }

    private static int parentIndex(int i) {
        return i % 2 == 0 ? i / 2 - 1 : i / 2;
    }

    private boolean isLeaf(int index) {
        return leftIndex(index) >= size;
    }

    private void swap(Object[] data, int i1, int i2) {
        Object temp = data[i1];
        data[i1] = data[i2];
        data[i2] = temp;
    }
}