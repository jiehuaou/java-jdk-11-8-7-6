package abc;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalApp {
    long currentId ;
    public static class Data{
        public int id;
        public String name;

        public Data(int id, String name){
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    static ThreadLocal<String> threadStore = new ThreadLocal<>();
    static ThreadLocal<Data> threadData = new ThreadLocal<>();

    /**
     * add data to ThreadLocal
     * @param index
     * @param name
     */
    void putData(int index, String name) {
        currentId = Thread.currentThread().getId();

        System.out.printf("%d putData ==> %s \n", currentId, name);
        threadStore.set(name);
        Data data = new Data(index, name);
        threadData.set(data);

    }

    /**
     * read ThreadLocal data
     */
    void readData() {
        System.out.printf("%d get name ==> %s \n" , currentId, threadStore.get());
        System.out.printf("%d get data ==> %s \n" , currentId, threadData.get());
    }

    /**
     * clean ThreadLocal data
     */
    void clean() {
        threadStore.remove();
        threadData.remove();
        System.out.printf("%d get name after clean ==> %s \n" , currentId, threadStore.get());
        System.out.printf("%d get data after clean ==> %s \n", currentId,  threadData.get());
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 3; i++) {
            final int index = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    ThreadLocalApp app = new ThreadLocalApp();
                    app.putData(index,"hello-" + index);
                    app.readData();
                    app.clean();
                }
            });
        }

        service.shutdown();
        while(!service.awaitTermination(1, TimeUnit.SECONDS)){
            System.out.println("waiting ...");
        };


    }
}
