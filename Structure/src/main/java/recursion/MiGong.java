package recursion;

public class MiGong {
    public static void main(String[] args) {
        //二维数组作为地图
        int[][] map = new int[8][7];
        //map中值1代表为迷宫的墙
        //设置迷宫中的墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 1; i < 7; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        /*map[2][2] = 1;
        map[1][2] = 1;*/

        System.out.println("迷宫的情况");
        for (int[] raw : map) {
            for (int i : raw) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        findWay1(map,1,1);  //小球的初始位置在map[1][1]
        System.out.println("小球走过，并标识过的 地图的情况");
        for (int[] raw : map) {
            for (int i : raw) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    //使用递归回溯给小球找路
    //说明:1.map表示地图
    //2.i,j表示从哪个位置出发,(1,1)
    //3.如果小球可以到位置(6,5),说明通路找到
    //4.约定: 当 map[i][j]为0表示该点没有走过 为1表示墙 为2表示通路可以走 为3表示该点已经走过，但是走不通
    //5.在走迷宫时，需要确定一个策略(方法)下->右->上->左 ，如果该点走不通，再回溯
    private static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {  //递归结束的条件
            return true;
        } else {
            if (map[i][j] == 0) {  //代表这个点还没走过
                map[i][j] = 2;  //先假定改点可以走通
                //按照策略下->右->上->左
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    return true;
                } else {   //此路不通
                    map[i][j] = 3;
                    return false;
                }
            } else {  //如果map[i][j] != 0,可能是1 , 2, 3
                return false;
            }
        }
    }

    //换一个策略,可以来找最优路线
    private static boolean findWay1(int[][] map, int i, int j) {
        if (map[6][5] == 2) {  //递归结束的条件
            return true;
        } else {
            if (map[i][j] == 0) {  //代表这个点还没走过
                map[i][j] = 2;  //先假定改点可以走通
                //按照策略上->右->下->左
                if (findWay1(map, i - 1, j)) {
                    return true;
                } else if (findWay1(map, i, j + 1)) {
                    return true;
                } else if (findWay1(map, i + 1, j)) {
                    return true;
                } else if (findWay1(map, i, j - 1)) {
                    return true;
                } else {   //此路不通
                    map[i][j] = 3;
                    return false;
                }
            } else {  //如果map[i][j] != 0,可能是1 , 2, 3
                return false;
            }
        }
    }
}
