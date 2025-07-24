package mission2;

public enum BrakeSystem {
    Mando(1), Continental(2), Bosch(3);

    private final int code;

    BrakeSystem(int code) {
        this.code = code;
    }

    public static BrakeSystem getBrakeTypefromCode(int code) {
        for (BrakeSystem e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Err");
    }
}
