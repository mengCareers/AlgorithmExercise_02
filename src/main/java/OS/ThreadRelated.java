package OS;

class Helper implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("threadHelper going to sleep for 5000");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread2 interrupted");
        }
    }
}


class Test implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("threadTest going to sleep for 5000");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Test testObj = new Test();
        Helper helperObj = new Helper();
        System.out.println("0. activeCount: " + Thread.activeCount());
        Thread thread1 = new Thread(testObj);
        Thread thread2 = new Thread(helperObj);
        System.out.println("1. activeCount: " + Thread.activeCount());
        thread1.start();
        thread2.start(); // some actions thread.sleep() in run(), thread is active
        System.out.println("2. activeCount: " + Thread.activeCount());

        System.out.println("Thread1 name: " + thread1.getName());
        System.out.println("Thread1 ID: " + thread1.getId());
        System.out.println("Thread1 isActive: " + thread1.isAlive());

        System.out.println("Thread2 name: " + thread2.getName());
        System.out.println("Thread2 ID: " + thread2.getId());
        System.out.println("Thread2 isActive: " + thread2.isAlive());

        Thread t = Thread.currentThread();
        System.out.println(t.getId());
        System.out.println(t.getName());

    }
}

public class ThreadRelated {
//    public static void main(String[] args) {
//
//        Runtime runtime = Runtime.getRuntime();
//        int cores = runtime.availableProcessors();
//        System.out.println(cores);
//        int numActiveThreads = Thread.activeCount();
//        System.out.println("numActiveThreads: " + numActiveThreads);
//        Thread curThread = Thread.currentThread();
//        System.out.println(curThread.getId());
//        Thread newThread = new Thread();
//
//    }

    public static void testFunction() {

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}
