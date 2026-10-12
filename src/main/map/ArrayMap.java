package map;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-backed implementation of the Map ADT.
 *
 * @param <K> the type of keys in this map.
 * @param <V> the type of values in this map.
 */
public class ArrayMap<K, V> implements Map<K, V> {

  private Entry<K, V>[] arr;  // the backing array
  private int size;           // how many pairs the map holds

  private static class Entry<K, V> implements Map.Entry<K, V> {
    K key;
    V value;

    Entry(K key, V value) {
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

  // arr only ever holds Entry<K, V>, so the cast is safe.
  @SuppressWarnings("unchecked")
  public ArrayMap() {
    arr = (Entry<K, V>[]) new Entry[10];  // start with room for 10 pairs
    size = 0;                             // the map starts empty
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
    return arr[i].value;
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
    int i = indexOf(key);
    if (i != -1) {
      arr[i].value = value;  // key present; replace its value
      return false;
    }
    if (size == arr.length) {
      grow();                // grow when the array is full
    }
    arr[size] = new Entry<>(key, value);  // append at the end
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
      return false;          // not present; nothing changed
    }
    arr[i] = arr[size - 1];  // move the last entry into the gap
    arr[size - 1] = null;    // clear the vacated slot
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

  // Returns the index of the entry holding key, or -1 if key is not found.
  private int indexOf(K key) {
    for (int i = 0; i < size; i++) {
      if (arr[i].key.equals(key)) {
        return i;
      }
    }
    return -1;
  }

  // Doubles the capacity. Same cast rationale as the constructor.
  @SuppressWarnings("unchecked")
  private void grow() {
    Entry<K, V>[] bigger = (Entry<K, V>[]) new Entry[arr.length * 2];
    for (int i = 0; i < size; i++) {
      bigger[i] = arr[i];
    }
    arr = bigger;
  }

  // Walks the filled prefix of the backing array, returning each entry's key.
  private class KeyIterator implements Iterator<K> {
    private int cursor = 0;  // index of the entry whose key comes next

    @Override
    public boolean hasNext() {
      return cursor < size;
    }

    @Override
    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return arr[cursor++].key;
    }
  }

  // The Iterable that entries() returns. Each call to iterator() starts a new traversal.
  private class Entries implements Iterable<Map.Entry<K, V>> {
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
      return new EntryIterator();
    }
  }

  // Walks the filled prefix of the backing array, returning each entry itself.
  private class EntryIterator implements Iterator<Map.Entry<K, V>> {
    private int cursor = 0;  // index of the entry that comes next

    @Override
    public boolean hasNext() {
      return cursor < size;
    }

    @Override
    public Map.Entry<K, V> next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return arr[cursor++];
    }
  }
}
