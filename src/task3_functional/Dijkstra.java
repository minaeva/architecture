package task3_functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dijkstra {
    final static String highPriority = "*/";
    final static String lowPriority = "+-";
    final static String operations = highPriority + lowPriority;
    static OwnStack stack = new OwnStack();
    static String bpn = new String();
    static OwnStringStack stringStack = new OwnStringStack();
    static int i = 0;

    private static final String input = "2+3*5-1*2-4";

    public static void main(String[] args) {
        //get backward_polish_notation with the help of MAP
        List<Character> list = input.chars()
                .mapToObj(ch -> (char) ch)
                .map(character -> processSymbol(character))
                .collect(Collectors.toList());
        if (stack.size() > 0) {
            bpn = pushStackToBpn(stack, bpn);
        }
        System.out.println(bpn);

        List<String> bpnList = bpn.chars()
                .mapToObj(c -> (char) c)
                .map(s -> String.valueOf(s))
                .collect(Collectors.toList());

        //recursion way
        int result1 = countRecurse(bpnList, 0);
        System.out.println(result1);

        //reduce way
        String result2 = bpnList.stream().reduce("0",
                (first, second) -> String.valueOf(countReduce(first, second)));
        System.out.println(result2);
    }

    private static int countRecurse(List<String> oldBpn, int i) {
        if (oldBpn.size() == 1) {
            return Integer.valueOf(oldBpn.get(0));
        }
        if (operations.indexOf(oldBpn.get(i + 2)) == -1) {
            return countRecurse(oldBpn, i + 1);
        }
        int first = Integer.valueOf(oldBpn.get(i));
        int second = Integer.valueOf(oldBpn.get(i + 1));
        String third = oldBpn.get(i + 2);
        int result = 0;
        switch (third) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = first / second;
                break;
        }
        List<String> newBpn = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            newBpn.add(oldBpn.get(j));
        }
        newBpn.add(String.valueOf(result));
        for (int j = i + 3; j < oldBpn.size(); j++) {
            newBpn.add(oldBpn.get(j));
        }
        return countRecurse(newBpn, 0);
    }

    private static int countReduce(String first, String second) {
        if (!first.equals("0")) {
            stringStack.add(first);
        }
        try {
            Integer.parseInt(second);
            stringStack.add(second);
            return 0;
        } catch (NumberFormatException e) {
            switch (second) {
                case "+":
                    return stringStack.popInt() + stringStack.popInt();
                case "-":
                    return - stringStack.popInt() + stringStack.popInt();
                case "*":
                    return stringStack.popInt() * stringStack.popInt();
                case "/":
                    return 1/stringStack.popInt() * stringStack.popInt();
            }
        }
        throw new RuntimeException("Unsupported operation " + second);
    }

    private static char processSymbol(char current) {
        if (Character.isDigit(current)) {
            bpn += current;
        } else if (isUnaryPlus(input, i)) {
            //do not track unary plus
        } else if (isUnaryMinus(input, i)) {
            current = input.charAt(++i);
            bpn += "0" + current + "-";
        } else if (Character.valueOf('(').equals(current)) {
            stack.add(current);
        } else if (Character.valueOf(')').equals(current)) {
            for (int j = stack.size() - 1; j >= 0; j--) {
                char charInStack = stack.pop();
                if (Character.valueOf('(').equals(charInStack)) {
                    break;
                } else {
                    bpn += charInStack;
                }
            }
        } else if (isBinaryOperation(current)) {
            while (stack.size() > 0) {
                if ((stackHasHigherOrSamePriority(current, stack))) {
                    char lastInStack = stack.pop();
                    bpn += lastInStack;
                } else {
                    break;
                }
            }
            stack.add(current);
        }
        i++;
        return current;
    }

    private static boolean isUnaryPlus(String string, int i) {
        // it is "+", it is the first symbol or goes after "("
        if (Character.valueOf('+').equals(string.charAt(i))) {
            if ((i == 0) || (i > 0) && Character.valueOf('(').equals(string.charAt(i - 1))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUnaryMinus(String string, int i) {
        // it is "-", it is the first symbol or goes after "("
        if (Character.valueOf('-').equals(string.charAt(i))) {
            if ((i == 0) || (i > 0) && Character.valueOf('(').equals(string.charAt(i - 1))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBinaryOperation(char c) {
        return (lowPriority.indexOf(c) > -1 || highPriority.indexOf(c) > -1);
    }

    private static boolean stackHasHigherOrSamePriority(char current, OwnStack stack) {
        char lastInStack = stack.peek();
        if (highPriority.indexOf(lastInStack) > -1) {
            return true;
        }
        if (lowPriority.indexOf(lastInStack) > -1 && lowPriority.indexOf(current) > -1) {
            return true;
        }
        return false;
    }

    private static String pushStackToBpn(OwnStack stack, String out) {
        while (stack.size() > 0) {
            out += stack.pop();
        }
        return out;
    }

}
