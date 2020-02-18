package ChickenCalculator;

import javax.swing.*;

public class mainForm {
    private FarmInfo farmInfo;
    private CalculateTimePanel cTime;
    private CalculateChickenPanel cChicken;

    private JPanel panelMain;


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
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));

        farmInfo = new FarmInfo();
        panelMain.add(farmInfo.farmPanel);

        cTime = new CalculateTimePanel(farmInfo::getChicken);
        panelMain.add(cTime.panel);

        cChicken = new CalculateChickenPanel(farmInfo::getChicken);
        panelMain.add(cChicken.panel);

    }

}
