package ChickenCalculator;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainForm {
    private Chicken mChicken;

    private JFormattedTextField chickenCountJFormattedTextField;
    private JFormattedTextField chickenPerMinuteJFormattedTextField;
    private JSpinner coopCountJSpinner;
    private JFormattedTextField boostJFormattedJTextField;
    private JCheckBox offlineJCheckBox;
    private JButton calculateTimeJButton;
    private JPanel panelMain;
    private JFormattedTextField endChickenGoalJTextField;

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
        initFarmInfo();
        initCalculateTimePanel();
    }

    public void initFarmInfo()
    {
        NumberFormatter nf = new NumberFormatter();
        nf.setMinimum(0);
        chickenCountJFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(nf));
        chickenCountJFormattedTextField.setValue(0);

        nf = new NumberFormatter();
        nf.setMinimum(1);
        chickenPerMinuteJFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(nf));
        chickenPerMinuteJFormattedTextField.setValue(1);

        nf = new NumberFormatter();
        nf.setMaximum(new Integer(999));
        nf.setMinimum(new Integer(1));
        boostJFormattedJTextField.setFormatterFactory(new DefaultFormatterFactory(nf));
        boostJFormattedJTextField.setValue(1);

        SpinnerNumberModel sm = (SpinnerNumberModel) coopCountJSpinner.getModel();
        sm.setMinimum(1);
        sm.setMaximum(4);
        sm.setValue(4);
    }

    public void initCalculateTimePanel()
    {
        NumberFormatter nf = new NumberFormatter();
        nf.setMinimum(1);
        this.endChickenGoalJTextField.setFormatterFactory(new DefaultFormatterFactory(nf));
        this.endChickenGoalJTextField.setValue(250000000);

        // get information when button is pressed
        this.calculateTimeJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                calculateTimeButtonPressed();
            }
        });
    }

    public void calculateTimeButtonPressed()
    {
        Chicken chicken = getResults();
        boolean isOffline = this.offlineJCheckBox.isSelected();
        int endAmount = Integer.parseInt(this.endChickenGoalJTextField.getText().replaceAll(",",""));

        System.out.println(chicken.calculateTimeToGoal(endAmount, isOffline));
        System.out.println(chicken.calculateChickenSpawn(60, isOffline));
    }


    public Chicken getResults()
    {
        // read inputs into variables
        int chickenPerMinute = Integer.parseInt(this.chickenPerMinuteJFormattedTextField.getText().replaceAll(",",""));
        int currentChickenCount = Integer.parseInt(this.chickenCountJFormattedTextField.getText().replaceAll(",",""));
        int coopCount = (int) coopCountJSpinner.getValue();
        int boostMultiplier = Integer.parseInt(this.boostJFormattedJTextField.getText().replaceAll(",",""));


        // return Chicken object
        return new Chicken(chickenPerMinute, currentChickenCount, coopCount, boostMultiplier);
    }

}
