package check.net.erp.base;

/**
 * JdkLoggerFactory.
 */
public class JdkLoggerFactory implements ILoggerFactory {

	public Logger getLogger(Class<?> clazz) {
		return new JdkLogger(clazz);
	}

	public Logger getLogger(String name) {
		return new JdkLogger(name);
	}
}
