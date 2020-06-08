package com.cai.kmp;

/**
 * 暴力匹配字符串
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "尚硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅谷你好";
        String str2 = "尚硅谷你尚硅谷你";
        int i = violenceMatch(str1, str2);
        System.out.println(i);
    }

    /**
     * 暴力匹配
     * @param str1   被匹配的字符串
     * @param str2   字符串碎片
     */
    public static int violenceMatch(String str1, String str2){
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int length1 = c1.length;
        int length2 = c2.length;
        int i = 0;
        int j = 0;//指向碎片
        while (i < length1 && j < length2){
            if(c1[i] == c2[j]){
                i++;
                j++;
            }else{
                i = i-j+1;
                j = 0;//j-j
            }
        }
        if(j == length2){
            return i - j;
        }else{
            return -1;
        }
    }
}
