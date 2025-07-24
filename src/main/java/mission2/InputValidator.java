package mission2;

import static mission2.AssembleStep.*;
import static mission2.delay.ThreadDelay;

public class InputValidator {
    private static final int INVALID_INPUT = -1;
    public static int parseInput(String userChoice) {
        try {
            return Integer.parseInt(userChoice);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            return INVALID_INPUT;
        }
    }

    public static boolean isValidRange(int step, int userChoice) {
        switch (step) {
            case CarType_Q:
                if (userChoice < 1 || userChoice > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine_Q:
                if (userChoice < 0 || userChoice > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem_Q:
                if (userChoice < 0 || userChoice > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem_Q:
                if (userChoice < 0 || userChoice > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
                break;
            case Run_Test:
                if (userChoice < 0 || userChoice > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }
}
