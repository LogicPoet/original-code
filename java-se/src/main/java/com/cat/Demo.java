package com.cat;

/**
 * @author liu.zhi
 * @date 2020/11/16 14:08
 */
public class Demo {

    public static void main(String[] args) {
        int[][] people = new int[][]{
           {7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}
        };
        int[][] ints = reconstructQueue(people);
        for (int i = 0; i < ints.length ; i++){
            System.out.println("["+ints[i][0]+","+ints[i][1]+"]");
        }
    }

    public static int[][] reconstructQueue(int[][] people) {
        int[][] fill = fill(people);
        return fill;
    }

    static int[][] fill(int[][] people){
        int[][] result = new int[people.length][people[1].length];
        // 遍历队列，把不能满足条件的暂存起来
        result[0][0] = people[0][0];
        result[0][1] = people[0][1];

        // 当前需要填入的行数
        int rIndex = 1;
        int lIndex = 1;
        for (int i = 1; i < people.length; i++) {
            int count = count(people[i][0], result,rIndex);
            if (count == people[i][1]) {
                result[rIndex][0] = people[i][0];
                result[rIndex][1] = people[i][1];
                rIndex ++ ;
            } else {
                result[people.length-lIndex][0] = people[i][0];
                result[people.length-lIndex][1] = people[i][1];
                lIndex ++;
            }
        }

        return result;
    }


    /**
     * 计算当前人数
     * @param l
     * @param people
     * @return
     */
    static int count(int l, int[][] people,int max) {
        System.out.println("身高: "+ l);
        int result = 0;
        for (int i = 1; i < max; i++) {
            if (people[i][0] >= l) {
                result++;
            }
            System.out.print(" "+ people[i][1]);
        }
        System.out.println("统计结果: "+ result);
        return result;
    }

}
