# Design Patterns Java Program

This Java program demonstrates the implementation of Factory Method, Decorator, and Observer design patterns.

## Factory Method Pattern

The Factory Method pattern is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

In this program, we have defined two types of ATM factories: `BasicATMFactory` and `PremiumATMFactory`. These factories create instances of `BasicATM` and `PremiumATM` classes, respectively.

## Decorator Pattern

The Decorator pattern is a structural design pattern that allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class.

In this program, we demonstrate the Decorator pattern with the use of decorator classes like `CardReader`, `Keypad`, and `CashDispenser`. These decorators add additional functionality to the `ATM` objects, such as reading the card, entering the PIN, and dispensing cash.

## Observer Pattern

The Observer pattern is a behavioral design pattern that defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

In this program, we showcase the Observer pattern with the use of the `ATMSubject` and `ATMObserver` classes. The `ATMSubject` maintains a list of observers (observing ATM transactions), and when a transaction is completed, it notifies all the attached observers.

## Usage

To run the program, make sure you have Java installed on your machine.

1. Clone this repository:
2. git clone https://github.com/eshreef1/ATM.git

