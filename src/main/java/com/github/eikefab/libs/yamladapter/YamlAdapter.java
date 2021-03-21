package com.github.eikefab.libs.yamladapter;

import org.bukkit.configuration.ConfigurationSection;

public interface YamlAdapter<V> {

    V adapt(String key, ConfigurationSection section);
    ConfigurationSection adapt(V value);

}
