package demo;

import practice.MapSet;

/** A demo of MapSet on the sequence of calls the notes trace. */
public final class MapSetDemo {

  private MapSetDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    MapSet<Integer> set = new MapSet<>();

    // 4 and 9 are new, so both adds return true.
    System.out.println("add(4): " + set.add(4));
    System.out.println("add(9): " + set.add(9));

    // 4 is already a key of the map, so this add returns false.
    System.out.println("add(4): " + set.add(4));

    // Expect true.
    System.out.println("contains(9): " + set.contains(9));

    // 7 is not a key of the map, so remove returns false.
    System.out.println("remove(7): " + set.remove(7));

    // 4 is a key of the map, so remove returns true.
    System.out.println("remove(4): " + set.remove(4));

    // Expect 1.
    System.out.println("size(): " + set.size());

    // The iterator is the map's key iterator. Expect 9.
    System.out.print("elements:");
    for (Integer element : set) {
      System.out.print(" " + element);
    }
    System.out.println();
  }
}
