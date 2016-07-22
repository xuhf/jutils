package com.vvkee.jutils.others;

import java.util.Random;

public class ColorSelect {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			random();
		}
	}

	public static void random() {
		int max = 70;
		int min = 50;
		Random r = new Random();
		int i = r.nextInt(max - min) + min;
		System.out.println(i);
	}

	public static void test() {
		/**
		 * 需求是将values的值落在哪个区间内，根据区间选取颜色
		 */
		int[] values = { 1, 20, 43, 23, 21, 4, 20000, 3500, 5100 };

		for (int i : values) {
			System.out.println("i is " + i + " color is " + getColor(i));
		}
	}

	public static String getColor(int num) {
		// colors 和 stacks保持一致
		String[] colors = { "red", "yellow", "black" };
		int[] stacks = { 1000, 5000, 10000 };
		for (int j = 0; j < stacks.length; j++) {
			int s = stacks[j];
			if (num <= s) {
				String c = colors[j];
				return c;
			}
		}
		return colors[colors.length - 1];
	}

}
