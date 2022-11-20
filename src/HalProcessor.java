

public class HalProcessor {


    private final Accumulator accumulator;
    private int programmeCounter = 0;

    public int getProgrammeCounter(){
        return programmeCounter;
    }

    public void incrementPC(){
        programmeCounter++;
    }

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

    public void jump(int jumpValue, int size) {
        if(jumpValue >= 0 && jumpValue < size){
             programmeCounter = jumpValue-1;
             return;
        }
        throw new IllegalArgumentException("Jump index is out of bounds");
    }

    public void jumpnull(int jumpValue, int programSize) {
        if (accumulator.equalZero()){
             jump(jumpValue,programSize);
        }
    }

    public void jumpneg(int parseInt, int programSize) {
        if (accumulator.neg()){
             jump(parseInt,programSize);
        }
    }

    public void jumppos(int parseInt, int programSize) {
        if (accumulator.pos()){
             jump(parseInt,programSize);
        }
    }
}
