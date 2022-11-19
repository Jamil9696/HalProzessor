public class Main {


    public static void main(String[] args) {


           try {

               // javac Main.java -d ../executable
               HalProcessor processor = new HalProcessor( new Accumulator(), new DataStorage());
               HalArchitecture halArchitecture = new HalArchitecture(
                       new ProgramMemory(),
                       processor,
                       new InputInterface(),
                       new OutputInterface()
               );

               // if arguments contains HAL Programm
               if (args.length > 0 && args[0].contains(".txt")) {

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

               // if arguments are simply one line HAL Instructions


               while (true) {
                   // execute Instruction

                   // exit program
                   System.exit(0);
               }
           }catch (Exception e){
            e.printStackTrace();
            }
        }
}
