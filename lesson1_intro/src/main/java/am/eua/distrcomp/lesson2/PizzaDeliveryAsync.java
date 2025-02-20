package am.eua.distrcomp.lesson2;

/**
 * A class for simulating asynchronous communication
 */
public class PizzaDeliveryAsync {
    /**
     * Takes the pizza order and initiates processing
     * @param customer customer name
     */
    public void orderPizza(String customer) {
        System.out.println("Pizza ordered from " + customer + ".");
        System.out.println("The pizza you ordered will be delivered soon, thank you " + customer + "!");
        System.out.println("GoodBye!");
        createPizza(customer);
    }

    /**
     * Creates a pizza
     * @param customer customer name
     */
    public void createPizza(String customer) {
        System.out.println("****Starting to create pizza for " + customer);
        process();
        System.out.println("****Created pizza for " + customer);
        deliverPizza(customer);
    }

    /**
     * Delivers a pizza
     * @param customer customer name
     */
    public void deliverPizza(String customer) {
        System.out.println("****Delivering pizza for "+customer);
        process();
        System.out.println("****Pizza delivered for "+customer);
    }

    /**
     * Does the processing(is called from createPizza and deliverPizza methods)
     */
    private void process(){
        System.out.println("****Processing started ...");
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("****Processing finished");
    }

    public static void main(String[] args){
        System.out.println("!messages starting with **** are not visible for the end client!");
        PizzaDeliveryAsync delivery = new PizzaDeliveryAsync();
        delivery.orderPizza("Andrea");
    }
}


