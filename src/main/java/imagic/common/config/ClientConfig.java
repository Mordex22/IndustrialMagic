package imagic.common.config;

import io.netty.buffer.ByteBuf;

public class ClientConfig extends BaseConfig {

    public final BooleanOption enablePlayerSounds = new BooleanOption(this, "client", "EnablePlayerSounds", true, "Play sounds for xxxxxxxx.");

    public final BooleanOption holidays = new BooleanOption(this, "client", "Holidays", true, "Christmas/New Years greetings in chat");

    @Override
    public void write(ByteBuf config) {
        throw new UnsupportedOperationException("Client config shouldn't be synced");
    }

    @Override
    public void read(ByteBuf config) {
        throw new UnsupportedOperationException("Client config shouldn't be synced");
    }
}
