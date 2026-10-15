package concurrent;

public class Transaction {
  public static long counter = 0;

  public static class Incrementer implements Runnable {

    @Override
    public void run() {
      System.out.println("Incrementer starting");
      for (long i = 0; i < 1_000_000; i++) {
        counter++;
      }
      System.out.println("Incrementer completed");
    }
  }

  static void main() throws Throwable {
    Runnable task = new Incrementer();
    Thread t1 = new Thread(task);
    Thread t2 = new Thread(task);
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    System.out.println("counter value is " + counter);
  }
}
