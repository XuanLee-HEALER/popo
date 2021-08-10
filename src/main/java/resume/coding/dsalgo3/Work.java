package resume.coding.dsalgo3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 使用栈和队列计算一个只有整数和加减乘除的表达式
 *
 * 1 + 2 - 3 * 5 / 2 - 2
 * 思路：将表达式改变为可以运算的序列
 * 需要一个序列结果队列和一个符号栈
 * 如果是整数直接进队列，如果是符号入栈
 * 每个符号如果优先级低于栈顶符号，则将栈顶元素弹出进队列，然后将此符号入队
 * 最后需要一个运算栈，使用队列的内容入栈，碰到符号则计算并将结果入栈
 */
public class Work {

    private static Queue<String> parse(String expr) {
        Queue<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        int prev = 0, next = 0;
        while (next < expr.length()) {
            String c = expr.substring(next, next+1);
            if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
                queue.add(expr.substring(prev, next).trim());
                prev = next + 1;
                next++;

                if (stack.isEmpty()) {
                    stack.push(c);
                } else {
                    if ((c.equals("+") || c.equals("-")) && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                        queue.add(stack.pop());
                        stack.push(c);
                    } else {
                        stack.push(c);
                    }
                }
            } else {
                next++;
            }
        }

        queue.add(expr.substring(prev, next).trim());
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        return queue;
    }

    private static String cal(Queue<String> q) {
        Stack<String> calStack = new Stack<>();

        while (!q.isEmpty()) {
            if (q.peek().equals("+") || q.peek().equals("-") || q.peek().equals("*") || q.peek().equals("/")) {
                var n1 = Integer.valueOf(calStack.pop());
                var n2 = Integer.valueOf(calStack.pop());
                switch (q.peek()) {
                    case "+" -> calStack.push(String.valueOf(n1 + n2));
                    case "-" -> calStack.push(String.valueOf(n1 - n2));
                    case "*" -> calStack.push(String.valueOf(n1 * n2));
                    case "/" -> calStack.push(String.valueOf(n1 / n2));
                }
                q.poll();
            } else {
                calStack.push(q.poll());
            }
        }

        return calStack.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("expr> \033[5m");
        while (scanner.hasNextLine()) {
            String expr = scanner.nextLine();
            var calQueue = parse(expr);
            String out = cal(calQueue);
            System.out.printf("outcome-> %s\n", out);
            System.out.print("expr> \033[5m");
        }
    }
}
