package tech.zerofiltre.jdwp;

import java.util.concurrent.*;

public class JdwpApp {

    static int i = 0;

    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(0, 10, 5000, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), r -> {
                    i++;
                    return new Thread(r, "Worker#" + i);
                });


        Runnable printText = () -> {
            while (true) {
                System.out.println(" ====== We will print data ====");
                sleep();
            }
        };

        Runnable printData = () -> {
            int i = 0;
            String[] data = getStringsFromRemoteService();
            while (true) {
                try {
                    System.out.println("String " + i + " =" + data[i]);
                } catch (Exception e) {
                    System.out.println("No more data to print");
                    i=0;
                }
                i++;
                sleep();
            }
        };


        executor.submit(printText);
        executor.submit(printData);
    }

    private static String[] getStringsFromRemoteService() {
        return new String[]{"a", "b", "c", "d", "e"};
    }


    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
