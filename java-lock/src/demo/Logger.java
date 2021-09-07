package demo;

public class Logger {
    String logClass = "";
    public Logger() {
        //
    }
    public Logger(Class className) {
        String [] ss = className.getName().split("\\.");
        logClass = ss[ss.length-1];
    }
    public void info(String msg) {
        System.out.println(logClass + "-" + msg);
    }

    void println(final String text) {
        System.out.println(text);
    }
}