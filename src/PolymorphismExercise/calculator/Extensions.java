package PolymorphismExercise.calculator;

import java.util.ArrayDeque;

public class Extensions {
    public static InputInterpreter buildInterpreter(CalculationEngine engine, ArrayDeque memory) {
        return new InputInterpreter(engine, memory);
    }
}
