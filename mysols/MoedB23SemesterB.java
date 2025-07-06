package mysols;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MoedB23SemesterB {
    interface ObserverSubject{
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers();
}

interface Observer{
    void update();
}



class Book implements ObserverSubject{
    private String title; // Unique for each book
    private String content;
    private int totalCopies;
    private int availableCopies;
    
    private List<Observer> observers = new ArrayList<Observer>();
    
    Book(String title, String content, int totalCopies){
        this.title = title;
        this.content = content;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }
    public String getContent(){
        return this.content;
    }
    
    public String getTitle(){
        return this.title;
    }

    public boolean borrowCopy(){
        if (availableCopies - 1 > 0){
            availableCopies--;
            return true;
        }
        return false;
        
    }
    
    public void returnCopy(){
        if (availableCopies + 1 <= totalCopies){
            availableCopies++;
            notifyObservers();
        }
        //What to do if it exceeds the number of totalCopies?
    }
    

    
    
    public void registerObserver(Observer observer){
        observers.add(observer);
    }
    
    public void unregisterObserver(Observer observer){
        for (int i = 0 ; i < observers.size() ; i++){
            if (observers.get(i).equals(observer)){
                observers.remove(i);
            }
            
        }
    }
    
    public void notifyObservers(){
        for (Observer observer : observers){
            observer.update();
        }
    }
    

}

class DigitalLibrary{
    private static DigitalLibrary instance;
    private Map<String, Book> books = new HashSet<String, Book>();
    
    private DigitalLibrary(){};
    
    public void addNewBook(String title, String content, int numOfCopies){
        books.add(title, new Book(title, content, numOfCopies));
    }
    
    public static DigitalLibrary getInstance(){
        if (instance == null){
            instance = new DigitalLibrary();
        }
        return instance;
    }
    
    public Book getBook(String title){
        return books.get(title);
    } 
    
}


abstract class LibraryApp{
    private static final String TOO_MANY_BOOKS_ERROR = "Please return the previous book first";
    private static final String BOOK_NOT_EXIST = "Library does not have this book";
    private static final String NO_BOOK_COPIES = "No avalible copied for this book at the time";
    protected Book borrowedBook = null;
    
    public void borrowBook(String bookTitle) {
        if (borrowedBook != null){
            System.out.println(TOO_MANY_BOOKS_ERROR);
            return;
        }
        
        Book libBook = DigitalLibrary.getInstance().getBook(bookTitle);
        
        if (libBook == null){
            System.out.println(BOOK_NOT_EXIST);
            return;
        }
        
        if (!libBook.borrowCopy()){
            System.out.println(NO_BOOK_COPIES); 
            return;
        }
        
        borrowedBook = libBook;
        
        
    }
    
    public void returnBook(){
        if (borrowedBook != null){
            borrowedBook.returnCopy();
        }
    }
    
    public void displayCurrentBook(){
        if (borrowedBook != null){
            System.out.println(borrowedBook.getContent());
        }
    }
}


abstract class VIPLibraryApp extends LibraryApp implements Observer{
    private static final String BOOK_AVALIBLE = "This book is now avalible: ";
    
    public void registerToWaitlist(){
        borrowedBook.registerObserver(this);
    }
    public void unregisterToWaitlist(){
        borrowedBook.unregisterObserver(this);
    }
    
    public void update(){
        System.out.println(BOOK_AVALIBLE + borrowedBook.getTitle());
    }
    
}

















////////////////////////////
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 

















import java.util.Scanner;

class InvalidTypeException extends Exception{
    private static final String INVALID_TYPE = "There is no Tamagotchi of type: ";
    
    InvalidTypeException(String type){
        super(INVALID_TYPE + type);
    }
}

interface InputMethod{
    String getInput();
}

interface OutputMethod{
    void returnOutput(String output);
}

class KeyboardInput implements InputMethod{
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public String getInput(){
        return scanner.nextLine();
    }
}

class PrintOutput implements OutputMethod{
    @Override
    public void returnOutput(String output){
        System.out.println(output);
    }
}

class IOMethodFactory{
    public static InputMethod getInputMethod(String inputMethod){
        switch (inputMethod){
            case "keyboard":
                return new KeyboardInput();
            default:
                return null;
        }
    }
    
    public static OutputMethod getOutputMethod(String outputMethod){
        switch (outputMethod){
            case "print":
                return new PrintOutput();
            default:
                return null;
        }
    }
}

class TamagotchiFactory{
    public static Tamagotchi getTamagotchiType(String name, String type) throws InvalidTypeException{
        switch (type){
            case "cat":
                return new CatTamagotchi(name);
            case "dog":
                return new DogTamagotchi(name);
            default:
                throw new InvalidTypeException(type);
        }
    }
} 

abstract class Tamagotchi {
    protected static final int MAX_STATUS = 100;
    protected static final int DEFAULT_STATUS = 50;
    protected static final int MIN_STATUS = 0;
    
    protected static final int HUNGER_DECREMENT_VALUE = 10;
    protected static final int ENERGY_DECREMENT_VALUE = 10;
    protected static final int HAPPY_INCREMENT_VALUE = 10;
    protected static final int SLEEP_INCREMENT_VALUE = 20;
    
    protected String name;
    private int hunger;
    private int happiness;
    private int energy;
    
    public abstract void makeHappySound();
    public abstract void specialAction();

    public Tamagotchi(String name) {
        this.name = name;
        this.hunger = DEFAULT_STATUS;
        this.happiness = DEFAULT_STATUS;
        this.energy = DEFAULT_STATUS;
    }

    public void feed() {
        if (hunger > HUNGER_DECREMENT_VALUE) {
            hunger -= HUNGER_DECREMENT_VALUE;
            System.out.println(name + " is eating. Hunger level: " + hunger);
            makeHappySound();
        } else {
            System.out.println(name + " is not hungry.");
        }
    }

    public void play() {
        if (happiness < MAX_STATUS && energy > ENERGY_DECREMENT_VALUE) {
            happiness += HAPPY_INCREMENT_VALUE;
            energy -= ENERGY_DECREMENT_VALUE;
            System.out.println(name + " is playing. Happiness level: " + happiness + ", Energy level: " + energy);
            makeHappySound();
            specialAction();
        } else if (energy <= ENERGY_DECREMENT_VALUE) {
            System.out.println(name + " is too tired to play.");
        } else {
            System.out.println(name + " is already very happy.");
        }
    }

    public void sleep() {
        if (energy < MAX_STATUS) {
            energy += SLEEP_INCREMENT_VALUE;
            System.out.println(name + " is sleeping. Energy level: " + energy);
        } else {
            System.out.println(name + " is fully rested.");
        }
    }

    public void checkStatus() {
        System.out.println("Status of " + name + ":");
        System.out.println("Hunger: " + hunger);
        System.out.println("Happiness: " + happiness);
        System.out.println("Energy: " + energy);
    }
}


class CatTamagotchi extends Tamagotchi{
    private static final String HAPPY_SOUND = " is happy and meows: Meow! Meow!";
    private static final String CHASE_MOUSE = " is happy and meows: Meow! Meow!";
    
    CatTamagotchi(String name){
        super(name);
    }
    
    @Override
    public void makeHappySound(){
        System.out.println(super.name + HAPPY_SOUND);
    }
    
    @Override
    public void specialAction(){
        System.out.println(CHASE_MOUSE);
    }
}

class DogTamagotchi extends Tamagotchi{
    private static final String HAPPY_SOUND = " is happy and barks: Woof! Woof!";
    private static final String FETCH_BALL = "I'm fetching a ball!";
    
    DogTamagotchi(String name){
        super(name);
    }
    
    @Override
    public void makeHappySound(){
        System.out.println(super.name + HAPPY_SOUND);
    }
    
    @Override
    public void specialAction(){
        System.out.println(FETCH_BALL);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the type of your Tamagotchi: ");
        String type = scanner.nextLine();
        System.out.print("Enter the name of your Tamagotchi: ");
        String name = scanner.nextLine();
        
        try{
            Tamagotchi myTamagotchi = TamagotchiFactory.getTamagotchiType(name, type);
        }
        catch (InvalidTypeException e){
            System.err.println(e.getMessage());
            System.exit(1);
        }

        while (true) {
            System.out.println("\nChoose an action: feed, play, sleep, status, quit");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "feed":
                    myTamagotchi.feed();
                    break;
                case "play":
                    myTamagotchi.play();
                    break;
                case "sleep":
                    myTamagotchi.sleep();
                    break;
                case "status":
                    myTamagotchi.checkStatus();
                    break;
                case "quit":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid action. Please choose again.");
            }
        }
    }
}
}


















/////////////////////////////////////////////
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 
/// 







