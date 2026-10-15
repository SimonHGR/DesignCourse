package immutable;

import module java.base;

class Average {
  private double sum;
  private long count;

  public Average() {
    this(0, 0);
  }

  private Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public Average include(double value) {
    return new Average(this.sum + value, count + 1);
  }

  public Average merge(Average other) {
    return new Average(this.sum + other.sum, this.count + other.count);
  }

  public OptionalDouble get() {
    if (count > 0) return OptionalDouble.of(sum/count);
    return OptionalDouble.empty();
  }
}

public class Averager {
  public static final long DATA_SIZE = 1_000_000_000;
  public static void main() {
    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(DATA_SIZE)
//        .parallel()
        .boxed()
//        .reduce(new Average(), Average::include, Average::merge)
        .reduce(new Average(),
            (a, v) -> a.include(v),
            (a1, a2) -> a1.merge(a2))
        .get()
        .ifPresentOrElse(a -> IO.println("Mean is: " + a),
            () -> IO.println("no data in stream"));
    long time = System.nanoTime() - start;
    System.out.printf("Time was: %3.6f\n", (time / 1_000_000_000.0));
  }
}
