package imagic.common.blocks.states;

import com.google.common.base.Predicate;

import com.google.common.base.Predicates;
import imagic.IndustrialMagic;
import imagic.common.IndustrialMagicBlocks;
import imagic.common.blocks.BlockBasic;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public class BlockStateBasic extends ExtendedBlockState {

    public static final PropertyBool activeProperty = PropertyBool.create("active");

    public BlockStateBasic(BlockBasic block, PropertyEnum<BasicBlockType> typeProperty) {
        super(block, new IProperty[]{BlockStateFacing.facingProperty, typeProperty},
                new IUnlistedProperty[]{});
    }


    public enum BasicBlock {
        BASIC_BLOCK;

        private PropertyEnum<BasicBlockType> predicatedProperty;

        public PropertyEnum<BasicBlockType> getProperty() {
            if (predicatedProperty == null) {
                predicatedProperty = PropertyEnum.create("type", BasicBlockType.class, new BasicBlockPredicate(this));
            }

            return predicatedProperty;
        }

        public Block getBlock() {
            return IndustrialMagicBlocks.BasicBlock;
        }
    }

    public enum BasicBlockType implements IStringSerializable {
        MINARITE_BLOCK(BasicBlock.BASIC_BLOCK, 0, "MinariteBlock", Predicates.alwaysFalse(), false);

        public BasicBlock blockType;
        public int meta;
        public String name;
        public Predicate<EnumFacing> facingPredicate;
        public boolean activatable;

        BasicBlockType(BasicBlock block, int metadata, String s, Predicate<EnumFacing> facingAllowed, boolean activeState) {
            blockType = block;
            meta = metadata;
            name = s;
            facingPredicate = facingAllowed;
            activatable = activeState;
        }

        @Nullable
        public static BasicBlockType get(IBlockState state) {
            if (state.getBlock() instanceof BlockBasic) {
                return state.getValue(((BlockBasic) state.getBlock()).getTypeProperty());
            }

            return null;
        }

        @Nullable
        public static BasicBlockType get(ItemStack stack) {
            return get(Block.getBlockFromItem(stack.getItem()), stack.getItemDamage());
        }

        @Nullable
        public static BasicBlockType get(Block block, int meta) {
            if (block instanceof BlockBasic) {
                return get(((BlockBasic) block).getBasicBlock(), meta);
            }

            return null;
        }

        public static BasicBlockType get(BasicBlock blockType, int metadata) {
            int index = blockType.ordinal() << 4 | metadata;
            if (index < values().length) {
                BasicBlockType firstTry = values()[index];

                if (firstTry.blockType == blockType && firstTry.meta == metadata) {
                    return firstTry;
                }
            }

            for (BasicBlockType type : values()) {
                if (type.blockType == blockType && type.meta == metadata) {
                    return type;
                }
            }

            IndustrialMagic.logger.error("Invalid BasicBlock.  type: {}, meta: {}", blockType.ordinal(), metadata);

            return null;
        }

        @Override
        public  String getName() {
            return name().toLowerCase(Locale.ROOT);
        }

        public boolean canRotateTo(EnumFacing side) {
            return facingPredicate.apply(side);
        }

        public boolean hasRotations() {
            return !facingPredicate.equals(Predicates.alwaysFalse());
        }

        public boolean hasActiveTexture() {
            return activatable;
        }
    }

    public static class BasicBlockPredicate implements Predicate<BasicBlockType> {

        public BasicBlock basicBlock;

        public BasicBlockPredicate(BasicBlock type) {
            basicBlock = type;
        }

        @Override
        public boolean apply(BasicBlockType input) {
            return input.blockType == basicBlock;
        }
    }

    public static class BasicBlockStateMapper extends StateMapperBase {

        @Nonnull
        @Override
        protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
            BlockBasic block = (BlockBasic) state.getBlock();
            BasicBlockType type = state.getValue(block.getTypeProperty());
            StringBuilder builder = new StringBuilder();

            if (type.hasActiveTexture()) {
                builder.append(activeProperty.getName());
                builder.append("=");
                builder.append(state.getValue(activeProperty));
            }

            if (type.hasRotations()) {
                EnumFacing facing = state.getValue(BlockStateFacing.facingProperty);

                if (!type.canRotateTo(facing)) {
                    facing = EnumFacing.NORTH;
                }

                if (builder.length() > 0) {
                    builder.append(",");
                }

                builder.append(BlockStateFacing.facingProperty.getName());
                builder.append("=");
                builder.append(facing.getName());
            }

            if (builder.length() == 0) {
                builder.append("normal");
            }

            ResourceLocation baseLocation = new ResourceLocation("imagic", type.getName());
            return new ModelResourceLocation(baseLocation, builder.toString());
        }
    }
}
