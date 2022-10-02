package stage6.project;

import java.util.Scanner;

enum State {
    READY,
    SHUTDOWN,
    WATER_INPUT,
    MILK_INPUT,
    BEANS_INPUT,
    CUPS_INPUT,
    BUY_CHOICE
}

public class CoffeeMachine {

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private String input;
    private State state = State.READY;

    CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    State getState() {
        return this.state;
    }

    void start() {
        ready();
    }

    void stop() {
        this.state = State.SHUTDOWN;
    }

    private void ready() {
        this.state = State.READY;
        System.out.println();
        System.out.print("Write action (buy, fill, take, remaining, exit): ");
    }

    void processInput(String input) {
        this.input = input;

        switch (this.state) {
            case READY:
                processReadyCommand();
                break;
            case WATER_INPUT:
            case MILK_INPUT:
            case BEANS_INPUT:
            case CUPS_INPUT:
                fill();
                break;
            case BUY_CHOICE:
                buy();
                break;
            default:
                System.out.println("Unknown input state");
                ready();
                break;
        }
    }

    private void processReadyCommand() {
        System.out.println();
        switch (input) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                printRemaining();
                break;
            case "exit":
                stop();
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }

    private void buy() {
        switch (this.state) {
            case READY:
                System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                        "back - to main menu: ");
                this.state = State.BUY_CHOICE;
                break;
            case BUY_CHOICE:
                boolean enough = isEnough(this.input);

                switch (this.input) {
                    case "1": // espresso
                        if (enough) {
                            this.water -= 250;
                            this.beans -= 16;
                            this.cups -= 1;
                            this.money += 4;
                        }
                        break;
                    case "2": // latte
                        if (enough) {
                            this.water -= 350;
                            this.milk -= 75;
                            this.beans -= 20;
                            this.cups -= 1;
                            this.money += 7;
                        }
                        break;
                    case "3": // cappuccino
                        if (enough) {
                            this.water -= 200;
                            this.milk -= 100;
                            this.beans -= 12;
                            this.cups -= 1;
                            this.money += 6;
                        }
                        break;
                    case "back":
                        break;
                    default:
                        System.out.println("Unknown buy command");
                        break;
                }
                ready();
                break;
            default:
                System.out.println("Unknown buy state");
                ready();
                break;
        }
    }
