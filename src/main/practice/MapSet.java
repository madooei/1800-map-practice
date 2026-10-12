package practice;

import java.util.Iterator;
import map.ArrayMap;
import map.Map;
import set.Set;

/**
 * An implementation of the Set ADT that stores its elements as the keys of a Map.
 *
 * @param <T> the type of elements in this set.
 */
public class MapSet<T> implements Set<T> {

  private Map<T, Boolean> map;  // the elements are the keys; every value is true

  public MapSet() {
    map = new ArrayMap<>();
  }

  @Override
  public boolean add(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    return map.put(item, true);  // put reports whether the key was new
  }

  @Override
  public boolean remove(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    return map.remove(item);
  }

  @Override
  public boolean contains(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    return map.containsKey(item);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public Iterator<T> iterator() {
    return map.iterator();  // the map iterates over its keys, which are the elements
  }
}
