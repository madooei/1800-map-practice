package demo;

import map.Map;
import practice.SortedArrayMap;

/** A demo of SortedArrayMap on the fruit example the notes trace. */
public final class SortedArrayMapDemo {

  private SortedArrayMapDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    SortedArrayMap<String, Integer> map = new SortedArrayMap<>();

    // Each key is new, so each put returns true.
    System.out.println("put(pear, 3): " + map.put("pear", 3));
    System.out.println("put(apple, 5): " + map.put("apple", 5));
    System.out.println("put(fig, 2): " + map.put("fig", 2));

    // banana belongs between apple and fig, so fig and pear shift right.
    System.out.println("put(banana, 4): " + map.put("banana", 4));

    // Binary search finds fig. Expect 2.
    System.out.println("get(fig): " + map.get("fig"));

    // Expect true.
    System.out.println("containsKey(banana): " + map.containsKey("banana"));

    // apple is at index 0, so the three later pairs shift left.
    System.out.println("remove(apple): " + map.remove("apple"));

    // Expect 3.
    System.out.println("size: " + map.size());

    // The keys come out in ascending order: banana fig pear.
    System.out.print("keys:");
    for (String key : map) {
      System.out.print(" " + key);
    }
    System.out.println();

    // The pairs come out in ascending order of their keys.
    System.out.println("entries:");
    for (Map.Entry<String, Integer> entry : map.entries()) {
      System.out.println("  " + entry.key() + " -> " + entry.value());
    }
  }
}
