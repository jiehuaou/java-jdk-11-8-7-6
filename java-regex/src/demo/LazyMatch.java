package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LazyMatch {
    public static void main(String[] args) {
        //"123 456".match(/\d+ \d+?/)
        String line = "abc 123 456 ppp";
        Pattern pattern = Pattern.compile("\\d+\\s\\d+?");  // output [123 4] , ignore [56]
        Matcher matcher = pattern.matcher(line);

        System.out.println(matcher);

        while (matcher.find()) {
            System.out.println("--------------------------");
            System.out.println("whole-group 0 -> [" + matcher.group() + "]");
//            System.out.println("group 1: " + matcher.group(1));
//            System.out.println("group 2: " + matcher.group(2));
//            System.out.println("group 3: " + matcher.group(3));
        }
    }
}
