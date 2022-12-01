import java.util.List;
import java.util.Optional;

public class HalArchitecture{

    private final ProgramMemory programMemory;
    private final HalProcessor halProcessor;
    private final InputInterface inputInterface;
    private final OutputInterface outputInterface;
    private boolean isDebug = true;

    private final Logger logger = Logger.getInstance();



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
        Optional<Instruction> optional = programMemory.getCurrentInstruction(halProcessor.getProgrammeCounter());

        if(optional.isEmpty()){
            return false;
        }


        logger.debugInfo(
                "Current PC: " + halProcessor.getProgrammeCounter() +
                ".Current Instruction: " + optional.get().getInstructionTyp().toString()
        );


        if(!decode(optional.get())){
            return false;
        }


        halProcessor.incrementPC();
        return true;
    }

    public boolean decode(Instruction instruction) {

        //  don't use enhanced switch (won't compile)
        switch (instruction.getInstructionTyp()) {
            case START:
                break;
            case STOP:
                return false;
            case IN:
                logger.debug("accumulator");
                halProcessor.in(inputInterface.input());
                logger.info();
                break;
            case OUT:
                outputInterface.print(halProcessor.out());
                break;
            case BUFIN:
                logger.debug("accumulator");
                halProcessor.in(inputInterface.read(Integer.parseInt(instruction.registerNumber())));
                logger.info();
                break;
            case BUFOUT:
                outputInterface.send(halProcessor.out(), Integer.valueOf(instruction.registerNumber()));
                break;
            case LOAD:
                logger.debug("register");
                halProcessor.in(programMemory.load(Float.valueOf(instruction.registerNumber())));
                logger.info();
                break;
            case LOADIND:
                logger.debug("register " + instruction.registerNumber());
                halProcessor.in(Integer.valueOf(instruction.registerNumber()));
                logger.info();
                break;
            case LOADNUM:
                logger.debug("register " + instruction.registerNumber());
                halProcessor.in(Float.valueOf(instruction.registerNumber()));
                logger.info();
                break;
            case STORE:
                logger.debug("register " + instruction.registerNumber());
                programMemory.store(halProcessor.out(), Float.valueOf(instruction.registerNumber()));
                logger.info();
                break;
            case STOREIND:
                logger.debug("register " + instruction.registerNumber());
                programMemory.store(halProcessor.out(), Integer.valueOf(instruction.registerNumber()));
                logger.info();
                break;
            case ADD:
                logger.debug("accumulator");
                halProcessor.add(programMemory.load(Float.valueOf(instruction.registerNumber())));
                logger.info();
                break;
            case ADDNUM:
                logger.debug("accumulator");
                halProcessor.add(Float.valueOf(instruction.registerNumber()));// constant
                logger.info();
                break;
            case SUB:
                logger.debug("accumulator");
                halProcessor.sub(programMemory.load(Float.valueOf(instruction.registerNumber())));
                logger.info();
                break;
            case SUBNUM:
                logger.debug("accumulator");
                halProcessor.sub(Float.valueOf(instruction.registerNumber()));// constant
                logger.info();
                break;
            case DIV:
                logger.debug("accumulator");
                halProcessor.div(programMemory.load(Float.valueOf(instruction.registerNumber())));
                logger.info();
                break;
            case DIVNUM:
                logger.debug("accumulator");
                halProcessor.div(Float.valueOf(instruction.registerNumber()));// constant
                logger.info();
                break;
            case MUL:
                logger.debug("accumulator");
                halProcessor.mul(programMemory.load(Float.valueOf(instruction.registerNumber())));
                logger.info();
                break;
            case MULNUM:
                logger.debug("accumulator");
                halProcessor.mul(Float.valueOf(instruction.registerNumber()));
                logger.info();
                break;
            case JUMP:
                 halProcessor.jump(Integer.parseInt(instruction.registerNumber()), programMemory.getProgramSize());
                break;
            case JUMPNULL:
                 halProcessor.jumpnull(Integer.parseInt(instruction.registerNumber()), programMemory.getProgramSize());
                break;
            case JUMPNEG:
                 halProcessor.jumpneg(Integer.parseInt(instruction.registerNumber()), programMemory.getProgramSize());
                break;
            case JUMPPOS:
                 halProcessor.jumppos(Integer.parseInt(instruction.registerNumber()), programMemory.getProgramSize());
                break;
            default:
                System.out.println("Invalid instruction");
        }

        return true;
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

    public Buffer getBuffer(int i, boolean readOnly){
        if(readOnly){
            return inputInterface.getBuffer(i);
        }else {
            return outputInterface.getBuffer(i);
        }
    }


    public void addBuffer(Buffer buffer,int i, boolean readOnly) {
        if(i < 0) return;

        if(readOnly){
            inputInterface.addBuffer(buffer, i);
        }else{
            outputInterface.addBuffer(buffer, i);
        }
    }




}
