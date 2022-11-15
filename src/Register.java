import java.util.ArrayList;
import java.util.List;

public class Register {


    private final List<Float> registers;

    public Register() {
        this.registers = new ArrayList<>();
    }


    public Float getValue(int index) {
        return registers.get(index);
    }

    public Float getValue(Float index){
       return getValue(index.intValue());
    }

    public void setValue(int index, float value) {
        registers.set(index,value);
    }
}
