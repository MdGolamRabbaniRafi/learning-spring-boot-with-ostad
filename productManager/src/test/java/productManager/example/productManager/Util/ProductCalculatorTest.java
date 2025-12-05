package productManager.example.productManager.Util;

import org.junit.jupiter.api.Test;
import productManager.example.productManager.Exception.InvalidArgumentException;

import static org.junit.jupiter.api.Assertions.*;

class ProductCalculatorTest {
    private final ProductCalculator calculator = new ProductCalculator();

    @Test
    void testCalculateDiscount_NoDiscount() {
        double result = calculator.calculateDiscountedPrice(100,0);

        assertEquals(100.0, result, 0.0001, "Price should remain same when discount is 0%");

    }

    @Test
    void testCalculateDiscount_Half(){
        double result = calculator.calculateDiscountedPrice(200,50);

        assertEquals(100.0,result,0.0001,"50% discount should return half price");
    }

    @Test
    void testCalculateDiscount_Full(){
        double result = calculator.calculateDiscountedPrice(200,100);

        assertEquals(0.0,result,0.0001,"100% discount should return 0 price");
    }

    @Test
    void testCalculateDiscount_InvalidNegativeRate() {
        assertThrows(InvalidArgumentException.class, () ->
                calculator.calculateDiscountedPrice(100.0, -0.1));
    }

    @Test
    void testCalculateDiscount_InvalidAboveOneRate() {
        assertThrows(InvalidArgumentException.class, () ->
                calculator.calculateDiscountedPrice(100.0, 150));
    }

    @Test
    void testIsQuantitySufficient_SufficientStock() {
        assertTrue(calculator.isQuantitySufficient(10, 5),
                "Should return true when current quantity is sufficient");
    }

    @Test
    void testIsQuantitySufficient_ExactStock() {
        assertTrue(calculator.isQuantitySufficient(5, 5),
                "Should return true when current quantity exactly matches required quantity");
    }

    @Test
    void testIsQuantitySufficient_InsufficientStock() {
        assertFalse(calculator.isQuantitySufficient(3, 5),
                "Should return false when current quantity is insufficient");
    }

    @Test
    void testIsQuantitySufficient_InvalidNegativeCurrent() {
        assertThrows(InvalidArgumentException.class, () ->
                        calculator.isQuantitySufficient(-1, 5),
                "Negative current quantity should throw InvalidArgumentException");
    }

    @Test
    void testIsQuantitySufficient_InvalidNegativeRequired() {
        assertThrows(InvalidArgumentException.class, () ->
                        calculator.isQuantitySufficient(5, -3),
                "Negative required quantity should throw InvalidArgumentException");
    }

}