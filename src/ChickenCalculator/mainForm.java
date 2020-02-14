package ChickenCalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainForm {
    private Chicken mChicken;

    private JTextField chickenCountJTextField;
    private JTextField chickenPerMinuteJTextField;
    private JSpinner coopCountJSpinner;
    private JFormattedTextField boostJFormattedJTextField;
    private JCheckBox offlineJCheckBox;
    private JButton calculateTimeJButton;
    private JPanel panelMain;

    public static void main(String[] args)
    {
        // create new frame
        JFrame frame = new JFrame("mainForm");

        // set default close behavior
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set content of new frame
        frame.setContentPane(new mainForm().panelMain);

        // fit everything into window
        frame.pack();

        // display window
        frame.setVisible(true);

    }

    public mainForm()
    {
        // get information when button is pressed
        this.calculateTimeJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getResults();
            }
        });


    }

    public Chicken getResults()
    {
        // read inputs into variables
        int chickenPerMinute = Integer.parseInt(this.chickenPerMinuteJTextField.getText());
        int currentChickenCount = Integer.parseInt(this.chickenCountJTextField.getText());
        int coopCount = (int) coopCountJSpinner.getValue();
        int boostMultiplier = Integer.parseInt(this.boostJFormattedJTextField.getText());
        boolean isOffline = this.offlineJCheckBox.isSelected();

        // return Chicken object
        return new Chicken(chickenPerMinute, currentChickenCount, coopCount, boostMultiplier);
    }

}
