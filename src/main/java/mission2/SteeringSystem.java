package mission2;

public enum SteeringSystem {
    //BOSCH_S = 1, MOBIS = 2;
    Bosch(1), Mobis(2);

    private final int code;

    SteeringSystem(int code) {
        this.code = code;
    }

    public static SteeringSystem getSteeringTypefromCode(int code) {
        for (SteeringSystem e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Err");
    }

    public int getCode() {
        return code;
    }
}