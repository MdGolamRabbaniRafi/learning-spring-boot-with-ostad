package productManager.example.productManager.Util;

import productManager.example.productManager.Exception.InvalidArgumentException;

public class ProductCalculator {
    public double calculateDiscountedPrice(double originalPrice, double discountRate) {
        if (originalPrice < 0 || discountRate < 0 || discountRate > 100) {
            throw new InvalidArgumentException("Invalid discount rate");
        }
        return originalPrice - (originalPrice * (discountRate / 100));
    }
    public boolean isQuantitySufficient(int currentQuantity, int requiredQuantity) {
        if (currentQuantity < 0 || requiredQuantity < 0) {
            throw new InvalidArgumentException("Quantity cannot be negative");
        }
        return currentQuantity >= requiredQuantity;
    }
}
