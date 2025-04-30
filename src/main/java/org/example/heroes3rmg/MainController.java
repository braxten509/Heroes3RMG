package org.example.heroes3rmg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.heroes3rmg.records.ValueAndConditions;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.example.heroes3rmg.Utilities.*;

/**
 * MainController class handles the user interface and logic for generating a template for the Heroes 3 Random Map Generator (RMG).
 * It initializes the UI components, validates user input, and writes the generated template to a file.
 * TODO: Add a load function to load a template from a file.
 */
public class MainController implements Initializable {
    @FXML
    protected Label errorMessage;

    @FXML
    protected TextField templateName;
    @FXML
    protected TextField zoneID;
    @FXML
    protected TextField zoneSize;
    @FXML
    protected TextField humanPositionsMin;
    @FXML
    protected TextField humanPositionsMax;
    @FXML
    protected TextField totalPositionsMin;
    @FXML
    protected TextField totalPositionsMax;
    @FXML
    protected TextField zoneOwner;
    @FXML
    protected TextField townsInZoneMin;
    @FXML
    protected TextField castlesInZoneMin;
    @FXML
    protected TextField townDensity;
    @FXML
    protected TextField castleDensity;
    @FXML
    protected TextField lumberMillQuantity;
    @FXML
    protected TextField mercuryMineQuantity;
    @FXML
    protected TextField oreMineQuantity;
    @FXML
    protected TextField sulfurMineQuantity;
    @FXML
    protected TextField crystalMineQuantity;
    @FXML
    protected TextField gemPondQuantity;
    @FXML
    protected TextField goldMineQuantity;
    @FXML
    protected TextField woodDepositDensity;
    @FXML
    protected TextField mercuryDepositDensity;
    @FXML
    protected TextField oreDepositDensity;
    @FXML
    protected TextField sulfurDepositDensity;
    @FXML
    protected TextField crystalDepositDensity;
    @FXML
    protected TextField gemDepositDensity;
    @FXML
    protected TextField goldDepositDensity;
    @FXML
    protected TextField highValueTreasureGoldValueMin;
    @FXML
    protected TextField highValueTreasureGoldValueMax;
    @FXML
    protected TextField highValueTreasureDensity;
    @FXML
    protected TextField midValueTreasureGoldValueMin;
    @FXML
    protected TextField midValueTreasureGoldValueMax;
    @FXML
    protected TextField midValueTreasureDensity;
    @FXML
    protected TextField lowValueTreasureGoldValueMin;
    @FXML
    protected TextField lowValueTreasureGoldValueMax;
    @FXML
    protected TextField lowValueTreasureDensity;

    @FXML
    protected CheckBox townsSameType;
    @FXML
    protected CheckBox matchResourcesToTown;
    @FXML
    protected CheckBox matchTerrainToTown;
    @FXML
    protected CheckBox matchMonstersToTown;

    protected LinkedHashMap<String, ValueAndConditions> requiredFields = new LinkedHashMap<>();
    protected LinkedHashMap<String, ValueAndConditions> optionalFields = new LinkedHashMap<>();

    @FXML
    protected ChoiceBox<String> mapSizeMin;
    @FXML
    protected ChoiceBox<String> mapSizeMax;
    @FXML
    protected ChoiceBox<String> zoneType;
    @FXML
    protected ChoiceBox<String> monsterStrength;

    @FXML
    protected MenuButton townTypesInZone;
    @FXML
    protected MenuButton terrainTypesInZone;
    @FXML
    protected MenuButton monsterTypesInZone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        townTypesInZone.getItems().clear();
        terrainTypesInZone.getItems().clear();
        monsterTypesInZone.getItems().clear();

        addCheckMenuItems(townTypesInZone, true, "Castle", "Rampart", "Tower",
                "Inferno", "Necropolis", "Dungeon", "Stronghold", "Fortress", "Conflux");
        addCheckMenuItems(terrainTypesInZone, true, "Dirt", "Sand", "Grass", "Snow", "Swamp", "Rough", "Cave", "Lava");
        addCheckMenuItems(monsterTypesInZone, true, "Neutral", "Castle", "Rampart", "Tower", "Inferno", "Necropolis",
                "Dungeon", "Stronghold", "Fortress", "Conflux");

        addChoiceMenuItems(mapSizeMin, "Small", "Medium", "Large", "Extra Large");
        addChoiceMenuItems(mapSizeMax, "Small", "Medium", "Large", "Extra Large");
        addChoiceMenuItems(zoneType, "Human/Computer Spawn", "Computer Only Spawn",
                "Treasure Zone", "Junction Zone");
        addChoiceMenuItems(monsterStrength, "Weak", "Average", "Strong");
    }

    @FXML
    protected void generateTemplate() throws IOException {
        ZoneOne.generateLineOne(this);
    }
}