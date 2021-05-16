package task2_oop;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    final static String highPriority = "*/";
    final static String lowPriority = "+-";

    public String sortingStation(String input) {
        OwnStack stack = new OwnStack();
        String out = new String();

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if (Character.isDigit(current)) {
                out += current;
            } else if (isUnaryPlus(input, i)) {
                //do not track unary plus
            } else if (isUnaryMinus(input, i)) {
                current = input.charAt(++i);
                out += "0" + current + "-";
            } else if (Character.valueOf('(').equals(current)) {
                stack.add(current);
            } else if (Character.valueOf(')').equals(current)) {
                for (int j = stack.size() - 1; j >= 0; j--) {
                    char charInStack = stack.pop();
                    if (Character.valueOf('(').equals(charInStack)) {
                        break;
                    } else {
                        out += charInStack;
                    }
                }
            } else if (isBinaryOperation(current)) {
                while (stack.size() > 0) {
                    if ((stackHasHigherOrSamePriority(current, stack))) {
                        char lastInStack = stack.pop();
                        out += lastInStack;
                    } else {
                        break;
                    }
                }
                stack.add(current);
            }
        }

        if (stack.size() > 0) {
            out = pushStackToOut(stack, out);
        }
        return out;
    }

    private boolean isUnaryPlus(String string, int i) {
        // it is "+", it is the first symbol or goes after "("
        if (Character.valueOf('+').equals(string.charAt(i))) {
            if ((i == 0) || (i > 0) && Character.valueOf('(').equals(string.charAt(i - 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean isUnaryMinus(String string, int i) {
        // it is "-", it is the first symbol or goes after "("
        if (Character.valueOf('-').equals(string.charAt(i))) {
            if ((i == 0) || (i > 0) && Character.valueOf('(').equals(string.charAt(i - 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean isBinaryOperation(char c) {
        return (lowPriority.indexOf(c) > -1 || highPriority.indexOf(c) > -1);
    }

    private boolean stackHasHigherOrSamePriority(char current, OwnStack stack) {
        char lastInStack = stack.peek();
        if (highPriority.indexOf(lastInStack) > -1) {
            return true;
        }
        if (lowPriority.indexOf(lastInStack) > -1 && lowPriority.indexOf(current) > -1) {
            return true;
        }
        return false;
    }

    private String pushStackToOut(OwnStack stack, String out) {
        while (stack.size() > 0) {
            out += stack.pop();
        }
        return out;
    }

    public int getResultFromRPN(String input) {
        String operations = highPriority + lowPriority;
        List<Integer> stack = new ArrayList<>();
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            if (operations.indexOf(c) > -1) {
                int first = stack.get(stack.size() - 2);
                int second = stack.get(stack.size() - 1);
                char operation = c;
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);

                stack.add(count(first, second, operation));

            } else {
                stack.add(Character.getNumericValue(c));
            }
        }
        return stack.get(0);
    }

    private int count(int first, int second, char operation) {
        if (Character.valueOf('+').equals(operation)) {
            return first + second;
        } else if (Character.valueOf('-').equals(operation)) {
            return first - second;
        } else if (Character.valueOf('*').equals(operation)) {
            return first * second;
        } else if (Character.valueOf('/').equals(operation)) {
            return first / second;
        }
        throw new RuntimeException("Unsupported operation " + operation);
    }

}
