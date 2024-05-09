package sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 24, 119, 1, -8, 18};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        int insertVal;
        int insetIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];  //待插入的值
            insetIndex = i - 1;  //待插入的值前一个元素的下标

            //找到要插入的位置,移动
            while (insetIndex >= 0 && insertVal < arr[insetIndex]){
                arr[insetIndex + 1] = arr[insetIndex];
                insetIndex--;
            }
            //完成插入
            arr[insetIndex + 1] = insertVal;
        }
    }
}
