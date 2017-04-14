package net.aurora.fuse.emulator.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.aurora.fuse.emulator.Aurora;

/**
 * The configuration class can be used to read a configuration file and store the values.
 * @author Josh
 */
public class Configuration {
    
    /**
     * The properties object holding the configuration values.
     */
    private Properties _properties;
    
    /**
     * Creates a new instance of the configuration file, storing all the values from the specified file.
     * @param file The file to read.
     */
    public Configuration(String file) {
        try {
            _properties = new Properties();
            _properties.load(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            Aurora.LOGGER.log(Level.SEVERE, "Cannot find ''{0}''!", file);
        } catch (IOException ex) {
            Aurora.LOGGER.log(Level.SEVERE, "Cannot read the file ''{0}'', '{1}", new Object[] { file, ex.getMessage() });
        }
    }
    
    /**
     * Gets a value as String by the specified key.
     * @param key The key to get.
     * @return The value as a String, or an empty string if the key isn't found.
     */
    public String getString(String key) {
        String value = _properties.getProperty(key);
        
        if (value == null) {
            Aurora.LOGGER.log(Level.WARNING, "Cannot find value by key ''{0}'', empty string returned!", key);
            
            return "";
        }
        
        return value;
    }
    
    /**
     * Gets a numeric value by the specified key.
     * @param key The key to get.
     * @return The numeric value, or -1 if the key isn't found or the value can't be converted to an Integer.
     */
    public int getInteger(String key) {
        String value = getString(key);
        
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            Aurora.LOGGER.log(Level.WARNING, "Cannot convert ''{0}'' into an integer, invalid number, -1 returned!", value);
            
            return -1;
        }
    }
}
