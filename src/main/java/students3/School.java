package students3;

import module java.base;

public class School {


  public static void main() {
    SuperIterable<Student> sis = new SuperIterable<>(List.of(
        new Student("Alice", 3.8, List.of("Math", "Physics", "Chemistry")),
        new Student("Bob", 2.2, List.of("Art", "Journalism")),
        new Student("Hua", 3.9, List.of("Quantum Mechanics")),
        new Student("Susan", 3.9, List.of("Math", "Physics", "Quantum Mechanics", "Rocket Science")),
        new Student("Ishan", 2.0, List.of("Statistics", "Math", "Physics", "Quantum Mechanics", "Rocket Science"))
    ));

    for (Student s : sis) {
      System.out.println(s);
    }

    System.out.println("--------------------------------");
//    sis.forEvery(e -> IO.println(e));
//    sis.forEvery(IO::println);
    sis.forEach(IO::println);
    System.out.println("--------------------------------");
    sis.forEach(s -> IO.println("A student is " + s));
    System.out.println("--------------------------------");
    sis.filter(s -> s.getCourses().size() > 3).forEach(IO::println);
    System.out.println("--------------------------------");
    new SuperIterable<>(List.of("Alice", "Bob", "Maverick", "Susan"))
        .filter(s -> s.length() > 3)
        .forEach(IO::println);
    System.out.println("--------------------------------");

    Predicate<Student> enthusiasticPred = s -> s.getCourses().size() > 3;
    Predicate<Student> smartPred = s -> s.getGpa() >= 3;
    Predicate<Student> notSmartPred = s -> s.getGpa() < 3;
//    Predicate<Student> enthusiasticButNotSmart = s -> s.getCourses().size() > 3 && s.getGpa() < 3;
//    Predicate<Student> enthusiasticButNotSmart = new Combiner<>(
//        enthusiasticPred,
//        notSmartPred
//    );
    sis
//        .filter(enthusiasticPred)
//        .filter(notSmartPred)
//        .filter(enthusiasticButNotSmart)
        .filter(Combiner.and(enthusiasticPred, notSmartPred))
        .forEach(IO::println);
  }
}