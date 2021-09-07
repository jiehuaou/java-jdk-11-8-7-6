package demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * tennis match point
 */
public class MatchPoint {
    // 3:0 (0,1,2) ,  4:3 , 5:4 , ...
    public static boolean isMatchPoint(String score){
        Pattern ptn = Pattern.compile("^3:[012]$|^4:3$|^5:4$|^6:5$|^7:6$|^8:7$|^9:8$");
        Matcher mtch = ptn.matcher(score);
        return mtch.find();
    }

    public static void main(String[] args) {
        //------- true -------
        System.out.println(isMatchPoint("3:0"));
        System.out.println(isMatchPoint("3:1"));
        System.out.println(isMatchPoint("3:2"));
        System.out.println(isMatchPoint("4:3"));
        System.out.println(isMatchPoint("5:4"));
        System.out.println(isMatchPoint("6:5"));
        System.out.println(isMatchPoint("7:6"));
        System.out.println(isMatchPoint("8:7"));
        System.out.println(isMatchPoint("9:8"));

        //----- false -----
        System.out.println("----------");
        System.out.println(isMatchPoint("1:3"));
        System.out.println(isMatchPoint("2:3"));
        System.out.println(isMatchPoint("3:3"));
        System.out.println(isMatchPoint("3:10"));
        System.out.println(isMatchPoint("3:11"));
        System.out.println(isMatchPoint("03:0"));
        System.out.println(isMatchPoint("3:0 3:0"));


    }
}
