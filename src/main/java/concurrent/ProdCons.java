package concurrent;

import module java.base;

public class ProdCons {
  private static final BlockingQueue<int[]> q = new ArrayBlockingQueue<>(10);
  private static final int ITEM_COUNT = 10_000;
  private static final int BUFFER_TEST = 500;

  public static void main() {
    Runnable producer = () -> {
      System.out.println("Producer starting");
      try {
        for (int c = 0; c < ITEM_COUNT; c++) {
          int[] data = {c, 0}; // transactionally "bad"
          if (c < BUFFER_TEST) {
            Thread.sleep(1); // ensure we test "empty queue" works
          }
          data[1] = c;
          if (c == ITEM_COUNT / 2) {
            data[0] = -1; // test our test!
          }
          q.put(data); data = null; // don't own this any more, must not touch it?
        }
      } catch (InterruptedException ie) {
        System.out.println("Unexpected!!! " + ie);
      }
      System.out.println("Producer completed");
    };

    Runnable consumer = () -> {
      int[] data = null;
      System.out.println("Consumer starting");
      try {
        for (int c = 0; c < ITEM_COUNT; c++) {
          if (c > ITEM_COUNT - BUFFER_TEST) {
            Thread.sleep(1); // Test buffer full case
          }
          data = q.take();
          if (data[0] != data[1] || data[0] != c) {
            System.out.println("***** ERROR, got " + Arrays.toString(data)
              + " for count " + c);
          }
        }
      } catch (InterruptedException ie) {
        System.out.println("Unexpected!!! " + ie);
      }
      System.out.println("Consumer completed");
    };

    Thread p = new Thread(producer);
    Thread c = new Thread(consumer);
    p.start();
    c.start();
    System.out.println("Main completed...");
  }
}
