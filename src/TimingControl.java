import java.util.List;

public class TimingControl {

    private final ProgramMemory programMemory;
    private final HalProcessor halProcessor;
    private int programCounter = 0;

    public TimingControl(ProgramMemory programMemory, HalProcessor halProcessor) {
        this.programMemory = programMemory;
        this.halProcessor = halProcessor;
    }


    public void fetch() {
        Instruction instruction = programMemory.getCurrentInstruction(programCounter);
        decode(instruction);
        programCounter++;
    }

    public void decode(Instruction instruction) {

        switch (instruction.getInstructionTyp()) {
            case START:
                System.out.println("Hello");
                break;
            case STOP:
                System.exit(1);
                break;
            case IN:
                System.out.println("Hello");
                break;
            case OUT:
                System.out.println("Hello");
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
                System.out.println("Hello");
                break;
            case STOREIND:
                System.out.println("Hello");
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
