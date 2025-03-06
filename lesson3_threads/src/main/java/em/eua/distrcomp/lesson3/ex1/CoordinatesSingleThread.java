import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class Walker implements Runnable {
    private String name;
    private int[] x;
    private long time;
    private static final ReentrantLock lock = new ReentrantLock();

    public Walker(String name, int[] startingCoords, long time) {
        this.name = name;
        this.x = startingCoords;
        this.time = time;
    }

    @Override
    public void run() {
        while (true) {
            if (!lock.isLocked()) {
                System.out.println(getMessage(name, x));
                x = calculateStep(x);

                // If person reaches [2,2], everyone else should wait for 3000ms
                if (x[0] == 2 && x[1] == 2) {
                    lock.tryLock();
                    try {
                        System.out.println(name + "  [2,2] everyone should wait");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock.isLocked()) {
                            lock.unlock();
                        }
                    }
                }

                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int[] calculateStep(int[] current) {
        int step = (int) Math.floor(Math.random() * 5);
        switch (step) {
            case 0:
                return new int[]{current[0] - 1, current[1]};
            case 2:
                return new int[]{current[0] + 1, current[1]};
            case 3:
                return new int[]{current[0], current[1] - 1};
            case 4:
                return new int[]{current[0], current[1] + 1};
            default:
                return current;
        }
    }

    private String getMessage(String person, int[] x) {
        return person + " is standing on [" + x[0] + "," + x[1] + "]";
    }
}





