package activeObject;

import java.util.concurrent.BlockingDeque;

public class ActiveObject {

    BlockingDeque<Runnable> q;
    Thread t;
    volatile Boolean stop;
    ActiveObject (int Capacity) {
        t = new Thread(()->{
            while (!stop){
                try {
                    q.take().run();
                }
                catch (InterruptedException e) {e.printStackTrace();}
            }
        });
        t.start();
    }
    //will add the task to the queue
    public void execute(Runnable r) {
        if(!stop)
            try {
                q.put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    //will send interrupt to the thread and shut it immediately
    public  void shutdownNow() {
        stop = true;
        t.interrupt();
    }
    //will finish all the tasks in the queue and then shut down
    public void shutdown() {
        stop = true;
    }
    public  int size () {
        return q.size();
    }
}
