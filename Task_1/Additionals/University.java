package Additionals;

public class University extends EducationalInstitutional
{
    // FIELDS
    private int accreditationLevel;
    private int facultiesAmount;

    // CONSTRUCTOR
    public University(String name, String address, int foundationYear, int accreditationLevel, int facultiesAmount)
    {
        super(name,address,foundationYear);
        this.accreditationLevel = accreditationLevel;
        this.facultiesAmount = facultiesAmount;
    }

    // METHODS
    public int getAccreditationLevel()
    {
        return accreditationLevel;
    }
}
