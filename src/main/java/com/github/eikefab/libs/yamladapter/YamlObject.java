package com.github.eikefab.libs.yamladapter;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

class YamlObject<A> {

    private final String key;
    private final ConfigurationSection section;

    public YamlObject(String key, ConfigurationSection section) {
        this.key = key;
        this.section = section;
    }

    public String getKey() {
        return key;
    }

    public ConfigurationSection getSection() {
        return section;
    }

    public A getValue(Class<? extends YamlAdapter<A>> adapter) {
        try {
            return adapter.newInstance().adapt(getKey(), getSection());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public void save(String path, A value, File file, Class<? extends YamlAdapter<A>> adapter) {
        final Configuration configuration = section.getRoot();

        try {
            final YamlAdapter<A> adapterInstance = adapter.newInstance();
            final FileConfiguration fileConfiguration = (FileConfiguration) configuration;

            fileConfiguration.set(path, adapterInstance.adapt(value));
            fileConfiguration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
