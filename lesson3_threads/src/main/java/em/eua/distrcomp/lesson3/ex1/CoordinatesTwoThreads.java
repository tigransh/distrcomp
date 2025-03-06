public class CoordinatesTwoThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Walker walker = new Walker("Person" + i, new int[]{0, 0}, 10);
            new Thread(walker).start();
        }
    }
}
