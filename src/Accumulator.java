public class Accumulator {


    private Float value;


    public void setValue(Float value){
        this.value = value;
    }

    public Float getValue() {
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


}
