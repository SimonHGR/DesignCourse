package arraysaslist;

import module java.base;

public class Demo {
  static void main() {
    Integer[] nums = {0,1,2,3,4,5};
    List<Integer> li = Arrays.asList(nums);

    IO.println(li);

    nums[2] = 99;
    IO.println(li);

    li.set(4, 100);
    IO.println(li);
    IO.println(Arrays.toString(nums));

    var res = Stream.iterate(1, x -> x + 1)
        .limit(10)
        .toList();

    var res2 = List.copyOf(res);
    IO.println("same object? " + (res == res2));

    List<String> ls1 = List.of("A", "B");
    List<String> ls2 = List.of("A", "B");
    List<String> ls3 = List.copyOf(ls2);
    IO.println("ls1 == ls2? " + (ls1 == ls2));
    IO.println("ls3 == ls2? " + (ls3 == ls2));

//    int const;
//    int goto;
//    var var = 10;
//    var = "Hello";

//    var l = new ArrayList<String>();
    List<String> l = new ArrayList<String>();

    Function<String, String> upc = (String s) -> {
      String upcString = s.toUpperCase();
      return upcString;
    };

    IO.println(upc.getClass().getName());
    Method [] methods = upc.getClass().getMethods();
    for (Method m : methods) {
      IO.println(m);
    }
  }
}
