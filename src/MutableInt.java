public class MutableInt {
    private int value;
    
    public MutableInt(int initialValue) {
        this.value = initialValue;
    }
    
    public synchronized void set(int value) {
        this.value = value;
    }
    
    public synchronized int get() {
        return value;
    }
}