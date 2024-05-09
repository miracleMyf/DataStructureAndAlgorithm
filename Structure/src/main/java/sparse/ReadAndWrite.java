package sparse;

import java.io.*;
import java.util.ArrayList;

public class ReadAndWrite {
    //将稀疏数组存入txt文件当中
    public static void writeArray(int[][] arr) throws IOException {
        File file = new File("sparse.txt");
        FileWriter fw = new FileWriter(file);
        //遍历二维数组将数组写入txt文件中
        for(int i = 0;i < arr.length;i++){
            for(int j = 0;j < arr[0].length - 1;j++){
                fw.write(arr[i][j] + ",");   //加逗号是为了在进行读操作时方便将其变为数组
            }
            fw.write(arr[i][2] + "");
            fw.write("\n");
        }
        fw.close();
    }

    //将稀疏数组从文件中读取出来
    public static int[][] readSparse() throws IOException {
        FileReader fr = new FileReader("sparse.txt");
        BufferedReader br = new BufferedReader(fr);
        //创建一个集合用来存放读取文件中的内容
        ArrayList<String> list = new ArrayList<String>();
        //用来存放一行的数据
        String line;
        while((line = br.readLine())!= null){
            list.add(line);
        }
        int[][] arr = new int[list.size()][3];
        int count = 0; //计数器
        for (String s : list) {
            //将字符串s通过split方法变为字符串数组
            String[] strs = s.split(",");
            arr[count][0] = Integer.parseInt(strs[0]);
            arr[count][1] = Integer.parseInt(strs[1]);
            arr[count][2] = Integer.parseInt(strs[2]);
            count++;
        }
        fr.close();
        br.close();
        return arr;
    }
}
