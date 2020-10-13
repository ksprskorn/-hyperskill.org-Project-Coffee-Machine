package machine;

import java.util.Scanner;

class Machine {
    private int waterCapacity = 0;
    private int milkCapacity = 0;
    private int coffeeBeanCapacity = 0;
    private int disposableCups = 0;
    private int money = 0;

    public Machine(int waterCapacity, int milkCapacity, int coffeeBeanCapacity, int disposableCups, int money) {
        this.waterCapacity = waterCapacity;
        this.milkCapacity = milkCapacity;
        this.coffeeBeanCapacity = coffeeBeanCapacity;
        this.disposableCups = disposableCups;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void addWaterCapacity(int waterCapacity) {
        this.waterCapacity += waterCapacity;
    }

    public void addMilkCapacity(int milkCapacity) {
        this.milkCapacity += milkCapacity;
    }

    public void addCoffeeBeanCapacity(int coffeeBeanCapacity) {
        this.coffeeBeanCapacity += coffeeBeanCapacity;
    }

    public void addDisposableCups(int disposableCups) {
        this.disposableCups += disposableCups;
    }

    public void order(int waterCapacity, int milkCapacity, int coffeeBeanCapacity, int disposableCups, int money) {
        if (this.waterCapacity - waterCapacity < 0 ||
                this.milkCapacity - milkCapacity < 0 ||
                this.coffeeBeanCapacity - coffeeBeanCapacity < 0 ||
                this.disposableCups - disposableCups < 0) {
            String ingredientNotEnough = "";
            if (this.waterCapacity - waterCapacity < 0) {
                ingredientNotEnough = ingredientNotEnough.concat("water");
            }
            if (this.milkCapacity - milkCapacity < 0) {
                ingredientNotEnough = ingredientNotEnough.length() == 0?ingredientNotEnough.concat("milk"):ingredientNotEnough.concat(", milk");
            }
            if (this.coffeeBeanCapacity - coffeeBeanCapacity < 0) {
                ingredientNotEnough = ingredientNotEnough.length() == 0?ingredientNotEnough.concat("coffee beans"):ingredientNotEnough.concat(", coffee beans");
            }
            if (this.disposableCups - disposableCups < 0) {
                ingredientNotEnough = ingredientNotEnough.length() == 0?ingredientNotEnough.concat("disposable cups"):ingredientNotEnough.concat(", disposable cups");
            }

            System.out.println("Sorry, not enough " + ingredientNotEnough);
        } else {

            this.waterCapacity -= waterCapacity;
            this.milkCapacity -= milkCapacity;
            this.coffeeBeanCapacity -= coffeeBeanCapacity;
            this.disposableCups -= disposableCups;
            this.money += money;
            System.out.println("I have enough resources, making you a coffee!\n");
        }
    }

    public void takeMoney() {
        this.money = 0;
    }

    public void printStatus() {
        System.out.println("The coffee machine has:\n"
                + this.waterCapacity + " of water\n"
                + this.milkCapacity + " of milk\n"
                + this.coffeeBeanCapacity + " of coffee beans\n"
                + this.disposableCups + " of disposable cups\n"
                + this.money + " of money\n");
    }
}

class Recipe {
    private int water = 0;
    private int milk = 0;
    private int coffeeBean = 0;
    private int cost = 0;
    private int disposableCups = 0;

    public Recipe(int water, int milk, int coffeeBean, int cost, int disposableCups) {
        this.water = water;
        this.milk = milk;
        this.coffeeBean = coffeeBean;
        this.cost = cost;
        this.disposableCups = disposableCups;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBean() {
        return coffeeBean;
    }

    public int getCost() {
        return cost;
    }

    public int getDisposableCups() {
        return disposableCups;
    }
}


public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Recipe espresso = new Recipe(250, 0, 16, 4, 1);
        Recipe latte = new Recipe(350, 75, 20, 7, 1);
        Recipe cappuccino = new Recipe(200, 100, 12, 6, 1);

