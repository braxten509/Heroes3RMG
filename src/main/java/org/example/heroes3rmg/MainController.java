package org.example.heroes3rmg;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * MainController class handles the user interface and logic for generating a template for the Heroes 3 Random Map Generator (RMG).
 * It initializes the UI components, validates user input, and writes the generated template to a file.
 * TODO: Add a load function to load a template from a file.
 */
public class MainController implements Initializable {
    @FXML
    private Label errorMessage;

    @FXML
    private TextField templateName;
    @FXML
    private TextField id1;
    @FXML
    private TextField zoneSize;
    @FXML
    private TextField humanPositionsMin;
    @FXML
    private TextField humanPositionsMax;
    @FXML
    private TextField totalPositionsMin;
    @FXML
    private TextField totalPositionsMax;
    @FXML
    private TextField zoneOwner;
    @FXML
    private TextField townsInZoneMin;
    @FXML
    private TextField castlesInZoneMin;
    @FXML
    private TextField townDensity;
    @FXML
    private TextField castleDensity;
    @FXML
    private TextField lumberMillQuantity;
    @FXML
    private TextField mercuryMineQuantity;
    @FXML
    private TextField oreMineQuantity;
    @FXML
    private TextField sulfurMineQuantity;
    @FXML
    private TextField crystalMineQuantity;
    @FXML
    private TextField gemPondQuantity;
    @FXML
    private TextField goldMineQuantity;

    @FXML
    private CheckBox townsSameType;

    LinkedHashMap<String, ValueAndConditions> requiredFields = new LinkedHashMap<>();
    LinkedHashMap<String, ValueAndConditions> optionalFields = new LinkedHashMap<>();

    @FXML
    private ChoiceBox<String> mapSizeMin;
    @FXML
    private ChoiceBox<String> mapSizeMax;
    @FXML
    private ChoiceBox<String> zoneType;
    @FXML
    private ChoiceBox<String> monsterStrength;

    @FXML
    private MenuButton townTypesInZone;
    @FXML
    private MenuButton terrainTypesInZone;
    @FXML
    private MenuButton monsterTypesInZone;

