package practice;

import java.util.ArrayList;
import java.util.List;
import map.Map;
import map.MapTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Runs the Map contract suite against SortedArrayMap. */
public class SortedArrayMapTest extends MapTest {

  @Override
  protected Map<String, Integer> createMap() {
    return new SortedArrayMap<>();
  }

  @Test
  public void iteratorYieldsKeysInAscendingOrder() {
    map.put("c", 3);
    map.put("a", 1);
    map.put("b", 2);
    List<String> seen = new ArrayList<>();
    for (String key : map) {
      seen.add(key);
    }
    List<String> expected = new ArrayList<>();
    expected.add("a");
    expected.add("b");
    expected.add("c");
    assertEquals(expected, seen);
  }

  @Test
  public void entriesYieldKeysInAscendingOrder() {
    map.put("c", 3);
    map.put("a", 1);
    map.put("b", 2);
    List<String> seen = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : map.entries()) {
      seen.add(entry.key());
    }
    List<String> expected = new ArrayList<>();
    expected.add("a");
    expected.add("b");
    expected.add("c");
    assertEquals(expected, seen);
  }

  @Test
  public void iteratorPastInitialCapacityYieldsKeysInAscendingOrder() {
    String letters = "abcdefghijkl";
    for (int i = letters.length() - 1; i >= 0; i--) {
      map.put(letters.substring(i, i + 1), i);
    }
    List<String> seen = new ArrayList<>();
    for (String key : map) {
      seen.add(key);
    }
    List<String> expected = new ArrayList<>();
    for (int i = 0; i < letters.length(); i++) {
      expected.add(letters.substring(i, i + 1));
    }
    assertEquals(expected, seen);
  }
}
