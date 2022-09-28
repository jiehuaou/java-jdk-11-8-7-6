package abc;


import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Optional;
import java.util.function.Function;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import lombok.extern.log4j.Log4j2;

/**
 * 1. make code simple
 * 2. invoke direct lambda without new class creation
 * 3. make lazy call conditionally
 */

@Log4j2
public class Lambda {

    static Function<String, String> func = (e)->{
        System.out.println("construct [hello world] " + e);
        return "hello " + "world " + e;
    };
    //private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Lambda.class);
    public static void main(String[] args) {

        makeLazyLogCall(1);  // lambda was call
        makeLazyLogCall(2);  // lambda not call 

        makeLazyOptionalCall("123");
        makeLazyOptionalCall(null);
    }

    static void makeLazyLogCall(int value){

        if(1 == value){
            Configurator.setLevel("abc", Level.DEBUG);
        }else if (2 == value) {
            Configurator.setLevel("abc", Level.INFO);
        }
          
        log.info("-->", () -> func.apply("info + lambda"));
        log.debug("--> ", func.apply("debug + not-lambda"));  // func always called when DEBUG,INFO
        log.debug("--> ", () -> func.apply("debug + lambda")); // lambda not called when Level==INFO
        System.out.println("");
    }

    static void makeLazyOptionalCall(String value){
        Optional<String> data = Optional.ofNullable(value);
        
        // lambda called only when Optional is empty 
        System.out.println("Optional data --> " + data.orElseGet(()->func.apply(value)));
        // func always called when Optional is empty ot not
        System.out.println("Optional data --> " + data.orElse(func.apply(value)));
        System.out.println("");
    }

}
