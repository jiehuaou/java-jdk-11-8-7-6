package hello;

import com.sun.javafx.util.Logging;

import java.util.ArrayList;

/**
 * there are three different class loaders here;
 * application loader,
 * extension loader,
 * bootstrap (displayed as null) loader.
 */
public class TestClassLoader {
    public static void main(String[] args) {
        System.out.println("Classloader of this class:"
                + TestClassLoader.class.getClassLoader());

        System.out.println("Classloader of Logging:"
                + Logging.class.getClassLoader());

        System.out.println("Classloader of ArrayList:"
                + ArrayList.class.getClassLoader());
    }
}
