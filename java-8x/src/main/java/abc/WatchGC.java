package abc;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;


/**
 * set HEAP=-Xms50M -Xmx50M
 *
 * SET GC=-XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:NewRatio=1
 *
 * java %HEAP% %GC% -cp lock-1.0-SNAPSHOT.jar abc.WatchGC
 *
 */
public class WatchGC {
    static Queue<BigObject> list ;

    static final String bigString = createBigString();

    public static void main(String[] args) throws InterruptedException {
        list = new LinkedList<>();
        addObject(500);
        while (true){
            addObject(15000 + 500);
            removeObject(15000);
            System.out.println("add 100, remove 100 ... " + System.currentTimeMillis());
            System.out.println("queue size ... " + list.size());
            Thread.sleep(500);
        }
    }

    private static String createBigString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 50; i++) {
            sb.append(UUID.randomUUID().toString());
        }
        return sb.toString();
    }


    private static void removeObject(int count) {
        for (int i = 0; i < count; i++) {
            list.poll();
        }
    }

    private static void addObject(int count){
        for (int i = 0; i < count; i++) {
            list.add(new BigObject(bigString));
        }
    }
}
