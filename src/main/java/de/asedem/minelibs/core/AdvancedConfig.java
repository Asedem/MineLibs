package de.asedem.minelibs.core;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

public class AdvancedConfig extends YamlConfiguration {

    private final File file;

    /**
     * Creates a SimpleConfiguration from a File.
     *
     * @param file The file where the configuration is in.
     * @throws IOException                   If there are any errors with the file.
     * @throws InvalidConfigurationException If the configuration is not in the right format.
     */
    public AdvancedConfig(@NotNull File file) throws IOException, InvalidConfigurationException {
        this.file = file;
        super.load(file);
    }

    /**
     * Creates a SimpleConfiguration from a file as a String.
     *
     * @param file The file where the configuration is in as a String.
     * @throws IOException                   If there are any errors with the file.
     * @throws InvalidConfigurationException If the configuration is not in the right format.
     */
    public AdvancedConfig(@NotNull String file) throws IOException, InvalidConfigurationException {
        this.file = new File(file);
        super.load(file);
    }

    /**
     * Creates a SimpleConfiguration in a File from a Reader.
     *
     * @param file   The File where the configuration is in.
     * @param reader The Reader where the configuration comes from.
     * @throws IOException                   If there are any errors with the file.
     * @throws InvalidConfigurationException If the configuration is not in the right format.
     */
    public AdvancedConfig(@NotNull File file, @NotNull Reader reader) throws IOException, InvalidConfigurationException {
        this.file = file;
        super.load(reader);
    }

    /**
     * Creates a SimpleConfiguration in a File from a String.
     *
     * @param file     The File where the configuration is in.
     * @param contents The String where the configuration comes from.
     * @throws InvalidConfigurationException If the configuration is not in the right format.
     */
    public AdvancedConfig(@NotNull File file, @NotNull String contents) throws InvalidConfigurationException {
        this.file = file;
        super.loadFromString(contents);
    }

    /**
     * Creates a SimpleConfiguration from a File.
     *
     * @param file The file where the configuration is in.
     * @param createFile If True creates the config file and the rood directory if not exists.
     * @throws IOException                   If there are any errors with the file.
     * @throws InvalidConfigurationException If the configuration is not in the right format.
     */
    public AdvancedConfig(@NotNull File file, boolean createFile) throws IOException, InvalidConfigurationException {
        this.file = file;
        if (createFile) {
            if (!this.file.getParentFile().exists()) this.file.getParentFile().mkdirs();
            if (!this.file.exists()) this.file.createNewFile();
        }
        super.load(file);
    }

    /**
     * Creates a SimpleConfiguration from a file as a String.
     *
     * @param file The file where the configuration is in as a String.
     * @param createFile If True creates the config file and the rood directory if not exists.
     * @throws IOException                   If there are any errors with the file.
     * @throws InvalidConfigurationException If the configuration is not in the right format.
     */
    public AdvancedConfig(@NotNull String file, boolean createFile) throws IOException, InvalidConfigurationException {
        this.file = new File(file);
        if (createFile) {
            if (!this.file.getParentFile().exists()) this.file.getParentFile().mkdirs();
            if (!this.file.exists()) this.file.createNewFile();
        }
        super.load(file);
    }

    /**
     * Creates a SimpleConfiguration in a File from a Reader.
     *
     * @param file   The File where the configuration is in.
     * @param reader The Reader where the configuration comes from.
     * @param createFile If True creates the config file and the rood directory if not exists.
     * @throws IOException                   If there are any errors with the file.
     * @throws InvalidConfigurationException If the configuration is not in the right format.
     */
    public AdvancedConfig(@NotNull File file, @NotNull Reader reader, boolean createFile) throws IOException, InvalidConfigurationException {
        this.file = file;
        if (createFile) {
            if (!this.file.getParentFile().exists()) this.file.getParentFile().mkdirs();
            if (!this.file.exists()) this.file.createNewFile();
        }
        super.load(reader);
    }

    /**
     * Creates a SimpleConfiguration in a File from a String.
     *
     * @param file     The File where the configuration is in.
     * @param contents The String where the configuration comes from.
     * @param createFile If True creates the config file and the rood directory if not exists.
     * @throws InvalidConfigurationException If the configuration is not in the right format.
     */
    public AdvancedConfig(@NotNull File file, @NotNull String contents, boolean createFile) throws IOException, InvalidConfigurationException {
        this.file = file;
        if (createFile) {
            if (!this.file.getParentFile().exists()) this.file.getParentFile().mkdirs();
            if (!this.file.exists()) this.file.createNewFile();
        }
        super.loadFromString(contents);
    }

