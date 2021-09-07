package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LazyMatch2 {
    public static void main(String[] args) {
        String line = "...<a href=\"link1\" class=\"doc\"> ... <a href=\"link2\" class=\"doc\">...";
        Pattern pattern = Pattern.compile("<a href=\".*?\" class=\".*?\">");
        Matcher matcher = pattern.matcher(line);

        System.out.println(matcher.toMatchResult());

        while (matcher.find()) {
            System.out.println("--------------------------");
            System.out.println("whole-group 0 -> [" + matcher.group() + "]");
//            System.out.println("group 1: " + matcher.group(1));
//            System.out.println("group 2: " + matcher.group(2));
//            System.out.println("group 3: " + matcher.group(3));
        }
    }
}
