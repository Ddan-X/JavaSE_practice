package ModuleTwo;

/**
 * 定义一个长度为[16][16]的整型二维数组并输入或指定所有位置的元素值，分别实现二维数组中所有行和所有列中所有元素的累加和并打印。
 * 再分别实现二维数组中左上角到右下角和右上角到左下角所有元素的累加和并打印。
 * @author  Dan Luo
 */
public class SumOfArray {
    private int right = 0;
    private int left =0 ;
    public static void main(String[] args) {

        // 1. 定义一个长度为[16][16]的整型二维数组
        int[][] arr = new int[16][16];

        // 2. 指定所有位置的元素值
        // 使用外层for循环控制行数
        for (int i = 0; i < arr.length; i++) {
            // 使用内层for循环控制列数
            for(int j =0; j< arr[i].length; j++){
                arr[i][j]= i+1;
                System.out.print(arr[i][j] + " "); //打印
            }
            System.out.println();
        }

        System.out.println("--------------------------------------------------");

        //实现二维数组中所有行所有元素的累加
        int totalRow =0;
        for (int i = 0; i < arr.length; i++) {
           int rowSum = 0;
           // 使用内层for循环控制列数
            for(int j =0; j< arr[i].length; j++){
                rowSum = rowSum+ arr[i][j];
            }
            totalRow = totalRow+ rowSum;
            System.out.println(i+"行之和："+ rowSum);
        }
        System.out.println("每行之和累加："+ totalRow); //打印

        System.out.println("--------------------------------------------------");

        //实现二维数组中所有列中所有元素的累加
        int totalCol = 0;
        for (int i = 0; i < arr.length; i++) {
            int colSum = 0; // 使用内层for循环控制列数
            for(int j =0; j< arr[i].length; j++){
                colSum = colSum+ arr[j][i];

            }
            totalCol = totalCol + colSum;
            System.out.print(i+ "列之和："+ colSum); //打印
            System.out.println();
        }
        System.out.println("每列之和累加："+ totalCol); //打印
        System.out.println("--------------------------------------------------");

        //声明对象
        SumOfArray s = new SumOfArray();

        int a = s.AddLeftDiagonalLine(arr, 0, 0);
        System.out.println("左上角到右下角之和："+ a); //打印

        System.out.println("--------------------------------------------------");
        int l = s.AddRightDiagonalLine(arr,0, arr.length-1);
        System.out.println("右上角到左下角之和："+ l); //打印
    }

    /**
     * 实现二维数组中左上角到右下角 [0,0] [1,1]...
     * @param arr 数组
     * @param i 左上角开始坐标
     * @param j 左上角开始坐标
     * @return  左上角到右下角之和
     */
    public int AddLeftDiagonalLine(int[][] arr, int i, int j){
        if(i >= arr.length){
            return right;
        }else {

            right = right + arr[i][j];
            return AddLeftDiagonalLine(arr, i+1, j+1);
        }

    }

    /**
     * 实现二维数组中右上角到左下角[0,15][1,14]...
     * @param arr 数组
     * @param i   右上角开始坐标
     * @param j   右上角开始坐标
     * @return      右上角到左下角之和
     */
    public int AddRightDiagonalLine(int[][] arr, int i, int j){
        if(j< 0){
            return left;
        }else {
            left = left + arr[i][j];
            return AddRightDiagonalLine(arr, i+1 ,j-1);
        }

    }
}