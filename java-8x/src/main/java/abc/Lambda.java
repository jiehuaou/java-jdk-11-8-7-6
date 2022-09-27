package abc;


import lombok.val;
import lombok.extern.log4j.Log4j2;

import java.util.Random;
import java.util.function.Function;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * 1. make code simple
 * 2. invoke direct lambda without new class creation
 * 3. make lazy call conditionally
 */

@Log4j2
public class Lambda {
    //private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Lambda.class);
    public static void main(String[] args) {

        makeLazyCall(1);  // lambda was call
        makeLazyCall(2);  // lambda not call 
    }

    static void makeLazyCall(int value){

        if(1 == value){
            Configurator.setLevel("abc", Level.DEBUG);
        }else if (2 == value) {
            Configurator.setLevel("abc", Level.INFO);
        }
        

        Function<Integer, String> lazy = (e)->{
                System.out.println("construct [hello world] " + e);
                return "hello " + "world";
            };
        
        log.info("--> {}", () -> lazy.apply(value));
        log.debug("--> {}", () -> lazy.apply(value)); // will not log when Level==INFO
    }
}
