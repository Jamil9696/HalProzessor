
import java.util.List;

public class HalProcessor {


    private final ProgramMemory programMemory;
    private final Accumulator accumulator;
    private final Register register;
    private int programCounter = 0;

    public HalProcessor(ProgramMemory programMemory, Accumulator accumulator, Register register) {
        this.programMemory = programMemory;
        this.accumulator = accumulator;
        this.register = register;
    }

    public void initProgramMemory(List<String[]> params) {
        params.forEach(param -> initProgramMemory(param[0], param[1]));
    }

    public void fetch() {
        Instruction instruction = programMemory.getCurrentInstruction(programCounter);
        execute(instruction);
        programCounter++;
    }

    public void execute(Instruction instruction) {

        switch (instruction.getInstructionTyp()) {
            case START -> System.out.println("Hello");
            case STOP -> System.exit(1);
            case IN -> System.out.println("Hello");
            case OUT -> System.out.println("Hello");
            case LOAD -> System.out.println("Hello");
            case LOADIND -> System.out.println("Hello");
            case LOADNUM -> System.out.println("Hello");
            case STORE -> System.out.println("Hello");
            case STOREIND -> System.out.println("Hello");
            case ADD -> add(instruction.getParameter());
            case ADDNUM -> System.out.println("Hello");
            case SUB -> sub(instruction.getParameter());
            case SUBNUM -> System.out.println("Hello");
            case DIV -> div(instruction.getParameter());
            case DIVNUM -> System.out.println("Hello");
            case MUL -> mul(instruction.getParameter());
            case MULNUM -> System.out.println("Hello");
            case JUMP -> System.out.println("Hello");
            case JUMPNULL -> System.out.println("Hello");
            case JUMPNEG -> System.out.println("Hello");
            case JUMPPOS -> System.out.println("Hello");
        }
    }

    private void add(Float value) {
        accumulator.add(register.getValue(value));
    }

    private void sub(Float value) {
        accumulator.sub(register.getValue(value));
    }

    private void div(Float value) {
        accumulator.div(register.getValue(value));
    }

    private void mul(Float value) {
        accumulator.mul(register.getValue(value));
    }


    public void initProgramMemory(String instruction, String instructionParam) throws IllegalArgumentException {
        programMemory.push(new Instruction(InstructionSet.valueOf(instruction), instructionParam));
    }

    public void printMemory() {
        programMemory.printMemory();
    }


}
