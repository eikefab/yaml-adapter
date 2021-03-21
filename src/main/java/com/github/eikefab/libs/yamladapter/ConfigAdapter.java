package com.github.eikefab.libs.yamladapter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;

public class ConfigAdapter<T> {

    private final FileConfiguration fileConfiguration;
    private final Set<T> values;

    public ConfigAdapter(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
        this.values = new HashSet<>();
    }

    public void adapt(String section, Class<? extends YamlAdapter<T>> adapter) {
        final ConfigurationSection configurationSection = fileConfiguration.getConfigurationSection(section);

        for (String key : configurationSection.getKeys(false)) {
            final ConfigurationSection keySection = configurationSection.getConfigurationSection(key);

            YamlObject<T> object = new YamlObject<T>(key, keySection);

            values.add(object.getValue(adapter));
        }
    }

    public Set<T> getValues() {
        return values;
    }

}
