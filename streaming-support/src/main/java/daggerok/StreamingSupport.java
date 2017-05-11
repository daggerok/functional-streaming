package daggerok;

import javafx.util.Pair;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class StreamingSupport {

  public static final String[] keys = {
      "mon", "tue", "wed", "thu", "fri", "sat", "sun"
  };
  public static final String[] values = {
      "1st", "2nd", "3rd", "4th", "5th", "6th", "7th"
  };

  public static final List<Pair<String, String>> pairs = IntStream.range(0, keys.length)
                                                                  .mapToObj(i -> new Pair<>(keys[i], values[i]))
                                                                  .collect(toList());

  public static String genKey() {
    return gen(keys);
  }

  public static String genValue() {
    return gen(values);
  }

  private static String gen(String... xs) {
    return xs[new Random().nextInt(xs.length)];
  }

  private StreamingSupport() {}
}
