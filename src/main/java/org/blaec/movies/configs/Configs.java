package org.blaec.movies.configs;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Configs {
    public static Config getConfig(String resource) {
        return ConfigFactory.parseResources(resource).resolve();
    }

    public static Config getConfig(String resource, String domain) {
        return getConfig(resource).getConfig(domain);
    }
}
