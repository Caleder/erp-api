package check.net.erp.base;

import java.util.List;

/**
 * 断言
 *
 * @author David Chen
 *
 */
public class Assert {

	/**
	 * 不为空声明
	 *
	 * @param argument
	 */
	public static void notBlank(final String argument, final String message) {
		if (StrKit.isBlank(argument)) {
			fail(message);
		}
	}

	public static void notBlank(final String argument, final int code) {
		if (StrKit.isBlank(argument)) {
			fail(code);
		}
	}

	/**
	 * 不为null声明
	 *
	 * @param argument
	 * @param errorMessage
	 */
	public static void notNull(final Object argument, final String message) {
		if (argument == null) {
			fail(message);
		}
	}

	public static void notNull(final Object argument, final int code) {
		if (argument == null) {
			fail(code);
		}
	}

	/**
	 * 列表不为空
	 *
	 * @param list
	 */
	public static <T> void notEmpty(List<T> list, String message) {
		if (list == null || list.isEmpty()) {
			fail(message);
		}
	}

	/**
	 * 列表不为空
	 *
	 * @param list
	 * @param errorCode
	 */
	public static <T> void notEmpty(List<T> list, final int code) {
		if (list == null || list.isEmpty()) {
			fail(code);
		}
	}

	// //////////////////////////////////声明为不等

	public static void notZero(long value, String message) {
		if (value == 0l) {
			fail(message);
		}
	}

	public static void notZero(int value, String message) {
		if (value == 0) {
			fail(message);
		}
	}

	public static void notZero(float value, String message) {
		if (Float.compare(value, 0f) == 0) {
			fail(message);
		}
	}

	public static void notZero(double value, String message) {
		if (Double.compare(value, 0d) == 0) {
			fail(message);
		}
	}

	public static void notEquals(long actual, long unexpected, String message) {
		if (actual == unexpected) {
			fail(message);
		}
	}

	public static void notEquals(int actual, int unexpected, String message) {
		if (actual == unexpected) {
			fail(message);
		}
	}

	public static void notEquals(float actual, float unexpected, String message) {
		if (Float.compare(actual, unexpected) == 0) {
			fail(message);
		}
	}

	public static void notEquals(double actual, double unexpected, String message) {
		if (Double.compare(actual, unexpected) == 0) {
			fail(message);
		}
	}

	// //////////////////////////////////声明为相等

	public static void equals(int actual, int expected, String message) {
		if (actual != expected) {
			fail(message);
		}
	}

	public static void equals(long actual, long expected, String message) {
		if (actual != expected) {
			fail(message);
		}
	}

	public static void equals(double actual, double expected, String message) {
		if (Double.compare(actual, expected) != 0) {
			fail(message);
		}
	}

	public static void equals(float actual, float expected, String message) {
		if (Float.compare(actual, expected) != 0) {
			fail(message);
		}
	}

	/**
	 * 与期望列表中的一个相等
	 *
	 * @param actual
	 * @param expectedList
	 * @param message
	 */
	public static void equalsAny(int actual, List<Integer> expectedList, String message) {
		if (!expectedList.contains(actual)) {
			fail(message);
		}
	}

	private static void fail(String message) {
		if (StrKit.isBlank(message)) {
			throw new BizException();
		}

		throw new BizException(message);
	}

	private static void fail(int code) {
		throw new BizException(code);
	}

	public static void notFalse(boolean value, String message) {
		if (!value) {
			fail(message);
		}
	}

	public static void notFalse(boolean value, int code) {
		if (!value) {
			fail(code);
		}
	}

	public static void notTrue(boolean value, String message) {
		if (value) {
			fail(message);
		}
	}

	public static void notTrue(boolean value, int code) {
		if (value) {
			fail(code);
		}
	}

	/**
	 * 之间（含边界）
	 *
	 * @param value
	 *            值
	 * @param least
	 *            至少
	 * @param most
	 *            至多
	 * @param leastMessage
	 *            小于至少值时信息
	 * @param mostMessage
	 *            大于至多值时信息
	 */
	public static void between(int value, int least, int most, String leastMessage, String mostMessage) {
		if (value < least) {
			fail(leastMessage);

		} else if (value > most) {
			fail(mostMessage);
		}
	}

	/**
	 * 之间（含边界）
	 *
	 * @param value
	 *            值
	 * @param least
	 *            至少
	 * @param most
	 *            至多
	 * @param message
	 *            小于至少值或大于至多值时信息
	 */
	public static void between(int value, int least, int most, String message) {
		if (value < least || value > most) {
			fail(message);
		}
	}

	/**
	 * 小于或等于
	 *
	 * @param value
	 *            值
	 * @param most
	 *            最大值
	 * @param message
	 *            信息
	 */
	public static void lessOrEquals(int value, int most, String message) {
		if (value > most) {
			fail(message);
		}
	}

	/**
	 * 小于
	 *
	 * @param value
	 * @param most
	 * @param message
	 */
	public static void lessThan(int value, int most, String message) {
		if (value >= most) {
			fail(message);
		}
	}

	/**
	 * 大于或等于
	 *
	 * @param value
	 * @param least
	 * @param message
	 */
	public static void moreOrEquals(int value, int least, String message) {
		if (value < least) {
			fail(message);
		}
	}

	/**
	 * 大于
	 *
	 * @param value
	 * @param least
	 * @param message
	 */
	public static void moreThan(int value, int least, String message) {
		if (value <= least) {
			fail(message);
		}
	}

}
