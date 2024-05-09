package sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后的数组为:" + Arrays.toString(arr));
    }

    //先分后合
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //分完进行合并
            merge(arr, left, right, mid, temp);
        }
    }

    //合并的方法
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int i = left;    //左边有序序列的初始索引
        int j = mid + 1; //右边有序序列的初始索引
        int t = 0;  //作为临时数组temp的下标

        //1.先把左右两边的有序数据按照规则填充到temp数组
        //直到左右两边其中一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        //2.将有剩余数据的一边的所有数据填充到temp中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        //3.将temp中的数据拷贝回原数组,需要注意temp中元素的个数，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }
}
