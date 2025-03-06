package em.eua.distrcomp.lesson3.ex1;

public class CoordinatesSingleThread {
    public static void main(String[] args) {
        int[] person1Coord = new int[]{0, 0};
        int[] person2Coord = new int[]{0, 0};

        while (true) {
            System.out.println(getMessage("person1", person1Coord));
            System.out.println(getMessage("person2", person2Coord));
            person1Coord = calculateStep(person1Coord);
            person2Coord = calculateStep(person2Coord);

            try {
                Thread.sleep(200l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] calculateStep(int[] current) {
        int step = (int) Math.floor(Math.random() * 4);
        switch (step) {
            case 0:
                return new int[]{current[0] - 1, current[1]};
            case 1:
                return new int[]{current[0] + 1, current[1]};
            case 2:
                return new int[]{current[0], current[1] - 1};
            case 3:
                return new int[]{current[0], current[1] + 1};
            default:
                return current;
        }
    }

    public static String getMessage(String person, int[] coord) {
        return person + " is standing on [" + coord[0] + "," + coord[1] + "]";
    }
}


