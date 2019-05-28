public class Main {
    public static void main(String[] args) {
        Runnable[] r = new Runnable[100];
        ScalableThreadPool pool = new ScalableThreadPool(1,2);
        for (int i = 0; i < 100; i++) {
            int j = i;
            r[i] = (() -> {
                try {
                    Thread.sleep(100 * (j == 10 ? 50 : 1));
                } catch (Exception ignore) {
                }
                System.out.println("task " + j);
            });
            pool.execute(()->{r[j].run(); System.out.println("is done by thread pool");});
        }
        for(int i = 0; i < 100; i++){
                try{Thread.sleep(200);} catch(Exception ignore){}
                System.out.println(i);
                if(i == 80)
                    pool.execute(()-> System.out.println("im alive"));
        }
        ExecutionManager manager = new ExecutionManager(3);
        manager.execute(()-> System.out.println("all tasks are completed"), r);
    }
}
