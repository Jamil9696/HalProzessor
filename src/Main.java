
public class Main {


    public static void main(String[] args) {

        // How to compile javac [ options ] [ sourcefiles ] [ classes ] [ @argfiles ]
        // javac Main.java -d ../executable
        HalProcessor processor = new HalProcessor(new ProgramMemory(), new Accumulator());


        // if arguments contains HAL Programm
        if(args.length > 0 && args[0].contains(".txt")) {

            // read program and initialize program memory
            HalProgramReader halProgramReader = new HalProgramReader(processor);
            halProgramReader.read_file(args[args.length - 1]);

            // execute Instructions


            // exit program
            System.exit(0);
        }

        // if arguments are simply one line HAL Instructions
        if(args.length > 0){
            // execute Instruction

            // exit program
            System.exit(0);
        }


        System.exit(1);
    }
}
