package com.vvkee.jutils.bit;

/**
 * Java位运算 通过位运算，可以在一个状态下判断是否支持某个产品，是否存在某个状态
 * 
 * @author xuhf
 *
 */
public class BitUtil {

	public static void main(String[] args) {
		int a = 1 << 0;
		int b = 1 << 1;
		int c = a | b;
		int d = c & ~a;
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(c));
		System.out.println(Integer.toBinaryString(d));
	}
}
