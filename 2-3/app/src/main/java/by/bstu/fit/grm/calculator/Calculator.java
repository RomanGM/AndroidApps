package by.bstu.fit.grm.calculator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;


public class Calculator implements Operations {

    static final Logger LOG = Logger.getLogger(Calculator.class.getName());
    Map<String, BinaryOperator<Float>> binary = new HashMap();
    Map<String, UnaryOperator<Float>> unary = new HashMap();
    Map<String, Supplier<String>> constant = new HashMap();

    public void AddFunc(String code, BinaryOperator<Float> op){    binary.put(code,op);    }
    public void AddFunc(String code, UnaryOperator<Float> op) {     unary.put(code,op);    }
    public void AddFunc(String code, Supplier<String> op)     {     constant.put(code,op); }

    public Float GetFunc(String code, Float x)               {   return unary.get(code).apply(x);    }
    public Float GetFunc(String code, Float x,Float y)       {   return binary.get(code).apply(x,y); }
    public String GetFunc(String code)                       {   return constant.get(code).get();    }
}
