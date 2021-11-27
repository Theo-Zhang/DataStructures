package Z.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
        public static void main(String[] args) {
            //测试
            //创建一个队列
            CircleArray Queue = new CircleArray(3);
            char key = ' ';//接收用户输入
            Scanner scanner = new Scanner(System.in);
            boolean loop = true;
            // 输出一个菜单
            while (loop){
                System.out.println("s(show):显示队列");
                System.out.println("e(exit):退出程序");
                System.out.println("a(add):添加数据到队列");
                System.out.println("g(get):从队列取出数据");
                System.out.println("h(head):查看队列头的数据");
                key = scanner.next().charAt(0); //接收一个字符
                switch (key){
                    case 's': // 展示数据
                        Queue.showQueue();
                        break;
                    case 'a': // 添加数据
                        System.out.println("输出一个数");
                        int value = scanner.nextInt();
                        Queue.addQueue(value);
                        break;
                    case'g': // 取出数据
                        try{
                            int res = Queue.getQueue();
                            System.out.printf("取出的数据是%d\n", res);
                        } catch (Exception e){
                            // TODO: handle exception
                            System.out.println(e.getMessage());
                        }
                    case'h': //显示头数据
                        try{
                            int res = Queue.headQueue();
                            System.out.printf("取出的数据是%d\n", res);
                        } catch (Exception e){
                            // TODO: handle exception
                            System.out.println(e.getMessage());
                        }
                    case'e': //退出
                        scanner.close();
                        loop = false;
                        break;
                    default:
                        break;
                }
            }
            System.out.println("程序退出");
        }
}


class CircleArray{
    private int maxSize; //表示数组的最大容量
    private int front; //队列头
    //front 变量的含义做一个调整：front 就指向队列的第一个元素，也就是arr[front]
    //front 的初始值 = 0
    private int rear; //队列尾
    //rear 变量的含义做一个调整：rear 就指向队列的最后一个元素的后一个位置，因为希望空出一个位置
    //rear 的初始值 = 0
    private int[] arr; //该数据用于存放数据，模拟队列

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，不可再加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须要考虑取模
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里需要分析出front是指向队列的第一个元素
        // 1.先把front对应的值保留到一个临时变量
        // 2.将front后移，考虑取模
        // 3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空的，没有数据");
            return;
        }
        for(int i = front; i < front + size(); i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        // 判断
        if (isEmpty()){
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front + 1];
    }
}

