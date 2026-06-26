package it.unicam.cs.mpgc.rpg129092.view;

public enum Menu {
    MAIN_MENU("/view/fxml/MainMenu.fxml"),
    BATTLE("/view/fxml/BattleScreen.fxml"),
    ENEMY_SELECTION("/view/fxml/EnemySelection.fxml"),
    NAME_SELECTION("/view/fxml/NameSelection.fxml");

    private final String fxmlPath;

    Menu(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }
}
