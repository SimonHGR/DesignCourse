package dynamicenum;

import module java.base;


// NOTE, This class does not work directly if serialized/deserialized
public final class Course {
  private static final HashMap<Course, Course> values = new HashMap<>();
  private final String name;
  private final String department;

  private Course(String name, String department) {
    if (name == null || department == null) {
      throw new IllegalArgumentException("nulls not allowed for name/department");
    }
    this.name = name;
    this.department = department;
  }

  @Override
  public boolean equals(Object obj) {
    return this == obj
    || (obj instanceof Course c
        && c.name == this.name
        && c.department == this.department);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, department);
  }

  public static Course of(String name, String department) {
    Course template = new Course(name, department);

    Course c = values.get(template);
    if (c == null) {
      // verify if we should create this!!!
      values.put(template, template);
      c = template;
    }
    return c;
  }

  public String getName() {
    return name;
  }
  public String getDepartment() {
    return department;
  }

  @Override
  public String toString() {
    return "Course{" +
        "name='" + name + '\'' +
        ", department='" + department + '\'' +
        '}';
  }
}

class UseCourse {
  public static void showCourseName(Course c) {
    System.out.println(c.getName());
  }

  public static void main() {
    Course math1 = Course.of("Mathematics 101", "Mathematics");
    Course math2 = Course.of("Mathematics 101", "Mathematics");
    Course math3 = Course.of("Mathematics 201", "Mathematics");
    Course stat1 = Course.of("Statistics 101", "Mathematics");
    Course stat2 = Course.of("Statistics 101", "Mathematics");

    IO.println("Should be equal/==");
    IO.println("math1 == math2? " + (math1 == math2));
    IO.println("math1.equals(math2)? " + (math1.equals(math2)));
    IO.println("--------------------------------------");
    IO.println("Should NOT be equal/==");
    IO.println("math1 == math3? " + (math1 == math3));
    IO.println("math1.equals(math3)? " + (math1.equals(math3)));
    IO.println("--------------------------------------");
    IO.println("Should be equal/==");
    IO.println("stat1 == stat2? " + (stat1 == stat2));
    IO.println("stat1.equals(stat2)? " + (stat2.equals(stat2)));
  }
}