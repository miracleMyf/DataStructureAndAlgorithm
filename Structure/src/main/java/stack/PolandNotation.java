package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //为了方便，逆波兰表达式中的数字和符号都用空格隔开
        String expression = "1+((2+3)*4)-5";//注意表达式
        //String expression = "1+(2-3*4)/5+6";//注意表达式

        List<String> infixExpressionList = toInfixExpressionArr(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]

        List<String> suffixExpreesionList = toSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的List=" + suffixExpreesionList); //ArrayList [1,2,3,+,4,*,+,5,–]

        int value = calculate(suffixExpreesionList);
        System.out.printf("表达式%s的计算结果为%d", expression, value);

       /* //先将表达式变为数组
        String[] arr = expression.split(" ");
        int value = calculate(arr);
        System.out.println(value);*/
    }


    //因为直接对表达式str进行操作不方便，因此先将"1+((2+3)×4)-5" 中缀的表达式转化为对应的list集合
    //1.方法:将中缀表达式转成对应的List
    public static List<String> toInfixExpressionArr(String s) {
        List<String> list = new ArrayList<>();
        int index = 0;  //从左到右遍历表达式的指针
        String str = "";     //用于对多位数的拼接
        while (index < s.length()) {
            char c = s.charAt(index);
            //如果为运算符则直接加入到list集合中
            if (c < 48 || c > 57) {
                list.add("" + c);
            } else {
                //为数字要判断其下一位是否还是数字,需要进行拼接,直到其下一位为运算符,或者到了表达式的末尾
                str += c;
                if (index == s.length() - 1) {
                    list.add("" + str);
                } else if (s.charAt(index + 1) < 48 || s.charAt(index + 1) > 57) {
                    list.add("" + str);
                    str = "";   //需要将str清空
                }
            }
            index++;
        }
        return list;
    }

    //2.方法:将中缀表达式的List转成对应的后缀表达式的list,
    /**
     * @param ls 中缀表达式的list集合
     */
    public static List<String> toSuffixExpreesionList(List<String> ls) {
        //创建一个栈存放运算符
        Stack<String> s1 = new Stack<>();
        //说明：因为s2 这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        List<String> s2 = new ArrayList<>();
        //遍历ls集合
        for (String item : ls) {
            //如果是数字则加入s2中
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push("(");
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();  //消除")"
            } else {
                //item是运算符的时候需要判断优先级
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //3.方法:完成对逆波兰表达式的计算
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")) {  //可以匹配多位整数(正则表达式)
                stack.push(s);
            } else {
                int nums1 = Integer.parseInt(stack.pop());
                int nums2 = Integer.parseInt(stack.pop());
                int res = 0;
                if (s.equals("+")) {
                    res = nums1 + nums2;
                } else if (s.equals("-")) {
                    res = nums2 - nums1;
                } else if (s.equals("*")) {
                    res = nums1 * nums2;
                } else if (s.equals("/")) {
                    res = nums2 / nums1;
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 1;
    private static final int DIV = 1;

    public static int getValue(String s) {
        int res = 0;
        switch (s) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                break;
        }
        return res;
    }
}