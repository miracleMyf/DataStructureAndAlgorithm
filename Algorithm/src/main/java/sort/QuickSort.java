package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {-9, 78, 0, 23, -567, 70};
        //int[] arr = {2,1,2,1,2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/

        //测试快排的执行速度
        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        quickSort(arr, 0, arr.length-1);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);

    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];  //基准值
        int temp;

        //while循环的目的是让比pivot 值小放到左边比pivot 值大放到右边
        while (l < r) {
            while (arr[l] < pivot) l++;  //找出大于等于pivot的值进行交换
            while (arr[r] > pivot) r--;  //找出小于等于pivot的值进行交换

            //先要对l和r的大小进行判断
            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) break;
            //进行交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //注意判断条件，和pivot相等也会交换两边数据，如果两边的值都是pivot，交换后不变，所以要手动移动索引
            if (arr[r] == pivot) l++;
            if (arr[l] == pivot) r--;
        }

        //需要对基准数两边再进行快速排序(运用递归调用)
        if (l == r) {
            l++;
            r--;  //l++ r--相当于把基准值去掉之后进行递归
        }
        //向左递归
        if (left < r)  quickSort(arr, left, r);
        //向右递归
        if (right > l) quickSort(arr, l, right);
    }
}
