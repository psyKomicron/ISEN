package com.psy.rockfx;

import com.psy.rockfx.calculator.Band;
import com.psy.rockfx.calculator.BandColor;
import com.psy.rockfx.calculator.ResistorValueCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class NewView
{
    private BandColor lastUsedColor = BandColor.Black;
    private final ArrayList<Band> bands = new ArrayList<>();
    private BandColor toleranceBandColor = BandColor.Black;
    private BandColor multiplierBandColor = BandColor.Black;
    @FXML
    private GridPane rootGrid;
    @FXML
    private HBox bandHBox;
    @FXML
    private ButtonBar bandControlButtonBar;
    @FXML
    private Button removeBandButton;
    @FXML
    private Label resultLabel;
    @FXML
    private ToggleButton autoUpdateToggleButton;
    @FXML
    private Button multiplierButton;
    @FXML
    private Button toleranceButton;
    @FXML
    private Label bandHBoxTooltip;

    @FXML
    public void initialize()
    {
        autoUpdateToggleButton.setSelected(true);
        multiplierButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(3), null)));
        toleranceButton.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(3), null)));
    }

    public void buttonOnAction(ActionEvent e)
    {
        // Open dialog or something to pick a color.
        // TODO: I18N
        var dialog = new ChoiceDialog<String>(
                "Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Gray", "White"
        );
        dialog.setContentText("Choose a color");
        dialog.setHeaderText("Band color picker");
        dialog.setSelectedItem(
                switch (lastUsedColor)
                {
                    case Black -> "Black";
                    case Brown -> "Brown";
                    case Red -> "Red";
                    case Orange -> "Orange";
                    case Yellow -> "Yellow";
                    case Green -> "Green";
                    case Blue -> "Blue";
                    case Purple -> "Purple";
                    case Gray -> "Gray";
                    case White -> "White";
                    case Gold -> "Gold";
                    case Silver -> "Silver";
                }
        );

        var res = dialog.showAndWait();
        if (res.isPresent() && e.getSource() instanceof Button sender)
        {
            String choice = res.get();
            Color color;
            BandColor newBandColor;
            switch (choice)
            {
                case "Black" ->
                {
                    color = Color.BLACK;
                    newBandColor = BandColor.Black;
                }
                case "Brown" ->
                {
                    color = Color.BROWN;
                    newBandColor = BandColor.Brown;
                }
                case "Red" ->
                {
                    color = Color.RED;
                    newBandColor = BandColor.Red;
                }
                case "Orange" ->
                {
                    color = Color.ORANGE;
                    newBandColor = BandColor.Orange;
                }
                case "Yellow" ->
                {
                    color = Color.YELLOW;
                    newBandColor = BandColor.Yellow;
                }
                case "Green" ->
                {
                    color = Color.GREEN;
                    newBandColor = BandColor.Green;
                }
                case "Blue" ->
                {
                    color = Color.BLUE;
                    newBandColor = BandColor.Blue;
                }
                case "Purple" ->
                {
                    color = Color.PURPLE;
                    newBandColor = BandColor.Purple;
                }
                case "Gray" ->
                {
                    color = Color.GRAY;
                    newBandColor = BandColor.Gray;
                }
                case "White" ->
                {
                    color = Color.WHITE;
                    newBandColor = BandColor.White;
                }
                case "Gold" ->
                {
                    color = Color.GOLD;
                    newBandColor = BandColor.Gold;
                }
                case "Silver" ->
                {
                    color = Color.SILVER;
                    newBandColor = BandColor.Silver;
                }
                default -> throw new IllegalArgumentException();
            }

            lastUsedColor = newBandColor;

            // Get the band associated with this button.
            int bandIndex = Integer.parseInt(sender.getId());
            if (bandIndex < bands.size())
            {
                bands.get(bandIndex).BandColor(newBandColor);
            }

            sender.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), null)));

            if (autoUpdateToggleButton.isSelected())
            {
                UpdateResult();
            }
        }
    }

    @FXML
    public void AddBandButton_Action()
    {
        // TODO: Use the last used band color to create new bands.
        // Create a new band.
        Band newBand = new Band(BandColor.Black, bands.size());
        bands.add(newBand);

        // Add new button.
        Button b = new Button();
        b.setId(Integer.toString(newBand.Rank()));
        b.maxWidth(5);
        b.prefWidth(5);
        b.setMaxHeight(200);
        b.setPrefHeight(Double.MAX_VALUE);
        HBox.setHgrow(b, Priority.NEVER);
        b.setAlignment(Pos.CENTER);
        b.setOnAction(this::buttonOnAction);
        b.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(3), null)));
        b.getStyleClass().add("band-button");

        bandHBox.getChildren().add(b);

        removeBandButton.setDisable(false);
        bandHBoxTooltip.setVisible(false);

        // Update the resistance value.
        if (autoUpdateToggleButton.isSelected())
        {
            UpdateResult();
        }
    }

    @FXML
    public void RemoveBandButton_Action()
    {
        // Remove last band.
        if (bandHBox.getChildren().size() > 0)
        {
            bandHBox.getChildren().remove(bandHBox.getChildren().size() - 1);

            removeBandButton.setDisable(bandHBox.getChildren().size() == 0);
            bandHBoxTooltip.setVisible(bandHBox.getChildren().size() == 0);
        }

        if (bands.size() > 0)
        {
            bands.remove(bands.size() - 1);
        }

        if (autoUpdateToggleButton.isSelected())
        {
            UpdateResult();
        }
    }

    @FXML
    public void ClearBandsButton_Action()
    {
        // Remove all bands.
        bandHBox.getChildren().clear();
        bands.clear();

        if (autoUpdateToggleButton.isSelected())
        {
            UpdateResult();
        }
    }

    @FXML
    public void AutoUpdateToggleButton_OnAction()
    {
        if (autoUpdateToggleButton.isSelected())
        {
            UpdateResult();
        }
    }

    @FXML
    public void RefreshButton_OnAction()
    {
        UpdateResult();
    }

    @FXML
    public void MultiplierButton_OnAction()
    {
        var dialog = new ChoiceDialog<String>(
                "Black", "Brown", "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Gray", "White", "Gold", "Silver"
        );
        dialog.setContentText("Choose a color");
        dialog.setHeaderText("Band color picker");

        var res = dialog.showAndWait();
        if (res.isPresent())
        {
            String choice = res.get();
            Color color;
            switch (choice)
            {
                case "Black" ->
                {
                    color = Color.BLACK;
                    multiplierBandColor = BandColor.Black;
                }
                case "Brown" ->
                {
                    color = Color.BROWN;
                    multiplierBandColor = BandColor.Brown;
                }
                case "Red" ->
                {
                    color = Color.RED;
                    multiplierBandColor = BandColor.Red;
                }
                case "Orange" ->
                {
                    color = Color.ORANGE;
                    multiplierBandColor = BandColor.Orange;
                }
                case "Yellow" ->
                {
                    color = Color.YELLOW;
                    multiplierBandColor = BandColor.Yellow;
                }
                case "Green" ->
                {
                    color = Color.GREEN;
                    multiplierBandColor = BandColor.Green;
                }
                case "Blue" ->
                {
                    color = Color.BLUE;
                    multiplierBandColor = BandColor.Blue;
                }
                case "Purple" ->
                {
                    color = Color.PURPLE;
                    multiplierBandColor = BandColor.Purple;
                }
                case "Gray" ->
                {
                    color = Color.GRAY;
                    multiplierBandColor = BandColor.Gray;
                }
                case "White" ->
                {
                    color = Color.WHITE;
                    multiplierBandColor = BandColor.White;
                }
                case "Gold" ->
                {
                    color = Color.GOLD;
                    multiplierBandColor = BandColor.Gold;
                }
                case "Silver" ->
                {
                    color = Color.SILVER;
                    multiplierBandColor = BandColor.Silver;
                }
                default -> throw new IllegalArgumentException();
            }

            multiplierButton.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), null)));

            if (autoUpdateToggleButton.isSelected())
            {
                UpdateResult();
            }
        }
    }

    @FXML
    public void ToleranceButton_OnAction()
    {
        var dialog = new ChoiceDialog<String>(
                "Black", "Brown", "Red", "Green", "Blue", "Purple", "Gray", "Gold", "Silver"
        );
        dialog.setContentText("Choose a color");
        dialog.setHeaderText("Band color picker");

        var res = dialog.showAndWait();
        if (res.isPresent())
        {
            String choice = res.get();
            Color color;
            switch (choice)
            {
                case "Black" ->
                {
                    color = Color.BLACK;
                    toleranceBandColor = BandColor.Black;
                }
                case "Brown" ->
                {
                    color = Color.BROWN;
                    toleranceBandColor = BandColor.Brown;
                }
                case "Red" ->
                {
                    color = Color.RED;
                    toleranceBandColor = BandColor.Red;
                }
                case "Orange" ->
                {
                    color = Color.ORANGE;
                    toleranceBandColor = BandColor.Orange;
                }
                case "Yellow" ->
                {
                    color = Color.YELLOW;
                    toleranceBandColor = BandColor.Yellow;
                }
                case "Green" ->
                {
                    color = Color.GREEN;
                    toleranceBandColor = BandColor.Green;
                }
                case "Blue" ->
                {
                    color = Color.BLUE;
                    toleranceBandColor = BandColor.Blue;
                }
                case "Purple" ->
                {
                    color = Color.PURPLE;
                    toleranceBandColor = BandColor.Purple;
                }
                case "Gray" ->
                {
                    color = Color.GRAY;
                    toleranceBandColor = BandColor.Gray;
                }
                case "White" ->
                {
                    color = Color.WHITE;
                    toleranceBandColor = BandColor.White;
                }
                case "Gold" ->
                {
                    color = Color.GOLD;
                    toleranceBandColor = BandColor.Gold;
                }
                case "Silver" ->
                {
                    color = Color.SILVER;
                    toleranceBandColor = BandColor.Silver;
                }
                default -> throw new IllegalArgumentException();
            }

            toleranceButton.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), null)));

            if (autoUpdateToggleButton.isSelected())
            {
                UpdateResult();
            }
        }
    }

    public void HelpButton_OnAction()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");

        VBox pane = new VBox();
        pane.setMaxWidth(300);
        pane.setSpacing(7);
        Label label = new Label();
        label.setWrapText(true);
        label.setText("Resistor bands are represented by buttons. You can click on them to change the color of the band, if auto-update is enabled the resistor value will be automatically updated.");
        Label label2 = new Label();
        label2.setWrapText(true);
        label2.setText("Use the + and - button to add and remove bands as needed. You cannot add or remove the bands on the left as they represent the resistor multiplier and tolerance.");
        Label label3 = new Label();
        label3.setWrapText(true);
        label3.setText("Auto-update button (top left) allows to automatically update the resistor value (and tolerance) following the changes you make.");

        pane.getChildren().add(label);
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
        alert.getDialogPane().setContent(pane);

        alert.show();
    }


    private void UpdateResult()
    {
        var res = ResistorValueCalculator.Calculate(bands, new Band(multiplierBandColor, 0), new Band(toleranceBandColor, 0));

        var tol = FormatNumber(res.tolerance());
        resultLabel.setText(FormatNumber(res.ohm()) + "Ω ±" + tol);
    }

    private <T extends Number> String FormatNumber(T number)
    {
        if (number instanceof Double l)
        {
            // Format using SI short numbers.
            if (l > 1_000_000_000.) // 10^9
            {
                var trunc = Math.round((l / 1_000_000_000.0) * 100.) / 100.;
                return "%s G".formatted(Double.toString(trunc));
            }
            else if (l > 1_000_000.) // 10^6
            {
                var trunc = Math.round((l / 1_000_000.0) * 100.) / 100.;
                return "%s M".formatted(Double.toString(trunc)); // TODO: Possible I18N
            }
            else if (l > 1_000.) // 10^3
            {
                var trunc = Math.round((l / 1_000.0) * 100.) / 100.;
                return "%s K".formatted(Double.toString(trunc)); // TODO: Possible I18N
            }
            else
            {
                double trunc = Math.round(l * 100.) / 100.;
                return Double.toString(trunc); // TODO: Possible I18N
            }
        }
        else if (number instanceof Float d)
        {
            // Format using percentages.
            var percentage = d * 100.f;
            if (percentage == (int)percentage)
            {
                return "%d%%".formatted((int)percentage);
            }
            else
            {
                return "%.2f%%".formatted(percentage);
            }
        }
        else
        {
            return String.format("%d", number);
        }
    }
}
