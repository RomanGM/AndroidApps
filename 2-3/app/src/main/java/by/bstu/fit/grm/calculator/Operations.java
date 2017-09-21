package by.bstu.fit.grm.calculator;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Created by Roman on 07.09.2017.
 */

public interface Operations {
     void AddFunc(String code, BinaryOperator<Float> op);
     void AddFunc(String code, UnaryOperator<Float> op) ;
     void AddFunc(String code, Supplier<String> op);

     Float GetFunc(String code, Float x) ;
     Float GetFunc(String code, Float x,Float y);
     String GetFunc(String code);
}
