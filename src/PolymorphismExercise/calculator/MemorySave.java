package PolymorphismExercise.calculator;

import java.util.ArrayDeque;

public class MemorySave implements Operation {

    private ArrayDeque<Integer> memory;

    public MemorySave(ArrayDeque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {
        this.memory.push(operand);
    }

    @Override
    public int getResult() {
        return this.memory.peek();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
