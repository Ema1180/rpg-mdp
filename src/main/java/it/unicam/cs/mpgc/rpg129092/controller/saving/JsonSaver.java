package it.unicam.cs.mpgc.rpg129092.controller.saving;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg129092.model.GameState;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonSaver implements GameSaver {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void save(GameState state, String filepath) throws IOException {
        try (FileWriter writer = new FileWriter(filepath)) {
            gson.toJson(state, writer);
        }
    }

    @Override
    public GameState load(String filepath) throws IOException {
        try (FileReader reader = new FileReader(filepath)) {
            return gson.fromJson(reader, GameState.class);
        }
    }
}