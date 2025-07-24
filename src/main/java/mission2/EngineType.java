package mission2;

public enum EngineType {
    //GM = 1, TOYOTA = 2, WIA = 3;
    GM(1), TOYOTA(2), WIA(3), 고장난(4);

    private final int code;

    EngineType(int code) {
        this.code = code;
    }

    public static EngineType getEngineTypefromCode(int code) {
        for (EngineType e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalArgumentException("Err");
    }

    public int getCode() {
        return code;
    }
}
