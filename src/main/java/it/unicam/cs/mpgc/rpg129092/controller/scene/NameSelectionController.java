package it.unicam.cs.mpgc.rpg129092.controller.scene;

import it.unicam.cs.mpgc.rpg129092.model.GameState;
import it.unicam.cs.mpgc.rpg129092.model.characters.Hero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class NameSelectionController {

    @FXML
    private TextField nameInput;

    // Riferimento locale allo SceneController iniettato dall'esterno
    private SceneController sceneController;

    public NameSelectionController() {
        // Costruttore vuoto mantenuto
    }

    /**
     * Questo metodo permette a SceneController di iniettare se stesso
     * subito dopo il caricamento della vista.
     */
    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    public void onConfirmClick(ActionEvent actionEvent) {
        String name = nameInput.getText().trim(); // .trim() rimuove eventuali spazi vuoti accidentali

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attenzione");
            alert.setHeaderText(null);
            alert.setContentText("Per favore, inserisci un nome valido per il tuo eroe!");
            alert.showAndWait();
            return; // Interrompiamo l'esecuzione così il gioco non prosegue con un nome vuoto
        }

        GameState newGamestate = new GameState(new Hero(name));

        // Utilizziamo l'istanza dello SceneController iniettata
        if (sceneController != null) {
            sceneController.navigateToEnemySelection(newGamestate);
        }
    }
}
