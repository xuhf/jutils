package com.vvkee.jutils.bit;

public class FlagUtil {

	public static long getBitValue(long flags, int bitIndex) {
		return (flags & (1 << bitIndex)) >> bitIndex;
	}

	/**
	 * 设置flags的某一位的值
	 * 
	 * @param flags
	 * @param bitIndex
	 *            [0,63]
	 * @param val
	 *            只能为0或1
	 * @return
	 */
	public static long setBitValue(long flags, int bitIndex, long val) {
		return flags & (~(1 << bitIndex)) | (val << bitIndex);
	}
}
