/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.aurora.fuse.emulator.packets.events.handshake;

import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.IPacketEvent;
import net.aurora.fuse.emulator.packets.types.ClientMessage;
import net.aurora.fuse.emulator.packets.types.ServerMessage;

/**
 *
 * @author Josh
 */
public class InfoRetrieveEvent implements IPacketEvent {

    @Override
    public void run(GameClient client, ClientMessage request) {
        ServerMessage response = new ServerMessage(5);
        response.writeString("name=" + client.getUser().getUsername(), 13);
        response.writeString("figure=" + client.getUser().getFigure(), 13);
        response.writeString("sex=" + client.getUser().getGender(), 13);
        response.writeString("customData=" + client.getUser().getMotto(), 13);
        response.writeString("ph_tickets=" + client.getUser().getTickets(), 13);
        response.writeString("photo_film=" + client.getUser().getFilm(), 13);
        response.writeString("ph_figure=", 13); // TODO: Add pool figure but lazy
        response.writeString("directMail=0", 13); // TODO: Dunno what this is
        client.sendMessage(response);
    }
    
}
