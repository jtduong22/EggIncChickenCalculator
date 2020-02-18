package ChickenCalculator;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainForm {
    private FarmInfo farmInfo;

    private JFormattedTextField chickenCountJFormattedTextField;
    private JFormattedTextField chickenPerMinuteJFormattedTextField;
    private JSpinner coopCountJSpinner;
    private JFormattedTextField boostJFormattedJTextField;
    private JCheckBox offlineJCheckBox;
    private JButton calculateTimeJButton;
    private JPanel panelMain;
    private JFormattedTextField endChickenGoalJTextField;
    private JPanel farmInfoJPanel;
    private JPanel calculateTimeJPanel;
    private JPanel calculateChickenJPanel;
    private JFormattedTextField waitTimeJFormattedTextField;
    private JButton calculateChickenJButton;

    public static void main(String[] args) {
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

    // constructor
    public mainForm() {
//        initFarmInfo();
//        initCalculateTimePanel();

        panelMain.removeAll();
        panelMain = new JPanel();

        farmInfo = new FarmInfo();
        panelMain.add(farmInfo.farmPanel);
//        panelMain.add(new FarmInfo().farmPanel);
    }

    // initialize farm info JPanel
    public void initFarmInfo() {
        // make chicken count text input accept only NUMBERS above 0
        NumberFormatter nf = new NumberFormatter();
        nf.setMinimum(0);
        chickenCountJFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(nf));
        chickenCountJFormattedTextField.setValue(0);

        // make chicken per minute text input accept only NUMBERS above 1
        // note: minimum is 1 to avoid division by 0 errors
        nf = new NumberFormatter();
        nf.setMinimum(1);
        chickenPerMinuteJFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(nf));
        chickenPerMinuteJFormattedTextField.setValue(1);

        // make make boost text input only accept numbers above 1
        // note: minimum is 1 to avoid division by 0 errors
        nf = new NumberFormatter();
        nf.setMaximum(999);
        nf.setMinimum(1);
        boostJFormattedJTextField.setFormatterFactory(new DefaultFormatterFactory(nf));
        boostJFormattedJTextField.setValue(1);

        // set minimum and maximum of coop Count spinner
        SpinnerNumberModel sm = (SpinnerNumberModel) coopCountJSpinner.getModel();
        sm.setMinimum(1);
        sm.setMaximum(4);
        sm.setValue(4);
    }

    // initialize calculate time JPanel
    public void initCalculateTimePanel() {
        // make chicken population goal text input accept only numbers
        // minimum is 1 for... arbitrary reasons
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

    public void calculateTimeButtonPressed() {
        Chicken chicken = farmInfo.getChicken();
        boolean isOffline = farmInfo.isOffline();

        int endAmount = 250000000;

        System.out.println(chicken.calculateTimeToGoal(endAmount, isOffline));
        System.out.println(chicken.calculateChickenSpawn(240, isOffline));
    }

}
