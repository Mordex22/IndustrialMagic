package imagic.common.recipe;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.Map;

public class ShapedIndustrialMagicRecipe extends ShapedOreRecipe {

    public ShapedIndustrialMagicRecipe(ResourceLocation group, Block result, Object... recipe) {
        this(group, new ItemStack(result), recipe);
    }

    public ShapedIndustrialMagicRecipe(ResourceLocation group, Item result, Object... recipe) {
        this(group, new ItemStack(result), recipe);
    }

    public ShapedIndustrialMagicRecipe(ResourceLocation group, @Nonnull ItemStack result, Object... recipe) {
        this(group, result, CraftingHelper.parseShaped(recipe));
    }

    public ShapedIndustrialMagicRecipe(ResourceLocation group, @Nonnull ItemStack result, CraftingHelper.ShapedPrimer primer) {
        super(group, result, primer);
    }

    public static ShapedOreRecipe factory(JsonContext context, JsonObject json) {
        String group = JsonUtils.getString(json, "group", "");

        Map<Character, Ingredient> ingredientMap = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : JsonUtils.getJsonObject(json, "key").entrySet()) {
            // TODO this is where I left off
        }
        return null;
    }
}
