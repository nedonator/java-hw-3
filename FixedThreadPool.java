import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class FixedThreadPool{
    private ThreadList threads;
    private Queue<Runnable> tasks;

    public FixedThreadPool(int threadCount){
        tasks = new ConcurrentLinkedQueue<>();
        threads = new ThreadList(threadCount, new RunThread(tasks));
    }

    public void execute(Runnable runnable){
        tasks.add(runnable);
        threads.activateThread();
    }
}

class RunThread implements Runnable{
    private Queue<Runnable> tasks;

    RunThread(Queue<Runnable> tasks){
        this.tasks = tasks;
    }

    @Override
    public void run(){
        Runnable r = tasks.poll();
        while(r != null){
            try {
                r.run();
                r = tasks.poll();
            } catch(Exception ignore){}
        }
    }
}

/*class ConcurrentQueue<T>{
    private Queue<T> q = new LinkedList<>();
    public synchronized T get() throws InterruptedException {
        synchronized (ConcurrentQueue.class) {
            T e = q.poll();
            if (e == null) {
                throw new InterruptedException();
            } else return e;
        }
    }
    public synchronized void put(T e){
        q.add(e);
    }

    public synchronized int size(){
        return q.size();
    }
}*/
