package mission2;

public enum CarType {
    //SEDAN = 1, SUV = 2, TRUCK = 3;
    Sedan(1), SUV(2), Truck(3);

    private final int code;

    CarType(int code) {
        this.code = code;
    }

    public static CarType getCarTypefromCode(int code) {
        for (CarType e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Err");
    }

    public int getCode() {
        return code;
    }
}
