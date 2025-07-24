package mission2;

import java.util.Scanner;

import static mission2.PrintMenu.showMenu;
import static mission2.delay.ThreadDelay;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private static final int INVALID_INPUT = -1;
    private static final int CarType_Q = 0;
    private static final int Run_Test = 4;

    private static final Car car = new Car();
    private static final CarAssembler assembler = new CarAssembler(car);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int step = CarType_Q;

        while (true) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            showMenu(step);

            System.out.print("INPUT > ");
            String buf = sc.nextLine().trim();

            if (exitAssemble(buf)) break;

            int answer = InputValidator.parseInput(buf);
            if (answer == INVALID_INPUT) {
                ThreadDelay(800);
                continue;
            }
            if (!InputValidator.isValidRange(step, answer)) continue;

            if (answer == 0) {
                step = getStep(step);
                continue;
            }
            step = assembler.assembleCar(step, answer);
        }

        sc.close();
    }

    private static int getStep(int step) {
        if (step == Run_Test) {
            step = CarType_Q;
        } else if (step > CarType_Q) {
            step--;
        }
        return step;
    }

    private static boolean exitAssemble(String buf) {
        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }
}
