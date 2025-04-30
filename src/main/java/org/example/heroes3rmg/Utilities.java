package org.example.heroes3rmg;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import org.example.heroes3rmg.records.ValueAndConditions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class Utilities {

    protected static void clearFile() {
        Path filePath = Paths.get("rmg.txt");
        try {
            Files.writeString(filePath, "", StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Cleared rmg.txt");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    protected static void addWithTab(String writeValue) {
        Path filePath = Paths.get("rmg.txt");
        try {
            Files.writeString(filePath, writeValue + "\t", StandardOpenOption.APPEND);
            System.out.println("Wrote '" + writeValue + "' to rmg.txt");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //... makes an array
    protected static void addCheckMenuItems(MenuButton menuButton, boolean itemsChecked, String... items) {
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
    protected static void addChoiceMenuItems(ChoiceBox<String> choiceBox, String... items) {
        for (String item : items) {
            choiceBox.getItems().add(item);
        }
    }

    protected static boolean isPositiveInteger(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("\\d+") && Integer.parseInt(str) > 0;
    }

    protected static boolean isMinimum(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("\\d+") && Integer.parseInt(str) >= 1;
    }

    protected static boolean isBetweenInclusive(String str, int min, int max) {
        if (str == null) {
            return false;
        }
        if (!str.matches("\\d+")) {
            return false;
        }
        int value = Integer.parseInt(str);
        return value >= min && value <= max;
    }

    protected static String getErrorMessage(Map.Entry<String, ValueAndConditions> entry) {
        String errorText = switch (entry.getKey()) {
            case "zoneSize", "humanPositionsMin" -> "must be an integer greater than or equal to 1";
            case "humanPositionsMax", "totalPositionsMin", "totalPositionsMax" ->
                    "must be an integer greater than or equal to 2";
            case "townsInZoneMin", "castlesInZoneMin", "zoneOwner" -> "must be blank or an integer between 1 and 8";
            case "townDensity", "castleDensity", "lumberMillQuantity", "mercuryMineQuantity", "oreMineQuantity",
                 "sulfurMineQuantity", "crystalMineQuantity", "gemPondQuantity", "goldMineQuantity",
                 "woodDepositDensity", "mercuryDepositDensity", "oreDepositDensity", "sulfurDepositDensity",
                 "crystalDepositDensity", "gemDepositDensity", "goldDepositDensity", "highValueTreasureDensity",
                 "midValueTreasureDensity", "lowValueTreasureDensity" -> "must be blank or an integer between 1 and 20";
            case "highValueTreasureGoldValueMin", "highValueTreasureGoldValueMax",
                 "midValueTreasureGoldValueMin", "midValueTreasureGoldValueMax",
                 "lowValueTreasureGoldValueMin", "lowValueTreasureGoldValueMax" ->
                    "must be blank or an integer between 1 and 100000";
            default -> "Invalid value for field: " + entry.getKey();
        };
        return "Invalid value '" + entry.getValue().value() + "' for " + entry.getKey() + ": " + errorText;
    }

}
