

public class Accumulator {


    private Float value = 0f;
    private final Logger logger = Logger.getInstance();

    public void overwrite(Float value){

        this.value = value;
        logger.log(value);

    }

    public Float getCurrentValue() {
        return value;
    }

    public void add (Float value2){

        value += value2;
        logger.log(value);
    }

    public void sub (Float value2){

        value -= value2;
        logger.log(value);
    }

    public void div (Float value2){
        value /= value2;
        logger.log(value);
    }

    public void mul (Float value2){
        value *= value2;
        logger.log(value);
    }
    public boolean equalZero() {
        return value == 0;
    }

    public boolean neg() {
        return value < 0;
    }

    public boolean pos() {
        return value > 0;
    }



}
