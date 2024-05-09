package arrayQueue;
import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        System.out.println("测试数组模拟环形队列的案例~~~");
        //设置4, 其队列的有效数据最大是3
        CircleArrayQueue queue = new CircleArrayQueue(4);
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

class CircleArrayQueue {
    private int maxSize; //数组的最大容量
    private int front;   //指向队列首个元素
    private int rear;    //指向队列的最后一个元素的后一个位置
    private int[] arr;   //用来存放元素的队列

    public CircleArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 添加数据到队列
    public void add(int value) {
        //先判断队列是否已满
        if (isFull()) {
            throw new RuntimeException("队列满，不能加入数据~");
        }
        arr[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据, 出队列
    public int get() {
        //先判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据~");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void show() {
        //先判断队列是否空
        if (isEmpty()) {
            System.out.println("队列为空,无法显示数据");
            return;
        }
        //显示队列中有效的数据
        for (int i = front;i < front + size();i++) {
            System.out.printf("arr[%d] = %d\n",i % maxSize,arr[i % maxSize]);
        }
    }
    // 显示队列的头数据， 注意不是取出数据
    public int peek(){
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据~");
        }
        return arr[front];
    }

    //获取队列中有效的数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
}