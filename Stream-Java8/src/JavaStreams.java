import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams {

    public static void main(String[] args) {

        System.out.println("Example 1");
        IntStream
                .range(1, 10)  //Range set the complete range of numbers from start to end
                .forEach(System.out::println);  //For each apply the same function to each element

        System.out.println("\nExample 2");
        IntStream
                .range(0, 10)
                .skip(5) //Skip function will skip the first given range of numbers
                //.forEach(System.out::println);
                .forEach(x -> System.out.println(x)); //x is the parameter passed into the for each function which carry the series of integers

        System.out.println("\nExample 3");
        System.out.println(
                IntStream
                        .range(1, 5)
                        .sum()); // Sum will calculate the sum of all numbers

        System.out.println("\nExample 4");
        Stream.of("Sahal", "Saib", "Faizan")
                .sorted() //Sorted will sort the elements in ascending order
                .findFirst()// find first will find the first element of stream
                .ifPresent(System.out::println);

        System.out.println("\nExample 5");
        Stream.of("Sahal", "Saib", "Faizan")
                .sorted() //Sorted will sort the elements in ascending order
                .forEach(x-> System.out.println(x));

        System.out.println("\nExample 6");
        Stream.of("Sahal", "Saib", "Faizan")
                .sorted() //Sorted will sort the elements in ascending order
                .findAny()// find any will find any element from stream
                .ifPresent(System.out::println);

        System.out.println("\nExample 7");
        System.out.println(
                Stream.of(1,2,3,4,5,"sahal")

                        .skip(3)
                        .count()
                //.count()
                );// count will return the count of elements in stream

        System.out.println("\nExample 8");
        String[] array = {"Java", "Python", "C#", "JavaScript"};
        Arrays.stream(array)
                .filter(x -> x.startsWith("J"))
                .sorted()
                .forEach(System.out::println);

        System.out.println("\nExample 9");
        Arrays.stream(new int[] {1,2,3,4,5,6,7,8,9})
                .map(x -> x*x)
                .forEach(x -> System.out.println(x));

        System.out.println("\nExample 10");
        Arrays.stream(new int[] {1,2,3,4,5,6,7,8,9})
                .map(x -> x*x)
                .average()
                .ifPresent(System.out::println);

        System.out.println("\nExample 11");
        List<String> people = Arrays.asList("sahal", "ahmed", "ahsan", "fahad", "kamran", "saib");
        people
                .stream()
                .sorted()
                .map(x -> x.toUpperCase())
                .filter(x-> x.startsWith("S"))
                .forEach(System.out::println);
    }
}
