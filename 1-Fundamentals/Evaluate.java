public class Evaluate {
    private static double calc(ListStack<String> ops, ListStack<Double> vals) {
        String op = ops.pop();
        double b = vals.pop();
        double a = vals.pop();
        double c;
        switch (op) {
            case "+":
                c = a+b;
                break;
            case "-":
                c = a-b;
                break;
            case "*":
                c = a*b;
                break;
            case "/":
                c = a/b;
                break;
            default:
                throw new RuntimeException();
        }
        return c;
    }
    public static double eval(String s) {
        ListStack<String> ops = new ListStack<String>();
        ListStack<Double> vals = new ListStack<Double>();
        String[] ss = s.split("\\s+");
        for (String i : ss) {
            switch (i) {
                case "(":
                    break;
                case "+":
                    ops.push("+");
                    break;
                case "-":
                    ops.push("-");
                    break;
                case "*":
                    ops.push("*");
                    break;
                case "/":
                    ops.push("/");
                    break;
                case ")":
                    vals.push(calc(ops, vals));
                    break;
                default:
                    vals.push(Double.parseDouble(i));
                    break;
            }
        }
        return vals.pop();
    }

    public static void main(String[] args) {
        String s = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        System.out.println(eval(s));
    }
}
