package equality;

import module java.base;

class Tire {
  private int diameter;
  private int width;

  public Tire(int width, int diameter) {
    this.width = width;
    this.diameter = diameter;
  }

  @Override
  public boolean equals(Object other) {
    if (other.getClass() != Tire.class)
      return false;
    return other instanceof Tire ot
        && ot.diameter == this.diameter
        && ot.width == this.width;
  }

  @Override
  public String toString() {
    return "Tire{" +
        "diameter=" + diameter +
        ", width=" + width +
        '}';
  }
}

class TruckTire extends Tire {
  private int load;

  public TruckTire(int width, int diameter, int load) {
    super(width, diameter);
    this.load = load;
  }

  @Override
  public String toString() {
    return "TruckTire{" +
        super.toString() +
        "load=" + load +
        '}';
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof TruckTire tt
        && this.load == tt.load
        && super.equals(other);

  }
}

public class UseTires {
  static void main() {
    Tire t1 = new Tire(155, 14);
    Tire t2 = new Tire(155, 15);
    Tire t3 = new Tire(155, 14);

    IO.println("t1.equals(t2)? " + (t1.equals(t2)));
    IO.println("t1.equals(t3)? " + (t1.equals(t3)));

    Tire tt1 = new TruckTire(155, 15, 10_000);
    Tire tt2 = new TruckTire(155, 14, 10_000);
    Tire tt3 = new TruckTire(155, 14, 10_000);

    IO.println("tt1.equals(tt2)? " + (tt1.equals(tt2)));
    IO.println("tt3.equals(tt2)? " + (tt3.equals(tt2)));

    IO.println("t3.equals(tt3)? " + (t3.equals(tt3)));
    IO.println("tt3.equals(t3)? " + (tt3.equals(t3)));


  }
}
