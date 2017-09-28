package com.howard.algorithm.SwordRefersToOffer;
/**
 * 剑指offer
 * 求1+2+3+...+n 
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 
 * 思路：利用短路运算符&&来退出递归
 * 1.需利用逻辑与的短路特性实现递归终止。 
 * 2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
 * 3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。
 * 2017年9月24日
 * @author hongwu
 */
public class SumSolution {
    public static int sumSolution(int n) {
    	int sum = n;
        boolean flag = (n>0)&&((sum+=sumSolution(n-1))>0);
        return sum;
    }
    /**
     * 解法2：利用Math的函数...底层也用了除法
     * @param n
     * @return
     */
    public static int sumSolutionII(int n) {
    	n = (int) (Math.pow(n, 2)+n)>>1;
        return n;
    }
    public static void main(String[] args) {
		System.out.println(sumSolution(3));
	}
}
