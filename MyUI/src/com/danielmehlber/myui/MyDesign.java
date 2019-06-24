package com.danielmehlber.myui;

import java.awt.*;
import java.util.ArrayList;

public class MyDesign {

    public static final float ANIM_SLOW = 0.5f;
    public static final float ANIM_NORMAL = 1.0f;
    public static final float ANIM_FAST = 1.5f;
    public static final float ANIM_VERY_FAST = 2.0f;
    public final static MyDesign DEFAULT = new MyDesign(new MyColor(240, 240, 240), MyColor.LIGHT_GRAY, MyColor.BLACK);
    public final static MyDesign FOX = new MyDesign(MyColor.DARK_GRAY, MyColor.ORANGE, MyColor.WHITE);
    public final static MyDesign SEA = new MyDesign(MyColor.MIDNIGHT_BLUE, MyColor.GREEN_SEA, MyColor.WHITE);
    public final static MyDesign PINK_NEON = new MyDesign(new MyColor(52, 78, 92), MyColor.PINK_NEON, MyColor.WHITE);
    public final static MyDesign DEEP_SEA = new MyDesign(MyColor.BLUE_DEEP_SEA, MyColor.YELLOW_C, MyColor.WHITE);
    public final static MyDesign BEACH = new MyDesign(MyColor.BLUE_GREYISH, MyColor.ORANGE_PINKY, MyColor.WHITE);
    /**
     * Background color
     */
    public MyColor baseColor;
    /**
     * Foreground Color
     */
    public MyColor accentColor;
    /**
     * Text Color
     */
    public MyColor textColor;
    /**
     * Background color of dialog
     */
    public MyColor dialogBaseColor = MyColor.WHITE;
    /**
     * Roundness of dialog box
     */
    public int dialogRoundness = 10;
    public Font font;
    public float animation_speed = 1.0f;
    public FRAME_DESIGN frameTopDesign;
    public BUTTON_DESIGN buttonDesign;
    public TOGGLE_BUTTON_DESIGN toggleButtonDesign;
    ArrayList<Designable> users = new ArrayList<>();

    public MyDesign() {
        this(MyColor.WHITE, MyColor.WHITE, MyColor.BLACK);
    }

    public MyDesign(MyColor base, MyColor accent, MyColor text) {
        baseColor = base;
        accentColor = accent;
        textColor = text;
        frameTopDesign = FRAME_DESIGN.FLAT;
        font = new Font("Tahoma", Font.BOLD, 12);
        buttonDesign = BUTTON_DESIGN.FLAT;
        toggleButtonDesign = TOGGLE_BUTTON_DESIGN.FIELD;
    }

    public MyColor getBaseColor() {
        return baseColor;
    }

    public void setBaseColor(MyColor base_color) {
        this.baseColor = base_color;
    }

    public MyColor getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(MyColor accent_color) {
        this.accentColor = accent_color;
    }

    public MyColor getTextColor() {
        return textColor;
    }

    public void setTextColor(MyColor text_color) {
        this.textColor = text_color;
    }

    public FRAME_DESIGN getFrameTopDesign() {
        return frameTopDesign;
    }

    public void setFrameTopDesign(FRAME_DESIGN frame_design) {
        this.frameTopDesign = frame_design;
    }

    public void register(Designable obj) {
        users.add(obj);
    }

    public void unregister(Designable obj) {
        users.remove(obj);
    }

    public void apply() {
        System.out.println("<MyUI> Updated design");
        for (Designable user : users) {
            user.applyDesign();
        }
    }

    public void setAnimationSpeed(float speed) {
        this.animation_speed = speed;
    }

    public enum FRAME_DESIGN {
        BAR, FLAT
    }

    public enum BUTTON_DESIGN {
        FLAT
    }

    public enum TOGGLE_BUTTON_DESIGN {
        FIELD, DOT, CHECKMARK
    }


}
