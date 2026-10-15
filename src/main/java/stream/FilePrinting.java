package stream;

import module java.base;

interface ExFunction<A, R> {
  R apply(A a) throws Throwable;
}

class Either<L, R> {
  private L left;
  private R right;

  private Either(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public static <L, R> Either<L, R> success(R r) {
    return new Either<>(null, r);
  }

  public static <L, R> Either<L, R> failure(L l) {
    return new Either<>(l, null);
  }

  public R getSuccess() {
    if (left != null) throw new IllegalStateException("Attempt to get success from a failure!");
    return right;
  }

  public void ifFailure(Consumer<L> op) {
    if (left != null) {
      op.accept(left);
    }
  }

  public boolean isSuccess() {
    return left == null;
  }

}


public class FilePrinting {
//  public static <A, R> Function<A, Stream<R>> wrap(ExFunction<A, Stream<R>> op) {
//    return a -> {
//      try {
//        return op.apply(a);
//      } catch (Throwable t) {
//        System.out.println("Ooops, that broke: " + t.getMessage());
//        return Stream.empty();
//      }
//    };
//  }

//  public static <A, R> Function<A, Optional<R>> wrap(ExFunction<A, R> op) {
//    return a -> {
//      try {
//        return Optional.of(op.apply(a));
//      } catch (Throwable t) {
//        System.out.println("Ooops, that broke: " + t.getMessage());
//        return Optional.empty();
//      }
//    };
//  }

  public static <A, R> Function<A, Either<Throwable, R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Either.success(op.apply(a));
      } catch (Throwable t) {
        System.out.println("Ooops, that broke: " + t.getMessage());
        return Either.failure(t);
      }
    };
  }

  public static void main() {
    Stream.of("A.txt", "B.txt", "C.txt")
//        .flatMap(fn -> Files.lines(Path.of(fn))) // Doesn't compile!!!

//        .flatMap(FilePrinting.wrap(fn -> Files.lines(Path.of(fn))))

//        .map(FilePrinting.wrap(fn -> Files.lines(Path.of(fn))))
//        .peek(o -> {
//          if (o.isEmpty()) {
//            IO.println("Uh oh, something failed!");
//          }
//        })
//        .filter(o -> o.isPresent())
//        .flatMap(o -> o.get())

        .map(FilePrinting.wrap(fn -> Files.lines(Path.of(fn))))
        .peek(e -> e.ifFailure(x -> IO.println("Failed with: " + x.getMessage())))
        .filter(e -> e.isSuccess())
        .flatMap(e -> e.getSuccess())
        .forEachOrdered(IO::println);
  }
}
