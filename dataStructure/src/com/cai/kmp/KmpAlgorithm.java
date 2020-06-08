package com.cai.kmp;

/**
 * kmp算法
 */
public class KmpAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int i = kmpAlgorithm(str1, str2, kmpNext(str2));
        System.out.println(i);
    }

    /**
     *
     * @param str1
     * @param str2  字符串碎片
     * @param next  部分匹配表
     * @return
     */
    public static int kmpAlgorithm(String str1, String str2, int[] next){
        for (int i = 0, j = 0; i < str1.length(); i++) {

            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                i = i-j+1; //因为i，j从0开始的
                return i;
            }
        }
        return -1;
    }


    /**
     * 部分匹配表
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {

            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }

            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
