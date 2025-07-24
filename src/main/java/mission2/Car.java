package mission2;

public class Car {
    private CarType type;
    private EngineType engine;
    private BrakeSystem brake;
    private SteeringSystem steering;

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public BrakeSystem getBrake() {
        return brake;
    }

    public void setBrake(BrakeSystem brake) {
        this.brake = brake;
    }

    public SteeringSystem getSteering() {
        return steering;
    }

    public void setSteering(SteeringSystem steering) {
        this.steering = steering;
    }
}
