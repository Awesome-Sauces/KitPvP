package me.alpha.kitpvp.DataSave;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class PlayerDataObjectInputStream extends ObjectInputStream {
    protected PlayerDataObjectInputStream() throws IOException, SecurityException {
        super.enableResolveObject(true);
    }

    public PlayerDataObjectInputStream(InputStream in) throws IOException {
        super(in);
        super.enableResolveObject(true);
    }

    protected Object resolveObject(Object obj) throws IOException {
        if (obj instanceof Wrapper) {
            try {
                (obj = ConfigurationSerialization.deserializeObject(((Wrapper)obj).map)).getClass();
            } catch (Throwable var3) {
                throw newIOException("Failed to deserialize object", var3);
            }
        }

        return super.resolveObject(obj);
    }

    private static IOException newIOException(String string, Throwable cause) {
        IOException exception = new IOException(string);
        exception.initCause(cause);
        return exception;
    }
}
