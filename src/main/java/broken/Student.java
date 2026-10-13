package broken;

import module java.base;

//
// Complete encapsulation, rules of integrity
//   enforced completely
// How to make student construction friendlier
// How to make Student immutable?
// How to construct pooled immutable students?
//
public class Student {
  public static final int MAX_COURSES = 5;
  String name; // Not null
  double gpa; // valid range 0 -> 4.0
  List<String> courses; // no duplicates

  public Student(String name) {
    this.name = name;
    this.courses = new ArrayList<>();
  }

  public Student(String name, ArrayList<String> courses) {
    this.name = name;
    this.courses = courses;
  }

  // return value indicates if registration successful
  public boolean addCourse(String course) {
    if (courses.size() < MAX_COURSES) {
      courses.add(course); // should refuse duplicates
      return true;
    } else {
      return false; // can't take any more courses
    }
  }

  public List<String> getCourses() {
    return courses;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getGpa() {
    return gpa;
  }

  public void setGpa(double gpa) {
    this.gpa = gpa;
  }
}
