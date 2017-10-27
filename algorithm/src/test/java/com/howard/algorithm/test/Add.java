package com.howard.algorithm.test;
/**
 * 剑指offer
 * 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号
 * 思路：(参考网友)从5和12相加等于17这个例子入手，5加17等于05+17，假设不进位，则5+7 = 2，0+1 = 1，1*10+2 = 12，而因为进位1，即10，所以12+10 =22
 * 换成二进制是否有同样规律？5：101 17：10001，
 * 第一步各位相加但不计进位，得到的结果是10100（最后一位两个数都是1，相加的结果是二进制的10。这一步不计进位，因此结果仍然是0）；
 * 第二步记下进位。在这个例子中只在最后一位相加时产生一个进位，结果是二进制的10;
 * 第三步把前两步的结果相加，得到的结果是10110，转换成十进制正好是22。
 * 
 * 接下来我们试着把二进制的加法用位运算来替代。第一步不考虑进位对每一位相加。0加0、1加1的结果都0，0加1、1加0的结果都是1。
 * 我们注意到，这和异或的结果是一样的。对异或而言，0和0、1和1异或的结果是0，而0和1、l和0的异或结果是1。
 * 接着考虑第二步进位，对0加0、0加1、1加0而言，都不会产生进位，只有1加1时，会向前产生一个进位。
 * 此时我们可以想象成是两个数先做位与运算，然后再向左移动一位。只有两个数都是1的时候，位与得到的结果是1，其余都是0。
 * 第三步把前两个步骤的结果相加。第三步相加的过程依然是重复前面两步，直到不产生进位为止。
 * 2017年9月23日
 * @author hongwu
 */
public class Add {
    public static int add(int num1,int num2) {
    	int i = 0;
    	do { //考虑其中num2为0的情况下必须执行一次
    		i = num1 ^ num2;
    		num2 = (num1 & num2) << 1;
    		num1 = i;
    	}while (num2 != 0);
		return i;
    }
    /**
     * 交换两个数
     * @param num1
     * @param num2
     */
    public static void swap(int num1,int num2) {
    	num1 = num1 ^ num2;
    	num2 = num1 ^ num2;
    	num1 = num1 ^ num2;
    	System.out.println(num1+" "+num2);
    }
    public static void main(String[] args) {
		System.out.println(add(3, 0));
		swap(5,12);
	}
}