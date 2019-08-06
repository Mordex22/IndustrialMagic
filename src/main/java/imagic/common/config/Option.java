package imagic.common.config;

import imagic.common.util.FieldsAreNonnullByDefault;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.config.Configuration;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@FieldsAreNonnullByDefault
public abstract class Option<THISTYPE extends Option>  {

    protected final String key;

    @Nullable
    protected final String comment;

    protected final String category;

    protected boolean requiresGameRestart = false;

    protected boolean requiresWorldRestart = false;

    public Option(BaseConfig owner, String category, String key, @Nullable String comment) {
        this.category = category;
        this.key = key;
        this.comment = comment;
        owner.registerOption(this);
    }

    protected abstract void load(Configuration config);

    protected abstract void write(ByteBuf buf);

    protected abstract void read(ByteBuf buf);

    public THISTYPE setRequiresWorldRestart(boolean requiresWorldRestart) {
        this.requiresWorldRestart = requiresWorldRestart;
        return (THISTYPE) this;
    }

    public THISTYPE setRequiresGameRestart(boolean requiresGameRestart) {
        this.requiresGameRestart = requiresGameRestart;
        return (THISTYPE) this;
    }
}