    /**
     * Give the file, where the configuration has to be saved in.
     *
     * @return the file for the configuration to be saved in.
     */
    @NotNull
    public File getFile() {
        return file;
    }

    /**
     * Sets the specified path to the given value.
     * If value is null, the entry will be removed. Any existing entry will be replaced, regardless of what the new value is.
     * Some implementations may have limitations on what you may store. See their individual javadocs for details. No implementations should allow you to store Configurations or ConfigurationSections, please use ConfigurationSection.createSection(java.lang.String) for that.
     *
     * @param path  Path of the object to set.
     * @param value New value to set the path to.
     * @throws IOException If the configuration cant be saved in a file.
     */
    public void setAndSave(@NotNull String path, @Nullable Object value) throws IOException {
        super.set(path, value);
        this.save();
    }

    /**
     * Saves this FileConfiguration.
     * This method will save using the system default encoding, or possibly using UTF8.
     *
     * @throws IOException If the configuration cant be saved in a file.
     */
    public void save() throws IOException {
        super.save(file);
    }

    /**
     * Saves this FileConfiguration to the specified location.
     * If the file does not exist, it will be created. If already exists, it will be overwritten. If it cannot be overwritten or created, an exception will be thrown.
     * This method will save using the system default encoding, or possibly using UTF8.
     *
     * @param file File to save to.
     * @throws IOException If the configuration cant be saved in a file.
     * @deprecated This superset of the YamlConfiguration save in the right file automatically.
     */
    @Override
    @Deprecated(since = "06.09.2022", forRemoval = true)
    public void save(@NotNull File file) throws IOException {
        super.save(file);
    }

    /**
     * Saves this FileConfiguration to the specified location.
     * If the file does not exist, it will be created. If already exists, it will be overwritten. If it cannot be overwritten or created, an exception will be thrown.
     * This method will save using the system default encoding, or possibly using UTF8.
     *
     * @param file File to save to.
     * @throws IOException If the configuration cant be saved in a file.
     * @deprecated This superset of the YamlConfiguration save in the right file automatically.
     */
    @Override
    @Deprecated(since = "06.09.2022", forRemoval = true)
    public void save(@NotNull String file) throws IOException {
        super.save(file);
    }

    /**
     * Loads this FileConfiguration from the specified location.
     * All the values contained within this configuration will be removed, leaving only settings and defaults, and the new values will be loaded from the given file.
     * If the file cannot be loaded for any reason, an exception will be thrown.
     *
     * @param file File to load from.
     * @throws IOException                   If the file cannot be found.
     * @throws InvalidConfigurationException If the config is not in the right format.
     */
    @Override
    @Deprecated(since = "06.09.2022", forRemoval = true)
    public void load(@NotNull File file) throws IOException, InvalidConfigurationException {
        super.load(file);
    }

    /**
     * Loads this FileConfiguration from the specified location.
     * All the values contained within this configuration will be removed, leaving only settings and defaults, and the new values will be loaded from the given file.
     * If the file cannot be loaded for any reason, an exception will be thrown.
     *
     * @param file File to load from.
     * @throws IOException                   If the file cannot be found.
     * @throws InvalidConfigurationException If the config is not in the right format.
     */
    @Override
    @Deprecated(since = "06.09.2022", forRemoval = true)
    public void load(@NotNull String file) throws IOException, InvalidConfigurationException {
        super.load(file);
    }

    /**
     * Loads this FileConfiguration from the specified reader.
     * All the values contained within this configuration will be removed, leaving only settings and defaults, and the new values will be loaded from the given stream.
     *
     * @param reader the reader to load from
     * @throws IOException                   If the stream cannot be found.
     * @throws InvalidConfigurationException If the config is not in the right format.
     */
    @Override
    @Deprecated(since = "06.09.2022", forRemoval = true)
    public void load(@NotNull Reader reader) throws IOException, InvalidConfigurationException {
        super.load(reader);
    }

    /**
     * Loads this FileConfiguration from the specified string, as opposed to from file.
     * All the values contained within this configuration will be removed, leaving only settings and defaults, and the new values will be loaded from the given string.
     * If the string is invalid in any way, an exception will be thrown.
     *
     * @param contents Contents of a Configuration to load.
     * @throws InvalidConfigurationException If the config is not in the right format.
     */
    @Override
    @Deprecated(since = "06.09.2022", forRemoval = true)
    public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {
        super.loadFromString(contents);
    }
}
