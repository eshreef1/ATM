/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmain;

import java.util.ArrayList;
import java.util.List;


abstract class ATMFactory {
    public abstract ATM createATM();
}

class BasicATMFactory extends ATMFactory {
    public ATM createATM() {
        return new BasicATM();
    }
}

class PremiumATMFactory extends ATMFactory {
    public ATM createATM() {
        return new PremiumATM();
    }
}


interface ATM {
    void insertCard(String cardNumber);
    void enterPIN(int pin);
    void withdrawCash(double amount);
}

class BasicATM implements ATM {
    @Override
    public void insertCard(String cardNumber) {
        System.out.println("Basic ATM: Card " + cardNumber + " inserted.");
    }

    @Override
    public void enterPIN(int pin) {
        System.out.println("Basic ATM: PIN " + pin + " entered.");
    }

    @Override
    public void withdrawCash(double amount) {
        System.out.println("Basic ATM: Withdrawing " + amount + " dollars.");
    }
}

class PremiumATM implements ATM {
    public void insertCard(String cardNumber) {
        System.out.println("Premium ATM: Card " + cardNumber + " inserted.");
    }

    public void enterPIN(int pin) {
        System.out.println("Premium ATM: PIN " + pin + " entered.");
    }

    @Override
    public void withdrawCash(double amount) {
        System.out.println("Premium ATM: Withdrawing " + amount + " dollars.");
    }
}

abstract class ATMDecorator implements ATM {
    protected ATM atm;

    public ATMDecorator(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(String cardNumber) {
        atm.insertCard(cardNumber);
    }

    @Override
    public void enterPIN(int pin) {
        atm.enterPIN(pin);
    }

    @Override
    public void withdrawCash(double amount) {
        atm.withdrawCash(amount);
    }
}

class CardReader extends ATMDecorator {
    public CardReader(ATM atm) {
        super(atm);
    }

    @Override
    public void insertCard(String cardNumber) {
        super.insertCard(cardNumber);
        System.out.println("Card Reader: Reading card " + cardNumber + ".");
    }
}

class Keypad extends ATMDecorator {
    public Keypad(ATM atm) {
        super(atm);
    }

    @Override
    public void enterPIN(int pin) {
        super.enterPIN(pin);
        System.out.println("Keypad: Entering PIN " + pin + ".");
    }
}

class CashDispenser extends ATMDecorator {
    public CashDispenser(ATM atm) {
        super(atm);
    }

    @Override
    public void withdrawCash(double amount) {
        super.withdrawCash(amount);
        System.out.println("Cash Dispenser: Dispensing " + amount + " dollars.");
    }
}

interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(String message);
}

interface Observer {
    void update(String message);
}

class ATMSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String message;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        this.message = message;
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers(message);
    }
}

class ATMObserver implements Observer {
    private String name;

    public ATMObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + ": " + message);
    }
}

// Main function
public class ATMMain {
    public static void main(String[] args) {
        // Create a basic ATM with card reader, keypad, and cash dispenser decorators
        ATMFactory basicATMFactory = new BasicATMFactory();
        ATM basicATM = basicATMFactory.createATM();
        basicATM = new CardReader(basicATM);
        basicATM = new Keypad(basicATM);
        basicATM = new CashDispenser(basicATM);







        ATMFactory premiumATMFactory = new PremiumATMFactory();
        ATM premiumATM = premiumATMFactory.createATM();
        premiumATM = new CardReader(premiumATM);
        premiumATM = new Keypad(premiumATM);
        premiumATM = new CashDispenser(premiumATM);

        // Create ATM observers
        ATMObserver observer1 = new ATMObserver("Observer 1");
        ATMObserver observer2 = new ATMObserver("Observer 2");

        ATMSubject atmSubject = new ATMSubject();
        atmSubject.attach(observer1);
        atmSubject.attach(observer2);

        System.out.println("Basic ATM transaction:");
        basicATM.insertCard("1234567890");
        basicATM.enterPIN(1234);
        basicATM.withdrawCash(100);
        atmSubject.setMessage("Basic ATM transaction completed.");

        System.out.println("\nPremium ATM transaction:");
        premiumATM.insertCard("0987654321");
        premiumATM.enterPIN(5678);
        premiumATM.withdrawCash(500);
        atmSubject.setMessage("Premium ATM transaction completed.");




        System.out.println("\nPremium ATM transaction without observer 2:");
        atmSubject.detach(observer2);
        premiumATM.insertCard("0987654321");
        premiumATM.enterPIN(5678);
        premiumATM.withdrawCash(500);
        atmSubject.setMessage("Premium ATM transaction completed without observer 2.");
    }
}