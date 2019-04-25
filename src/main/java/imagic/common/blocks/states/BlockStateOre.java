package imagic.common.blocks.states;

import imagic.common.blocks.BlockOre;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public class BlockStateOre extends BlockStateContainer {

    public static final PropertyEnum<EnumOreType> typeProperty = PropertyEnum.create("type", EnumOreType.class);

    public BlockStateOre(BlockOre block) {
        super(block, typeProperty);
    }

    public enum EnumOreType implements IStringSerializable {
        MINARITE;

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
