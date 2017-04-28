/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurora.fuse.emulator.packets.events.navigator;

import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.game.navigator.RoomCategory;
import net.aurora.fuse.emulator.packets.IPacketEvent;
import net.aurora.fuse.emulator.packets.types.ClientMessage;
import net.aurora.fuse.emulator.packets.types.ServerMessage;

/**
 *
 * @author Josh
 */
public class NavigateEvent implements IPacketEvent {

    @Override
    public void run(GameClient client, ClientMessage request) {
        boolean hideFull = request.readVL64() == 1;
        Integer categoryId = request.readVL64();
        
        RoomCategory category = Aurora.getGame().getNavigator().getRoomCategory(categoryId);
        
        if (category != null) {
            ServerMessage response = new ServerMessage(220);
            
            Aurora.getGame().getNavigator().serializeCategory(response, category, hideFull);
            
            for (RoomCategory subCategory : Aurora.getGame().getNavigator().getSubCategories(categoryId)) {
                Aurora.getGame().getNavigator().serializeSubCategory(response, subCategory);
            }
        
            client.sendMessage(response);
        }
    }
    
}
