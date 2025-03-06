//n=2
//package em.eua.distrcomp.lesson3.ex1;
//
//public class CoordinatesTwoThreads {
//    public static void main(String[] args) {
//        
//        Walker walker1 = new Walker("person1", new int[]{0, 0}, 100); 
//        Walker walker2 = new Walker("person2", new int[]{0, 0}, 1000); 
//
//   
//        new Thread(walker1).start();
//        new Thread(walker2).start();
//    }
//}

//n=3
//package em.eua.distrcomp.lesson3.ex1;
//
//public class CoordinatesTwoThreads {
//    public static void main(String[] args) {
//        int numPersons = 100;  
//        Walker[] walkers = new Walker[numPersons];  
//       
//        for (int i = 0; i < numPersons; i++) {
//            walkers[i] = new Walker("person" + (i + 1), new int[]{0, 0}, 1000);
//            new Thread(walkers[i]).start();  
//        }
//    }
//}



//n=4
package em.eua.distrcomp.lesson3.ex1;

import java.util.concurrent.locks.ReentrantLock;

public class CoordinatesTwoThreads {
    public static void main(String[] args) {
        int numPersons = 10;  
        Walker[] walkers = new Walker[numPersons]; 
        ReentrantLock lock = new ReentrantLock(); 

        for (int i = 0; i < numPersons; i++) {
            walkers[i] = new Walker("person" + (i + 1), new int[]{0, 0}, 1000);
            new Thread(walkers[i]).start();  
        }
    }
}


