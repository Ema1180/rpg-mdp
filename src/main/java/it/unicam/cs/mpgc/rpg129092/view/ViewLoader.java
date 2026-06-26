package it.unicam.cs.mpgc.rpg129092.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class ViewLoader {
    public record ViewResult<T>(Parent root, T fxController) {}

    public static <T> ViewResult<T> load(Menu menu) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewLoader.class.getResource(menu.getFxmlPath()));
            Parent root = loader.load();
            T fxController = loader.getController();
            return new ViewResult<>(root, fxController);
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare la vista: " + menu.getFxmlPath(), e);
        }
    }
}
