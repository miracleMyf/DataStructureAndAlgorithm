package sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {
        //根据前面的推导过程，我们可以得到最终的基数排序代码
        //还需要根据元素最多具有多少位 maxlength , 来确定一共循环排序几轮
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxlength = (max + "").length();

        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxlength; i++, n *= 10) {
            //(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序依次取出数据并放入原来的数组中
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++){
                      arr[index++] = bucket[k][l];
                    }
                }
                //每轮排序后需要将数组bucketElementCounts清空
                bucketElementCounts[k] = 0;
            }
            System.out.println("第"+(i+1)+"轮排序，arr =" + Arrays.toString(arr));
        }


        /*//推导的过程如下
        //使用基数排序对数组arr进行排序
        //基数排序是取出元素各位的值,来决定放在哪个桶中 0 ~ 9,使用二维数组int[][] bucket
        int[][] bucket = new int[10][arr.length];

        //需要一个一维数组记录每个桶中有几个数据,从而方便从桶中取出数据进行排序
        int[] bucketElementCounts = new int[10];

        System.out.println("第一轮排序,根据个位进行排序");
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i] % 10;  //取出元素的个位数
            bucket[num][bucketElementCounts[num]] = arr[i];
            bucketElementCounts[num]++;  //代表第num个桶中有一个数据
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;  //作为数组arr的下标
        //遍历每一桶，并将桶中的数据，放入到原数组, i就是各各桶
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int l = 0; l < bucketElementCounts[i]; l++) {
                     arr[index] = bucket[i][l];
                     index++;
                }
            }
            //第1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
            bucketElementCounts[i] = 0;
        }
        System.out.println("第1轮，对个位的排序处理 arr =" + Arrays.toString(arr));


        System.out.println("第二轮排序,根据十位进行排序");
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i] / 10 % 10;  //取出元素的个位数
            bucket[num][bucketElementCounts[num]] = arr[i];
            bucketElementCounts[num]++;  //代表第num个桶中有一个数据
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;  //作为数组arr的下标
        //遍历每一桶，并将桶中的数据，放入到原数组, i就是各各桶
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int l = 0; l < bucketElementCounts[i]; l++) {
                    arr[index] = bucket[i][l];
                    index++;
                }
            }
            //第2轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
            bucketElementCounts[i] = 0;
        }
        System.out.println("第2轮，对十位的排序处理 arr =" + Arrays.toString(arr));


        System.out.println("第三轮排序,根据百位进行排序");
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i] / 100 % 10;  //取出元素的个位数
            bucket[num][bucketElementCounts[num]] = arr[i];
            bucketElementCounts[num]++;  //代表第num个桶中有一个数据
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;  //作为数组arr的下标
        //遍历每一桶，并将桶中的数据，放入到原数组, i就是各各桶
        for (int i = 0; i < bucketElementCounts.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int l = 0; l < bucketElementCounts[i]; l++) {
                    arr[index] = bucket[i][l];
                    index++;
                }
            }
            //第l轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
            bucketElementCounts[i] = 0;
        }
        System.out.println("第3轮，对百位的排序处理 arr =" + Arrays.toString(arr));*/
    }
}
