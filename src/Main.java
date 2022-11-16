public class Main {


    public static void main(String[] args) {


           try {
               // How to compile javac [ options ] [ sourcefiles ] [ classes ] [ @argfiles ]
               // javac Main.java -d ../executable
               HalProcessor processor = new HalProcessor( new Accumulator(), new Register());
               TimingControl timingControl = new TimingControl(new ProgramMemory(), processor);

               // if arguments contains HAL Programm
               if (args.length > 0 && args[0].contains(".txt")) {

                   // read program
                   HalProgramReader halProgramReader = new HalProgramReader();
                   halProgramReader.read_file(args[args.length - 1]);

                   // init program memory
                   timingControl.initProgramMemory(halProgramReader.getInstructionParams());
                   timingControl.printMemory();
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
