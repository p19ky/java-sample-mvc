package lab3.model;

import junit.framework.TestCase;

import static org.junit.Assert.assertNotEquals;

public class PersonTest extends TestCase {
    private static Person person;

    public void setUp() throws Exception {
        person=new Person("John","Doe");
    }

    public void tearDown() throws Exception {
    }

    public void testGetFirstName() {
        assertEquals("John", person.getFirstName());
        assertNotEquals("Jon", person.getFirstName());
    }

    public void testSetFirstName() {
        person.setFirstName("Johnny");
        assertEquals("Johnny", person.getFirstName());
        assertNotEquals("John", person.getFirstName());
    }

    public void testGetLastName() {
        assertEquals("Doe", person.getLastName());
        assertNotEquals("Do", person.getLastName());
    }

    public void testSetLastName() {
        person.setLastName("Deep");
        assertEquals("Deep", person.getLastName());
        assertNotEquals("Depp", person.getLastName());
    }
}