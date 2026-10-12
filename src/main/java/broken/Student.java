package broken;

import module java.base;

public class Student {
  public static final int MAX_COURSES = 5;
  String name; // Must not be null
  List<String> courses;

  public Student(String name) {
    this.name = name;
    this.courses = new ArrayList<>();
  }

  public Student(String name, ArrayList<String> courses) {
    this.name = name;
    this.courses = courses;
  }

  public boolean addCourse(String course) {
    if (courses.size() < MAX_COURSES) {
      courses.add(course);
      return true;
    } else {
      return false; // can't take any more courses
    }
  }

  public List<String> getCourses() {
    return courses;
  }

}
