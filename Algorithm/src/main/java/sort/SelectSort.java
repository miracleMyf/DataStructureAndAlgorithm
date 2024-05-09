package sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 24, 119, 1, -8, 18};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));

        selectSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
    }

    //选择排序一共进行数组长度减一轮排序,每一轮排序中又是一个循环(每一轮都是将最小的放在操作数据的首位)
    //先假定当前这个数是最小数,然后与后面数进行比较,当发现有比当前数更小的数,则重新确定最小数,并且得到下标
    //当遍历到数字末尾时,就得到本轮最小数和下标,最后进行交换
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];  //最小数
            int minIndex = i;  //最小数下标
            //1.遍历数组寻找最小数
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];  //重新确定最小数,并且得到下标
                    minIndex = j;
                }
            }
            //2.交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            /*System.out.println("第"+ (i+1) +"趟排序后的结果为");
            System.out.println(Arrays.toString(arr));*/
        }
    }
}
