package edu.uoc.pac2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class ContactTest {

    private ByteArrayOutputStream outContent;
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    @BeforeEach
    void init() {
        try{
            Field field = Contact.class.getDeclaredField("nextId");
            field.setAccessible(true);
            field.set(null, 0);
        }catch(Exception e) {
            fail("Parameterized constructor failed");
        }
    }


    @Test
    void testContactDefault() {
        Contact contact = new Contact();
        assertEquals(0, contact.getId());
        assertEquals("Name", contact.getName());
        assertEquals("SURNAME", contact.getSurname());
        assertEquals("+34652668900", contact.getPhone());
        assertEquals("dpoo@uoc.edu", contact.getEmail());
        assertTrue(contact.isEmailPriority());
        assertEquals('F', contact.getGroup());
        assertEquals(null, contact.getBirthday());
        assertEquals(-1, contact.getAge());
    }

    @Test
    void testContact() {
        Contact contact = new Contact("David","Garcia Solorzano","933263696");
        assertEquals(0, contact.getId());
        assertEquals("David", contact.getName());
        assertEquals("GARCIA-SOLORZANO", contact.getSurname());
        assertEquals("933263696", contact.getPhone());
        assertEquals("dpoo@uoc.edu", contact.getEmail());
        assertTrue(contact.isEmailPriority());
        assertEquals('F', contact.getGroup());
        assertEquals(null, contact.getBirthday());
        assertEquals(-1, contact.getAge());
    }

    @Test
    void testGetId() {
        Contact contact = new Contact();
        assertEquals(0, contact.getId());
        assertEquals(1, Contact.getNextId());

        contact = new Contact();
        assertEquals(1, contact.getId());
        assertEquals(2, Contact.getNextId());
    }

    @Test
    void testSetName() {
        Contact contact = new Contact();

        contact.setName("Pau");
        assertEquals("Pau",contact.getName());

        contact.setName("marina");
        assertEquals("marina",contact.getName());

        contact.setName("ELENA");
        assertEquals("ELENA",contact.getName());

        contact.setName("             David");
        assertEquals("David",contact.getName());

        contact.setName("david             ");
        assertEquals("david",contact.getName());

        contact.setName("        david                          ");
        assertEquals("david",contact.getName());

        contact.setName("Jose Antonio Rodolfo Adolfo");
        assertEquals("[ERROR] Name cannot be longer than 20 characters", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setName("Jose Antonio Rodolfo");
        assertEquals("Jose Antonio Rodolfo",contact.getName());
        restoreStreams();

        setUpStreams();
        contact.setName("Jose Antonio Rodolfito");
        assertEquals("[ERROR] Name cannot be longer than 20 characters", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
    }

    @Test
    void testSetSurname() {
        Contact contact = new Contact();

        contact.setSurname("Garcia");
        assertEquals("GARCIA", contact.getSurname());

        contact.setSurname("solorzano");
        assertEquals("SOLORZANO", contact.getSurname());

        contact.setSurname("Garcia Solorzano");
        assertEquals("GARCIA-SOLORZANO", contact.getSurname());

        contact.setSurname("Garcia  Solorzano");
        assertEquals("GARCIA-SOLORZANO", contact.getSurname());

        contact.setSurname("Garcia   Solorzano");
        assertEquals("GARCIA-SOLORZANO", contact.getSurname());

        contact.setSurname("Garcia   Solorzano de la  Rosa");
        assertEquals("GARCIA-SOLORZANO-DE-LA-ROSA", contact.getSurname());
    }

    @Test
    void testSetSurname2() {
        try{
            Contact contact = new Contact();

            contact.setSurname("Garcia", "&");
            assertEquals("GARCIA", contact.getSurname());

            contact.setSurname("solorzano", "&");
            assertEquals("SOLORZANO", contact.getSurname());

            contact.setSurname("Garcia Solorzano","&");
            assertEquals("GARCIA&SOLORZANO", contact.getSurname());

            contact.setSurname("Garcia  Solorzano","=");
            assertEquals("GARCIA=SOLORZANO", contact.getSurname());

            contact.setSurname("Garcia   Solorzano","%");
            assertEquals("GARCIA%SOLORZANO", contact.getSurname());

            contact.setSurname("Garcia   Solorzano de la  Rosa", ":");
            assertEquals("GARCIA:SOLORZANO:DE:LA:ROSA", contact.getSurname());

        }catch(Exception e){
            fail("testSetSurname2 failed");
        }
    }

    @Test
    void testSetBirthday() {
        Contact contact = new Contact();

        LocalDate birthday = LocalDate.of(2022,7,19);

        contact.setBirthday(birthday);
        assertTrue(contact.getBirthday().equals(birthday));

        contact.setBirthday(null);
        assertNull(contact.getBirthday());
    }

    @Test
    void testGetAge() {
        Contact contact = new Contact();
        assertEquals(-1,contact.getAge());

        LocalDate birthday = LocalDate.of(1983,9,12);
        contact.setBirthday(birthday);
        assertEquals(39,contact.getAge());

        birthday = LocalDate.of(1978,11,8);
        contact.setBirthday(birthday);
        assertEquals(44,contact.getAge());

        birthday = LocalDate.of(2014,12,2);
        contact.setBirthday(birthday);
        assertEquals(8,contact.getAge());

        birthday = LocalDate.of(2017,9,16);
        contact.setBirthday(birthday);
        assertEquals(5,contact.getAge());

        contact.setBirthday(null);
        assertEquals(-1,contact.getAge());
    }

    @Test
    void testSetEmailPriority() {
        Contact contact = new Contact();
        assertTrue(contact.isEmailPriority());

        contact.setEmailPriority(false);
        assertFalse(contact.isEmailPriority());
    }
    @Test
    void testSetGroup() {
        Contact contact = new Contact();
        assertEquals('F', contact.getGroup());
        restoreStreams();

        setUpStreams();
        contact.setGroup('W');
        assertEquals('W', contact.getGroup());
        restoreStreams();

        setUpStreams();
        contact.setGroup('F');
        assertEquals('F', contact.getGroup());
        restoreStreams();

        setUpStreams();
        contact.setGroup('O');
        assertEquals('O', contact.getGroup());
        restoreStreams();

        setUpStreams();
        contact.setGroup('w');
        assertEquals("[ERROR] Group is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setGroup('N');
        assertEquals("[ERROR] Group is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();

        setUpStreams();
        contact.setGroup('S');
        assertEquals("[ERROR] Group is incorrect", outContent.toString().replaceAll("\n|\r\n", System.getProperty("line.separator").trim()));
        restoreStreams();
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        //check attribute fields
        assertEquals(9, Contact.class.getDeclaredFields().length);

        try {
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("id").getModifiers()));
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("nextId").getModifiers()));
            assertTrue(Modifier.isStatic(Contact.class.getDeclaredField("nextId").getModifiers()));
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("name").getModifiers()));
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("surname").getModifiers()));
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("phone").getModifiers()));
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("email").getModifiers()));
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("emailPriority").getModifiers()));
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("birthday").getModifiers()));
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredField("group").getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("[ERROR] There is some problem with the definition of the attributes");
            e.printStackTrace();
        }
    }

    @Test
    @Tag("sanity")
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        //check constructors
        assertEquals(3, Contact.class.getDeclaredConstructors().length);

        try {
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredConstructor().getModifiers()));
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredConstructor(String.class, String.class, String.class).getModifiers()));
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredConstructor(String.class, String.class, String.class, String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("There is some problem with the definition of constructors");
            e.printStackTrace();
        }

        //check methods, parameters and return types
        try {
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("getId").getModifiers()));
            assertEquals(int.class, Contact.class.getDeclaredMethod("getId").getReturnType());
            assertTrue(Modifier.isPrivate(Contact.class.getDeclaredMethod("setId").getModifiers()));
            assertTrue(Modifier.isStatic(Contact.class.getDeclaredMethod("getNextId").getModifiers()));
            assertEquals(int.class, Contact.class.getDeclaredMethod("getNextId").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the id attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("getName").getModifiers()));
            assertEquals(String.class, Contact.class.getDeclaredMethod("getName").getReturnType());
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("setName", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the name attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("getSurname").getModifiers()));
            assertEquals(String.class, Contact.class.getDeclaredMethod("getSurname").getReturnType());
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("setSurname", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the surname attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("getBirthday").getModifiers()));
            assertEquals(LocalDate.class, Contact.class.getDeclaredMethod("getBirthday").getReturnType());
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("setBirthday", LocalDate.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the birthday attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("getAge").getModifiers()));
            assertEquals(int.class, Contact.class.getDeclaredMethod("getAge").getReturnType());
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter getAge");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("getPhone").getModifiers()));
            assertEquals(String.class, Contact.class.getDeclaredMethod("getPhone").getReturnType());
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("setPhone", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the phone attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("getEmail").getModifiers()));
            assertEquals(String.class, Contact.class.getDeclaredMethod("getEmail").getReturnType());
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("setEmail", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the email attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("isEmailPriority").getModifiers()));
            assertEquals(boolean.class, Contact.class.getDeclaredMethod("isEmailPriority").getReturnType());
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("setEmailPriority", boolean.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the emailPriority attribute");
            e.printStackTrace();
        }

        try{
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("getGroup").getModifiers()));
            assertEquals(char.class, Contact.class.getDeclaredMethod("getGroup").getReturnType());
            assertTrue(Modifier.isPublic(Contact.class.getDeclaredMethod("setGroup", char.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("[ERROR] There is some problem with the definition of getter or setter methods of the group attribute");
            e.printStackTrace();
        }
    }

}
