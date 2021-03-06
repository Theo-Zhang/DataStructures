package Z.linkedlist;

import java.lang.management.BufferPoolMXBean;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero4);
        //显示
        singleLinkedList.list();
    }
}

// 定义SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    // 添加节点到单项链表
    // 思路，当不考虑编号顺序时
    // 1.找到当前链表的最后节点
    // 2.将最后这个节点的next 指向 新的节点
   public void add(HeroNode heroNode){
        // 因为head节点不能动，因为我们需要一个辅助遍历temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true){
            // 找到链表的最后
            if(temp.next == null){
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向链表的最后
        // 将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode){
       // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        // 因为单链表中，我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag表征添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {// 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已经存在
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; // 后移遍历当前链表
        }
        // 判断flag的值
        if(flag){ //
            System.out.printf("准备插入的英雄编号已经%d存在，不能再加入\n",heroNode.no);
        }else{
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 显示链表[遍历]
    public void list(){
        // 判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 因为是头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            // 判断是否到链表最后
            if(temp == null){
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }
}


// 定义HeroNode，每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname+"}";
    }
}

