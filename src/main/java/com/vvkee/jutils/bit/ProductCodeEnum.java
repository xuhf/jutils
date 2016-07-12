package com.vvkee.jutils.bit;

public enum ProductCodeEnum {

	A(16),
	B(17),
	C(18),
	D(19),
	E(20);

	// 在第几位，（右边第一位为的bitIndex为0）
	private int bitIndex;
	private int status;

	private ProductCodeEnum(int bitIndex) {
		this.bitIndex = bitIndex;
		this.status = (1 << bitIndex);
	}

	public int value() {
		return this.status;
	}

	/**
	 * 标志在标志集的第几位，从右往左开始数，从0开始。标志集右边第一位为的bitIndex为0
	 * 
	 * @return
	 */
	public int bitIndex() {
		return bitIndex;
	}

	public static void main(String[] args) {
		System.out.println(A.value() == 0x00010000);
		System.out.println(B.value() == 0x00020000);
		System.out.println(C.value() == 0x00040000);
		System.out.println(D.value() == 0x00080000);
		System.out.println(E.value() == 0x00100000);
	}
}
