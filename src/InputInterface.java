import java.util.Scanner;

public class InputInterface {

    Float input1;
    Float input2;

    public void printInput1(){
        System.out.println(input1);
    }

    public void printInput2(){
        System.out.println(input2);
    }

    public void setInput1(Float input1){
        this.input1 = input1;
    }

    public void setInput2(Float input2){
        this.input2 = input2;
    }


    public Float read() {
        Scanner scanner = new Scanner(System.in);
        Float input = scanner.nextFloat();

        return input;
    }
}
