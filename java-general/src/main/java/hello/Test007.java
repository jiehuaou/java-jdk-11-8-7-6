package hello;

import java.util.*;

public class Test007 {

    public static void main(String[] args) {
        String s1 = "abc ";
        String s2 = "abc ";
        String s3 = s1.concat(s2).concat("efg");
        System.out.println(s3);

        String.join(",", "abc", "def");

        StringJoiner joiner = new StringJoiner(",");
        joiner.add("abc").add("def");

        System.out.println(joiner.toString());

    }

    /**
     * Instantiate in constructor
     * @param args
     */
    public static void main1(String[] args) {
        // Slower
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList("one", "two", "three"));

        // Faster
        Set<String> set2 = new HashSet<>(Arrays.asList("one", "two", "three"));

        set2.contains("one");
    }

    /**
     * AddAll is faster than Add
     */

    public void main2() {
        Integer[] a = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            a[i] = i;
        }
        // faster
        ArrayList arr = new ArrayList<Integer>();
        arr.addAll(Arrays.asList(a));  // System.arraycopy
        // slower
        ArrayList arr2 = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++) {
            arr2.add(a[i]); // one by one copy
        }
    }

    public static enum Color {
        RED, YELLOW, GREEN
    }
    public void main3() {
        EnumSet<Color> colors = EnumSet.allOf(Color.class);
        //colors.forEach(el->System.out.println(el));
        System.out.println(colors);

        EnumSet<Color> colors2 = EnumSet.of(Color.RED, Color.GREEN);
        EnumSet<Color> colors3 = EnumSet.of(Color.RED, Color.YELLOW);
        colors2.retainAll(colors3);
//        colors2.forEach(el->System.out.println(el));
        System.out.println(colors2);

        colors2.addAll(colors3);
        System.out.println(colors2);

        System.out.println(Color.RED.name());
        System.out.println(Color.RED.ordinal());
        System.out.println(Color.RED.hashCode());

        Color c1 = Color.GREEN;
        Color c2 = Color.RED;
        Color c3 = Color.GREEN;
        Color c4 = Color.valueOf("YELLOW");
        Color c5 = Color.valueOf("GREEN");

        if(c1.equals(c3)){
            System.out.println(c1 + " == " + c3);
        }
        if(c1 != c2){
            System.out.println(c1 + " != " + c2);
        }
        colors2.add(c4);
        colors2.add(c5);
        System.out.println(colors2);

        String ss = "abc";
        ss.isEmpty();

    }

    public static void main5(String[] args) {
        new Test007().main3();
    }
}
