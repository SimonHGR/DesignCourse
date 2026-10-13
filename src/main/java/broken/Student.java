package broken;

import module java.base;

//
// Complete encapsulation, rules of integrity
//   enforced completely
//   - how many "attacks" can you come up with?
// How to make student construction friendlier
//   Create factory, easy way for initialization of
//   courses list
// (not really appropriate for this class but:
//   create Builder
//   allow for building multiple students with
//   almost identical initializations)
// How to make Student immutable?
// How to construct pooled immutable students?
//
public final class Student {
  public static final int MAX_COURSES = 5;
  private String name; // Not null
  private double gpa; // valid range 0 -> 4.0
  private List<String> courses; // no duplicates

  private Student(String name, double gpa, List<String> courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  public static Student ofNameGpaCourses(String name, double gpa, String ... courses) {
    List<String> problems = validate(name, gpa, List.of(courses));
    if (!problems.isEmpty()) {
      problems.forEach(IO::println);
      throw new IllegalStateException("Bad parameters " + problems.stream().collect(Collectors.joining(", ")));
    }
//    return new Student(name, gpa, new ArrayList<>(List.of(courses)));
    return new Student(name, gpa, List.of(courses));
  }

  // return value indicates if registration successful
  public boolean addCourse(String course) {
    List<String> proposedCourses = new ArrayList<>(courses);
    proposedCourses.add(course);
    if (validate(this.name, this.gpa, proposedCourses).isEmpty()) {
      courses = List.copyOf(proposedCourses);
      return true;
    } else {
      return false; // can't take any more courses
    }
  }

  public List<String> getCourses() {
    return courses; // currently courses is a List.of or List.copyOf (i.e. unmodifiable)
    // alternatively , return List.copyOf your modifiable data
    // or Collections.unmodifiableXxx wrapper / proxy
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    // validate first
    this.name = name;
  }

  public double getGpa() {
    return gpa;
  }

  public void setGpa(double gpa) {
    // validate
    this.gpa = gpa;
  }

  public static List<String> validate(String name, double gpa, Collection<String> courses) {
    List<String> problems = new ArrayList<>();
    if (name == null) problems.add("Need a name");
    if (gpa < 0 || gpa > 4.0) problems.add("Invalid gpa");
    if (courses.size() > 5) problems.add("too many courses");
    return problems;
  }
}
