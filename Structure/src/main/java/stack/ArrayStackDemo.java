package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试
        ArrayStack stack = new ArrayStack(4);
        boolean loop = true;
        String key = "";
        Scanner sc = new Scanner(System.in);

        while (loop){
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = sc.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入要添加的值");
                    int value = sc.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int num = stack.pop();
                        System.out.println("出栈的值为:"+num);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}


//创建一个用数组实现栈的类
class ArrayStack {
    private int top = -1;  //代表栈顶默认为-1
    private int maxSize; //栈的最大容量
    private int[] stack;  //使用数组来实现栈

    //构造器
    public ArrayStack(int maxSize) {
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
    public int peek(){
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空,没有数据");
        }
        return stack[top];
    }
}