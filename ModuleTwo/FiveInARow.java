package ModuleTwo;


import java.util.Scanner;

/**
 *  编程实现控制台版并支持两人对战的五子棋游戏。
 *
 * （1）绘制棋盘 - 写一个成员方法实现
 *
 * （2）提示黑方和白方分别下棋并重新绘制棋盘 - 写一个成员方法实现。
 *
 * （3）每当一方下棋后判断是否获胜 - 写一个成员方法实现。
 *
 * @author  Dan luo
 */
public class FiveInARow {

    //绘制棋盘
    public void drawBoard(int[][] board){
        //打印第一行
        System.out.print("  ");

        for(int i =0; i< board.length;i++){
            System.out.print(Long.toHexString(i)+" ");//利用十六进制，打印

        }
        System.out.println();

        for(int i =0; i<board.length;i++){

            //打印每行利用数组

            System.out.print(Long.toHexString(i));

            for(int j =0; j< board[i].length; j++){
                //提取数组里的值，数组的值来判断打印棋盘,因为会下棋
                int value = board[i][j];
                char model = ' ';
                switch (value){
                    case 0:
                        model = '+'; //默认值0，则是空棋盘
                        break;
                    case 1:
                        model = 'O'; //先手代表值1，棋子表示为O
                        break;
                    case 2:
                        model='X';
                        break;
                }

                System.out.print(" "+model);
            }

            System.out.println();
        }
    }

    //提示黑方和白方分别下棋并重新绘制棋盘
    public void play(String player1, String player2, int[][] board,Scanner scanner){
        //因为是先循环下棋直到玩家赢了

        drawBoard(board);
        boolean player =true;
        int winPlayer;
        do{
            //判断谁在下棋，先手代表1

            String playerName = player ? player1 : player2;
            System.out.print(playerName+ " 执子下棋");

            int x, y;

            //判断是不是有效坐标
            boolean empty = true;
            String la = "0123456789abcdef";
            do{
                System.out.println("请输入有效坐标：");
                String local = scanner.next();
                //得到坐标 （x,y）
                String rowX =local.substring(0,1);
                String colY =local.substring(1,2);
                empty = la.contains(rowX)&&la.contains(colY);

                while (!empty){
                    System.out.println("此处不能下，请重新输入坐标：");
                    local = scanner.next();
                    rowX =local.substring(0,1);
                    colY =local.substring(1,2);
                    empty = la.contains(rowX)&&la.contains(colY);
                }

                x=Integer.parseInt(rowX, 16);//十六进制,
                y =Integer.parseInt(colY, 16);

                //当empty 是false 则会一直循环
                empty= (local.length()==2) && (x>=0) && (x<board.length) && (y>=0) && (y<board.length)&& (board[x][y]==0);

            }while (!empty);

            //下棋,也相当于赋值给数组 1 or 2
            board [x][y] = player? 1:2;

            //转换棋手
            player = (!player);

            drawBoard(board);
            //判断是否五子连起来了，有没有赢
            winPlayer = fourWayCheck(board,x,y);


        }while (winPlayer ==0);

        System.out.println("Player "+winPlayer+" Win!!");
    }

    //判断是否五子连珠， 五子的连珠虽然有8个方向, 但是四个方向是相反方向，比如：坐标（2，2） 向右上角就是x+1，y+1；左下角就是x-1，y-1

    /**
     * 一个方向是否连续的判断,当这个 1 一直移动 直到遇到 和自己不一样的 2 时， 才退出
     * @param board 棋盘
     * @param x 行坐标
     * @param y  纵坐标
     * @param dx  x向移动的间距只能是 1，-1 ，0
     * @param dy  y向移动的间距只能是 1，-1 ，0
     * @return  一个方向的棋子数
     */
    public int check(int[][]board,int x, int y, int dx, int dy){
        //这里的数组值只有1或者2，判断当前计算的棋子
        int player = board[x][y];
        int count=0;
        int moveX = x;//重新声明一个移动的 其实也可以不声明，下面直接用x
        int moveY= y;
        int movePlayer;

        do{
            count++; //这是当前下的那颗棋子
            moveX = moveX + dx; //移动
            moveY = moveY + dy;
            if(moveY<0 || moveX<0 || moveX > board.length-1||moveY > board.length-1){// 棋子在边框上
                return count;
            }
            movePlayer = board[moveX][moveY];//记录最新移动的棋子是什么，若不是原来的则退出循环

        }while (movePlayer==player);

        return count;
    }

    //正反方向移动， 计算棋子个数 相加
    public boolean checkFive(int[][]board,int x, int y, int dx, int dy){
        int count = 0;
        count = count + check(board, x, y, dx, dy); //正向移动
        count = count + check(board, x, y, -dx, -dy);//反方向移动
        count = count -1;
        return count >= 5;
    }

    public int fourWayCheck(int[][]board,int x, int y){
        boolean five;
        five = checkFive(board, x, y, 0,1 ) //x 不动 y 动的 竖向
                || checkFive(board, x, y,1,0) //y 不动， x 动的 横向
                || checkFive(board, x, y, 1, 1)// x,y 都 动的 右上斜向 ,换成 -1，-1 也可以
                || checkFive(board, x, y, -1,1 );// x,y 都动的 左上斜向， 1，-1也可以

        if(five){
            return board[x][y];
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[16][16];
        FiveInARow game = new FiveInARow();
        String player1 = "snow,O";
        String player2 ="rain,X";
        game.play(player1,player2,board,scanner);
    }
}
