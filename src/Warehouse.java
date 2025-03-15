

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class Warehouse {
    private final Queue<Point> apples = new LinkedList<>();
    private final int maxCapacity;

    public Warehouse(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized boolean addApple() {
        if (apples.size() < maxCapacity) {
            return apples.add(new Point());
        }
        return false;
    }

    public synchronized boolean removeApple() {
        return apples.poll() != null;
    }

    public synchronized int size() {
        return apples.size();
    }

    public boolean isFull() {
        return size() >= maxCapacity;
    }

    public boolean isEmpty() {
        return apples.isEmpty();
    }
}