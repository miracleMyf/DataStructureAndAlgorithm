package Structure.src.main.java.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode2 hero1 = new HeroNode2(4, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(5, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(10, "林冲", "豹子头");
        DoubleLinkedList list = new DoubleLinkedList();
        list.addOrderByNo(hero1);
        list.addOrderByNo(hero4);
        list.addOrderByNo(hero3);
        list.addOrderByNo(hero2);
        list.list();

        /*System.out.println("测试删除的方法");
        list.deleteByNo(8);
        list.list();

        System.out.println("测试修改的方法");
        HeroNode2 node = new HeroNode2(1,"mmm","miracle");
        list.updateByNo(node);
        list.list();*/
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    // 先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头结点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历链表的方法
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 dummyHead = head.next;
        while (dummyHead != null) {
            System.out.println(dummyHead);
            dummyHead = dummyHead.next;
        }
    }

    //添加节点的方法
    public void add(HeroNode2 node) {
        //头结点不能变化,需要借助一个虚拟头结点
        HeroNode2 dummyHead = head;
        while (dummyHead.next != null) {
            dummyHead = dummyHead.next;
        }
        dummyHead.next = node;
        node.pre = dummyHead;
    }

    //按照节点的no能够进行排序
    public void addOrderByNo(HeroNode2 node) {
        HeroNode2 dummyHead = head;
        //需要遍历该链表判断节点插入的位置,或者节点已经存在则无法添加(将flag改为true)
        boolean flag = false; // 标识,默认节点不存在
        //链表不为空才需要遍历,为空直接添加即可
        while (dummyHead.next != null){
            if (dummyHead.next.no == node.no){
                flag = true;  //表示节点以及存在
                break;
            } else if (dummyHead.next.no > node.no) {
                //找到了节点需要插入的位置,dummy后面
                break;
            }
            dummyHead = dummyHead.next;
        }
        if (flag){
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", node.no);
        }else if (dummyHead.next != null){  //新插入的节点在链表中间
            HeroNode2 temp = dummyHead.next;
            dummyHead.next = node;
            node.pre = dummyHead;
            node.next = temp;
            temp.pre = node;
        }else {    //新插入的节点在链表末尾
            dummyHead.next = node;
            node.pre = dummyHead;
        }
    }

    public void updateByNo(HeroNode2 node) {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~无法修改");
            return;
        }
        HeroNode2 temp = head.next;
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

    // 从双向链表中删除一个节点,
    // 说明
    // 1 对于双向链表，我们可以直接找到要删除的这个节点
    // 2 找到后，自我删除即可
    public void deleteByNo(int no) {
        if (head.next == null){
            System.out.println("链表为空,无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null){
            if (temp.no == no){
                temp.pre.next = temp.next;
                if (temp.next != null){
                    temp.next.pre = temp.pre;
                }
            }
            temp = temp.next;
        }
    }

}


//定义HeroNode2节点,每个HeroNode2节点就是一个对象
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    //构造器
    HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

