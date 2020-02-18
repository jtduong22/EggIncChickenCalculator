package ChickenCalculator;

import java.util.function.Supplier;

public class CalculateTimePanel extends InputPanel {
    Supplier<Chicken> getChickenCallback;

    public CalculateTimePanel(Supplier<Chicken> func)
    {
        super();

        initTextField(250000000, 1);
        getChickenCallback = func;
        setButtonAction(this::calculateTimeUntilGoal);
        resultJLabel.setText("N/A Minutes");

        panel.add(addLabel("Set Chicken Count Goal", inputJFormattedTextField));
        panel.add(inputJButton);
        panel.add(resultJLabel);
    }

    public void calculateTimeUntilGoal()
    {
        Chicken chicken = (Chicken) getChickenCallback.get();

        String s = inputJFormattedTextField.getText().replaceAll(",","");
        int count = Integer.parseInt(s);

        boolean isOffline = chicken.isOffline();

        int time = chicken.calculateTimeToGoal(count, isOffline);
        resultJLabel.setText(time + " Minutes");
    }

}
