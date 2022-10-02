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
