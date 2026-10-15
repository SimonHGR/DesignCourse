package either;

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

  public static <L, R> Either<L, R> success(R right) {
    return new Either<>(null, right);
  }

  public static <L, R> Either<L, R> failure(L left) {
    return new Either<>(left, null);
  }

  public boolean isSuccess() {
    return left == null;
  }

  public boolean isFailure() {
    return left != null;
  }

  public L getFailure() {
    if (left == null) throw new IllegalStateException("Attempt to get left from a right-Either");
    else return left;
  }

  public R getSuccess() {
    if (left != null) throw new IllegalStateException("Attempt to get right from a left-Either");
    else return right;
  }

  public Either<L, R> ifFailed(Consumer<L> op) {
    if (left != null) op.accept(left);
    return this;
  }

  public R getSuccessOrElse(Supplier<R> sr) {
    if (left != null) return sr.get();
    else return right;
  }

  // Specific to use case of a Throwable on the left
  public static <R> Either<Throwable, R> recover(Either<Throwable, R> e, ExFunction<Throwable, R> recoverOp) {
    if (e.isSuccess()) return e;
    else try {
      Throwable t = e.getFailure();
      return Either.success(recoverOp.apply(t));
    } catch (Throwable t) {
      e.getFailure().addSuppressed(t);
      return e;
    }
  }

  public static <A, R> Function<A, Either<Throwable, R>> wrap(ExFunction<A, R> op) {
    return a -> {
      try {
        return Either.success(op.apply(a));
      } catch (Throwable t) {
        return Either.failure(t);
      }
    };
  }
}

public class UseEither {
  public static Stream<String> recoverMissingFile(Throwable t) throws Throwable {
    IO.println("Recovering from: " + t.getMessage());
    if (t.getMessage().equals("B.txt")) {
      return Files.lines(Path.of("D.txt"));
    }
    throw new IllegalStateException("Shouldn't try to recover from anything but B.txt");
  }

//  public static void main() {
//    Stream.of("A.txt", "B.txt", "C.txt")
//        .map(Either.wrap(f -> Files.lines(Path.of(f))))
//        .map(e -> e.ifFailed(t -> IO.println("Problem: " + t.getMessage())))
//        .map(e -> e.getSuccessOrElse(() -> Stream.empty()))
//        .flatMap(s -> s)
//        .forEachOrdered(IO::println);
//  }

  public static void main() {
    Stream.of("A.txt", "B.txt", "C.txt")
        .map(Either.wrap(f -> Files.lines(Path.of(f))))
        .map(e -> Either.recover(e, UseEither::recoverMissingFile))
        .map(e -> e.ifFailed(t -> IO.println("Persistent problem: " + t.getMessage())))
        .filter(Either::isSuccess)
        .flatMap(Either::getSuccess)
        .forEachOrdered(IO::println);
  }
}
