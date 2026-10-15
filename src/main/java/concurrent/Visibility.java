package concurrent;

public class Visibility {
  public static volatile boolean stop = false;

  static class Stopper implements Runnable {

    @Override
    public void run() {
      while (! stop)
        ;
      System.out.println("Stopper stopping!");
    }
  }

  static void main() throws Throwable {
    Thread st = new Thread(new Stopper());
    st.start();
    Thread.sleep(1_000);
    System.out.println("setting stop flag to true");
    stop = true;
    System.out.println("stop flag is: " + stop + " main exiting");
  }
}
