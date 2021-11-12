package PolymorphismExercise.calculator;

import java.util.ArrayDeque;

public class InputInterpreter {
    private final ArrayDeque<Integer> memory;
    private CalculationEngine engine;

    public InputInterpreter(CalculationEngine engine, ArrayDeque<Integer> stack) {
        this.engine = engine;
        this.memory = stack;
    }

    public boolean interpret(String input) {
        try {
            engine.pushNumber(Integer.parseInt(input));
        } catch (Exception ex) {
            engine.pushOperation(this.getOperation(input));
        }
        return true;
    }

    public Operation getOperation(String operation) {
        if (operation.equals("*")) {
            return new Multiplication();
        } else if (operation.equals("/")) {
            return new DivisionOperation();
        } else if (operation.equals("ms")) {
            return new MemorySave(this.memory);
        } else if (operation.equals("mr")) {
            return new MemoryRecall(this.memory);
        }
        return null;
    }
}
