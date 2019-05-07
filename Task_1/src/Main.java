package src;

import Additionals.EducationalInstitutional;
import Additionals.School;
import Additionals.University;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main
{
    // METHODS
    private static  void showMatrix(int[][] matrix)
    {
        for(int i = 0; i < matrix.length; ++i)
        {
            for (int j = 0; j < matrix[i].length; ++j)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static  void randomFillMatrix(int[][] matrix)
    {
        for(int i = 0; i < matrix.length; ++i)
        {
            for (int j = 0; j < matrix[i].length; ++j)
            {
                matrix[i][j] = ThreadLocalRandom.current().nextInt(1000);
            }
        }
    }

    private static int digitSumInNumber(int number)
    {
        int digitSum = 0, ten = 10;

        while(number >= ten)
        {
            digitSum += number % ten;
            number /= ten;
        }
        return digitSum + number;
    }

    private static void fillVectorWithMaxValues(int matrix[][], int vectorMax[])
    {
        for(int i = 0; i < matrix.length; ++i)
        {
            for (int j = 0; j < matrix[i].length; ++j)
            {
                if( vectorMax[i] < digitSumInNumber(matrix[i][j]) )
                {
                    vectorMax[i] = digitSumInNumber(matrix[i][j]);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Input n: ");
        int n = in.nextInt();

        System.out.print("Input m: ");
        int m = in.nextInt();

        in.nextLine();

        // fills matrix in random way
        int matrix[][] = new int[n][m];
        randomFillMatrix(matrix);

        showMatrix(matrix);

        // first
        int vectorMax[] = new int[n];

        fillVectorWithMaxValues(matrix,vectorMax);

        System.out.println(Arrays.toString(vectorMax));

        // second
        System.out.println("Input string: ");
        String line = in.nextLine().replaceAll("t", "th");
        System.out.println("\"t\" letter is replaced by \"th\":\n" + line);

        // third
        EducationalInstitutional educatInstitutional[] = new EducationalInstitutional[]
                {
                        new School("First", "street 1", 1345,5,100),
                        new University("FirstU", "street 2", 1890, 1,56),
                        new School("Second", "street 3", 1700,2,24),
                        new University("SecondU", "street 4", 1560, 2,32),
                };


        Arrays.sort(educatInstitutional);
        for (EducationalInstitutional ei: educatInstitutional)
        {
            System.out.println(ei.getYear());
        }

        int minPupils = 9999;
        School schoolWithMinimumPupilsAmount = null;
        for (EducationalInstitutional ei: educatInstitutional)
        {
            if(ei instanceof School)
            {
                School school = (School) ei;
                if(minPupils > school.getPupilsAmount())
                {
                    minPupils = school.getPupilsAmount();
                    schoolWithMinimumPupilsAmount = school;
                }
            }
        }

        if(schoolWithMinimumPupilsAmount!=null)
        {
            System.out.println("Minimum pupils amount:" + schoolWithMinimumPupilsAmount.getPupilsAmount());
        }
        else
        {
            System.out.println("There isn't any school.");
        }


        System.out.println("Input accreditation level: ");
        int accreditationLevel = in.nextInt();
        for (EducationalInstitutional ei: educatInstitutional)
        {

            if(ei instanceof University)
            {
                University university = (University) ei;
                if(accreditationLevel == university.getAccreditationLevel())
                {
                    System.out.println("University name: " + university.getName() + " with accreditation level " + university.getAccreditationLevel());
                }
            }
        }


        in.close();

    }
}
