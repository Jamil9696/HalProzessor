
public class Register {


    private final Float[] registers;
    private final int SIZE = 15;

    public Register() {
        this.registers = new Float[SIZE];
    }

    public Float getValue(int index) throws IllegalArgumentException{

        if(index >= SIZE) {
            throw new IllegalArgumentException("Index is too big");
        }
        return registers[index];
    }

    public Float getValue(Float index){
       return getValue(index.intValue());

    }

    public void setValue(int index, Float value)  {

        if(index >= SIZE) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        registers[index] = value;
    }

    public void setValue(Float index, Float value) {
        setValue(index.intValue(), value);
    }
}
