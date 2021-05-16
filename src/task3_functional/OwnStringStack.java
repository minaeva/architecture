package task3_functional;

import java.util.ArrayList;
import java.util.List;

public class OwnStringStack {

    private List<String> stack = new ArrayList<>();

    public String pop() {
        String top = peek();
        stack.remove(lastOne());
        return top;
    }

    public int popInt() {
        String top = peek();
        stack.remove(lastOne());
        return Integer.parseInt(top);
    }

    public String peek() {
        return lastOne();
    }

    public void add(String s) {
        stack.add(s);
    }

    public int size() {
        return stack.size();
    }

    private String lastOne() {
        return stack.get(stack.size() - 1);
    }
}
