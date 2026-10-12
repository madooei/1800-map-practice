package practice;

import java.util.Iterator;
import java.util.NoSuchElementException;
import map.Map;

/**
 * A sorted-array implementation of the Map ADT.
 *
 * @param <K> the type of keys in this map.
 * @param <V> the type of values in this map.
 */
public class SortedArrayMap<K extends Comparable<K>, V> implements Map<K, V> {

  private K[] keys;    // kept in ascending order
  private V[] values;  // values[i] is the value for keys[i]
  private int size;    // how many pairs the map holds

  // Comparable[] (not Object[]) for keys because K is bounded by Comparable.
  // keys only ever holds K and values only ever holds V, so the casts are safe.
  @SuppressWarnings("unchecked")
  public SortedArrayMap() {
    keys = (K[]) new Comparable[10];  // start with room for 10 pairs
    values = (V[]) new Object[10];
    size = 0;                         // the map starts empty
  }

  @Override
  public V get(K key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    int i = indexOf(key);
    if (i == -1) {
      return null;  // key not present
    }
    return values[i];
  }

  @Override
  public boolean containsKey(K key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    return indexOf(key) != -1;
  }

  @Override
  public boolean put(K key, V value) {
    if (key == null || value == null) {
      throw new IllegalArgumentException();
    }
    int i = insertionPoint(key);
    if (i < size && keys[i].equals(key)) {
      values[i] = value;  // key present; replace its value
      return false;
    }
    if (size == keys.length) {
      grow();
    }
    for (int j = size; j > i; j--) {  // shift later keys and values right
      keys[j] = keys[j - 1];
      values[j] = values[j - 1];
    }
    keys[i] = key;
    values[i] = value;
    size++;
    return true;
  }

  @Override
  public boolean remove(K key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    int i = indexOf(key);
    if (i == -1) {
      return false;  // not present; nothing changed
    }
    for (int j = i; j < size - 1; j++) {  // shift later keys and values left
      keys[j] = keys[j + 1];
      values[j] = values[j + 1];
    }
    keys[size - 1] = null;  // clear the vacated slots
    values[size - 1] = null;
    size--;
    return true;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<K> iterator() {
    return new KeyIterator();
  }

  @Override
  public Iterable<Map.Entry<K, V>> entries() {
    return new Entries();
  }

  // Returns the index of key, or -1 if key is not found.
  private int indexOf(K key) {
    int i = insertionPoint(key);
    if (i < size && keys[i].equals(key)) {
      return i;
    }
    return -1;
  }

  // Index where key sits, or where it would belong if absent (binary search).
  private int insertionPoint(K key) {
    int low = 0;
    int high = size - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int cmp = keys[mid].compareTo(key);
      if (cmp == 0) {
        return mid;
      } else if (cmp < 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }

  // Doubles the capacity of both arrays. Same cast rationale as the constructor.
  @SuppressWarnings("unchecked")
  private void grow() {
    K[] biggerKeys = (K[]) new Comparable[keys.length * 2];
    V[] biggerValues = (V[]) new Object[values.length * 2];
    for (int i = 0; i < size; i++) {
      biggerKeys[i] = keys[i];
      biggerValues[i] = values[i];
    }
    keys = biggerKeys;
    values = biggerValues;
  }

  // A key-value pair built on demand, since the key and value live in separate arrays.
  private static class Pair<K, V> implements Map.Entry<K, V> {
    K key;
    V value;

    Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public K key() {
      return key;
    }

    @Override
    public V value() {
      return value;
    }
  }

  // Walks the filled prefix of keys, which yields the keys in ascending order.
  private class KeyIterator implements Iterator<K> {
    private int cursor = 0;  // index of the key that comes next

    @Override
    public boolean hasNext() {
      return cursor < size;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return keys[cursor++];
    }
  }

  // The Iterable that entries() returns. Each call to iterator() starts a new traversal.
  private class Entries implements Iterable<Map.Entry<K, V>> {
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
      return new EntryIterator();
    }
  }

  // Walks the filled prefix of both arrays, building a Pair for each index.
  private class EntryIterator implements Iterator<Map.Entry<K, V>> {
    private int cursor = 0;  // index of the pair that comes next

    @Override
    public boolean hasNext() {
      return cursor < size;
    }

    @Override
    public Map.Entry<K, V> next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Pair<K, V> pair = new Pair<>(keys[cursor], values[cursor]);
      cursor++;
      return pair;
    }
  }
}
