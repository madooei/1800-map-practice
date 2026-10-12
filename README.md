# Map — Practice

Two practice problems. `SortedArrayMap` is a map that keeps its keys in a sorted array and their values in a second array lined up with it, so that `get` and `containsKey` can use binary search. The map is iterable over its keys and over its entries, and both come out in ascending key order. `MapSet` is a set that stores its elements as the keys of a map and passes each operation on to that map. Its iterator is the map's key iterator.

## Prerequisites

- JDK 17+
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      map/
        Map.java                        # the chapter's Map ADT contract, vendored
        ArrayMap.java                   # the chapter's array-backed map, vendored
      set/
        Set.java                        # the iterable Set ADT contract, vendored
      practice/
        SortedArrayMap.java             # parallel sorted arrays, binary-search lookup
        MapSet.java                     # a set stored as the keys of a map
    test/
      map/
        MapTest.java                    # the chapter's Map contract suite, vendored
      set/
        SetTest.java                    # the iterable Set contract suite, vendored
      practice/
        SortedArrayMapTest.java         # MapTest plus ascending-order checks
        MapSetTest.java                 # SetTest plus checks on the backing map
  scripts/
    test.sh                             # compile and run every JUnit test
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh practice.SortedArrayMapTest` — compiles everything and runs only that test class.
- `scripts/test.sh practice.MapSetTest` — compiles everything and runs only that test class.

There are no demo programs for these problems; the tests are how you check your work.

## What's here

- `map.Map<K, V>` — the chapter's `Map` ADT, including `Map.Entry`. It is copied here unchanged, so this problem's code compiles and runs on its own.
- `practice.SortedArrayMap<K extends Comparable<K>, V>` — the sorted map. A binary-search helper finds a key or the position where it belongs. `put` and `remove` shift both arrays to keep them in step. The entry iterator builds each `Map.Entry` from the two arrays as it goes.
- `map.MapTest` — the chapter's abstract contract suite for `Map`, copied here unchanged. `SortedArrayMapTest` extends it.
- `practice.SortedArrayMapTest` — runs the contract suite against `SortedArrayMap`, and also checks that `iterator()` and `entries()` return the keys in ascending order.
- `map.ArrayMap<K, V>` — the chapter's array-backed map, copied here with its full code, so `MapSet` has a working map to store its elements in.
- `set.Set<T>` — the `Set` ADT as the iterator chapter left it: it extends `Iterable<T>`. It is copied here unchanged.
- `practice.MapSet<T>` — the set backed by a map. It stores each element as a key of an `ArrayMap<T, Boolean>` with the value `true`, and passes `add`, `remove`, `contains`, and `size` on to the map. Its iterator is the map's key iterator.
- `set.SetTest` — the abstract contract suite for `Set`, copied from the iterator chapter. `MapSetTest` extends it.
- `practice.MapSetTest` — runs the contract suite against `MapSet`, and also reads the set's private `Map` field through reflection to check that the elements are stored as its keys.