        Machine coffeeMachine = new Machine(400, 540, 120, 9, 550);

        boolean exit = false;

        while (!exit) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();
            int value = 0;

            switch (action) {
                case "remaining":
                    coffeeMachine.printStatus();
                    break;
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    action = scanner.nextLine();
                    switch (action) {
                        case "1":
                            coffeeMachine.order(espresso.getWater(), espresso.getMilk(), espresso.getCoffeeBean(), espresso.getDisposableCups(), espresso.getCost());
                            break;
                        case "2":
                            coffeeMachine.order(latte.getWater(), latte.getMilk(), latte.getCoffeeBean(), latte.getDisposableCups(), latte.getCost());
                            break;
                        case "3":
                            coffeeMachine.order(cappuccino.getWater(), cappuccino.getMilk(), cappuccino.getCoffeeBean(), cappuccino.getDisposableCups(), cappuccino.getCost());
                            break;
                        case "back":
                            break;
                        default:
                            break;
                    }

                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add:");
                    value = scanner.nextInt();
                    coffeeMachine.addWaterCapacity(value);

                    System.out.println("Write how many ml of milk do you want to add:");
                    value = scanner.nextInt();
                    coffeeMachine.addMilkCapacity(value);

                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    value = scanner.nextInt();
                    coffeeMachine.addCoffeeBeanCapacity(value);

                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    value = scanner.nextInt();
                    coffeeMachine.addDisposableCups(value);

                    break;
                case "take":
                    System.out.println("I gave you $" + coffeeMachine.getMoney() + "\n");
                    coffeeMachine.takeMoney();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    break;
            }
        }

//        int oneCupOfWaterMl = 200;
//        int oneCupOfMilkMl = 50;
//        int oneCupOfCoffeeBeansG = 15;
//
//        System.out.println("Write how many ml of water the coffee machine has:");
//        int hasWaterMl = scanner.nextInt();
//        System.out.println("Write how many ml of milk the coffee machine has:");
//        int hasMilkMl = scanner.nextInt();
//        System.out.println("Write how many grams of coffee beans the coffee machine has:");
//        int hasCoffeeBeansG = scanner.nextInt();
//
//        System.out.println("Write how many cups of coffee you will need:");
//        int cups = scanner.nextInt();
//
//        int waterCups = hasWaterMl / oneCupOfWaterMl;
//        int milkCups = hasMilkMl / oneCupOfMilkMl;
//        int coffeeBeansCups = hasCoffeeBeansG / oneCupOfCoffeeBeansG;
//
//        int minCups = Integer.min(Integer.min(waterCups, milkCups), coffeeBeansCups);
//
//        if (minCups == cups) {
//            System.out.println("Yes, I can make that amount of coffee");
//        } else if (minCups == 0) {
//            System.out.println("No, I can make only 0 cup(s) of coffee");
//        } else if (minCups - cups > 0) {
//            System.out.println("Yes, I can make that amount of coffee (and even " + (minCups - cups) + " more than that)");
//        } else if (cups - minCups > 0) {
//            System.out.println("No, I can make only " + minCups + " cup(s) of coffee");
//        }

//        System.out.println("For " + cups + " cups of coffee you will need:");
//        System.out.println(oneCupOfWaterMl * cups + " ml of water");
//        System.out.println(oneCupOfMilkMl * cups + " ml of milk");
//        System.out.println(oneCupOfCoffeeBeansG * cups + "375 g of coffee beans");


//        System.out.println("Starting to make a coffee");
//        System.out.println("Grinding coffee beans");
//        System.out.println("Boiling water");
//        System.out.println("Mixing boiled water with crushed coffee beans");
//        System.out.println("Pouring coffee into the cup");
//        System.out.println("Pouring some milk into the cup");
//        System.out.println("Coffee is ready!");
    }
}
