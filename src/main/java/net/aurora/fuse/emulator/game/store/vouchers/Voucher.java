package net.aurora.fuse.emulator.game.store.vouchers;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.storage.DatabaseQuery;

/**
 *
 * @author Josh
 */
public class Voucher {
    
    private String code;
    private String type;
    private String value;
    
    public Voucher(ResultSet set) {
        try {
            code = set.getString("code");
            type = set.getString("type");
            value = set.getString("value");
        } catch (SQLException ex) {
            
        }
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
    
    public void delete() {
        try (DatabaseQuery dbQuery = Aurora.getDatabase().create()) {
            dbQuery.setQuery("DELETE FROM `vouchers` WHERE `code` = ?");
            dbQuery.setString(1, code);
            dbQuery.execute();
        }
    }
    
}
