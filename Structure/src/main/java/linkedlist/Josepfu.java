package linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(25);
        list.list();

        System.out.println("测试出圈方法是否正确");
        list.countBoy(1,2,25);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个first节点,当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {   //nums是添加的小孩的数量
        // nums 做一个数据校验
        if (nums < 1) return;
        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);  //先形成一个环
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void list() {
        //先判断环形链表是否为空
        if (first == null) {
            System.out.println("链表中为空,没有添加任何小孩");
            return;
        }
        //遍历链表的辅助指针
        Boy curBoy = first;
        while (curBoy.getNext() != first) {
            System.out.printf("小孩的编号为:%d\n", curBoy.getNo());
            curBoy = curBoy.getNext();
        }
    }

    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param k 从第几个小孩开始数数
     * @param m 数到几的小孩出队列
     * @param n 一共有多少个小孩
     */
    public void countBoy(int k, int m, int n) {
        //1.剪枝操作
        if (first == null || k < 1 || k > n) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        //2.需要定义辅助指针,并且指向链表末尾(需要一直保持在first指针的前一个)
        Boy helper = first;
        while (helper.getNext() != first){
            helper = helper.getNext();
        }
        //3.在报数之前需要将first指向第k个小孩
        for (int i = 0;i < k - 1;i++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //4.当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次, 然后出圈
        //这里是一个循环操作，当环形链表中只有一个元素时退出循环
        while (helper != first){
            for (int i = 0;i < m -1;i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }
}


//定义Boy节点
class Boy {
    private int no;   //编号
    private Boy next; //指向下一个节点,默认null

    Boy(int no) {
        this.no = no;
    }
    //因为成员变量是private,所以要提供对应的get和set方法
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
