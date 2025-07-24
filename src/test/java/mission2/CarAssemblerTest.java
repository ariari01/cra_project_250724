package mission2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarAssemblerTest {
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
}