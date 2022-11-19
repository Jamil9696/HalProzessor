

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

    public void in(Float parameter) {accumulator.overwrite(parameter);}

    public Float out(){return accumulator.getCurrentValue();}

}
