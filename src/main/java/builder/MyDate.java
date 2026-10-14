package builder;

public class MyDate {
  private int day, month, year;

  private MyDate(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  @Override
  public String toString() {
    return "MyDate{" +
        "day=" + day +
        ", month=" + month +
        ", year=" + year +
        '}';
  }

  public static boolean isValidDate(int day, int month, int year) {
    return day > 0 && day <= daysInMonth(month, year);
  }

  public static int daysInMonth(int month, int year) {
    return switch (month) {
      case 9, 4, 6, 11 -> 30;
      case 1, 3, 5, 7, 8, 10, 12 -> 31;
      case 2 -> isLeapYear(year) ? 29 : 28;
      default -> throw new IllegalArgumentException("Bad Month value");
    };
  }

  public static boolean isValidMonth(int month) {
    return month > 0 && month < 13;
  }

  public static boolean isLeapYear(int year) {
    return year % 4 == 0 && !(year % 100 == 0) || (year % 400 == 0);
  }

  public static Builder builder() {
    return new Builder(0, 0, 0);
  }

  public static class Builder {
    private MyDate target;

    private Builder(int day, int month, int year) {
      target = new MyDate(day, month, year);
    }

    public Builder day(int day) {
      return new Builder(day, target.month, target.year);
    }

    public Builder month(int month) {
      return new Builder(target.day, month, target.year);
    }

    public Builder year(int year) {
      return new Builder(target.day, target.month, year);
    }

    public MyDate build() {
      if (isValidDate(target.day, target.month, target.year)) {
        return new MyDate(target.day, target.month, target.year);
      } else throw new IllegalArgumentException("Bad date");
    }
  }
}

class TryMyDate {
  static void main() {
    MyDate.Builder builder = MyDate.builder();
//    builder.build();
    MyDate.Builder b1 = builder.month(10).year(2026);
    MyDate today = b1.day(14).build();
    IO.println(today);
    MyDate tomorrow = b1.day(15).build();
    IO.println(tomorrow);

  }
}