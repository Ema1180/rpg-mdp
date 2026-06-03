package it.unicam.cs.mpgc.rpg129092.controller.saving;
import it.unicam.cs.mpgc.rpg129092.model.GameState;
import java.io.IOException;

public interface GameSaver {
    void save(GameState state, String filepath) throws IOException;
    GameState load(String filepath) throws IOException;
}
