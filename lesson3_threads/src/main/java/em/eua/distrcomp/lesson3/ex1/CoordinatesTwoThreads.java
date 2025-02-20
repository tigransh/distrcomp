package em.eua.distrcomp.lesson3.ex1;

public class CoordinatesTwoThreads {
    public static void main(String[] args) {
        Walker walker1 = new Walker("person1", new int[]{0, 0});
        Walker walker2 = new Walker("person2", new int[]{0, 0});
        new Thread(walker1).start();
        new Thread(walker2).start();
    }
}
