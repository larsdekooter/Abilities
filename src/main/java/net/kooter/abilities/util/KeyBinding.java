package net.kooter.abilities.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY = "key.category.abilities.abilities";

    public static final String KEY_ENABLE_VEINMINER = "key.abilities.enable_veinminer";
    public static final String KEY_ENABLE_TWERKER = "key.abilities.enable_twerker";

    public static final KeyMapping VEINMINING_KEY = new KeyMapping(KEY_ENABLE_VEINMINER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY);
    public static final KeyMapping TWERKER_KEY = new KeyMapping(KEY_ENABLE_TWERKER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY);
}
