package option;

import module java.base;

public class UseOptional {
  public static Optional<Stream<String>> getLines(String fn) {
    try {
      return Optional.of(Files.lines(Path.of(fn)));
    } catch(IOException ioe) {
      return Optional.empty();
    }
  }

  public static void main() {
    Stream.of("A.txt", "B.txt", "C.txt")
        .map(UseOptional::getLines)
        .peek(o -> {if (o.isEmpty()) IO.println("Problem!");})
        .filter(Optional::isPresent)
        .flatMap(Optional::get)
        .forEachOrdered(IO::println);
  }
}
