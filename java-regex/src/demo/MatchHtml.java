package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * lazy match   <.+?>  try to find near ">"
 * greedy match <.+>   try to find last ">" at the end of string
 */
public class MatchHtml {
    public static void main(String[] args) {
        String line = "<div>This condition was 1>0 || 0<1 </div> not used <div>placed for QT3000 OK</div>";
        Pattern pattern = Pattern.compile("(<.+?>)(.*?)(</.+?>)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println("--------------------------");
            System.out.println("whole-group 0 -> [" + matcher.group() + "]");
            System.out.println("group 1: " + matcher.group(1));
            System.out.println("group 2: " + matcher.group(2));
            System.out.println("group 3: " + matcher.group(3));
        }
    }
}
