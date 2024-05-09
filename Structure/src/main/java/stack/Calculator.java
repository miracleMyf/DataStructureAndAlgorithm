package stack;

public class Calculator {
    public static void main(String[] args) {
        //测试能否计算一个中缀表达式
        //String expression = "70+20*6-4";
        String expression = "70*3/2+1";
        //需要两个栈,一个存放数字,一个存放字符
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0; //用来遍历表达式
        int num1;
        int num2;
        char operator;  //计算的运算符
        char ch;
        int res = 0;
        String keepNum = "";  //多位数的拼接
        //开始while循环的扫描expression
        while (index < expression.length()) {
            ch = expression.charAt(index);
            //判断ch 是数字还是字符
            if (operatorStack.isOperator(ch)) {
                //是字符,先判断字符栈是否为空
                if (operatorStack.isEmpty()) {
                    //字符栈为空,直接添加
                    operatorStack.push(ch);
                } else {
                    //字符栈不为空,还需要判断字符栈栈顶的字符和当前要加入的字符的优先级
                    //如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数,
                    //在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈
                    // 然后需要再将运算符与字符栈顶的运算符来比较优先级
                    while (!operatorStack.isEmpty() && operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = (char) operatorStack.pop();
                        res = numStack.cal(num1, num2, operator);
                        numStack.push(res);
                    }
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                        operatorStack.push(ch);
                }
            } else {
                //是数字,直接添加到数字栈中
                //numStack.push(ch - 48);
                keepNum += ch;
                //判断ch是否为expression的最后一位
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operatorStack.isOperator(expression.charAt(index + 1))) {
                        //相当于keepNum会一直拼接到后一位为运算符,然后再入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";  //需要将keepNum清空
                    }
                }
            }
            index++;
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行.
        //循环结束的条件为字符栈中没有运算符了
        while (!operatorStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = (char) operatorStack.pop();
            res = numStack.cal(num1, num2, operator);
            numStack.push(res);
        }
        //将数栈的最后数，pop出，就是结果
        //int res2 = numStack.pop();
        System.out.printf("表达式%S计算出来的值为:%d", expression, res);
    }
}


//与之前创建的栈类有所不同,需要增加3个方法,比如判断当前字符是不是运算符,还需要对运算符的优先级进行比较
class ArrayStack2 {
    private int top = -1;  //代表栈顶默认为-1
    private int maxSize; //栈的最大容量
    private int[] stack;  //使用数组来实现栈

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];  //对数组设置大小
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //添加元素,入栈
    public void push(int num) {
        //先判断栈是否已满
        if (isFull()) {
            System.out.println("栈已经满了,无法添加元素");
            return;
        }
        top++;
        stack[top] = num;
    }

    //遍历栈
    public void list() {
        //先判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    //出栈--pop
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空,没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //获取栈顶的元素
    public char peek() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空,没有数据");
        }
        return (char) stack[top];
    }

    //1.判断是不是一个运算符
    public boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    //2.返回运算符的优先级, 优先级使用数字表示,数字越大，则优先级就越高.
    public int priority(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else {
            return -1;
        }
    }

    //3.最后需要从数字栈和符号栈中取出元素进行计算
    public int cal(int num1, int num2, char operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;  // 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}