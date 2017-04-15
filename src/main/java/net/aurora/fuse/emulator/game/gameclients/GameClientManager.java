package net.aurora.fuse.emulator.game.gameclients;

import gnu.trove.map.hash.THashMap;
import io.netty.channel.Channel;
import net.aurora.fuse.emulator.packets.types.ServerMessage;

/**
 * The GameClientManager class for managing existing GameClients and creating new ones.
 * @author Josh
 */
public class GameClientManager {
    /**
     * The list with all the connected GameClients.
     */
    private final THashMap<String, GameClient> gameClients;
    
    public GameClientManager() {
        gameClients = new THashMap<>();
    }
    
    /**
     * Adds a new GameClient to the connected GameClients list.
     * @param channel The channel of the connecting client.
     */
    public void addGameClient(Channel channel) {
        if (gameClients.containsKey(channel.id().asLongText())) {
            removeGameClient(channel);
        }
        
        GameClient gameClient = new GameClient(channel);
        gameClients.put(channel.id().asLongText(), gameClient);
        gameClient.sendMessage(new ServerMessage(0));
    }
    
    /**
     * Disconnects a connected GameClient and removes it from the list.
     * @param channel The channel of the disconnecting client.
     */
    public void removeGameClient(Channel channel) {
        gameClients.remove(channel.id().asLongText());
    }
    
    /**
     * Gets a connected GameClient by the specified channel.
     * @param channel The channel of the client to get.
     * @return The GameClient object of the client, or null if the client doesn't exist.
     */
    public GameClient getGameClient(Channel channel) {
        return gameClients.get(channel.id().asLongText());
    }
}
