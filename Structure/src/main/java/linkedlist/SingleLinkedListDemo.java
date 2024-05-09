package linkedlist;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(5, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(8, "林冲", "豹子头");

        SingleLinkedList list = new SingleLinkedList();

        list.addByOrder(hero1);
        list.addByOrder(hero4);
        list.addByOrder(hero3);
        list.addByOrder(hero2);
        list.list();

        HeroNode hero5 = new HeroNode(4, "卜倩", "蕾姆");
        HeroNode hero6 = new HeroNode(7, "莫云帆", "宿傩");
        HeroNode hero7 = new HeroNode(10, "胡赛", "赛狗");
        HeroNode hero8 = new HeroNode(21, "林冲", "豹子头");
        HeroNode hero9 = new HeroNode(22, "林冲", "豹子头");

        SingleLinkedList list1 = new SingleLinkedList();

        list1.addByOrder(hero5);
        list1.addByOrder(hero6);
        list1.addByOrder(hero7);
        list1.addByOrder(hero8);
        list1.addByOrder(hero9);
        list1.list();

        System.out.println("----------");
        SingleLinkedList list2 = mergeTwoLists(list.getHead(), list1.getHead());
        list2.list();


/*
        list.list();
        System.out.println("反转链表");
        reverseList1(list.getHead());
        list.list();

        System.out.println("测试一下逆序打印的方法");
        reversePrint(list.getHead());




        HeroNode hero0 = new HeroNode(1,"莫云帆","小霸王");
        System.out.println("修改链表");
        list.updateByNo(hero0);
        System.out.println("------------------------------");
        //System.out.println(list.getHead());
        list.list();

        System.out.println("删除第四个英雄");
        list.deleteByNo(4);
        list.list();


        System.out.println("验证获取链表节点个数的方法");
        int size = getLength(list.getHead());
        System.out.printf("链表的长度为:%d\n",size);

        System.out.println("验证获取链表倒数第K个节点的方法");
        HeroNode node = findLastIndexNode(list.getHead(), 3);
        System.out.println("倒数第3个节点为:"+ node);*/
    }

//关于链表面试题的解答:
//1.获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    private static int getLength(HeroNode head) {
        if (head.next == null) return 0; //空链表
        int count = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }


    //2.查找单链表中的倒数第k个结点 【新浪面试题】
    private static HeroNode findLastIndexNode(HeroNode head, int k) {
        if (head.next == null) return null;  //空链表
        //先获取链表的总长度
        int size = getLength(head);
        //剪枝
        if (k <= 0 || k > size) return null;
        HeroNode cur = head.next;
        for (int i = 0; i < size - k; i++) {
            cur = cur.next;
        }
        return cur;
    }


    //3.将单链表反转(创建了一个新链表)
    private static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) return;
        //需要创建一个新的头结点
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode cur = head.next;
        /// HeroNode next = null;  用来存放当前节点[cur]的下一个节点
        while (cur != null) {
            HeroNode next = cur.next;
            cur.next = reverseHead.next;  //将当前节点插入到新链表的最前端
            reverseHead.next = cur;  //将cur连接到新的链表上
            cur = next; //右移
        }
        head.next = reverseHead.next;  //将反转后的链表赋值给原链表
    }

    //3.1将单链表反转(不创建新的链表,使用双指针法)
    private static void reverseList1(HeroNode head) {
        if (head.next == null || head.next.next == null) return;
        HeroNode pre = null;  //cur的下一个节点
        HeroNode cur = head.next;
        while (cur != null) {
            HeroNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head.next = pre;
    }

    //4.逆序打印链表
//可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    private static void reversePrint(HeroNode head) {
        if (head.next == null) return;
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }


    //5.合并两个有序的单链表，合并之后的链表依然有序【课后练习.】
    private static SingleLinkedList mergeTwoLists(HeroNode head1, HeroNode head2) {
        //头结点newHead是不变的
        HeroNode newHead = new HeroNode(0, "", "");
        HeroNode dummy = newHead;
        if (head1.next == null && head2.next == null) return null;
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        while (cur1 != null && cur2 != null) {
            if (cur1.no < cur2.no) {
                dummy.next = cur1;
                cur1 = cur1.next;
            } else {
                dummy.next = cur2;
                cur2 = cur2.next;
            }
            dummy = dummy.next;
        }
        //这行代码起到了将剩余链表拼接到新链表末尾的作用。
        // 这里使用了条件运算符，根据cur1 是否为空来判断选择哪一个链表作为剩余链表。
        // 如果cur1为空，则选择cur2作为剩余链表；如果cur1不为空，则选择cur1作为剩余链表。
        // 然后，将dummy的 next 指针指向这个剩余链表，完成了将剩余链表拼接到新链表末尾的操作。
        dummy.next = cur1 == null ? cur2 : cur1;
        return new SingleLinkedList(newHead);
    }
}


//用来存放英雄节点的链表
class SingleLinkedList {
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加这个链表类的有参和无参构造,在合并两个链表时能用上
    public SingleLinkedList() {
    }

    public SingleLinkedList(HeroNode head) {
        this.head = head;
    }

    //添加一个获取头结点的方法
    public HeroNode getHead() {
        return head;
    }

    //添加节点的方法
    public void add(HeroNode node) {
        //头结点不能变化,需要借助一个虚拟头结点
        HeroNode dummyHead = head;
        while (dummyHead.next != null) {
            dummyHead = dummyHead.next;
        }
        dummyHead.next = node;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode node) {
        //需要遍历该链表,判断链表中是否有该节点,并且找到该节点应该插入的位置
        boolean flag = false; //标识,默认链表中没有改节点
        HeroNode temp = head; //虚拟节点
        while (temp.next != null) { //循环就是要找到节点所插入的位置
            //这三个if的判断条件非常重要
            //if (temp.next == null)  break;
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                flag = true; //节点已经存在,不能再添加
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", node.no);
        } else {
            //插入到链表中, temp的后面
            node.next = temp.next;
            temp.next = node;
        }
    }

    //删除节点,思路:
    //1. head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是temp.next.no 和需要删除的节点的no比较
    public void deleteByNo(int no) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.printf("准备删除的英雄编号:%d 不存在,无法修改\n", no);
                break;
            } else if (temp.next.no == no) {  //找到了要删除的英雄id
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改.
    //说明
    //1. 根据 newHeroNode 的 no 来修改即可
    public void updateByNo(HeroNode node) {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~无法修改");
            return;
        }
        HeroNode temp = head.next;
        //遍历链表判断是否有该节点,有的话就进行修改
        while (temp != null) {
            if (temp.no == node.no) {
                //修改名称
                temp.name = node.name;
                temp.nickName = node.nickName;
                break;
            }
            temp = temp.next;
        }
    }


    //遍历链表的方法
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode dummyHead = head.next;
        while (dummyHead != null) {
            System.out.println(dummyHead);
            dummyHead = dummyHead.next;
        }
    }
}


//定义HeroNode节点,每个HeroNode节点就是一个对象
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    //构造器
    HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}