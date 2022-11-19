
public class DataStorage {


    private final Float[] registers;

    public DataStorage(int size) {

        this.registers = new Float[size];
    }

    public Float getValue(int index) throws IllegalArgumentException{

        if(index >= registers.length) {
            throw new IllegalArgumentException("Index is too big");
        }
        return registers[index];
    }

    public Float getValue(Float index){
       return getValue(index.intValue());
    }

    public void setValue(int index, Float value)  {

        if(index >= registers.length) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        registers[index] = value;
    }

    public void setValue(Float index, Float value) {
        setValue(index.intValue(), value);
    }
}
