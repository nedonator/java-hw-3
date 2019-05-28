public class Counter{
    private int count = 0;

    public Counter(){}

    public synchronized void add(){
        count++;
    }

    public synchronized int get(){
        return count;
    }

    public synchronized int addAndGet(){
        return ++count;
    }
}
