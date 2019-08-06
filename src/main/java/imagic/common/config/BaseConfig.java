package imagic.common.config;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseConfig {

    protected static BaseConfig NULL_OWNER = new BaseConfig() {
        @Override
        public void registerOption(Option option) {
        }
    };

    private List<Option> options = new ArrayList<>();

    public void registerOption(Option option) {
        options.add(option);
    }

    public void load(Configuration config) {
        options.forEach(o -> o.load(config));
    }

    public void write(ByteBuf config) {
        options.forEach(o -> o.write(config));
    }

    public void read(ByteBuf config) {
        options.forEach(o -> o.read(config));
    }
}
