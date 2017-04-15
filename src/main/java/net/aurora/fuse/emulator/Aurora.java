package net.aurora.fuse.emulator;

import java.util.logging.Logger;
import net.aurora.fuse.emulator.core.Configuration;
import net.aurora.fuse.emulator.game.Game;
import net.aurora.fuse.emulator.networking.game.NetworkListener;
import net.aurora.fuse.emulator.packets.PacketHandler;
import net.aurora.fuse.emulator.storage.DatabaseManager;

/**
 * The main singleton class holding all application items.
 *
 * @author Josh
 */
public class Aurora {
    
    public static final Logger LOGGER = Logger.getLogger("AuroraEmulator");
    
    private static Configuration databaseConfiguration;
    private static Configuration networkConfiguration;
    
    private static Game game;
    private static PacketHandler packetHandler;
    private static NetworkListener networkListener;
    private static DatabaseManager databaseManager;
    
    public static void main(String[] args) {
        LOGGER.info("Initializing Aurora emulator...");
        
        databaseConfiguration = new Configuration("db_config.properties");
        networkConfiguration = new Configuration("network_config.properties");
        
        databaseManager = new DatabaseManager(databaseConfiguration.getString("db.server"),
                databaseConfiguration.getString("db.user"),
                databaseConfiguration.getString("db.pass"),
                databaseConfiguration.getString("db.database"));
        
        game = new Game();
        
        packetHandler = new PacketHandler();
        
        networkListener = new NetworkListener();
        networkListener.start(networkConfiguration.getInteger("game.tcp.port"));
    }
    
    public static Configuration getDatabaseConfiguration() {
        return databaseConfiguration;
    }
    
    public static Game getGame() {
        return game;
    }
    
    public static PacketHandler getPacketHandler() {
        return packetHandler;
    }
    
    public static DatabaseManager getDatabase() {
        return databaseManager;
    }
}
