package imagic.common.recipe;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Set;

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
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only");
            }
            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol");
            }

            ingredientMap.put(entry.getKey().toCharArray()[0], CraftingHelper.getIngredient(entry.getValue(), context));
        }

        ingredientMap.put(' ', Ingredient.EMPTY);

        JsonArray patternJ = JsonUtils.getJsonArray(json, "pattern");

        if (patternJ.size() == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        }

        String[] pattern = new String[patternJ.size()];
        for (int x=0; x<pattern.length; x++) {
            String line = JsonUtils.getString(patternJ.get(x), "pattern[" + x + "]");
            if (x > 0 && pattern[0].length() != line.length()) {
                throw new JsonSyntaxException("Invalid pattern: each row much be the same width");
            }
            pattern[x] = line;
        }

        CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
        primer.width = pattern[0].length();
        primer.height = pattern.length;
        primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
        primer.input = NonNullList.withSize(primer.width * primer.height, Ingredient.EMPTY);

        Set<Character> keys = Sets.newHashSet(ingredientMap.keySet());
        keys.remove(' ');

        int x = 0;
        for (String line : pattern) {
            for (char chr : line.toCharArray()) {
                Ingredient ing = ingredientMap.get(chr);
                if (ing == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + chr + "' but it's not defined in the key");
                }
                primer.input.set(x++, ing);
                keys.remove(chr);
            }
        }

        if (!keys.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + keys);
        }

        ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
        return new ShapedIndustrialMagicRecipe(group.isEmpty() ? null : new ResourceLocation(group), result, primer);
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        return output;
    }

    public static class RecipeFactory implements IRecipeFactory {

        @Override
        public IRecipe parse(JsonContext context, JsonObject json) {
            return ShapedIndustrialMagicRecipe.factory(context, json);
        }
    }
}
