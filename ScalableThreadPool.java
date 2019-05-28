import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ScalableThreadPool {
    private Queue<Runnable> tasks;
    private ThreadList minThreadList;
    private ThreadList addThreadList;

    public ScalableThreadPool(int min, int max){
        tasks = new ConcurrentLinkedQueue<>();
        Runnable task = new RunThread(tasks);
        minThreadList = new ThreadList(min, task);
        addThreadList = new ThreadList(max - min, task);
    }

    public void execute(Runnable runnable){
        tasks.add(runnable);
        if(!minThreadList.activateThread() && tasks.size() > 3)addThreadList.activateThread();
    }
}
