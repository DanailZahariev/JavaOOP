package Inheritance.StrackOfStrings;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    private List<String> data;
    private int index;

    public Stack(List<String> data) {
        this.data = new ArrayList<>();
        this.index++;
    }

    public void push(String element) {
        this.data.add(element);
    }

    public String peek(){
        return this.data.get(this.index);
    }

    public String pop(){
        return this.data.remove(this.index--);
    }

    public boolean isEmpty(){
        return this.data.isEmpty();
    }
}
