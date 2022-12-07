package edu.uoc.pac2;

import java.time.LocalDate;

public class Contact {
    private int id;
    private static int nextId = 0;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private boolean emailPriority;
    private char group;
    private LocalDate birthday;

    //Constructors
    public Contact() {
        setName("Name");
        setSurname("Surname");
        setPhone("+34652668900");
        setEmail("dpoo@uoc.edu");
        setEmailPriority(true);
        setGroup('F');
        setId();
    }

    public Contact(String name, String surname, String phone) {
        setName(name);
        setSurname(surname);
        setPhone(phone);
        setEmail("dpoo@uoc.edu");
        setEmailPriority(true);
        setGroup('F');
        setId();
    }

    public Contact(String name, String surname, String phone, String email) {
        setName(name);
        setSurname(surname);
        setPhone(phone);
        setEmail(email);
        setEmailPriority(true);
        setGroup('F');
        setId();
    }

    //Methods
    public int getId() {
        return id;
    }

    private void setId() {
        this.id = nextId;
        incNextId();
    }

    public static int getNextId() {
        return nextId;
    }

    private void incNextId() {
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().length() >20) {
            System.out.println("[ERROR] Name cannot be longer than 20 characters");
        } else {
            this.name = name.trim();
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        //We remove whitespace, convert it to uppercase and replace it with hyphens
        this.surname = surname.trim().toUpperCase().replaceAll(" +", "-");
    }

    public void setSurname(String surname, String symbol) {
        //We remove whitespace, convert it to uppercase and replace it with symbol
        this.surname = surname.trim().toUpperCase().replaceAll(" +", symbol);
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        //Returns the years between the current year and the year of birth of the contact, if it is NULL it returns -1
        if (birthday != null) {
            return LocalDate.now().getYear() - birthday.getYear();
        } else {
            return -1;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        //Check the pattern of the entered phone
        if (phone.matches("(\\+\\d{2})?[0-9]{9}")) {
            this.phone = phone;
        } else {
            System.out.println("[ERROR] Phone pattern is incorrect");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //Check the pattern of the entered email
        if(email.matches("^[a-zA-Z+]+(.[a-zA-Z]+)*@[a-zA-Z]+\\.[a-zA-Z]{1,}$")){
            this.email = email;
        } else {
            System.out.println("[ERROR] Email pattern is incorrect");
        }
    }

    public boolean isEmailPriority() {
        return emailPriority;
    }

    public void setEmailPriority(boolean emailPriority) {
        this.emailPriority = emailPriority;
    }

    public char getGroup() {
        return group;
    }

    public void setGroup(char group) {
        //Check if the group is F, W o O
        if(group=='F'||group=='W'||group=='O'){
            this.group = group;
        } else {
            System.out.println("[ERROR] Group is incorrect");
        }
    }

}




