package em.eua.distrcomp.lesson3.ex1;

public class Walker implements Runnable {
    private String name;
    private int[] coords;

    public Walker(String name, int[] startingCoords) {
        this.name = name;
        this.coords = startingCoords;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getMessage(name, coords));
            coords = calculateStep(coords);
            try {
                Thread.sleep(100l);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    private String getMessage(String person, int[] coord) {
        return person + " is standing on [" + coord[0] + "," + coord[1] + "]";
    }
}



