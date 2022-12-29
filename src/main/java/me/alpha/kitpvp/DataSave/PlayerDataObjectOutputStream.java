package me.alpha.kitpvp.DataSave;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class PlayerDataObjectOutputStream extends ObjectOutputStream {
    protected PlayerDataObjectOutputStream() throws IOException, SecurityException {
        super.enableReplaceObject(true);
    }

    public PlayerDataObjectOutputStream(OutputStream out) throws IOException {
        super(out);
        super.enableReplaceObject(true);
    }

    protected Object replaceObject(Object obj) throws IOException {
        if (!(obj instanceof Serializable) && obj instanceof ConfigurationSerializable) {
            obj = Wrapper.newWrapper((ConfigurationSerializable)obj);
        }

        return super.replaceObject(obj);
    }
}