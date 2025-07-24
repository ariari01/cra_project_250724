package mission2;

public class delay {
    public static void ThreadDelay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