    private void clearFile() {
        Path filePath = Paths.get("rmg.txt");
        try {
            Files.writeString(filePath, "", StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Cleared rmg.txt");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addWithTab(String writeValue) {
        Path filePath = Paths.get("rmg.txt");
        try {
            Files.writeString(filePath, writeValue + "\t", StandardOpenOption.APPEND);
            System.out.println("Wrote '" + writeValue + "' to rmg.txt");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //... makes an array
    private void addCheckMenuItems(MenuButton menuButton, boolean itemsChecked, String... items) {
        for (String item : items) {
            CheckBox checkBox = new CheckBox(item);
            if (itemsChecked) {
                checkBox.setSelected(itemsChecked);
            }
            CustomMenuItem customMenuItem = new CustomMenuItem(checkBox, false);
            customMenuItem.setHideOnClick(false);
            menuButton.getItems().add(customMenuItem);
        }
    }

    //... makes an array
    private void addChoiceMenuItems(ChoiceBox<String> choiceBox, String... items) {
        for (String item : items) {
            choiceBox.getItems().add(item);
        }
    }

    private boolean isPositiveInteger(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("\\d+") && Integer.parseInt(str) > 0;
    }

    private boolean isBetweenInclusive(String str, int min, int max) {
        if (str == null) {
            return false;
        }
        if (!str.matches("\\d+")) {
            return false;
        }
        int value = Integer.parseInt(str);
        return value >= min && value <= max;
    }

    private String getErrorMessage(Map.Entry<String, ValueAndConditions> entry) {
        String errorText = switch (entry.getKey()) {
            case "zoneSize", "humanPositionsMin" -> "must be an integer greater than or equal to 1";
            case "humanPositionsMax", "totalPositionsMin", "totalPositionsMax" ->
                    "must be an integer greater than or equal to 2";
            case "townsInZoneMin", "castlesInZoneMin", "zoneOwner" -> "must be blank or an integer between 1 and 8";
            case "townDensity", "castleDensity", "lumberMillQuantity", "mercuryMineQuantity", "oreMineQuantity",
                 "sulfurMineQuantity", "crystalMineQuantity", "gemPondQuantity", "goldMineQuantity" ->
                    "must be blank or an integer between 1 and 20";
            default -> "Invalid value for field: " + entry.getKey();
        };
        return "Invalid value '" + entry.getValue().value() + "' for " + entry.getKey() + ": " + errorText;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCheckMenuItems(townTypesInZone, true, "Castle", "Rampart", "Tower",
                "Inferno", "Necropolis", "Dungeon", "Stronghold", "Fortress", "Conflux");

        addChoiceMenuItems(mapSizeMin, "Small", "Medium", "Large", "Extra Large");
        addChoiceMenuItems(mapSizeMax, "Small", "Medium", "Large", "Extra Large");
        addChoiceMenuItems(zoneType, "Human/Computer Spawn", "Computer Only Spawn",
                "Treasure Zone", "Junction Zone");
        addChoiceMenuItems(monsterStrength, "Weak", "Average", "Strong");
    }

    @FXML
    protected void generateTemplate() {
        errorMessage.setText("");

        requiredFields.put("templateName", new ValueAndConditions(templateName.getText()));
        requiredFields.put("mapSizeMin", new ValueAndConditions(mapSizeMin.getValue()));
        requiredFields.put("mapSizeMax", new ValueAndConditions(mapSizeMax.getValue()));
        requiredFields.put("zoneType", new ValueAndConditions(zoneType.getValue()));
        requiredFields.put("zoneSize", new ValueAndConditions(zoneSize.getText(), isPositiveInteger(zoneSize.getText())));
        requiredFields.put("humanPositionsMin", new ValueAndConditions(humanPositionsMin.getText(), isPositiveInteger(humanPositionsMin.getText()), isBetweenInclusive(humanPositionsMin.getText(), 1, 8)));
        requiredFields.put("humanPositionsMax", new ValueAndConditions(humanPositionsMax.getText(), isPositiveInteger(zoneSize.getText()), isBetweenInclusive(humanPositionsMax.getText(), 2, 8)));
        requiredFields.put("totalPositionsMin", new ValueAndConditions(totalPositionsMin.getText(), isPositiveInteger(zoneSize.getText()), isBetweenInclusive(totalPositionsMin.getText(), 2, 8)));
        requiredFields.put("totalPositionsMax", new ValueAndConditions(totalPositionsMax.getText(), isPositiveInteger(zoneSize.getText()), isBetweenInclusive(totalPositionsMax.getText(), 2, 8)));

        optionalFields.put("zoneOwner", new ValueAndConditions(zoneOwner.getText(), isBetweenInclusive(zoneOwner.getText(), 1, 8)));
        optionalFields.put("townsInZoneMin", new ValueAndConditions(townsInZoneMin.getText(), isPositiveInteger(townsInZoneMin.getText()), isBetweenInclusive(townsInZoneMin.getText(), 1, 8)));
        optionalFields.put("castlesInZoneMin", new ValueAndConditions(castlesInZoneMin.getText(), isPositiveInteger(castlesInZoneMin.getText()), isBetweenInclusive(castlesInZoneMin.getText(), 1, 8)));
        optionalFields.put("townDensity", new ValueAndConditions(townDensity.getText(), isPositiveInteger(townDensity.getText()), isBetweenInclusive(townDensity.getText(), 1, 20)));
        optionalFields.put("castleDensity", new ValueAndConditions(castleDensity.getText(), isPositiveInteger(castleDensity.getText()), isBetweenInclusive(castleDensity.getText(), 1, 20)));
        optionalFields.put("lumberMillQuantity", new ValueAndConditions(lumberMillQuantity.getText(), isPositiveInteger(lumberMillQuantity.getText()), isBetweenInclusive(lumberMillQuantity.getText(), 1, 20)));
        optionalFields.put("mercuryMineQuantity", new ValueAndConditions(mercuryMineQuantity.getText(), isPositiveInteger(mercuryMineQuantity.getText()), isBetweenInclusive(mercuryMineQuantity.getText(), 1, 20)));
        optionalFields.put("oreMineQuantity", new ValueAndConditions(oreMineQuantity.getText(), isPositiveInteger(oreMineQuantity.getText()), isBetweenInclusive(oreMineQuantity.getText(), 1, 20)));
        optionalFields.put("sulfurMineQuantity", new ValueAndConditions(sulfurMineQuantity.getText(), isPositiveInteger(sulfurMineQuantity.getText()), isBetweenInclusive(sulfurMineQuantity.getText(), 1, 20)));
        optionalFields.put("crystalMineQuantity", new ValueAndConditions(crystalMineQuantity.getText(), isPositiveInteger(crystalMineQuantity.getText()), isBetweenInclusive(crystalMineQuantity.getText(), 1, 20)));
        optionalFields.put("gemPondQuantity", new ValueAndConditions(gemPondQuantity.getText(), isPositiveInteger(gemPondQuantity.getText()), isBetweenInclusive(gemPondQuantity.getText(), 1, 20)));
        optionalFields.put("goldMineQuantity", new ValueAndConditions(goldMineQuantity.getText(), isPositiveInteger(goldMineQuantity.getText()), isBetweenInclusive(goldMineQuantity.getText(), 1, 20)));

        for (var requiredField : requiredFields.entrySet()) {
            var requiredFieldValue = requiredField.getValue().value();
            var conditions = requiredField.getValue().conditions();
            if (requiredFieldValue == null || requiredFieldValue.isEmpty()) {
                errorMessage.setText("Missing required field: " + requiredField.getKey());
                return;
            }
            for (boolean condition : conditions) {
                if (!condition) {
                    String correctionMessage = getErrorMessage(requiredField);

                    errorMessage.setText(correctionMessage);
                    return;
                }
            }
        }

        for (var optionalField : optionalFields.entrySet()) {
            var optionalFieldValue = optionalField.getValue().value();
            var conditions = optionalField.getValue().conditions();
            if (optionalFieldValue == null || optionalFieldValue.isEmpty()) {
                continue;
            }
            for (boolean condition : conditions) {
                if (!condition) {
                    String correctionMessage = getErrorMessage(optionalField);

                    errorMessage.setText(correctionMessage);
                    return;
                }
            }
        }

        if (townsInZoneMin.getText().isEmpty() && castlesInZoneMin.getText().isEmpty()) {
            errorMessage.setText("At least one of the fields 'Towns in Zone Min' or 'Castles in Zone Min' must be filled");
            return;
        }

        clearFile();

        addWithTab(templateName.getText());

        switch (mapSizeMin.getValue()) {
            case "Small" -> addWithTab("2");
            case "Medium" -> addWithTab("4");
            case "Large" -> addWithTab("9");
            case "Extra Large" -> addWithTab("16");
        }

        switch (mapSizeMax.getValue()) {
            case "Small" -> addWithTab("2");
            case "Medium" -> addWithTab("4");
            case "Large" -> addWithTab("9");
            case "Extra Large" -> addWithTab("16");
        }

        addWithTab(id1.getText());

        switch (zoneType.getValue()) {
            case "Human/Computer Spawn" -> addWithTab("x\t\t\t");
            case "Computer Only Spawn" -> addWithTab("\tx\t\t");
            case "Treasure Zone" -> addWithTab("\t\tx\t");
            case "Junction Zone" -> addWithTab("\t\t\tx");
        }

        addWithTab(zoneSize.getText());
        addWithTab(humanPositionsMin.getText());
        addWithTab(humanPositionsMax.getText());
        addWithTab(totalPositionsMin.getText());
        addWithTab(totalPositionsMax.getText());

        addWithTab(zoneOwner.getText());
        addWithTab(townsInZoneMin.getText());
        addWithTab(castlesInZoneMin.getText());

        addWithTab(townDensity.getText());
        addWithTab(castleDensity.getText());

        addWithTab(townsSameType.isSelected() ? "x" : "");

        for (var item : townTypesInZone.getItems()) {
            CheckBox checkBox = (CheckBox) ((CustomMenuItem) item).getContent();
            if (checkBox.isSelected()) {
                addWithTab("x");
            } else {
                addWithTab("");
            }
        }

        addWithTab(lumberMillQuantity.getText());
        addWithTab(mercuryMineQuantity.getText());
        addWithTab(oreMineQuantity.getText());
        addWithTab(sulfurMineQuantity.getText());
        addWithTab(crystalMineQuantity.getText());
        addWithTab(gemPondQuantity.getText());
        addWithTab(goldMineQuantity.getText());
    }
}