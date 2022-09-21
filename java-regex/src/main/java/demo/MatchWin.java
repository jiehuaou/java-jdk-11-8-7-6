package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * tennis win
 */
public class MatchWin {
    // 4:0 (0,1,2),  5:3 , 6:4, 7:5 ...
    public static boolean isWin(String score){
        Pattern ptn = Pattern.compile("^(4:[012])$|^(5:3)$|^(6:4)$|^(7:5)$|^(8:6)$|^(9:7)$");
        Matcher mtch = ptn.matcher(score);
        return mtch.find();
    }
}
