import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProgramMemory {

    private final List<Instruction> instructionList = new ArrayList<>();
    private final DataStorage dataStorage;

    int programCounter = 0;

    public ProgramMemory() {
        this.dataStorage = new DataStorage();
    }

    public void push(Instruction instruction){
        instructionList.add(instruction);
    }

    public void pop(){
        if(!instructionList.isEmpty()){
            instructionList.remove(instructionList.size() -1);
        }
    }

    public Optional<Instruction>  getCurrentInstruction(int index){
        return index >= instructionList.size() ? Optional.empty() : Optional.of(instructionList.get(index));
    }

    public void printMemory(){
        instructionList.forEach(instruction ->
                System.out.println(instruction.getInstructionTyp().toString() + " " + instruction.getParameter() )
        );
    }

    public void store(Float data, Float index) {
        dataStorage.setValue(index,data);
    }
    public void store(Float data, Integer index) {
        dataStorage.setValue(index,data);
    }
}
