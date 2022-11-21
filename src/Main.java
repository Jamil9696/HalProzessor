public class Main {


    public static void main(String[] args) {

           long start = System.currentTimeMillis();

           System.out.println("Args: " + args);
           Logger logger = Logger.getInstance();
           try {

               if(args.length > 1 && args[args.length-2].equals("-d")) {
                   logger.setDebugMode(true);
               }

               // javac Main.java -d ../executable
               HalProcessor processor = new HalProcessor( new Accumulator());
               HalArchitecture halArchitecture = new HalArchitecture(
                       new ProgramMemory(new DataStorage(15)),
                       processor,
                       new InputInterface(),
                       new OutputInterface()
               );

               // if arguments contains HAL Programm
               if (args.length > 0 && args[args.length-1].contains(".txt")) {

                   // read program
                   HalProgramReader halProgramReader = new HalProgramReader();
                   halProgramReader.read_file(args[args.length - 1]);

                   // init program memory
                   halArchitecture.initProgramMemory(halProgramReader.getInstructionParams());
                   System.out.println("============ Current program ==============");
                   halArchitecture.printMemory();
                   System.out.println("=========================================");
                   // execute Instructions
                  while(halArchitecture.fetch());

               }

           }catch (Exception e){
            e.printStackTrace();
            }

        long end = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: "+ (end - start));

        }

}
