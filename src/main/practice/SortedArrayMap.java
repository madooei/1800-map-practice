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
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public V get(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean containsKey(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean put(K key, V value) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean remove(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public int size() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public Iterator<K> iterator() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public Iterable<Map.Entry<K, V>> entries() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Returns the index of key, or -1 if key is not found.
  private int indexOf(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Index where key sits, or where it would belong if absent (binary search).
  private int insertionPoint(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Doubles the capacity of both arrays. Same cast rationale as the constructor.
  @SuppressWarnings("unchecked")
  private void grow() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
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
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }

    @Override
    public V value() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }
  }

  // Walks the filled prefix of keys, which yields the keys in ascending order.
  private class KeyIterator implements Iterator<K> {
    private int cursor = 0;  // index of the key that comes next

    @Override
    public boolean hasNext() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }

    @Override
    public K next() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }
  }

  // The Iterable that entries() returns. Each call to iterator() starts a new traversal.
  private class Entries implements Iterable<Map.Entry<K, V>> {
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }
  }

  // Walks the filled prefix of both arrays, building a Pair for each index.
  private class EntryIterator implements Iterator<Map.Entry<K, V>> {
    private int cursor = 0;  // index of the pair that comes next

    @Override
    public boolean hasNext() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }

    @Override
    public Map.Entry<K, V> next() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }
  }
}
