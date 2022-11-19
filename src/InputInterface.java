import java.util.Scanner;

public class InputInterface {

    public Float read() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("IN: ");
        Float input = scanner.nextFloat();

        return input;
    }
}
