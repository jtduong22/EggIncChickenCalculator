package ChickenCalculator;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public class FarmInfo {

    public JPanel farmPanel;
    private JFormattedTextField chickenCountJFormattedTextField;
    private JFormattedTextField chickenPerMinuteJFormattedTextField;
    private JFormattedTextField boostJFormattedTextField;
    private JSpinner coopCountJSpinner;
    private JCheckBox offlineJCheckBox;

    public FarmInfo()
    {
        farmPanel = new JPanel();
        farmPanel.setLayout(new BoxLayout(farmPanel, BoxLayout.Y_AXIS));

        chickenCountJFormattedTextField = initJFormattedTextfield(0, 0);
        chickenPerMinuteJFormattedTextField = initJFormattedTextfield(1,1);
        boostJFormattedTextField = initJFormattedTextfield(1,1);
        coopCountJSpinner = initCoopCount(4,1,4);
        offlineJCheckBox = new JCheckBox("Offline", true);

        farmPanel.add(addLabel("Chicken Count", chickenCountJFormattedTextField));
        farmPanel.add(addLabel("Chicken Per Minute: ", chickenPerMinuteJFormattedTextField));
        farmPanel.add(addLabel("Boost Amount: ", boostJFormattedTextField));
        farmPanel.add(addLabel("Coop Amount", coopCountJSpinner));
        farmPanel.add(offlineJCheckBox);

    }

    private JPanel addLabel(String text, JComponent component)
    {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout());

        jPanel.add(new JLabel(text),0);
        jPanel.add(component, 1);

        return jPanel;
    }

    // create JFormattedTextField that accepts only numbers
    private JFormattedTextField initJFormattedTextfield(int initialValue)
    {
        // create JFormattedTextField
        JFormattedTextField textField = new JFormattedTextField();

        // set textField to accept only numbers
        NumberFormatter nf = new NumberFormatter();
        textField.setFormatterFactory(new DefaultFormatterFactory(nf));

        // set initial value to number
        textField.setValue(initialValue);

        // set right alignment
        textField.setHorizontalAlignment(JTextField.RIGHT);

        return textField;
    }

    // create JFormattedTextField that accepts only numbers with a minimum value
    private JFormattedTextField initJFormattedTextfield(int initialValue, int minimumValue)
    {
        // create textField that only accepts numbers
        JFormattedTextField textField = initJFormattedTextfield(initialValue);

        // set minimum value
        NumberFormatter nf = (NumberFormatter) textField.getFormatter();
        nf.setMinimum(minimumValue);

        return textField;
    }

    private JSpinner initCoopCount(int initialValue, int minimumValue, int maximumValue)
    {
        // create new spinner object
        JSpinner spinner = new JSpinner();

        // set spinner parameters
        SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
        model.setMinimum(minimumValue);
        model.setMaximum(maximumValue);
        model.setValue(initialValue);

        // return spinner
        return spinner;
    }

    public Chicken getChicken()
    {
        // temporary class, removes commas from string and converts to int
        // needed since JFormattedTextBox adds comma
        // i.e 123,456,789 turns to 123456789
        class convert { public int parseInt(JFormattedTextField t) {return Integer.parseInt(t.getText().replaceAll(",",""));}}
        convert c = new convert();

        // get information about farm from inputs
        int count =  c.parseInt(chickenCountJFormattedTextField);
        int chickPerMinute = c.parseInt(chickenPerMinuteJFormattedTextField);
        int boost = c.parseInt(boostJFormattedTextField);
        int coopCount = (int) coopCountJSpinner.getValue();

        return new Chicken(chickPerMinute, count, coopCount, boost);
    }

    public boolean isOffline() { return offlineJCheckBox.isSelected();}

}
