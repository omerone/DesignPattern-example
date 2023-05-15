package activeObject;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;

public class MainTrain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
            }
            return "42";
        } , es).thenApply(s->Integer.parseInt(s))
               .thenApply(x->x*2)
               .thenAccept(ans->System.out.println("the result is:"+ans));
        es.shutdown();
        System.out.println("main is done");
    }
;}
