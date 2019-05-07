package Additionals;

public class EducationalInstitutional implements Comparable<EducationalInstitutional>
{
    // FIELDS
    private String name;
    private String address;
    private int foundationYear;

    // CONSTRUCTOR
    public EducationalInstitutional(String name, String address, int foundationYear)
    {
        this.name = name;
        this.address = address;
        this.foundationYear = foundationYear;
    }

    // METHODS
    public  int compareTo(EducationalInstitutional other)
    {
        return Integer.compare(this.foundationYear,other.foundationYear);
    }
    public int getYear()
    {
        return foundationYear;
    }
    public String getName()
    {
        return name;
    }
}
