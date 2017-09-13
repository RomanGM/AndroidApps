package by.bstu.fit.grm.calculator;

/**
 * Created by Roman on 07.09.2017.
 */

public interface Operations {
    float sum(float x, float y);
    float residual(float x, float y);
    float mult(float x, float y);
    float division(float x, float y);
    float reverseSign(float x);
    float cos(float x);
    float sin(float x);
    float sqr(float x);
    float modulus(float x);
    float addDot(float x);
    float addPi(float x);
    float result(float x, float y);
}
