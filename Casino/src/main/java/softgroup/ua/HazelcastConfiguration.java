package softgroup.ua;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alexander on 29.03.17.
 */
@Configuration
public class HazelcastConfiguration {
    private static int TEN_MINUES = 10*60;
    @Bean
    public Config config() {
        return new Config()
                .addMapConfig(
                        new MapConfig()
                                .setName("token-cache")
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(TEN_MINUES))
                .setProperty("hazelcast.logging.type", "slf4j");
    }
}