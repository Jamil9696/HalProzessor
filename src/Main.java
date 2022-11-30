public class Main {


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        System.out.println("Args: " + args);
        Logger logger = Logger.getInstance();

        HalArchitectureSystem halArchitectureSystem = new HalArchitectureSystem();

        if(args.length > 1 && args[args.length-2].equals("-d")) {
            logger.setDebugMode(true);
        }


        if (args.length > 0 && args[args.length-1].contains(".txt")) {

           halArchitectureSystem.setUp(args[args.length-1]);

        }
    }
}
