//package em.eua.distrcomp.lesson3.ex1;
//
//public class Walker implements Runnable {
//    private String name;
//    private int[] coords;
//    private long time;
//
//    public Walker(String name, int[] startingCoords, long time) {
//        this.name = name;
//        this.coords = startingCoords;
//        this.time=time;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            System.out.println(getMessage(name, coords));
//            coords = calculateStep(coords);
//            try {
//                Thread.sleep(time);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private int[] calculateStep(int[] current) {
//        int step = (int) Math.floor(Math.random() * 5);
//        switch (step) {
//            case 0:
//                return new int[]{current[0] - 1, current[1]};
//            case 2:
//                return new int[]{current[0] + 1, current[1]};
//            case 3:
//                return new int[]{current[0], current[1] - 1};
//            case 4:
//                return new int[]{current[0], current[1] + 1};
//            default:
//                return current;
//        }
//    }
//
//    private String getMessage(String person, int[] coord) {
//        return person + " is standing on [" + coord[0] + "," + coord[1] + "]";
//    }
//}
//
//
//

//------------------------------------------------------------------------------------------


// n=3
//package em.eua.distrcomp.lesson3.ex1;
//
//public class Walker implements Runnable {
//    private String name;
//    private int[] coords;
//    private long time;  
//
//    public Walker(String name, int[] startingCoords, long time) {
//        this.name = name;
//        this.coords = startingCoords;
//        this.time = time;
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            System.out.println(getMessage(name, coords)); 
//            coords = calculateStep(coords); 
//
//            try {
//                Thread.sleep(time);  
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//  
//    private int[] calculateStep(int[] current) {
//        int step = (int) Math.floor(Math.random() * 4);  
//        switch (step) {
//            case 0:
//                return new int[]{current[0] - 1, current[1]}; 
//            case 1:
//                return new int[]{current[0] + 1, current[1]}; 
//            case 2:
//                return new int[]{current[0], current[1] - 1}; 
//            case 3:
//                return new int[]{current[0], current[1] + 1};  
//            default:
//                return current;
//        }
//    }
//
// 
//    private String getMessage(String person, int[] coord) {
//        return person + " is standing on [" + coord[0] + "," + coord[1] + "]";
//    }
//}


//n=4
package em.eua.distrcomp.lesson3.ex1;

import java.util.concurrent.locks.ReentrantLock;

public class Walker implements Runnable {
    private String name;
    private int[] coords;
    private long time;  
    static private ReentrantLock lock= new ReentrantLock();

    public Walker(String name, int[] startingCoords, long time) {
        this.name = name;
        this.coords = startingCoords;
        this.time = time;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getMessage(name, coords));  
            coords = calculateStep(coords);  

           
            if (coords[0] == 2 && coords[1] == 2) {
                try {
                    lock.lock();
                    System.out.println(name + " has reached [2, 2]. Մնացած բոլորը կսպասեն 3000 ms.");
                    Thread.sleep(3000);  
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();  
                }
            }

            try {
                Thread.sleep(time);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private int[] calculateStep(int[] current) {
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

  
    private String getMessage(String person, int[] coord) {
        return person + " is standing on [" + coord[0] + "," + coord[1] + "]";
    }
}

