package Additionals;

public class School extends EducationalInstitutional
{
    // FIELDS
    private int number;
    private int pupilsAmount;

    // CONSTRUCTOR
    public School(String name, String address, int foundationYear, int number, int pupilsAmount)
    {
        super(name, address, foundationYear);
        this.number = number;
        this.pupilsAmount = pupilsAmount;
    }

    // METHODS
    public int getPupilsAmount()
    {
        return pupilsAmount;
    }
}
