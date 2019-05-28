import java.util.concurrent.Callable;

public class Task<T> {
    private Callable<? extends T> callable;
    private T result;
    private volatile boolean finished = false;
    private RuntimeException exception;

    public Task(Callable<? extends T> callable){
        this.callable = callable;
    }

    public T get(){
        if(!finished){
            synchronized (this) {
                if (!finished) {
                    try {
                        result = callable.call();
                    } catch (Exception e) {
                        exception = new RuntimeException(e);
                    } finally {
                        finished = true;
                    }
                }
            }
        }
        if(exception != null) throw exception;
        return result;
    }
}


