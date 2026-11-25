package Parcial_02.Practica05;

public class Alumno {
    private int id;
    private String name;
    private int age;
    private String mail;

    public Alumno(int id, String name, int age, String mail) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMail() { return mail; }

    public String toCSV() {
        return id + "," + name + "," + age + "," + mail;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + name + " | Edad: " + age + " | Correo: " + mail;
    }
} 