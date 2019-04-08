
package my.suveng.server.common.poi.core;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @description POI实体缓存类
 * @author <a href="linguozhi@52tt.com">soothing</a>
 * @since 2016-07-13
 * @version 1.0.0
 */
public final class POIEntityMetaCache {

	/**
	 * POI实体缓存 K: 实体类class V: 实体类元数据
	 */
	private static ConcurrentHashMap<Class<?>, POIEntityMeta> cache = new ConcurrentHashMap<Class<?>, POIEntityMeta>();

	/**
	 * 获得实体类的原数据（不考虑线程安全问题，因为多次解析不会对业务造成异常）
	 *
	 * @param clazz
	 *            void
	 */
	public static POIEntityMeta getEntityMeta(Class<?> clazz) {
		// 在开发模式下, 支持修改立即生效

		POIEntityMeta entityMeta = cache.get(clazz);
		if (entityMeta != null) {
			return entityMeta;
		}

		entityMeta = ClassMetaResolver.resolver(clazz);
		if (entityMeta != null) {
			cache.put(clazz, entityMeta);
		}
		return entityMeta;
	}

	public static POIEntityMeta getEntityMeta(Class<?> clazz, boolean includeSupperCls) {
		// 在开发模式下, 支持修改立即生效

		POIEntityMeta entityMeta = cache.get(clazz);
		if (entityMeta != null) {
			return entityMeta;
		}

		entityMeta = ClassMetaResolver.resolver(clazz);
		if (entityMeta != null) {
			cache.put(clazz, entityMeta);
		}
		return entityMeta;
	}
}
