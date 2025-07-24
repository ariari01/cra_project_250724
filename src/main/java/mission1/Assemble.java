package mission1;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private static final int INVALID_INPUT = -1;
    private static final int COMBINATION_PASS_CODE = 99;

    private static final int CarType_Q = 0;
    private static final int Engine_Q = 1;
    private static final int BrakeSystem_Q = 2;
    private static final int SteeringSystem_Q = 3;
    private static final int Run_Test = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;

    private static int[] carInfo = new int[5];

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

            int answer = validCheckInputValue(buf);
            if (isValidInput(answer, step)) continue;

            if (answer == 0) {
                if (step == Run_Test) {
                    step = CarType_Q;
                } else if (step > CarType_Q) {
                    step--;
                }
                continue;
            }

            step = assembleCar(step, answer);
        }

        sc.close();
    }

    private static boolean isValidInput(int answer, int step) {
        if (answer == INVALID_INPUT) {
            delay(800);
            return true;
        }

        if (!isValidRange(step, answer)) {
            delay(800);
            return true;
        }
        return false;
    }

    private static boolean exitAssemble(String buf) {
        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }

    private static int assembleCar(int step, int answer) {
        switch (step) {
            case CarType_Q:
                selectCarType(answer);
                delay(800);
                step = Engine_Q;
                break;
            case Engine_Q:
                selectEngine(answer);
                delay(800);
                step = BrakeSystem_Q;
                break;
            case BrakeSystem_Q:
                selectBrakeSystem(answer);
                delay(800);
                step = SteeringSystem_Q;
                break;
            case SteeringSystem_Q:
                selectSteeringSystem(answer);
                delay(800);
                step = Run_Test;
                break;
            case Run_Test:
                if (answer == 1) {
                    runProducedCar();
                    delay(2000);
                } else if (answer == 2) {
                    System.out.println("Test...");
                    delay(1500);
                    testProducedCar();
                    delay(2000);
                }
                break;
        }
        return step;
    }

    private static void showMenu(int step) {
        switch (step) {
            case CarType_Q:
                showCarTypeMenu();
                break;
            case Engine_Q:
                showEngineMenu();
                break;
            case BrakeSystem_Q:
                showBrakeMenu();
                break;
            case SteeringSystem_Q:
                showSteeringMenu();
                break;
            case Run_Test:
                showRunTestMenu();
                break;
        }
    }

    private static int validCheckInputValue(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            return INVALID_INPUT;
        }
    }

    private static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }

    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }

    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }

    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CarType_Q:
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine_Q:
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem_Q:
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem_Q:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
                break;
            case Run_Test:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }

    private static void selectCarType(int carType) {
        carInfo[CarType_Q] = carType;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", carType == 1 ? "Sedan" : carType == 2 ? "SUV" : "Truck");
    }

    private static void selectEngine(int engineType) {
        //GM = 1, TOYOTA = 2, WIA = 3;
        carInfo[Engine_Q] = engineType;
        String name = engineType == GM ? "GM" : engineType == TOYOTA ? "TOYOTA" : engineType == WIA ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }

    private static void selectBrakeSystem(int brakeSystemType) {
        //MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
        carInfo[BrakeSystem_Q] = brakeSystemType;
        String name = brakeSystemType == MANDO ? "MANDO" : brakeSystemType == CONTINENTAL ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }

    private static void selectSteeringSystem(int steeringSystemType) {
        //BOSCH_S = 1, MOBIS = 2;
        carInfo[SteeringSystem_Q] = steeringSystemType;
        String name = steeringSystemType == BOSCH_S ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }


    private static int getErrorCodeByValidCheck() {
        if (carInfo[CarType_Q] == SEDAN && carInfo[BrakeSystem_Q] == CONTINENTAL) return 1;
        if (carInfo[CarType_Q] == SUV && carInfo[Engine_Q] == TOYOTA) return 2;
        if (carInfo[CarType_Q] == TRUCK && carInfo[Engine_Q] == WIA) return 3;
        if (carInfo[CarType_Q] == TRUCK && carInfo[BrakeSystem_Q] == MANDO) return 4;
        if (carInfo[BrakeSystem_Q] == BOSCH_B && carInfo[SteeringSystem_Q] != BOSCH_S) return 5;
        return COMBINATION_PASS_CODE;
    }

    private static void runProducedCar() {
        if (getErrorCodeByValidCheck() != COMBINATION_PASS_CODE) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (carInfo[Engine_Q] == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        String[] brakeNames = {"", "Mando", "Continental", "Bosch"};
        String[] steeringNames = {"", "Bosch", "Mobis"};

        System.out.printf("Car Type : %s\n", carNames[carInfo[CarType_Q]]);
        System.out.printf("Engine   : %s\n", engNames[carInfo[Engine_Q]]);
        System.out.printf("Brake    : %s\n", brakeNames[carInfo[BrakeSystem_Q]]);
        System.out.printf("Steering : %s\n", steeringNames[carInfo[SteeringSystem_Q]]);
        System.out.println("자동차가 동작됩니다.");
    }

    private static void testProducedCar() {
        int errorCode = getErrorCodeByValidCheck();

        if (errorCode == 1) {
            fail("Sedan에는 Continental제동장치 사용 불가");
            return;
        }
        if (errorCode == 2) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
            return;
        }
        if (errorCode == 3) {
            fail("Truck에는 WIA엔진 사용 불가");
            return;
        }
        if (errorCode == 4) {
            fail("Truck에는 Mando제동장치 사용 불가");
            return;
        }
        if (errorCode == 5) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
            return;
        }

        System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }


    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
