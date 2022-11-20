

public class Accumulator {


    private Float value = 0f;


    public void overwrite(Float value){
        this.value = value;
    }

    public Float getCurrentValue() {
        return value;
    }

    public void add (Float value2){
        value += value2;
    }

    public void sub (Float value2){
        value -= value2;
    }

    public void div (Float value2){
        value /= value2;
    }

    public void mul (Float value2){
        value *= value2;
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
