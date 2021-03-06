package check.net.erp.base.tools;

import java.util.Map;

/**
 * 2014-05-17修改id为string类型
 *
 * @author <a href="mailto:liusan.dyf@taobao.com">liusan.dyf</a>
 * @version 1.0
 * @since 2014年5月17日
 */
public class User {
	private long id;
	private String name;
	private String data;
	private Map<String, Object> extra;

	/**
	 * 是否是空的user对象 2012-08-06 by liusan.dyf
	 */
	private boolean isEmpty;

	/**
	 * 时间戳 2015-1-8 18:39:46 by 六三
	 */
	private long t;

	/**
	 * 临时设置状态到用户对象上，比如设置已经过期
	 */
	private int status;// 2015-1-8 18:47:05 by 六三

	/**
	 * 用户的一些标记
	 */
	private long tags;// 2015-4-20 20:37:50 by 六三

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Map<String, Object> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, Object> v) {
		this.extra = v;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getT() {
		return t;
	}

	public void setT(long t) {
		this.t = t;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTags() {
		return tags;
	}

	public void setTags(long tags) {
		this.tags = tags;
	}
}
