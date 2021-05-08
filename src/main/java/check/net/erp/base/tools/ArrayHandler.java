package check.net.erp.base.tools;

public class ArrayHandler implements TokenHandler {
	private Object[] array;
	private int length;

	public ArrayHandler(Object[] array) {
		this.array = array;

		if (this.array != null)
			length = array.length;
	}

	@Override
	public String handle(String token) {
		if (array == null)
			return "";

		int index = Convert.toInt(token, -1);
		if (index == -1 || index >= length)
			return "";

		Object ele = array[index];
		if (ele == null)
			return "";

		return ele.toString();
	}
}
