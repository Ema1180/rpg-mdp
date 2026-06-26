package it.unicam.cs.mpgc.rpg129092.view;

import javafx.scene.Scene;
import javafx.scene.text.Font;

public class AssetManager {

    public static void applyStile(Scene scene) {
        String cssPath = AssetManager.class.getResource("/view/stile.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
    }

    public static void loadMainMenuFont() {
        Font.loadFont(AssetManager.class.getResourceAsStream("/view/fonts/AngelicWar.ttf"), 54);
    }

    public static void loadGenericFont() {
        Font.loadFont(AssetManager.class.getResourceAsStream("/view/fonts/Bleeding_Cowboys.ttf"), 44);
    }
}
