package model;

public class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof");
    }

    @Override
    public void attack() {
        System.out.println("Vous avez été mordu");
    }

    @Override
    public void call() {
        System.out.println("Il vient à vos pieds");
    }
}
