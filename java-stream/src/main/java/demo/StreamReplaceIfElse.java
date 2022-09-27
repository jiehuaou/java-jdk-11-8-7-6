package demo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamReplaceIfElse {
    public static void main(String[] args) {

        Map<Predicate<String>, Function<String, String>> rules = new HashMap<>();

        final Predicate<String> rule1 = (x)->x.equalsIgnoreCase("world");
        final Predicate<String> rule2 = (x)->x.equalsIgnoreCase("hello");
        final Predicate<String> rule3 = (x)->x.equalsIgnoreCase("web");

        rules.put(rule1, (x) -> "this is world : " + x);
        rules.put(rule2, (x) -> "this is hello : " + x);
        rules.put(rule3, (x) -> "this is web : " + x);

        String result = applyRule(rules, "hello");
        System.out.println("result -> " + result);

        result = applyRule(rules, "world");
        System.out.println("result -> " + result);

        result = applyRule(rules, "123");
        System.out.println("result -> " + result);

    }

    public static String applyRule(Map<Predicate<String>, Function<String, String>> rules, String param) {
        return rules.entrySet().stream()
                .filter(e -> e.getKey().test(param))
                .findFirst()
                .map(e -> e.getValue().apply(param))
                .orElseGet(() -> "unknown : " + param); // default logic
    }
}
