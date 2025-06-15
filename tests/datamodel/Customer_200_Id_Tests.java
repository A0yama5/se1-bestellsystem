package datamodel;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Customer_200_Id_Tests {

    @Test @Order(200)
    void test200_IdNullAfterConstruction() {
        Customer c = new Customer();
        assertEquals(null, c.getId());
    }

    @Test @Order(201)
    void test201_setIdRegularValue() {
        Customer c = new Customer();
        c.setId(123456L);
        assertEquals(123456L, c.getId());
    }

    @Test @Order(202)
    void test202_setIdTwiceStillFirstValue() {
        Customer c = new Customer();
        c.setId(100L);
        c.setId(999L);
        assertEquals(100L, c.getId());
    }

    @Test @Order(210)
    void test210_setIdMinValueAndPlusOne() {
        Customer c1 = new Customer();
        c1.setId(1L);
        assertEquals(1L, c1.getId());

        Customer c2 = new Customer();
        c2.setId(2L);
        assertEquals(2L, c2.getId());
    }

    @Test @Order(211)
    void test211_setIdMaxValueAndMinusOne() {
        Customer c1 = new Customer();
        c1.setId(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, c1.getId());

        Customer c2 = new Customer();
        c2.setId(Long.MAX_VALUE - 1);
        assertEquals(Long.MAX_VALUE - 1, c2.getId());
    }

    @Test @Order(212)
    void test212_setIdZero() {
        Customer c = new Customer();
        c.setId(0L);
        assertEquals(0L, c.getId());
    }

    @Test @Order(220)
    void test220_setIdNegativeValue() {
        Customer c = new Customer();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> c.setId(-1L));
        assertEquals("invalid id (negative)", ex.getMessage());
    }

    @Test @Order(221)
    void test221_setIdLongMinValue() {
        Customer c = new Customer();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> c.setId(Long.MIN_VALUE));
        assertEquals("invalid id (negative)", ex.getMessage());
    }
} 
