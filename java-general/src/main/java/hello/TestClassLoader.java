package hello;

//import com.sun.javafx.util.Logging;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * there are three different class loaders here;
 * application loader,
 * extension loader,
 * bootstrap (displayed as null) loader.
 */

@Slf4j
public class TestClassLoader {
    public static void main(String[] args) {
        System.out.println("Classloader of this class:"
                + TestClassLoader.class.getClassLoader());

        log.info("-->");

        System.out.println("Classloader of Logging:"
                + log.getClass().getClassLoader());

        System.out.println("Classloader of ArrayList:"
                + ArrayList.class.getClassLoader());
    }
}
