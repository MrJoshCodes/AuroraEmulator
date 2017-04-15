package net.aurora.fuse.emulator.game.store.vouchers;

import gnu.trove.map.hash.THashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.types.ServerMessage;
import net.aurora.fuse.emulator.storage.DatabaseQuery;

/**
 *
 * @author Josh
 */
public class VoucherManager {
    
    private final THashMap<String, Voucher> vouchers;
    private final THashMap<String, IVoucher> voucherHandlers;
    
    public VoucherManager() {
        vouchers = new THashMap<>();
        voucherHandlers = new THashMap<>();
        
        voucherHandlers.put("coins", new CoinVoucher());
        voucherHandlers.put("tickets", new TicketVoucher());
        
        try (DatabaseQuery dbQuery = Aurora.getDatabase().create()) {
            dbQuery.setQuery("SELECT * FROM `vouchers`");
            ResultSet set = dbQuery.getResultSet();
            
            while (set.next()) {
                vouchers.put(set.getString("code"), new Voucher(set));
            }
        } catch (SQLException ex) {
            
        }
        
        Aurora.LOGGER.log(Level.INFO, "Loaded ''{0}'' vouchers.", vouchers.size());
    }
    
    public void TryVoucher(GameClient client, String code) {
        Voucher voucher = vouchers.get(code);
        
        if (voucher != null) {            
            IVoucher voucherHandler = voucherHandlers.get(voucher.getType());
            
            if (voucherHandler != null) {
                voucherHandler.redeem(client, voucher.getValue());
                voucher.delete();
                
                client.sendMessage(new ServerMessage(212));
            }
        } else {
            ServerMessage response = new ServerMessage(213);
            response.writeString("1", 0);
            client.sendMessage(response);
        }
    }
    
}
