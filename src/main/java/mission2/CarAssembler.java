package mission2;

import java.util.HashMap;
import java.util.Map;

import static mission2.delay.ThreadDelay;
import static mission2.AssembleStep.*;

public class CarAssembler {
    private final Car car;
    private static final int COMBINATION_PASS_CODE = 99;
    private static final Map<Integer, String> ERROR_MESSAGES = new HashMap<>();

    static {
        ERROR_MESSAGES.put(1, "Sedan에는 Continental제동장치 사용 불가");
        ERROR_MESSAGES.put(2, "SUV에는 TOYOTA엔진 사용 불가");
        ERROR_MESSAGES.put(3, "Truck에는 WIA엔진 사용 불가");
        ERROR_MESSAGES.put(4, "Truck에는 Mando제동장치 사용 불가");
        ERROR_MESSAGES.put(5, "Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
    }

    public CarAssembler(Car car) {
        this.car = car;
    }

    public int assembleCar(int step, int answer) {
        switch (step) {
            case CarType_Q:
                selectCarType(answer);
                ThreadDelay(800);
                step = Engine_Q;
                break;
            case Engine_Q:
                selectEngine(answer);
                ThreadDelay(800);
                step = BrakeSystem_Q;
                break;
            case BrakeSystem_Q:
                selectBrakeSystem(answer);
                ThreadDelay(800);
                step = SteeringSystem_Q;
                break;
            case SteeringSystem_Q:
                selectSteeringSystem(answer);
                ThreadDelay(800);
                step = Run_Test;
                break;
            case Run_Test:
                if (answer == 1) {
                    runProducedCar();
                    ThreadDelay(2000);
                } else if (answer == 2) {
                    System.out.println("Test...");
                    ThreadDelay(1500);
                    testProducedCar();
                    ThreadDelay(2000);
                }
                break;
        }
        return step;
    }

    public void selectCarType(int code) {
        car.setType(CarType.getCarTypefromCode(code));
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", car.getType());
    }

    public void selectEngine(int code) {
        car.setEngine(EngineType.getEngineTypefromCode(code));
        System.out.printf("%s 엔진을 선택하셨습니다.\n", car.getEngine());
    }

    public void selectBrakeSystem(int code) {
        car.setBrake(BrakeSystem.getBrakeTypefromCode(code));
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", car.getBrake());
    }

    public void selectSteeringSystem(int code) {
        car.setSteering(SteeringSystem.getSteeringTypefromCode(code));
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", car.getSteering());
    }

    private int getErrorCodeByValidCheck() {
        if (car.getType() == CarType.Sedan && car.getBrake() == BrakeSystem.Continental) return 1;
        if (car.getType() == CarType.SUV && car.getEngine() == EngineType.TOYOTA) return 2;
        if (car.getType() == CarType.Truck && car.getEngine() == EngineType.WIA) return 3;
        if (car.getType() == CarType.Truck && car.getBrake() == BrakeSystem.Mando) return 4;
        if (car.getBrake() == BrakeSystem.Bosch && car.getSteering() != SteeringSystem.Bosch) return 5;
        return 99;
    }

    private void runProducedCar() {
        if (getErrorCodeByValidCheck() != COMBINATION_PASS_CODE) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (car.getEngine() == EngineType.고장난) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        System.out.printf("Car Type : %s\n", car.getType());
        System.out.printf("Engine   : %s\n", car.getEngine());
        System.out.printf("Brake    : %s\n", car.getBrake());
        System.out.printf("Steering : %s\n", car.getSteering());
        System.out.println("자동차가 동작됩니다.");
    }

    private void testProducedCar() {
        int errorCode = getErrorCodeByValidCheck();

        if (ERROR_MESSAGES.containsKey(errorCode)) {
            fail(ERROR_MESSAGES.get(errorCode));
            return;
        }
        System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    private void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }
}
