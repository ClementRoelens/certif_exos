package model;

public class Cat implements Animal{
    @Override
    public void makeSound() {
        System.out.println("Miaou");
    }

    @Override
    public void attack() {
        System.out.println("Vous avez été griffé");
    }

    @Override
    public void call() {
        System.out.println("Il s'en fout");
    }
}
