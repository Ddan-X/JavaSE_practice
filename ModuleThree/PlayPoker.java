package ModuleThree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 使用集合实现斗地主游戏的部分功能，要求如下：
 *
 *  （1）首先准备 54 张扑克牌并打乱顺序。
 *
 *  （2）由三个玩家交替摸牌，每人 17 张扑克牌，最后三张留作底牌。
 *
 *  （3）查看三个玩家手中的扑克牌和底牌。
 *
 *  （4）其中玩家手中的扑克牌需要按照大小顺序打印，规则如下：
 *
 *     手中扑克牌从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3
 */
public class PlayPoker {
    public static void main(String[] args) {
        //首先准备 54 张扑克牌并打乱顺序。
        ArrayList<String> poker = new ArrayList<>();

        String[] numbers = {"2","A","K","Q","J","10","9","8","7","6","5","4","3"}; //13
        String[] sign = {"♠","♥","♦","♣"};
        poker.add("大王");
        poker.add("小王");
        for(String n : numbers){
            for(String s : sign){
                poker.add(s+n);// 拼接花色数字
            }
        }
        Collections.shuffle(poker);

        //由三个玩家交替摸牌，每人 17 张扑克牌，最后三张留作底牌。
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();
        ArrayList<String> remain = new ArrayList<>();

        for(int i =0; i<poker.size();i++){
            //交替发牌是关键,找规律
            //[0...53]; 0-player1 1-player2  2-player3  i=0 i*3+0 i*3+1 i*3+2
            //          3-player1 4-player2  5-player3  i=1
            //          6-player1 7-player2  8-player3  i=2
            //          9         10         11         i=3
            //         48-player1 49-player2 50-player3
            //...剩余51，52，53.. 得出i*3的余数规律
            String card = poker.get(i);
            if(i>50){
                remain.add(card);
            } else if(i%3==0){
                player1.add(card);
            }else if(i%3==1){
                player2.add(card);
            }else if(i%3==2){
                player3.add(card);
            }

        }

        //自定义Comparator对象，自定义排序
        Comparator c = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if(getIndex(s1)<=getIndex(s2)){
                    return 1;
                }else {
                    return -1;
                }
            }
        };


        player1.sort(c);
        player2.sort(c);
        player3.sort(c);

        //查看三个玩家手中的扑克牌和底牌。
        System.out.println("玩家1："+player1);
        System.out.println("玩家2："+player2);
        System.out.println("玩家3："+player3);
        System.out.println("底牌："+remain);



    }

    //从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3

    public static int getIndex(String num) {
        if (num.contains("J")) {
            return 9;
        }else if (num.contains("Q")) {
            return 10;
        }else if (num.contains("K")) {
            return 11;
        }else if (num.contains("A")) {
            return 12;
        }else if (num.contains("2")) {
            return 13;
        }else if (num.contains("小王")) {
            return 14;
        }else if (num.contains("大王")) {
            return 15;
        }else if(num.contains("3")){
            return 1;
        }else if(num.contains("4")){
            return 2;
        }else if(num.contains("5")){
            return 3;
        }else if(num.contains("6")){
            return 4;
        }else if(num.contains("7")){
            return 5;
        }else if(num.contains("8")){
            return 6;
        }else if(num.contains("9")){
            return 7;
        }else if(num.contains("10")){
            return 8;
        }else {
            return 0;
        }
    }
}
