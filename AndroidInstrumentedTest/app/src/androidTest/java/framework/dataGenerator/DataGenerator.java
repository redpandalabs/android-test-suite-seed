package framework.dataGenerator;

import java.util.Random;

import io.bloco.faker.Faker;

/**
 * Created by vivek on 20/12/18.
 */

public class DataGenerator {

    int years[] = {

            1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000,
            2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010,
            2011,2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019
    };

    int months[] = {

            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
    };

    int days[] = {

            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18,
            19, 20, 21, 22, 23, 24, 25, 26,
            27, 28, 29, 30, 31

    };

    Random rand = new Random();
    Faker faker = new Faker();

    private String name = faker.name.firstName();

    public void setName(String name){

        this.name = name;
    }

    public String getName(){

        return name;

    }

    public String getEmail(){
        return faker.internet.email(name);
    }

    public String getPassword(){

        return name+"123@";
    }

    public String getAddress(){
        return faker.address.secondaryAddress();
    }

    public String getGender(){

        String arr[] = {"M", "F"};
        String gen = selectGender(arr);
        return gen;
    }

    private String selectGender(String[] arr){

        int x = rand.nextInt(2);
        return arr[x];
    }

    public int getYear(){

        return rand.nextInt(years.length);

    }

    public int getMonthOfYear(){
        return rand.nextInt(months.length);
    }

    public int getDayOfMonth(){
        return rand.nextInt(days.length);
    }

    public String getMonthInChar(int val){

        switch(val){

            case 1: {
                return "Jan";

            }

            case 2: {
                return "Feb";
            }
            case 3: {
                return "Mar";
            }
            case 4: {
                return "Apr";
            }
            case 5: {
                return "May";
            }
            case 6: {
                return "Jun";
            }
            case 7: {
                return "Jul";
            }
            case 8: {
                return "Aug";
            }
            case 9: {
                return "Sep";
            }
            case 10: {
                return "Oct";
            }
            case 11: {
                return "Nov";
            }
            case 12: {
                return "Dec";
            }
            default: return null;
        }
    }

}
