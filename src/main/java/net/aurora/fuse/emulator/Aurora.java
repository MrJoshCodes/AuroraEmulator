package net.aurora.fuse.emulator;

import java.util.logging.Logger;
import net.aurora.fuse.emulator.core.Configuration;

/**
 * The main singleton class holding all application items.
 * @author Josh
 */
public class Aurora {
    
    public static final Logger LOGGER = Logger.getLogger("AuroraEmulator");
    
    private static Configuration _database_configuration;
    
    public static void main(String[] args) {
        LOGGER.info("Initializing Aurora emulator...");
        
        _database_configuration = new Configuration("db_config.properties");
        
        LOGGER.info(_database_configuration.getString("db.server"));
    }
    
    public static Configuration getDatabaseConfiguration() {
        return _database_configuration;
    }
}
