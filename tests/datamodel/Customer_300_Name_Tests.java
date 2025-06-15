package datamodel;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Customer_300_Name_Tests {

    private Customer c1;

    @BeforeEach
    public void setUpBeforeEach() {
        c1 = new Customer();
    }

    @Test @Order(300)
    void test300_setNameFirstAndLastName() {
        c1.setName("Eric", "Meyer");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(301)
    void test301_setNameFirstAndLastName() {
        c1.setName("", "Meyer");
        assertEquals("", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(302)
    void test302_setNameFirstAndLastName() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class, () -> c1.setName("Eric", "")
        );
        assertEquals("last name empty", thrown.getMessage());
        assertEquals("", c1.getFirstName());
        assertEquals("", c1.getLastName());
    }

    @Test @Order(303)
    void test303_setNameFirstAndLastName() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class, () -> c1.setName("", "")
        );
        assertEquals("last name empty", thrown.getMessage());
        assertEquals("", c1.getFirstName());
        assertEquals("", c1.getLastName());
    }

    @Test @Order(310)
    void test310_setNameSingleName() {
        c1.setName("Eric Meyer");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(311)
    void test311_setNameSingleName() {
        c1.setName("Meyer, Eric");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(312)
    void test312_setNameSingleName() {
        c1.setName("Meyer; Eric");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(313)
    void test313_setNameSingleName() {
        c1.setName("E. Meyer");
        assertEquals("E.", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(320)
    void test320_setNameDoubleLastName() {
        c1.setName("Tim Schulz-Mueller");
        assertEquals("Tim", c1.getFirstName());
        assertEquals("Schulz-Mueller", c1.getLastName());
    }

    @Test @Order(321)
    void test321_setNameDoubleLastName() {
        c1.setName("Schulz-Mueller, Tim");
        assertEquals("Tim", c1.getFirstName());
        assertEquals("Schulz-Mueller", c1.getLastName());
    }

    @Test @Order(322)
    void test322_setNameDoubleLastName() {
        c1.setName("Schulz-Mueller; Tim");
        assertEquals("Tim", c1.getFirstName());
        assertEquals("Schulz-Mueller", c1.getLastName());
    }

    @Test @Order(330)
    void test330_setNameSingleArgumentConstructor() {
        Customer c1 = new Customer("Eric Meyer");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(331)
    void test331_setNameSingleArgumentConstructor() {
        Customer c1 = new Customer("Meyer, Eric");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(332)
    void test332_setNameSingleArgumentConstructor() {
        Customer c1 = new Customer("Meyer; Eric");
        assertEquals("Eric", c1.getFirstName());
        assertEquals("Meyer", c1.getLastName());
    }

    @Test @Order(333)
    void test333_setNameSingleArgumentConstructor() {
        Customer c1 = new Customer("Tim Schulz-Mueller");
        assertEquals("Tim", c1.getFirstName());
        assertEquals("Schulz-Mueller", c1.getLastName());
    }

    @Test @Order(334)
    void test334_setNameSingleArgumentConstructor() {
        Customer c1 = new Customer("Schulz-Mueller, Tim");
        assertEquals("Tim", c1.getFirstName());
        assertEquals("Schulz-Mueller", c1.getLastName());
    }

    @Test @Order(335)
    void test335_setNameSingleArgumentConstructor() {
        Customer c1 = new Customer("Schulz-Mueller; Tim");
        assertEquals("Tim", c1.getFirstName());
        assertEquals("Schulz-Mueller", c1.getLastName());
    }
}
