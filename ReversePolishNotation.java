import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ReversePolishNotation{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        double accumlator = 0;
        List<Double> stack = new ArrayList<>(2);
        for (int i=0;i<input.length();i++) {
            String s = String.valueOf(input.charAt(i));
            if (s.matches("\\d")) {
                stack.add(Double.valueOf(s));
                continue;
            }
            double element1 = stack.get(stack.size()-1);
            double element2 = stack.get(stack.size()-2);
            if (s.equals("+")) accumlator = element2 + element1;
            if (s.equals("-")) accumlator = element2 - element1;
            if (s.equals("*")) accumlator = element2 * element1;
            if (s.equals("/")) accumlator = element2 / element1;
            if (s.equals("^")) accumlator = Math.pow(element2, element1);

            System.out.println("accumlator: "+accumlator+" / stack: "+stack);
            stack.remove(element1);
            stack.remove(element2);
            stack.add(accumlator);
        }
        System.out.println(stack);
    }
}