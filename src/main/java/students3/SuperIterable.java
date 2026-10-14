package students3;

import module java.base;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

//  public void showAll(SuperIterable<E> this) {
// this is actually "forEach" :)
//  public void forEvery(Consumer<E> op) {
//    for (E s : self) {
//      op.accept(s);
//    }
//  }

  public SuperIterable<E> filter(SuperIterable<E> this, Predicate<E> crit) {
    ArrayList<E> res = new ArrayList<>();
    for (E s : self) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
    return new SuperIterable<>(res);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
