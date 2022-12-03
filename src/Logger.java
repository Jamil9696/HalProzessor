import java.util.Scanner;

public final class Logger {

    private static boolean IS_DEBUG = false;
    private static String TARGET = "";
    private static Float VALUE = 0f;

    private static Logger instance;

    private Logger () {}

    public static Logger getInstance () {
        if (Logger.instance == null) {
            Logger.instance = new Logger ();
        }
        return Logger.instance;
    }

    public void setDebugMode(boolean _isDebug){
        IS_DEBUG = _isDebug;
    }

    public void debug(String _target){
        if(!IS_DEBUG) return;

        synchronized (Logger.getInstance()) {
            if (!_target.equals(TARGET)) {
                TARGET = _target;
                VALUE = 0f;
            }
            System.out.println("Before Data change in " + TARGET + ". Value: " + VALUE);
        }
    }

    public void log(Float _value){
        if(!IS_DEBUG) return;

        synchronized (Logger.getInstance()) {
            VALUE = _value;
        }
    }


    public void info(){
        if(!IS_DEBUG) return;

        synchronized (Logger.getInstance()) {
            System.out.println("After Data change in " + TARGET + ". Value: " + VALUE);
            System.out.println();
        }
    }


    public void debugInfo(String debugInfo) {
        if(!IS_DEBUG) return;

        synchronized (Logger.getInstance()){
            System.out.println(debugInfo);
        }

    }

    public synchronized float synchronizedInput(String name){
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + " IN: ");
        Float  input = scanner.nextFloat();
        return input;
    }
}
