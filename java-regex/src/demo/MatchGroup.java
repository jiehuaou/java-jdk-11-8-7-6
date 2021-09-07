package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchGroup {

    static void testMatchGroup1(){
        String line = "This order was placed for QT 3000 abc OK, hello 123 world,,999 oracle";
        // [space+alphabet] ? number+  [space+alphabet]
        Pattern pattern = Pattern.compile("([\\s[a-zA-Z]]+)?(\\d+)([\\s[a-zA-Z]]+)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println("---------");
            System.out.println("whole-group 0 -> [" + matcher.group(0) + "]");
            System.out.println("group 1: " + matcher.group(1));
            System.out.println("group 2: " + matcher.group(2));
            System.out.println("group 3: " + matcher.group(3));
        }
    }
    // lazy match
    static void testMatchGroup2(){
        String line = "This order was placed for QT3000! OK?";
        Pattern pattern = Pattern.compile("(.+?)(\\d+)(.*)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println("group 1: " + matcher.group(1));
            System.out.println("group 2: " + matcher.group(2));
            System.out.println("group 3: " + matcher.group(3));
        }
    }

    public static void main(String[] args) {
        testMatchGroup2();
    }
}
