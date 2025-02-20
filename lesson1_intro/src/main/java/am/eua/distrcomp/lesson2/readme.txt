Create  a class “PizzaDelivery” with three methods:
    “orderPizza”, “createPizza” and “deliverPizza”.
Each method puts the response by printing output in the console “Sysem.out.prinltn()”.
A user from “main” method calls the “orderPizza” method.
In each method add wait time for 5 seconds to make the flow more realistic.
In the first case simulate synchronous communication:
    oderPizza is called, which calls createPizza and waits for the response,
    which calls deliverPizza and waits for the response.
In the second case simulate asynchronous communication as follows:
    orderPizza responds immediately then calls createPizza,
    which then calls deliverPizza.