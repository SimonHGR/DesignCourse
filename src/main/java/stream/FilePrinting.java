package stream;

import module java.base;

interface ExFunction<A, R> {
  R apply(A a) throws Throwable;
}

public class FilePrinting {
  public static <A, R> Function<A, Stream<R>> wrap(ExFunction<A, Stream<R>> op) {
    return a -> {
      try {
        return op.apply(a);
      } catch (Throwable t) {
        System.out.println("Ooops, that broke: " + t.getMessage());
        return Stream.empty();
      }
    };
  }


  public static void main() {
    Stream.of("A.txt", "B.txt", "C.txt")
//        .flatMap(fn -> Files.lines(Path.of(fn)))
        .flatMap(FilePrinting.wrap(fn -> Files.lines(Path.of(fn))))
        .forEachOrdered(IO::println);
  }
}
