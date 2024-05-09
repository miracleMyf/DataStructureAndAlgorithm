package sort;

import java.util.Arrays;

public class BubbleSort {
    //冒泡排序一共进行数组长度减一次循环,每次循环里面又是一次循环
    //每一趟排序的次数都在逐渐减少(每一轮都是将最大的放在末尾),时间复杂度为0(n^2)
    //优化:当一趟排序中没有进行过一次交换,则说明已经是有序了,可以退出循环
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        bubbleSort(arr);
    }

    private static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false; //标识变量,默认没有进行过交换
            for (int j = 0; j < arr.length - 1 - i; j++){
                if (arr[j + 1] < arr[j]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag){
                break;
            }

            System.out.println("第"+ (i+1) +"趟排序后的结果为");
            System.out.println(Arrays.toString(arr));
        }
    }
}
