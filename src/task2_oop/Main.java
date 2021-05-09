package task2_oop;

public class Main {

    public static void main(String[] args) {
        InOut inOut = new InOut();
        String input = inOut.read();

        Dijkstra dijkstra = new Dijkstra();
        String rpn = dijkstra.sortingStation(input);
        inOut.write(rpn);

        int result = dijkstra.getResultFromRPN(rpn);
        inOut.write(result);
    }
}
