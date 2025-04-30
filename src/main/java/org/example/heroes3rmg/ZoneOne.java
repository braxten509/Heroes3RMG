package org.example.heroes3rmg;

import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import org.example.heroes3rmg.records.ValueAndConditions;

import static org.example.heroes3rmg.Utilities.addWithTab;
import static org.example.heroes3rmg.Utilities.getErrorMessage;
import static org.example.heroes3rmg.Utilities.*;

public class ZoneOne {
    public static void generateLineOne(MainController mainController) {
        try {
            mainController.errorMessage.setText("");

            mainController.requiredFields.put("templateName", new ValueAndConditions(mainController.templateName.getText()));
            mainController.requiredFields.put("mapSizeMin", new ValueAndConditions(mainController.mapSizeMin.getValue()));
            mainController.requiredFields.put("mapSizeMax", new ValueAndConditions(mainController.mapSizeMax.getValue()));

            if (mainController.mapSizeMin.getValue() != null && mainController.mapSizeMax.getValue() != null) {
                System.out.println("Map Size Min: " + mainController.mapSizeMin.getValue());
                int mapSizeMinInt = switch (mainController.mapSizeMin.getValue()) {
                    case "Small" -> 2;
                    case "Medium" -> 4;
                    case "Large" -> 9;
                    case "Extra Large" -> 16;
                    default -> 0;
                };
                int mapSizeMaxInt = switch (mainController.mapSizeMax.getValue()) {
                    case "Small" -> 2;
                    case "Medium" -> 4;
                    case "Large" -> 9;
                    case "Extra Large" -> 16;
                    default -> 0;
                };
                if (mapSizeMinInt > mapSizeMaxInt) {
                    mainController.errorMessage.setText("Map Size Min must be less than or equal to Map Size Max");
                    return;
                }
            }

            mainController.requiredFields.put("zoneType", new ValueAndConditions(mainController.zoneType.getValue()));
            mainController.requiredFields.put("zoneSize", new ValueAndConditions(mainController.zoneSize.getText(), isPositiveInteger(mainController.zoneSize.getText())));
            mainController.requiredFields.put("humanPositionsMin", new ValueAndConditions(mainController.humanPositionsMin.getText(), isPositiveInteger(mainController.humanPositionsMin.getText()), isBetweenInclusive(mainController.humanPositionsMin.getText(), 1, 8)));
            mainController.requiredFields.put("humanPositionsMax", new ValueAndConditions(mainController.humanPositionsMax.getText(), isPositiveInteger(mainController.zoneSize.getText()), isBetweenInclusive(mainController.humanPositionsMax.getText(), 2, 8)));
            mainController.requiredFields.put("totalPositionsMin", new ValueAndConditions(mainController.totalPositionsMin.getText(), isPositiveInteger(mainController.zoneSize.getText()), isBetweenInclusive(mainController.totalPositionsMin.getText(), 2, 8)));
            mainController.requiredFields.put("totalPositionsMax", new ValueAndConditions(mainController.totalPositionsMax.getText(), isPositiveInteger(mainController.zoneSize.getText()), isBetweenInclusive(mainController.totalPositionsMax.getText(), 2, 8)));

            mainController.optionalFields.put("zoneOwner", new ValueAndConditions(mainController.zoneOwner.getText(), isBetweenInclusive(mainController.zoneOwner.getText(), 1, 8)));
            mainController.optionalFields.put("townsInZoneMin", new ValueAndConditions(mainController.townsInZoneMin.getText(), isPositiveInteger(mainController.townsInZoneMin.getText()), isBetweenInclusive(mainController.townsInZoneMin.getText(), 1, 8)));
            mainController.optionalFields.put("castlesInZoneMin", new ValueAndConditions(mainController.castlesInZoneMin.getText(), isPositiveInteger(mainController.castlesInZoneMin.getText()), isBetweenInclusive(mainController.castlesInZoneMin.getText(), 1, 8)));
            mainController.optionalFields.put("townDensity", new ValueAndConditions(mainController.townDensity.getText(), isPositiveInteger(mainController.townDensity.getText()), isBetweenInclusive(mainController.townDensity.getText(), 1, 20)));
            mainController.optionalFields.put("castleDensity", new ValueAndConditions(mainController.castleDensity.getText(), isPositiveInteger(mainController.castleDensity.getText()), isBetweenInclusive(mainController.castleDensity.getText(), 1, 20)));
            mainController.optionalFields.put("lumberMillQuantity", new ValueAndConditions(mainController.lumberMillQuantity.getText(), isPositiveInteger(mainController.lumberMillQuantity.getText()), isBetweenInclusive(mainController.lumberMillQuantity.getText(), 1, 20)));
            mainController.optionalFields.put("mercuryMineQuantity", new ValueAndConditions(mainController.mercuryMineQuantity.getText(), isPositiveInteger(mainController.mercuryMineQuantity.getText()), isBetweenInclusive(mainController.mercuryMineQuantity.getText(), 1, 20)));
            mainController.optionalFields.put("oreMineQuantity", new ValueAndConditions(mainController.oreMineQuantity.getText(), isPositiveInteger(mainController.oreMineQuantity.getText()), isBetweenInclusive(mainController.oreMineQuantity.getText(), 1, 20)));
            mainController.optionalFields.put("sulfurMineQuantity", new ValueAndConditions(mainController.sulfurMineQuantity.getText(), isPositiveInteger(mainController.sulfurMineQuantity.getText()), isBetweenInclusive(mainController.sulfurMineQuantity.getText(), 1, 20)));
            mainController.optionalFields.put("crystalMineQuantity", new ValueAndConditions(mainController.crystalMineQuantity.getText(), isPositiveInteger(mainController.crystalMineQuantity.getText()), isBetweenInclusive(mainController.crystalMineQuantity.getText(), 1, 20)));
            mainController.optionalFields.put("gemPondQuantity", new ValueAndConditions(mainController.gemPondQuantity.getText(), isPositiveInteger(mainController.gemPondQuantity.getText()), isBetweenInclusive(mainController.gemPondQuantity.getText(), 1, 20)));
            mainController.optionalFields.put("goldMineQuantity", new ValueAndConditions(mainController.goldMineQuantity.getText(), isPositiveInteger(mainController.goldMineQuantity.getText()), isBetweenInclusive(mainController.goldMineQuantity.getText(), 1, 20)));
            mainController.optionalFields.put("woodDepositDensity", new ValueAndConditions(mainController.woodDepositDensity.getText(), isPositiveInteger(mainController.woodDepositDensity.getText()), isBetweenInclusive(mainController.woodDepositDensity.getText(), 1, 20)));
            mainController.optionalFields.put("mercuryDepositDensity", new ValueAndConditions(mainController.mercuryDepositDensity.getText(), isPositiveInteger(mainController.mercuryDepositDensity.getText()), isBetweenInclusive(mainController.mercuryDepositDensity.getText(), 1, 20)));
            mainController.optionalFields.put("oreDepositDensity", new ValueAndConditions(mainController.oreDepositDensity.getText(), isPositiveInteger(mainController.oreDepositDensity.getText()), isBetweenInclusive(mainController.oreDepositDensity.getText(), 1, 20)));
            mainController.optionalFields.put("sulfurDepositDensity", new ValueAndConditions(mainController.sulfurDepositDensity.getText(), isPositiveInteger(mainController.sulfurDepositDensity.getText()), isBetweenInclusive(mainController.sulfurDepositDensity.getText(), 1, 20)));
            mainController.optionalFields.put("crystalDepositDensity", new ValueAndConditions(mainController.crystalDepositDensity.getText(), isPositiveInteger(mainController.crystalDepositDensity.getText()), isBetweenInclusive(mainController.crystalDepositDensity.getText(), 1, 20)));
            mainController.optionalFields.put("gemDepositDensity", new ValueAndConditions(mainController.gemDepositDensity.getText(), isPositiveInteger(mainController.gemDepositDensity.getText()), isBetweenInclusive(mainController.gemDepositDensity.getText(), 1, 20)));
            mainController.optionalFields.put("goldDepositDensity", new ValueAndConditions(mainController.goldDepositDensity.getText(), isPositiveInteger(mainController.goldDepositDensity.getText()), isBetweenInclusive(mainController.goldDepositDensity.getText(), 1, 20)));

            mainController.optionalFields.put("highValueTreasureGoldValueMin", new ValueAndConditions(mainController.highValueTreasureGoldValueMin.getText(), isPositiveInteger(mainController.highValueTreasureGoldValueMin.getText()), isBetweenInclusive(mainController.highValueTreasureGoldValueMin.getText(), 1, 100000)));
            mainController.optionalFields.put("highValueTreasureGoldValueMax", new ValueAndConditions(mainController.highValueTreasureGoldValueMax.getText(), isPositiveInteger(mainController.highValueTreasureGoldValueMax.getText()), isBetweenInclusive(mainController.highValueTreasureGoldValueMax.getText(), 1, 100000)));
            mainController.optionalFields.put("highValueTreasureDensity", new ValueAndConditions(mainController.highValueTreasureDensity.getText(), isPositiveInteger(mainController.highValueTreasureDensity.getText()), isBetweenInclusive(mainController.highValueTreasureDensity.getText(), 1, 20)));
            mainController.optionalFields.put("midValueTreasureGoldValueMin", new ValueAndConditions(mainController.midValueTreasureGoldValueMin.getText(), isPositiveInteger(mainController.midValueTreasureGoldValueMin.getText()), isBetweenInclusive(mainController.midValueTreasureGoldValueMin.getText(), 1, 100000)));
            mainController.optionalFields.put("midValueTreasureGoldValueMax", new ValueAndConditions(mainController.midValueTreasureGoldValueMax.getText(), isPositiveInteger(mainController.midValueTreasureGoldValueMax.getText()), isBetweenInclusive(mainController.midValueTreasureGoldValueMax.getText(), 1, 100000)));
            mainController.optionalFields.put("midValueTreasureDensity", new ValueAndConditions(mainController.midValueTreasureDensity.getText(), isPositiveInteger(mainController.midValueTreasureDensity.getText()), isBetweenInclusive(mainController.midValueTreasureDensity.getText(), 1, 20)));
            mainController.optionalFields.put("lowValueTreasureGoldValueMin", new ValueAndConditions(mainController.lowValueTreasureGoldValueMin.getText(), isPositiveInteger(mainController.lowValueTreasureGoldValueMin.getText()), isBetweenInclusive(mainController.lowValueTreasureGoldValueMin.getText(), 1, 100000)));
            mainController.optionalFields.put("lowValueTreasureGoldValueMax", new ValueAndConditions(mainController.lowValueTreasureGoldValueMax.getText(), isPositiveInteger(mainController.lowValueTreasureGoldValueMax.getText()), isBetweenInclusive(mainController.lowValueTreasureGoldValueMax.getText(), 1, 100000)));
            mainController.optionalFields.put("lowValueTreasureDensity", new ValueAndConditions(mainController.lowValueTreasureDensity.getText(), isPositiveInteger(mainController.lowValueTreasureDensity.getText()), isBetweenInclusive(mainController.lowValueTreasureDensity.getText(), 1, 20)));

            for (var requiredField : mainController.requiredFields.entrySet()) {
                var requiredFieldValue = requiredField.getValue().value();
                var conditions = requiredField.getValue().conditions();
                if (requiredFieldValue == null || requiredFieldValue.isEmpty()) {
                    mainController.errorMessage.setText("Missing required field: " + requiredField.getKey());
                    return;
                }
                for (boolean condition : conditions) {
                    if (!condition) {
                        String correctionMessage = getErrorMessage(requiredField);

                        mainController.errorMessage.setText(correctionMessage);
                        return;
                    }
                }
            }

            for (var optionalField : mainController.optionalFields.entrySet()) {
                var optionalFieldValue = optionalField.getValue().value();
                var conditions = optionalField.getValue().conditions();
                if (optionalFieldValue == null || optionalFieldValue.isEmpty()) {
                    continue;
                }
                for (boolean condition : conditions) {
                    if (!condition) {
                        String correctionMessage = getErrorMessage(optionalField);

                        mainController.errorMessage.setText(correctionMessage);
                        return;
                    }
                }
            }

            if (mainController.townsInZoneMin.getText().isEmpty() && mainController.castlesInZoneMin.getText().isEmpty()) {
                mainController.errorMessage.setText("At least one of the fields 'Towns in Zone Min' or 'Castles in Zone Min' must be filled");
                return;
            }

            if (mainController.monsterStrength.getValue() == null || mainController.monsterStrength.getValue().isEmpty()) {
                mainController.errorMessage.setText("Monster Strength must be selected");
                return;
            }

            clearFile();

            addWithTab(mainController.templateName.getText());

            switch (mainController.mapSizeMin.getValue()) {
                case "Small" -> addWithTab("2");
                case "Medium" -> addWithTab("4");
                case "Large" -> addWithTab("9");
                case "Extra Large" -> addWithTab("16");
            }

            switch (mainController.mapSizeMax.getValue()) {
                case "Small" -> addWithTab("2");
                case "Medium" -> addWithTab("4");
                case "Large" -> addWithTab("9");
                case "Extra Large" -> addWithTab("16");
            }

            addWithTab(mainController.zoneID.getText());

            switch (mainController.zoneType.getValue()) {
                case "Human/Computer Spawn" -> addWithTab("x\t\t\t");
                case "Computer Only Spawn" -> addWithTab("\tx\t\t");
                case "Treasure Zone" -> addWithTab("\t\tx\t");
                case "Junction Zone" -> addWithTab("\t\t\tx");
            }

            addWithTab(mainController.zoneSize.getText());
            addWithTab(mainController.humanPositionsMin.getText());
            addWithTab(mainController.humanPositionsMax.getText());
            addWithTab(mainController.totalPositionsMin.getText());
            addWithTab(mainController.totalPositionsMax.getText());

            addWithTab(mainController.zoneOwner.getText());
            addWithTab(mainController.townsInZoneMin.getText());
            addWithTab(mainController.castlesInZoneMin.getText());

            addWithTab(mainController.townDensity.getText());
            addWithTab(mainController.castleDensity.getText());

            addWithTab(mainController.townsSameType.isSelected() ? "x" : "");

            for (var item : mainController.townTypesInZone.getItems()) {
                CheckBox checkBox = (CheckBox) ((CustomMenuItem) item).getContent();
                if (checkBox.isSelected()) {
                    addWithTab("x");
                } else {
                    addWithTab("");
                }
            }

            addWithTab(mainController.lumberMillQuantity.getText());
            addWithTab(mainController.mercuryMineQuantity.getText());
            addWithTab(mainController.oreMineQuantity.getText());
            addWithTab(mainController.sulfurMineQuantity.getText());
            addWithTab(mainController.crystalMineQuantity.getText());
            addWithTab(mainController.gemPondQuantity.getText());
            addWithTab(mainController.goldMineQuantity.getText());

            addWithTab(mainController.woodDepositDensity.getText());
            addWithTab(mainController.mercuryDepositDensity.getText());
            addWithTab(mainController.oreDepositDensity.getText());
            addWithTab(mainController.sulfurDepositDensity.getText());
            addWithTab(mainController.crystalDepositDensity.getText());
            addWithTab(mainController.gemDepositDensity.getText());
            addWithTab(mainController.goldDepositDensity.getText());

            addWithTab(mainController.matchResourcesToTown.isSelected() ? "x" : "");

            for (var item : mainController.terrainTypesInZone.getItems()) {
                CheckBox checkBox = (CheckBox) ((CustomMenuItem) item).getContent();
                if (checkBox.isSelected()) {
                    addWithTab("x");
                } else {
                    addWithTab("");
                }
            }

            addWithTab(mainController.matchTerrainToTown.isSelected() ? "x" : "");

            addWithTab(mainController.monsterStrength.getValue());

            addWithTab(mainController.matchMonstersToTown.isSelected() ? "x" : "");

            for (var item : mainController.monsterTypesInZone.getItems()) {
                CheckBox checkBox = (CheckBox) ((CustomMenuItem) item).getContent();
                if (checkBox.isSelected()) {
                    addWithTab("x");
                } else {
                    addWithTab("");
                }
            }

            addWithTab(mainController.highValueTreasureGoldValueMin.getText());
            addWithTab(mainController.highValueTreasureGoldValueMax.getText());
            addWithTab(mainController.highValueTreasureDensity.getText());
            addWithTab(mainController.midValueTreasureGoldValueMin.getText());
            addWithTab(mainController.midValueTreasureGoldValueMax.getText());
            addWithTab(mainController.midValueTreasureDensity.getText());
            addWithTab(mainController.lowValueTreasureGoldValueMin.getText());
            addWithTab(mainController.lowValueTreasureGoldValueMax.getText());
            addWithTab(mainController.lowValueTreasureDensity.getText());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
