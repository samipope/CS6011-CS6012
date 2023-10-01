import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FractionTest {
//initializing a bunch of Fractions to test inside my methods
    Fraction f1 = new Fraction(1,3);
    Fraction f2 = new Fraction(1,4);
    Fraction f3 = new Fraction(-1,4);
    Fraction f4 = new Fraction(1,2);
    Fraction f5 = new Fraction(2,8);
    Fraction f6 = new Fraction(7,12);

    @org.junit.jupiter.api.Test
    void plus() {
        //checking the function with two positive numbers
        assertEquals(f1.plus(f2), f6);
        assertNotEquals(f1.plus(f2), f3);
        assertNotEquals(f1.plus(f2), f4);
        assertNotEquals(f1.plus(f2), f5);
        //checking the method can add a negative and positive fraction
        assertEquals(f2.plus(f3), new Fraction(0, 1));
        //checking the fraction will reduce and add correctly and will add up to another fraction that has been initialized
        assertEquals(f5.plus(f2), f4);
    }

    @org.junit.jupiter.api.Test
    void minus() {
        //testing the result is negative
        assertNotEquals(f2.minus(f1), f3);
        assertEquals(f2.minus(f1), new Fraction(-1, 12));
        //testing when the result is positive
        assertNotEquals(f4.minus(f1), f4);
        assertEquals(f4.minus(f1), new Fraction(1, 6));
        //testing when you subtract a negative number
        assertNotEquals(f2.minus(f3), f2);
        assertEquals(f2.minus(f3), f4);
    }

    @org.junit.jupiter.api.Test
    void times() {
        assertNotEquals(f2.times(f3), f2);
        assertNotEquals(f4.times(f1), f4);
        assertNotEquals(f2.times(f1), f3);
        assertEquals(f1.times(f2), new Fraction(1, 12));
        assertEquals(f3.times(f4), new Fraction(-1, 8));
        assertEquals(f5.times(f6), new Fraction(7, 48));
    }

    @org.junit.jupiter.api.Test
    void dividedBy() {
        assertNotEquals(f2.dividedBy(f3), f2);
        assertNotEquals(f4.dividedBy(f1), f4);
        assertNotEquals(f2.dividedBy(f1), f3);
        assertEquals(f1.dividedBy(f2), new Fraction(4, 3));
        assertEquals(f3.dividedBy(f4), new Fraction(-1, 2));
        assertEquals(f5.dividedBy(f6), new Fraction(3, 7));
    }

    @org.junit.jupiter.api.Test
    void reciporacal() {
        assertNotEquals(f2.reciprocal(), f2);
        assertNotEquals(f4.reciprocal(), f4);
        assertNotEquals(f2.reciprocal(), f3);
        assertEquals(f3.reciprocal(), new Fraction(4, -1));
        assertEquals(f2.reciprocal(), new Fraction(4, 1));
        assertEquals(f4.reciprocal(), new Fraction(2, 1));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
            assertEquals(f1.toString(), "1/3");
            assertEquals(f2.toString(), "1/4");
            assertEquals(f3.toString(), "-1/4");
        }

    @org.junit.jupiter.api.Test
    void toDouble() {
        assertEquals(f2.toDouble(), 0.25, 0.0001);
        assertEquals(f4.toDouble(), 0.5, 0.0001);
        assertEquals(f5.toDouble(), 0.25, 0.0001);
    }

    @Test
    public void runAllTests(){
        plus();
        minus();
        times();
        dividedBy();
        reciporacal();
        toString();
        toDouble();
    }
}