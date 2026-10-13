package single;

class Thing {
private static final Thing self = new Thing();
  private int x;

  class MyInnerThing {
    private int y;
    int showX() {
      return x;
    }
  }

  public int getY(MyInnerThing mit) {
    return mit.y;
  }

  private Thing() {
  }

  public static Thing getOne() {
    return self;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  @Override
  public String toString() {
    return "Thing{" +
        "x=" + x +
        '}';
  }

  class Thang extends Thing {
  }

}

public class UseSingleton {
  public static void main() {
//    Thing t = new Thing();
//    Thing t2 = new Thing();
    Thing t1 = Thing.getOne();
    Thing t2 = Thing.getOne();
    System.out.println("t1: " + t1);
    System.out.println("t2: " + t2);
    t1.setX(100);
    System.out.println("t1: " + t1);
    System.out.println("t2: " + t2);
  }
}
