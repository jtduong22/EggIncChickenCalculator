package ChickenCalculator;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;

public abstract class InputPanel {
    protected JPanel panel;

    protected JButton inputJButton;
    protected JFormattedTextField inputJFormattedTextField;
    protected JLabel resultJLabel;

    public InputPanel()
    {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        inputJFormattedTextField = new JFormattedTextField();
        inputJButton = new JButton("Calculate");
        resultJLabel = new JLabel();
    }

    protected void initTextField(int initialValue, int minValue)
    {
        // set minimum
        NumberFormatter nf = new NumberFormatter();
        nf.setMinimum(minValue);

        // set textbox to numbers only
        inputJFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(nf));

        // set initial value
        inputJFormattedTextField.setValue(initialValue);
    }

    // set button to perform function passed in whenever pressed
    public void setButtonAction(Runnable func)
    {
        // get information when button is pressed
        inputJButton.addActionListener(actionEvent -> func.run());
    }

    protected JPanel addLabel(String text, JComponent component)
    {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout());

        jPanel.add(new JLabel(text),0);
        jPanel.add(component, 1);

        return jPanel;
    }
}
