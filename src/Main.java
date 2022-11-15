public class Main {


    public static void main(String[] args) {


           try {
               // How to compile javac [ options ] [ sourcefiles ] [ classes ] [ @argfiles ]
               // javac Main.java -d ../executable
               HalProcessor processor = new HalProcessor(new ProgramMemory(), new Accumulator(), new Register());


               // if arguments contains HAL Programm
               if (args.length > 0 && args[0].contains(".txt")) {

                   // read program
                   HalProgramReader halProgramReader = new HalProgramReader();
                   halProgramReader.read_file(args[args.length - 1]);

                   // init program memory
                   processor.initProgramMemory(halProgramReader.getInstructionParams());
                   processor.printMemory();
                   // execute Instructions

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
