package practice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import map.Map;
import set.Set;
import set.SetTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Runs the Set contract suite against MapSet. */
public class MapSetTest extends SetTest {

  @Override
  protected Set<Integer> createSet() {
    return new MapSet<>();
  }

  @Test
  public void addedItemsAreTheKeysOfTheMap() {
    set.add(4);
    set.add(9);
    set.add(6);
    List<Integer> expected = new ArrayList<>();
    expected.add(4);
    expected.add(6);
    expected.add(9);
    assertEquals(expected, sortedKeysOfMap(set));
  }

  @Test
  public void duplicateAddLeavesOneKeyInTheMap() {
    set.add(4);
    set.add(4);
    assertEquals(1, mapOf(set).size());
  }

  @Test
  public void removedItemIsNoLongerAKeyOfTheMap() {
    set.add(4);
    set.add(9);
    set.remove(4);
    List<Integer> expected = new ArrayList<>();
    expected.add(9);
    assertEquals(expected, sortedKeysOfMap(set));
  }

  // Reads the set's private Map field through reflection. The field's name is
  // the implementer's choice, so it is found by its type.
  private static Map<?, ?> mapOf(Set<Integer> set) {
    for (Field field : set.getClass().getDeclaredFields()) {
      if (Map.class.isAssignableFrom(field.getType())) {
        try {
          field.setAccessible(true);
          return (Map<?, ?>) field.get(set);
        } catch (ReflectiveOperationException e) {
          throw new IllegalStateException("cannot read the Map field of MapSet");
        }
      }
    }
    throw new IllegalStateException("MapSet has no field of type Map");
  }

  // The keys of the set's map, sorted, so tests do not depend on the map's order.
  private static List<Integer> sortedKeysOfMap(Set<Integer> set) {
    List<Integer> keys = new ArrayList<>();
    for (Object key : mapOf(set)) {
      keys.add((Integer) key);
    }
    Collections.sort(keys);
    return keys;
  }
}
