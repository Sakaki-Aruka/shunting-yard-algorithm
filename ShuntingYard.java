import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class ShuntingYard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null || input.length() == 0) return;

        List<String> outQueue = new ArrayList<>();
        List<String> stack = new ArrayList<>();
        for (int i=0;i<input.length();i++) {
            String s = String.valueOf(input.charAt(i));

            System.out.println("queue: "+outQueue+" / stack: "+stack);

            if (s.matches("\\d")) outQueue.add(s);
            if (s.equals("(")) stack.add(0,s);
            if (s.equals(")")) {
                int start = stack.indexOf("(");
                outQueue.addAll(stack.subList(0, start));
                while (start > -1) {
                    stack.remove(0);
                    start--;
                }
                continue;
            }
            if (s.matches("(\\+|\\-|\\*|/|\\^)")) {
                if (stack.size() == 0) {
                    stack.add(0,s);
                    continue;
                } 
                
                int _newPriority = getPriority(s);
                int _collectedPriority = getPriority(stack.get(0));
                if (_newPriority > _collectedPriority) {
                    stack.add(0, s);
                    continue;
                }

                if (!s.equals("^")) {
                    outQueue.add(stack.get(0));
                stack.remove(0);
                }
                stack.add(0, s);
            }
        }
        outQueue.addAll(stack);
        System.out.println(outQueue);
        System.out.println(String.join("",outQueue));
    }

    private static int getPriority(String in) {
        if (in.equals("^")) return 4;
        if (in.equals("+") || in.matches("-")) return 2;
        if (in.equals("*") || in.equals("/")) return 3;
        return -1;
    }
}
