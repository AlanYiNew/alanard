package cache;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnection {
	private JedisPool pool = null;
	private JedisCluster cluster = null;

	
	//TODO　test jediscluster using docker and consider flexibility
	@SuppressWarnings("unchecked")
	public JedisConnection(Map<String, Object> map) {
		JedisPoolConfig config = new JedisPoolConfig();
		try {
			if (Boolean.valueOf((String) map.get("usesCluster")) == true) {
				if (map.get("nodes") instanceof Map[]) {
					Set<HostAndPort> nodes = new HashSet<HostAndPort>();
					for (Map<String, Object> obj : (Map<String, Object>[]) map.get("nodes")) {
						String ip = (String) obj.get("ip");
						int port = Integer.valueOf((String) obj.get("port"));
						nodes.add(new HostAndPort(ip, port));
					}
					cluster = new JedisCluster(nodes);
				} else {
					throw new Exception("Fail to find nodes list while jedis is set to use cluster");
				}
			} else {
				int maxTotal = map.get("maxTotal") == null ? 5 : Integer.valueOf((String) map.get("maxTotal"));
				int maxIdle = map.get("maxIdle") == null ? 3 : Integer.valueOf((String) map.get("maxIdle"));
				int minIdle = map.get("minIdle") == null ? 2 : Integer.valueOf((String) map.get("minIdle"));
				int maxWaitMillis = map.get("maxWaitMillis") == null ? 50000
						: Integer.valueOf((String) map.get("maxWaitMillis"));

				config.setMaxTotal(maxTotal);
				config.setMaxIdle(maxIdle);
				config.setMinIdle(minIdle);
				config.setMaxWaitMillis(maxWaitMillis);

				config.setTestOnBorrow(true);
				config.setTestOnReturn(true);
				pool = new JedisPool(config,"localhost");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			// Log.error("Fail in initializing JedisConnection");
		}
	}
}
