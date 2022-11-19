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
        Optional<Instruction> optional = programMemory.getCurrentInstruction(programCounter);

        if(optional.isEmpty()){
            return false;
        }

        decode(optional.get());
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
                System.exit(0);
                break;
            case IN:
                halProcessor.in(inputInterface.read());
                break;
            case OUT:
                outputInterface.print(halProcessor.out());
                break;
            case LOAD:
                halProcessor.in(programMemory.load(Float.valueOf(instruction.registerNumber())));
                break;
            case LOADIND:
                System.out.println("Hello");
                break;
            case LOADNUM:
                System.out.println("Hello");
                break;
            case STORE:
                programMemory.store(halProcessor.out(), Float.valueOf(instruction.registerNumber()));
                break;
            case STOREIND:
                programMemory.store(halProcessor.out(), Integer.valueOf(instruction.registerNumber()));
                break;
            case ADD:
                halProcessor.add(programMemory.load(Float.valueOf(instruction.registerNumber())));
                break;
            case ADDNUM:
                halProcessor.add(Float.valueOf(instruction.registerNumber()));// constant
                break;
            case SUB:
                halProcessor.sub(programMemory.load(Float.valueOf(instruction.registerNumber())));
                break;
            case SUBNUM:
                System.out.println("Hello");
                break;
            case DIV:
                halProcessor.div(programMemory.load(Float.valueOf(instruction.registerNumber())));
                break;
            case DIVNUM:
                System.out.println("Hello");
                break;
            case MUL:
                halProcessor.mul(programMemory.load(Float.valueOf(instruction.registerNumber())));
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
