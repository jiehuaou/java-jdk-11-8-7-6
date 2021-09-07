package hello;

import java.util.Optional;

public class OptionalTest {
    public static void givenOptional_whenMapWorks_thenCorrect2() {
        String name = "baeldung";
        Optional<String> nameOptional = Optional.of(name);

        int len = nameOptional
                .map(String::length)
                .orElse(0);
        System.out.println("8 == " + len);
    }
    public static void main(String[] args) {
        givenOptional_whenMapWorks_thenCorrect2();
    }
}
