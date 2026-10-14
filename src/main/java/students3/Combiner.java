package students3;

import module java.base;

//public class Combiner<E> implements Predicate<E> {
//  private Predicate<E> first;
//  private Predicate<E> second;
//
//  public Combiner(Predicate<E> second, Predicate<E> first) {
//    this.second = second;
//    this.first = first;
//  }
//
//  public boolean test(E target) {
//    return first.test(target) && second.test(target);
//  }
//}

public class Combiner {
  public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
//    Predicate<E> result = e -> first.test(e) && second.test(e);
//    return result;
    return e -> first.test(e) && second.test(e);
  }
}