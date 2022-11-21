import java.util.Arrays;

public class DataStorage {


    private final Float[] registers;
    private final Logger logger = Logger.getInstance();

    public DataStorage(int size) {

        this.registers = new Float[size];

       for(int i = 0; i < size; i++){
           registers[i] = 0f;
       }
    }

    public Float getValue(int index) throws IllegalArgumentException{

        if(index >= registers.length) {
            throw new IllegalArgumentException("Index is too big");
        }
        return registers[index];
    }
    public void setValue(int index, Float value)  {

        if(index >= registers.length) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        registers[index] = value;
        logger.log(value);
    }

    public void setValue(Float index, Float value) {
        setValue(index.intValue(), value);
    }
}
