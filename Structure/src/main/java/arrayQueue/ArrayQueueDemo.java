package arrayQueue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';  //接收用户输入
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("p(peek): 查看队列头的数据");
            key = sc.next().charAt(0);
            switch (key){
                case 's':
                    queue.show();
                    break;
                case  'e':
                    sc.close();
                    loop = false;
                    break;
                case 'a':
                    try {
                        System.out.println("输入一个数");
                        int n = sc.nextInt();
                        queue.add(n);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int res = queue.get();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        int res = queue.peek();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("成功退出程序");
    }
}

//使用数组来实现队列
class ArrayQueue {
    private int maxSize; //数组的最大容量
    private int front;   //指向队列头部,不包含首个元素
    private int rear;    //指向队列尾部
    private int[] arr;   //用来存放元素的队列

    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 添加数据到队列
    public void add(int n) {
        //先判断队列是否已满
        if (isFull()) {
            throw new RuntimeException("队列满，不能加入数据~");
            //System.out.println("队列满，不能加入数据~");
            //return;
        }
        rear++;
        arr[rear] = n;
    }

    // 获取队列的数据, 出队列
    public int get() {
        //先判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据~");
        }
        front++;
        return arr[front];
    }

    // 显示队列的所有数据
    public void show() {
        //先判断队列是否空
        if (isEmpty()) {
            System.out.println("队列为空,无法显示数据");
            return;
        }
        for (int i = 0;i < arr.length;i++) {
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }
    // 显示队列的头数据， 注意不是取出数据
    public int peek(){
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据~");
        }
        return arr[front + 1];
    }
}