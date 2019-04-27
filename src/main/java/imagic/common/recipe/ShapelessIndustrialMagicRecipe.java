package imagic.common.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;

public class ShapelessIndustrialMagicRecipe extends ShapelessOreRecipe {

    public ShapelessIndustrialMagicRecipe(ResourceLocation group, Block result, Object... recipe) {
        this(group, new ItemStack(result), recipe);
    }

    public ShapelessIndustrialMagicRecipe(ResourceLocation group, Item result, Object... recipe) {
        this(group, new ItemStack(result), recipe);
    }

    public ShapelessIndustrialMagicRecipe(ResourceLocation group, NonNullList<Ingredient> input, @Nonnull ItemStack result) {
        super(group, input, result);
    }

    public ShapelessIndustrialMagicRecipe(ResourceLocation group, @Nonnull ItemStack result, Object... recipe) {
        super(group, result, recipe);
    }

    public static ShapelessIndustrialMagicRecipe factory(JsonContext context, JsonObject json) {
        String group = JsonUtils.getString(json, "group", "");

        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients")) {
            ingredients.add(CraftingHelper.getIngredient(ele, context));
        }

        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for shapeless recipe");
        }

        ItemStack itemStack = ShapedRecipes.deserializeItem(JsonUtils.getJsonObject(json, "result"), true);
        return new ShapelessIndustrialMagicRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, itemStack);
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        return output;
    }

    public static class RecipeFactory implements IRecipeFactory {

        @Override
        public IRecipe parse(JsonContext context, JsonObject json) {
            return ShapelessIndustrialMagicRecipe.factory(context, json);
        }
    }
}
