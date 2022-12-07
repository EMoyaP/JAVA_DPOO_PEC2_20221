package edu.uoc.pac2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class ContactTest {

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
        try {
            Contact contact = new Contact();
            assertEquals(0, contact.getId());
            assertEquals("Name", contact.getName());
            assertEquals("SURNAME", contact.getSurname());
            assertEquals("+34652668900", contact.getPhone());
            assertEquals("dpoo@uoc.edu", contact.getEmail());
            assertTrue(contact.isEmailPriority());
            assertEquals('F', contact.getGroup());
            assertNull(contact.getBirthday());

            try {
                assertEquals(-1, contact.getAge());
            } catch (Exception e) {
                assertEquals("[ERROR] Birthday is null", e.getMessage());
            }

        } catch (Exception e) {
            fail("testContactDefault");
        }
    }
    @Test
    void testContact() {
        try{
            Contact contact = new Contact("David","Garcia Solorzano","933263696");
            assertEquals(0, contact.getId());
            assertEquals("David", contact.getName());
            assertEquals("GARCIA-SOLORZANO", contact.getSurname());
            assertEquals("933263696", contact.getPhone());
            assertEquals("dpoo@uoc.edu", contact.getEmail());
            assertTrue(contact.isEmailPriority());
            assertEquals('F', contact.getGroup());
            assertNull(contact.getBirthday());

            try{
                assertEquals(-1, contact.getAge());
            }catch(Exception e){
                assertEquals("[ERROR] Birthday is null",e.getMessage());
            }

        }catch(Exception e){
            fail("testCar failed");
        }
    }

    @Test
    void testGetId() {
        try{
            Contact contact = new Contact();

            assertEquals(0, contact.getId());
            assertEquals(1, Contact.getNextId());

            contact = new Contact();
            assertEquals(1, contact.getId());
            assertEquals(2, Contact.getNextId());

        }catch(Exception e){
            fail("testGetId failed");
        }
    }

    @Test
    void testSetName() {
        try{
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

            Exception ex = assertThrows(Exception.class, () ->	contact.setName("Jose Antonio Rodolfo Adolfo"));
            assertEquals("[ERROR] Name cannot be longer than 20 characters", ex.getMessage());

            contact.setName("Jose Antonio Rodolfo");
            assertEquals("Jose Antonio Rodolfo",contact.getName());

            ex = assertThrows(Exception.class, () -> contact.setName("Jose Antonio Rodolfito"));
            assertEquals("[ERROR] Name cannot be longer than 20 characters", ex.getMessage());

        }catch(Exception e){
            fail("testSetName failed");
        }
    }

    @Test
    void testSetSurname() {
        try{
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

        }catch(Exception e){
            fail("testSetSurname failed");
        }
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
        try{
            Contact contact = new Contact();

            LocalDate birthday = LocalDate.of(2022,7,19);

            contact.setBirthday(birthday);
            assertEquals(birthday,contact.getBirthday());

            contact.setBirthday(null);
            assertNull(contact.getBirthday());
        }catch(Exception e){
            fail("testSetBirthday failed");
        }
    }

    @Test
    void testGetAge() {

        try{
            Contact contact = new Contact();
            Exception ex = assertThrows(Exception.class, contact::getAge);
            assertEquals("[ERROR] Birthday is null",ex.getMessage());

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
            ex = assertThrows(Exception.class, contact::getAge);
            assertEquals("[ERROR] Birthday is null",ex.getMessage());

        }catch(Exception e){
            fail("testGetAge failed");
        }
    }

    @Test
    void testSetEmailPriority() {
        Contact contact;

        try{
            contact = new Contact();
            assertTrue(contact.isEmailPriority());

            contact.setEmailPriority(false);
            assertFalse(contact.isEmailPriority());

        }catch(Exception e){
            fail("testSetEmailPriority failed");
        }
    }
    @Test
    void testSetGroup() {
        try{
            Contact contact = new Contact();
            assertEquals('F', contact.getGroup());

            contact.setGroup('W');
            assertEquals('W', contact.getGroup());

            contact.setGroup('F');
            assertEquals('F', contact.getGroup());

            contact.setGroup('O');
            assertEquals('O', contact.getGroup());

            Exception ex = assertThrows(Exception.class, () ->	 contact.setGroup('w'));
            assertEquals("[ERROR] Group is incorrect", ex.getMessage());

            ex = assertThrows(Exception.class, () ->	 contact.setGroup('N'));
            assertEquals("[ERROR] Group is incorrect", ex.getMessage());

            ex = assertThrows(Exception.class, () ->	 contact.setGroup('S'));
            assertEquals("[ERROR] Group is incorrect", ex.getMessage());

        }catch(Exception e){
            fail("testSetGroup failed");
        }
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
