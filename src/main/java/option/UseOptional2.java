package option;

import module java.base;

interface ExFunction<A, R> {
  R apply(A a) throws Throwable;

  public static <A, R> Function<A, Optional<R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Optional.of(op.apply(a));
      } catch (Throwable t) {
        return Optional.empty();
      }
    };
  }
}

public class UseOptional2 {
  public static void main() {
    Stream.of("A.txt", "B.txt", "C.txt")
        .map(ExFunction.wrap(fn -> Files.lines(Path.of(fn))))
        .peek(o -> {if (o.isEmpty()) IO.println("Problem!");})
        .filter(Optional::isPresent)
        .flatMap(Optional::get)
        .forEachOrdered(IO::println);
  }
}
