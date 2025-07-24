package mission2;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssembleTest {
    @Nested
    class CarAssemblerTest2 {
        private Car mockCar;
        private CarAssembler assembler;

        @BeforeEach
        void setup() {
            mockCar = mock(Car.class);
            assembler = new CarAssembler(mockCar);
        }

        @Test
        void 유효한_CARTYPE_선택() {
            assembler.selectCarType(1); // SEDAN
            verify(mockCar).setType(CarType.Sedan);
        }

        @Test
        void 없는_CARTYPE_선택() {
            assertThrows(IllegalArgumentException.class, () -> assembler.selectCarType(99));
        }

        @Test
        void 유효한_ENGINE_선택() {
            assembler.selectEngine(2); // SEDAN
            verify(mockCar).setEngine(EngineType.TOYOTA);
        }

        @Test
        void 없는_ENGINE_선택() {
            assertThrows(IllegalArgumentException.class, () -> assembler.selectEngine(99));
        }

        @Test
        void 유효한_BRAKE_선택() {
            assembler.selectBrakeSystem(2); // SEDAN
            verify(mockCar).setBrake(BrakeSystem.Continental);
        }

        @Test
        void 없는_BRAKE_선택() {
            assertThrows(IllegalArgumentException.class, () -> assembler.selectBrakeSystem(99));
        }

        @Test
        void 유효한_STEERING_선택() {
            assembler.selectSteeringSystem(1); // SEDAN
            verify(mockCar).setSteering(SteeringSystem.Bosch);
        }

        @Test
        void 없는_STEERING_선택() {
            assertThrows(IllegalArgumentException.class, () -> assembler.selectSteeringSystem(99));
        }

        @Test
        void Sedan에는_Continental제동장치_사용_불가() {
            when(mockCar.getType()).thenReturn(CarType.Sedan);
            when(mockCar.getBrake()).thenReturn(BrakeSystem.Continental);
            assembler.assembleCar(4, 1);
        }

        @Test
        void SUV에는_TOYOTA엔진_사용_불가() {
            when(mockCar.getType()).thenReturn(CarType.SUV);
            when(mockCar.getEngine()).thenReturn(EngineType.TOYOTA);
            assembler.assembleCar(4, 1);
        }

        @Test
        void Truck에는_WIA엔진_사용_불가() {
            when(mockCar.getType()).thenReturn(CarType.Truck);
            when(mockCar.getEngine()).thenReturn(EngineType.WIA);
            assembler.assembleCar(4, 1);
        }

        @Test
        void Truck에는_Mando제동장치_사용_불가() {
            when(mockCar.getType()).thenReturn(CarType.Truck);
            when(mockCar.getBrake()).thenReturn(BrakeSystem.Mando);
            assembler.assembleCar(4, 1);
        }

        @Test
        void Bosch제동장치에는_Bosch조향장치_이외_사용_불가() {
            when(mockCar.getBrake()).thenReturn(BrakeSystem.Bosch);
            when(mockCar.getSteering()).thenReturn(SteeringSystem.Mobis);
            assembler.assembleCar(4, 1);
        }

        @Test
        void 고장난_엔진() {
            when(mockCar.getType()).thenReturn(CarType.SUV);
            when(mockCar.getBrake()).thenReturn(BrakeSystem.Bosch);
            when(mockCar.getSteering()).thenReturn(SteeringSystem.Bosch);
            when(mockCar.getEngine()).thenReturn(EngineType.고장난);
            assembler.assembleCar(4, 1); // Run Test
        }

        @Test
        void 자동차_조합_PASS() {
            when(mockCar.getType()).thenReturn(CarType.SUV);
            when(mockCar.getBrake()).thenReturn(BrakeSystem.Bosch);
            when(mockCar.getSteering()).thenReturn(SteeringSystem.Bosch);
            when(mockCar.getEngine()).thenReturn(EngineType.GM);
            assembler.assembleCar(4, 1); // Run Test
        }

        @Test
        void 자동차_조합_FAIL() {
            when(mockCar.getType()).thenReturn(CarType.Sedan);
            when(mockCar.getBrake()).thenReturn(BrakeSystem.Continental);
            assembler.assembleCar(4, 2); // Test
        }

        @Test
        void 자동차_조합_PASS2() {
            when(mockCar.getType()).thenReturn(CarType.SUV);
            when(mockCar.getEngine()).thenReturn(EngineType.GM);
            when(mockCar.getBrake()).thenReturn(BrakeSystem.Bosch);
            when(mockCar.getSteering()).thenReturn(SteeringSystem.Bosch);
            assembler.assembleCar(4, 2); // Test
        }

        @Test
        void 조립STEP_PASS() {
            int nextStep = assembler.assembleCar(0, 1); // CarType_Q
            assertEquals(1, nextStep);
            nextStep = assembler.assembleCar(1, 2); // Engine_Q
            assertEquals(2, nextStep);
            nextStep = assembler.assembleCar(2, 1); // BrakeSystem_Q
            assertEquals(3, nextStep);
            nextStep = assembler.assembleCar(3, 1); // SteeringSystem_Q
            assertEquals(4, nextStep);
        }
    }

    @Nested
    class InputValidatorTest {
        @Test
        void 입력값이_숫자일때() {
            int result = InputValidator.parseInput("3");
            assertThat(result).isEqualTo(3);
        }

        @Test
        void 입력값이_숫자가_아닐때() {
            int result = InputValidator.parseInput("a");
            assertThat(result).isEqualTo(-1); // INVALID_INPUT
        }

        @Test
        void CARTYPE단계_입력값_PASS() {
            boolean result = InputValidator.isValidRange(AssembleStep.CarType_Q, 1);
            assertThat(result).isTrue();
        }

        @Test
        void CARTYPE단계_입력값_FAIL() {
            boolean result = InputValidator.isValidRange(AssembleStep.CarType_Q, 5);
            assertThat(result).isFalse();
        }

        @Test
        void ENGINE단계_입력값_PASS() {
            boolean result = InputValidator.isValidRange(AssembleStep.Engine_Q, 2);
            assertThat(result).isTrue();
        }

        @Test
        void ENGINE단계_입력값_FAIL() {
            boolean result = InputValidator.isValidRange(AssembleStep.Engine_Q, 9);
            assertThat(result).isFalse();
        }

        @Test
        void 제어단계_입력값_PASS() {
            boolean result = InputValidator.isValidRange(AssembleStep.BrakeSystem_Q, 1);
            assertThat(result).isTrue();
        }

        @Test
        void 제어단계_입력값_FAIL() {
            boolean result = InputValidator.isValidRange(AssembleStep.BrakeSystem_Q, -1);
            assertThat(result).isFalse();
        }

        @Test
        void 조향단계_입력값_PASS() {
            boolean result = InputValidator.isValidRange(AssembleStep.SteeringSystem_Q, 2);
            assertThat(result).isTrue();
        }

        @Test
        void 조향단계_입력값_FAIL() {
            boolean result = InputValidator.isValidRange(AssembleStep.SteeringSystem_Q, 3);
            assertThat(result).isFalse();
        }

        @Test
        void RUN단계_입력값_PASS() {
            boolean result = InputValidator.isValidRange(AssembleStep.Run_Test, 1);
            assertThat(result).isTrue();
        }

        @Test
        void RUN단계_입력값_FAIL() {
            boolean result = InputValidator.isValidRange(AssembleStep.Run_Test, -1);
            assertThat(result).isFalse();
        }
    }

    @Test
    void SHOWMENU_TEST() {
        assertDoesNotThrow(() -> PrintMenu.showMenu(1)); // CarType_Q
        assertDoesNotThrow(() -> PrintMenu.showMenu(2)); // Engine_Q
        assertDoesNotThrow(() -> PrintMenu.showMenu(3)); // BrakeSystem_Q
        assertDoesNotThrow(() -> PrintMenu.showMenu(4)); // SteeringSystem_Q
        assertDoesNotThrow(() -> PrintMenu.showMenu(5)); // Run_Test
    }
}