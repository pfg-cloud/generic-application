package usecases;

public class MemoryUsage {

    private static final int MEGABYTES = 1000000;

    private static MemoryUsage memoryUsage;

    private byte[] buffer;

    private MemoryUsage(final int amountInMb) {
        buffer = new byte[amountInMb * MEGABYTES];
    }

    private void clear() {
        buffer = null;
    }

    public static void allocate(final int amountInMb) {
        memoryUsage = new MemoryUsage(amountInMb);
    }

    public static void clearMemory() {
        if (memoryUsage == null) {
            return;
        }
        memoryUsage.clear();
        memoryUsage = null;
    }
}
