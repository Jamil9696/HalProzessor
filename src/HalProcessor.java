

public class HalProcessor {


    private final Accumulator accumulator;
    private final DataStorage register;
    private int programCounter = 0;

    public HalProcessor( Accumulator accumulator, DataStorage register) {

        this.accumulator = accumulator;
        this.register = register;
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
