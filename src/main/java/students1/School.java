package students1;

import module java.base;

interface Criterion {
  boolean test(Student s);
}

class SmartStudentCriterion implements Criterion {
  @Override
  public boolean test(Student s) {
    return s.gpa > 3;
  }
}

class EnthusiasticStudentCriterion implements Criterion {

  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 3;
  }
}

public class School {
  public static void showAllStudents(List<Student> ls) {
    for (var s : ls) {
      System.out.println(s);
    }
  }

//  public static void showSmartStudents(List<Student> ls) {
//    for (var s : ls) {
//      if (s.gpa > 3) {
//        System.out.println(s);
//      }
//    }
//  }

  public static List<Student> getSmartList(List<Student> ls, double threshold) {
    ArrayList<Student> res = new ArrayList<>();
    for (Student s : ls) {
      if (s.gpa > threshold) {
        res.add(s);
      }
    }
    return res;
  }

  private static double smartnessThreshold = 3;
  public static List<Student> getSmartList(List<Student> ls) {
    return getSmartList(ls, smartnessThreshold);
  }

  public static List<Student> getEnthusiasticList(List<Student> ls, int threshold) {
    ArrayList<Student> res = new ArrayList<>();
    for (Student s : ls) {
      if (s.getCourses().size() > threshold) {
        res.add(s);
      }
    }
    return res;
  }

  public static List<Student> getListByCriterion(Iterable<Student> ls, Criterion crit) {
    ArrayList<Student> res = new ArrayList<>();
    for (Student s : ls) {
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
//    showSmartStudents(roster);
    showAllStudents(getSmartList(roster));
    System.out.println("--------------------------------");
    showAllStudents(getSmartList(roster, 3.5));
    System.out.println("--------------------------------");
    showAllStudents(getSmartList(roster, 2));
    System.out.println("--------------------------------");
    showAllStudents(getEnthusiasticList(roster, 3));
    System.out.println("--------------------------------");
    showAllStudents(getListByCriterion(roster, new EnthusiasticStudentCriterion()));
    System.out.println("--------------------------------");
    showAllStudents(getListByCriterion(roster, new SmartStudentCriterion()));


  }
}