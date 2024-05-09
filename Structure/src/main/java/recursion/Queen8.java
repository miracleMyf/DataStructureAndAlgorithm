package recursion;

public class Queen8 {
    //定义皇后的个数
    int max = 8;
    //定义一个一维数组存放皇后
    int[] arr = new int[max];
    //计数器,统计结果的个数
    static int count = 0;
    //计数器,统计一共judge了多少次
    static int judgeNum = 0;

    public static void main(String[] args) {
        Queen8 queen = new Queen8();
        queen.put(0);
        System.out.printf("一共有%d个解法:\n", count);
        System.out.printf("判断是否冲突%d次", judgeNum);
    }

    //1.已经有结果时打印数组
    private void print() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //2.当我们放置第n个皇后时,需要判断与前n - 1个皇后是否冲突
    private boolean judge(int n) {
        judgeNum++;
        //需要判断是否处于同一列  ---->arr[i] == arr[n]
        //需要判断是否处于同一斜线 --->Math.abs(n - i) == Math.abs(arr[i] - arr[n])
        //不需要判断是否在同一行,因为n是自增的
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[i] - arr[n])) {
                return false;
            }
        }
        return true;
    }

    //3.编写一个方法放置第n个皇后
    private void put(int n) {
        if (n == max) {   //递归结束的条件
            print();  //输出得到的结果
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[n] = i;  //放置第n个皇后
            //判断放置第n个皇后到第i列是是否冲突
            if (judge(n)) {   //不冲突
                put(n + 1);  //递归调用,放置第n+1个皇后
            }
            //else 如果这个位置冲突,那么通过for循环可以选择下一个位置,可以进行回溯
        }
    }
}
