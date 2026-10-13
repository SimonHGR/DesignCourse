package students2;

import module java.base;

interface Criterion<E> {
  boolean test(E s);
}

class SmartStudentCriterion implements Criterion<Student> {
  @Override
  public boolean test(Student s) {
    return s.gpa > 3;
  }
}

class EnthusiasticStudentCriterion implements Criterion<Student> {

  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

class LongStringCriterion implements Criterion<String> {

  @Override
  public boolean test(String s) {
    return s.length() > 4;
  }
}


public class School {
  public static <E> void showAllStudents(List<E> ls) {
    for (var s : ls) {
      System.out.println(s);
    }
  }

  public static <E> List<E> getListByCriterion(Iterable<E> ls, Criterion<E> crit) {
    ArrayList<E> res = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main() {
    List<Student> roster = List.of(
        new Student("Alice", 3.8, List.of("Math", "Physics", "Chemistry")),
        new Student("Bob", 2.2, List.of("Art", "Journalism")),
        new Student("Susan", 3.9, List.of("Math", "Physics", "Quantum Mechanics", "Rocket Science"))
    );
    showAllStudents(roster);
    System.out.println("--------------------------------");
    showAllStudents(getListByCriterion(roster, new EnthusiasticStudentCriterion()));
    System.out.println("--------------------------------");
    showAllStudents(getListByCriterion(roster, new SmartStudentCriterion()));

    System.out.println("--------------------------------");
    showAllStudents(getListByCriterion(List.of("Alice", "Bob", "Maverick", "Susan"), new LongStringCriterion()));
  }
}