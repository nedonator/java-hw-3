public class ThreadList {
    private Thread[] threads;
    private Runnable task;
    private int size;
    private int it = 0;
    ThreadList(int threadCount, Runnable task){
        this.task = task;
        size = threadCount;
        threads = new Thread[threadCount];
    }
    synchronized boolean activateThread(){
        int i = (it + 1) % size;
        while(true){
            if(threads[i] == null || threads[i].getState() == Thread.State.TERMINATED){
                threads[i] = new Thread(task);
                threads[i].start();
                it = i;
                return true;
            }
            if(i == it)
                return false;
            i = (i + 1) % size;
        }
    }
}
