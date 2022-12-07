package edu.uoc.pac2;

import java.time.LocalDate;

/**
 * Class Contact
 * @author  Eugenio Moya Pérez
 * @version 2.0
 */
public class Contact {

    /**
     * Id contact identifier.
     */
    private int id;

    /**
     * nextId next contact identifier available.
     */
    private static int nextId = 0;

    /**
     * name contact name with a maximum of 20 characters.
     */
    private String name;

    /**
     * surname contact surname.
     */
    private String surname;

    /**
     * phone contact phone number.
     */
    private String phone;

    /**
     * email contact email address.
     */
    private String email;

    /**
     * emailPriority if contact email is priority.
     */
    private boolean emailPriority;

    /**
     * group contact group.
     */
    private char group;

    /**
     * birthDate contact birth date.
     */
    private LocalDate birthday;

    /**
     * Contact class constructor whit the default values.
     * @throws Exception for setName, getAge, setPhone and setEmail.
     */
    public Contact() throws Exception {
        setName("Name");
        setSurname("surname");
        setPhone("+34652668900");
        setEmail("dpoo@uoc.edu");
        setEmailPriority(true);
        setGroup('F');
        setId();
    }

    /**
     * Contact class constructor.
     * @param name This parameter is the name of the contact.
     * @param surname This parameter is the surname of the contact.
     * @param phone This parameter is the phone of the contact.
     * @throws Exception for setName, getAge, setPhone and setEmail.
     */
    public Contact(String name, String surname, String phone) throws Exception {
        setName(name);
        setSurname(surname);
        setPhone(phone);
        setEmail("dpoo@uoc.edu");
        setEmailPriority(true);
        setGroup('F');
        setId();
    }
    /**
     * Contact class constructor.
     * @param name This parameter is the name of the contact.
     * @param surname This parameter is the surname of the contact.
     * @param phone This parameter is the phone of the contact.
     * @throws Exception for setName, getAge, setPhone and setEmail.
     */
    public Contact(String name, String surname, String phone, String email) throws Exception {
        setName(name);
        setSurname(surname);
        setPhone(phone);
        setEmail(email);
        setEmailPriority(true);
        setGroup('F');
        setId();
    }

    /**
     * Getter method that returns the id of the object.
     * @return id as an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method that sets the id whit the next Id available and increments the nextId.
     */
    private void setId() {
        this.id = nextId;
        incNextId();
    }

    /**
     * Getter method that returns the Id available.
     * @return nextId as an integer.
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Method that increments the nextId available.
     */
    private void incNextId() {
        nextId++;
    }

    /**
     * Getter method that returns the name of the object.
     * @return name as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method that sets the name of the object.
     * @param name receives a String with the name of the object.
     * @throws Exception if the name is longer than 20 characters.
     */
    public void setName(String name) throws Exception {
        if (name.trim().length() >20) {
            throw new Exception("[ERROR] Name cannot be longer than 20 characters");
        } else {
            this.name = name.trim();
        }
    }

    /**
     * Getter method that returns the surname of the object.
     * @return surname as a String.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter method that sets the surname of the object.
     * Modifies the received string by removing whitespace and adding the "-" symbol if necessary.
     * @param surname receives a String with the surname of the object.
     */
    public void setSurname(String surname) {
        this.surname = surname.trim().toUpperCase().replaceAll(" +", "-");
    }

    /**
     * Setter method that sets the surname of the object.
     * Modifies the received string by removing whitespace and adding the a symbol if necessary.
     * @param surname receives a String with the surname of the object.
     * @param symbol receives a char with the symbol to be added to the surname.
     */
    public void setSurname(String surname, String symbol) {
        this.surname = surname.trim().toUpperCase().replaceAll(" +", symbol);
    }

    /**
     * Getter method that returns the birthday of the object.
     * @return birthday as a LocalDate.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Setter method that sets the birthday of the object.
     * @param birthday receives a LocalDate with the birthday of the object.
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter method that returns the age of the object.
     * @return birthday as a LocalDate.
     * @throws Exception if the birthday is null.
     */
    public int getAge() throws Exception {
        if (birthday != null) {
            return LocalDate.now().getYear() - birthday.getYear();
        } else {
            throw new Exception("[ERROR] Birthday is null");
        }
    }

    /**
     * Getter method that returns the phone of the object.
     * @return phone as a String.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter method that sets the phone of the object.
     * @param phone receives a String with the phone of the object.
     * @throws Exception if the phone pattern is not valid.
     */
    public void setPhone(String phone) throws Exception {
        if (phone.matches("(\\+\\d{2})?[0-9]{9}")) {
            this.phone = phone;
        } else {
            throw new Exception("[ERROR] Phone pattern is incorrect");
        }
    }

    /**
     * Getter method that returns the email of the object.
     * @return email as a String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method that sets the email of the object.
     * @param email receives a String with the email of the object.
     * @throws Exception if the email pattern is not valid.
     */
    public void setEmail(String email) throws Exception {
        if(email.matches("^[a-zA-Z+]+(.[a-zA-Z]+)*@[a-zA-Z]+\\.[a-zA-Z]{1,}$")){
            this.email = email;
        } else {
            throw new Exception("[ERROR] Email pattern is incorrect");
        }
    }

    /**
     * Getter method that returns the emailPriority of the object.
     * @return emailPriority as a boolean.
     */
    public boolean isEmailPriority() {
        return emailPriority;
    }

    /**
     * Setter method that sets the emailPriority of the object.
     * @param emailPriority receives a boolean with the emailPriority of the object.
     */
    public void setEmailPriority(boolean emailPriority) {
        this.emailPriority = emailPriority;
    }

    /**
     * Getter method that returns the group of the object.
     * @return group as a char.
     */
    public char getGroup() {
        return group;
    }

    /**
     * Setter method that sets the group of the object.
     * @param group receives a char with the group of the object.
     * @throws Exception if the group is not valid.
     */
    public void setGroup(char group) throws Exception {
        //Check if the group is F, W o O
        if(group=='F'||group=='W'||group=='O'){
            this.group = group;
        } else {
            throw new Exception("[ERROR] Group is incorrect");
        }
    }

}




