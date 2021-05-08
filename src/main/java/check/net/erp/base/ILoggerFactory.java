package check.net.erp.base;

/**
 * ILoggerFactory.
 */
public interface ILoggerFactory {

	Logger getLogger(Class<?> clazz);

	Logger getLogger(String name);
}
