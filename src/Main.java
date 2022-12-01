public class Main {


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        System.out.println("Args: " + args);
        Logger logger = Logger.getInstance();

        HalThreadSystem halArchitectureSystem = new HalThreadSystem();

        if(args.length > 1 && args[args.length-2].equals("-d")) {
            logger.setDebugMode(true);
        }


        if (args.length > 0 && args[args.length-1].contains(".txt")) {

           halArchitectureSystem.setUp(args[args.length-1]);

        }

        long end = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: "+ (end - start));
    }
}
