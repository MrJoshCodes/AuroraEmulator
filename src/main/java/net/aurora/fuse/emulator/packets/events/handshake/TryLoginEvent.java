package net.aurora.fuse.emulator.packets.events.handshake;

import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.game.users.User;
import net.aurora.fuse.emulator.packets.IPacketEvent;
import net.aurora.fuse.emulator.packets.types.ClientMessage;
import net.aurora.fuse.emulator.packets.types.ServerMessage;

/**
 *
 * @author Josh
 */
public class TryLoginEvent implements IPacketEvent {

    @Override
    public void run(GameClient client, ClientMessage request) {
        String username = request.readString();
        String password = request.readString();
        ServerMessage response;
        
        User user = Aurora.getGame().getUsers().getUserByName(username);
        
        if (user != null) {
            if (user.getPassword().equals(password)) {
                response = new ServerMessage(3);
                client.sendMessage(response);
                
                client.sendFigureSets();
                
                client.setUser(user);
            } else {
                response = new ServerMessage(33);
                response.writeString("login incorrect: Wrong password", 0);
                client.sendMessage(response);
            }
        } else {
            response = new ServerMessage(33);
            response.writeString("login incorrect: Wrong username", 0);
            client.sendMessage(response);
        }
    }
    
}
