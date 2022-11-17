

public class HalProcessor {


    private final Accumulator accumulator;
    private final Register register;
    private int programCounter = 0;

    public HalProcessor( Accumulator accumulator, Register register) {

        this.accumulator = accumulator;
        this.register = register;
    }

    public void add(Float value) {
        accumulator.add(register.getValue(value));
    }

    public void sub(Float value) {
        accumulator.sub(register.getValue(value));
    }

    public void div(Float value) {
        accumulator.div(register.getValue(value));
    }

    public void mul(Float value) {
        accumulator.mul(register.getValue(value));
    }


    public void in(Float parameter) {
        // TODO: implement functionality
    }

    public Float out() {
       return 0.0f; // TODO: implement functionality
    }


}
