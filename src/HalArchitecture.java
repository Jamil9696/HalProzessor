import java.util.List;
import java.util.Optional;

public class HalArchitecture {

    private final ProgramMemory programMemory;
    private final HalProcessor halProcessor;
    private final InputInterface inputInterface;
    private final OutputInterface outputInterface;
    private int programCounter = 0;
    private boolean isStarted = false;


    public HalArchitecture(
            ProgramMemory programMemory,
            HalProcessor halProcessor,
            InputInterface inputInterface,
            OutputInterface outputInterface) {
        this.programMemory = programMemory;
        this.halProcessor = halProcessor;
        this.inputInterface = inputInterface;
        this.outputInterface = outputInterface;
    }


    public boolean fetch() {
        Optional<Instruction> instruction = programMemory.getCurrentInstruction(programCounter);

        if(instruction.isEmpty()){
            return false;
        }

        decode(instruction.get());
        programCounter++;
        return true;
    }

    public void decode(Instruction instruction) {

        //  don't use enhanced switch (won't compile)
        switch (instruction.getInstructionTyp()) {
            case START:
                isStarted = true; // purpose
                break;
            case STOP:
                System.exit(1);
                break;
            case IN:
                halProcessor.in(inputInterface.read());
                break;
            case OUT:
                outputInterface.print(halProcessor.out());
                break;
            case LOAD:
                System.out.println("Hello");
                break;
            case LOADIND:
                System.out.println("Hello");
                break;
            case LOADNUM:
                System.out.println("Hello");
                break;
            case STORE:
                programMemory.store(halProcessor.out(), Float.valueOf(instruction.getParameter()));
                break;
            case STOREIND:
                programMemory.store(halProcessor.out(), Integer.valueOf(instruction.getParameter()));
                break;
            case ADD:
                halProcessor.add(Float.valueOf(instruction.getParameter()));
                break;
            case ADDNUM:
                System.out.println("Hello");
                break;
            case SUB:
                halProcessor.sub(Float.valueOf(instruction.getParameter()));
                break;
            case SUBNUM:
                System.out.println("Hello");
                break;
            case DIV:
                halProcessor.div(Float.valueOf(instruction.getParameter()));
                break;
            case DIVNUM:
                System.out.println("Hello");
                break;
            case MUL:
                halProcessor.mul(Float.valueOf(instruction.getParameter()));
                break;
            case MULNUM:
                System.out.println("Hello");
                break;
            case JUMP:
                System.out.println("Hello");
                break;
            case JUMPNULL:
                System.out.println("Hello");
                break;
            case JUMPNEG:
                System.out.println("Hello");
                break;
            case JUMPPOS:
                System.out.println("Hello");
                break;
            default:
                System.out.println("Invalid instruction");
        }
    }


    public void initProgramMemory(String instruction, String instructionParam) throws IllegalArgumentException {
        programMemory.push(new Instruction(InstructionSet.valueOf(instruction), instructionParam));
    }

    public void printMemory() {
        programMemory.printMemory();
    }


    public void initProgramMemory(List<String[]> params) {
        params.forEach(param -> initProgramMemory(param[0], param[1]));
    }


}
