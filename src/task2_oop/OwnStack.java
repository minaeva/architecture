package task2_oop;

public class OwnStack {

    private String stack = new String();

    public char pop() {
        char top = peek();
        stack = stack.substring(0, stack.length() - 1);
        return top;
    }

    public char peek() {
        return stack.charAt(stack.length() - 1);
    }

    public void add(char c) {
        stack += c;
    }

    public int size() {
        return stack.length();
    }
}
