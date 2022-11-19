

public class HalProcessor {


    private final Accumulator accumulator;


    public HalProcessor( Accumulator accumulator) {
        this.accumulator = accumulator;
    }

    public void add(Float value) {
        accumulator.add(value);
    }

    public void sub(Float value) {
        accumulator.sub(value);
    }

    public void div(Float value) {
        accumulator.div(value);
    }

    public void mul(Float value) {
        accumulator.mul(value);
    }

    public void in(Integer parameter){in(parameter.floatValue());}

    public void in(Float parameter) {accumulator.overwrite(parameter);}

    public Float out(){return accumulator.getCurrentValue();}

    public int jump(int parseInt, int size) {
        if(parseInt >= 0 && parseInt < size){
            return parseInt-1;
        }
        throw new IllegalArgumentException("Jump index is out of bounds");
    }

    public int jumpnull(int parseInt, int programSize) {
        if (accumulator.equalZero()){
            return jump(parseInt,programSize);
        }
        return parseInt;
    }

    public int jumpneg(int parseInt, int programSize) {
        if (accumulator.neg()){
            return jump(parseInt,programSize);
        }
        return parseInt;
    }

    public int jumppos(int parseInt, int programSize) {
        if (accumulator.pos()){
            return jump(parseInt,programSize);
        }
        return parseInt;
    }
}
