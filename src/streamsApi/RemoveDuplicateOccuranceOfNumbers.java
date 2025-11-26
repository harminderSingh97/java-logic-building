package streamsApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** Remove duplicate occurance of Numbers Except First and
  Last in output and also Ignore if that number comes only once..
 */
public class RemoveDuplicateOccuranceOfNumbers {
    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1, 3, 3, 3, 3, 1, 1, 4, 9, 8, 7, 7, 7, 8, 9);
        Integer first = input.get(0);
        Integer last = input.get(input.size() - 1);
        List<Integer> middle = IntStream.range(1, input.size() - 1)
                .filter(i -> (i > 0 && input.get(i - 1).equals(input.get(i))) ||
                        (i < input.size() - 1 && input.get(i + 1).equals(input.get(i))))
                .mapToObj(input::get)
                .distinct()  // Ensure uniqueness per number in consecutive groups
                .collect(Collectors.toList());
        List<Integer> output = new ArrayList<>();
        output.add(first);
        output.addAll(middle);
        output.add(last);
        System.out.println(output);  // Output: [1, 3, 1, 7, 9]
    }

}
