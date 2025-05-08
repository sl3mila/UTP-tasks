/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad1;


import jdk.jshell.EvalException;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.function.BinaryOperator;

public class Calc {

    private BigDecimal result;
    private HashMap<String, BinaryOperator<BigDecimal>> operations;

    public Calc() {}

    public String doCalc(String cmd) {
        String[] calcul = cmd.split("\\s+");

        try {
            BigDecimal num1 = new BigDecimal(calcul[0]);
            BigDecimal num2 = new BigDecimal(calcul[2]);
            String operation = calcul[1];

            operations = new HashMap<>();
            operations.put("+", BigDecimal::add);
            operations.put("-", BigDecimal::subtract);
            operations.put("*", BigDecimal::multiply);
            operations.put("/", (a, b) -> a.divide(b, 6, RoundingMode.HALF_UP));

            result = operations.getOrDefault(operation, (a, b) -> BigDecimal.ZERO).apply(num1, num2);

            return result.stripTrailingZeros().toPlainString();
        } catch (Exception e) {
            return "Invalid command to calc";
        }
    }
}  
