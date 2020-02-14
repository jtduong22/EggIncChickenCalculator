package ChickenCalculator;

// Class definition that calculates the number of chickens given a time interval or vice versa
public class Chicken
{
    // public

    // private
    private int chickenPerMinute;
    private int currentChickenCount;
    private int coopCount;
    private int boostMultiplier;

    // constants
    final int OFFLINEMULTIPLIER = 3;

    // constructors
    public Chicken(int chickenPerMinute, int currentChickenCount, int coopCount, int boostMultiplier)
    {
        // set class member values
        this.chickenPerMinute = chickenPerMinute;
        this.currentChickenCount = currentChickenCount;
        this.coopCount = coopCount;
        this.boostMultiplier = boostMultiplier;
    }

    public Chicken(int chickenPerMinute)
    {
        // call other constructor with default values
        this(chickenPerMinute, 0, 4, 1);
    }

    // methods to calculate how much time to reach a number of chicken
    public int calculateTimeToGoal(int endChickenCount, boolean isOffline)
    {
        int multiplier = isOffline ? OFFLINEMULTIPLIER : 1;
        multiplier *= boostMultiplier * coopCount;
        return calculateTimeToGoal(endChickenCount, multiplier);
    }

    public int calculateTimeToGoal(int endChickenCount)
    {
        return calculateTimeToGoal(endChickenCount, true);
    }

    private int calculateTimeToGoal(int endChickenCount, int multiplier)
    {
        int remainingChicken = endChickenCount - currentChickenCount;
        int time = remainingChicken / (chickenPerMinute * multiplier);

        return time;
    }

    // methods to calculate how many chickens will hatch in a period of time
    public int calculateChickenSpawn(int minutes, int multiplier)
    {
        int chicken = chickenPerMinute * minutes * multiplier;
        return chicken;
    }

    public int calculateChickenSpawn(int minutes, boolean isOffline)
    {
        int multiplier = isOffline ? OFFLINEMULTIPLIER : 1;
        multiplier *= boostMultiplier * coopCount;
        return calculateChickenSpawn(minutes, multiplier);
    }

    public int calculateChickenSpawn(int minutes)
    {
        return calculateChickenSpawn(minutes, true);
    }

}
