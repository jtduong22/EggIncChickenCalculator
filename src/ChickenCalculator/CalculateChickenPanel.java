package ChickenCalculator;

import java.util.function.Supplier;

public class CalculateChickenPanel extends InputPanel {
    Supplier<Chicken> getChickenCallback;

    public CalculateChickenPanel(Supplier<Chicken> func)
    {
        super();

        initTextField(60, 1);
        getChickenCallback = func;
        setButtonAction(this::calculateTimeUntilGoal);
        resultJLabel.setText("N/A Chickens");

        panel.add(addLabel("Set Time (in Minutes)", inputJFormattedTextField));
        panel.add(inputJButton);
        panel.add(resultJLabel);
    }

    public void calculateTimeUntilGoal()
    {
        Chicken chicken = getChickenCallback.get();

        String s = inputJFormattedTextField.getText().replaceAll(",","");
        int time = Integer.parseInt(s);

        int count = chicken.calculateChickenSpawn(time);
        resultJLabel.setText(count + " Chickens after " + time + " minutes");
    }
}
