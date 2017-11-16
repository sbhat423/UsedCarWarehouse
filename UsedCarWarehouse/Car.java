import java.util.*;
/**
 * Car class contains all the attributes of a car
 * such as car registration number, year made, colours,
 * car make, car model and year make.
 * 
 * It contains setters and getter methods to access all the above mentioned attributes
 * along with the display method.
 * 
 * @author Sharat Bhat 
 * @version 1.0 20/10/2017
 */

//a car can have no colour
public class Car
{
    private String carMake;
    private String carModel;
    private String carRegistrationNumber;
    private ArrayList<String> colour;
    private int price;
    private int yearMade;
    
    /**
     * Default constructor of an object of car class
     */
    public Car()
    {
        this.carRegistrationNumber = "";
        this.yearMade = 9999;
        colour = new ArrayList<>();
        colour.add("");
        colour.add("");
        colour.add("");
        this.carMake = "";
        this.carModel = "";
        this.price = 0;
    }
    
    /**
     * Constructor for objects of class Car
     */
    public Car(String carRegistrationNumber, int yearMade, String colour1, String colour2, String colour3, String carMake, String carModel, int price)
    {
        if ( carRegistrationNumber.isEmpty() || carRegistrationNumber.length() > 6 || !containsValidCharacters(carRegistrationNumber))
            this.carRegistrationNumber = "";
        else
            this.carRegistrationNumber = carRegistrationNumber;
        if (yearMade < 1900 || yearMade > Calendar.getInstance().get(Calendar.YEAR))
            this.yearMade = 999;
        else
            this.yearMade = yearMade;
        colour = new ArrayList<>();
        colour1 = colour1.toLowerCase().trim();
        colour2 = colour2.toLowerCase().trim();
        colour3 = colour3.toLowerCase().trim();
        if (colour1.isEmpty() && colour2.isEmpty() && colour3.isEmpty())
        {
            colour.add(colour1);
            colour.add(colour2);
            colour.add(colour3);
        }
        else
            if (colour1.equals(colour2) && colour2.equals(colour3) && colour1.equals(colour3))
            {
                colour.add("");
                colour.add("");
                colour.add("");
            }
            else if (colour1.equals(colour2))
            {
                colour.add("");
                colour.add("");
                colour.add(colour3);
            }
            else if (colour1.equals(colour3))
            {
                colour.add("");
                colour.add(colour2);
                colour.add("");
            }
            else if (colour2.equals(colour3))
            {
                colour.add(colour1);
                colour.add("");
                colour.add("");
            }
            else
            {
                colour.add(colour1);
                colour.add(colour2);
                colour.add(colour3);
            }
        carMake = carMake.trim();
        if (!containsValidCharacters(carMake) || carMake.isEmpty())
            this.carMake = "";
        else
            this.carMake = carMake;
        carModel = carModel.trim();
        if (!containsValidCharacters(carModel) || carModel.isEmpty())  
            this.carModel = "";
        else
            this.carModel = carModel;
        if (price < 0)
            this.price = 0;
        else
            this.price = price;
    }
    
    /**
     * This method takes a string as an input and returns true if it contains only numbers else it returns false
     * <p>
     * @param inputString string to be tested.
     * @return boolean true if inputString contains only numbers else false.
     */
    private boolean containsOnlyNumbers(String inputString)
    {
        inputString = inputString.trim();
        if (inputString.equals(null))
            return false;
        else
            for (int i = 0; i < inputString.length(); i++)
                if (!(inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9'))
                    return false;
        return true;
    }
    
    /**
     * This method checks whether an input string contains either alphabets or numbers, only.
     * <p>
     * @param inputString string to be tested
     * @return boolean true id the inputString contains valid characters else false.
     */
    private boolean containsValidCharacters(String inputString)
    {
        boolean validityFlag = true;
        int inputStringLength = inputString.trim().length();
        for (int i = 0 ; i < inputStringLength ; i++)
        {
            char digit = inputString.charAt(i);
            if (!((digit >= 'A' && digit <= 'Z') || (digit >= 'a' && digit <= 'z') || (digit >= '0' && digit <= '9')))
            {
                validityFlag = false;
                break;
            }
        }
        return validityFlag;
    }
    
    /**
     * This method prints the attributes of an object of class Car.
     */
    public void display()
    {
        System.out.println();
        System.out.println("Registration Number: " + carRegistrationNumber + "  |  YearMade: " 
        + yearMade + "  |  Colour1: " + colour.get(0) + "  |  Colour2: " + colour.get(1) 
        + "  |  Colour3: " + colour.get(2) + "  |  Car Make: " + carMake + "  |  Car Model: " 
        + carModel + "  |  Price: " + price);
    }
    
    /**
     * This getter method returns the value of all attributes of an object of class Car, concatinated in a single string.
     * 
     * @return String concatinated value of all the attributes.
     */
    public String getCarDetails()
    {
        String carDetail = getCarRegistrationNumber();
        carDetail = carDetail + "," + getYearMade();
        carDetail = carDetail + "," + getColour1();
        carDetail = carDetail + "," + getColour2();
        carDetail = carDetail + "," + getColour3();
        carDetail = carDetail + "," + getCarMake();
        carDetail = carDetail + "," + getCarModel();
        carDetail = carDetail + "," + getPrice();
        return carDetail;
    }
    
    /**
     * This getter method returns the value of car make of an object of class Car.
     * 
     * @return String value of car make.
     */
    public String getCarMake()
    {
        return carMake;
    }
    
    /**
     * This getter method returns the value of car model of an object of class Car.
     * 
     * @return String value of car model.
     */
    public String getCarModel()
    {
        return carModel;
    }
    
     /**
     * This getter method returns the value of car registration number of an object of class Car.
     * 
     * @return String car registration number.
     */
    public String getCarRegistrationNumber()
    {
        return carRegistrationNumber;
    }
    
     /**
     * This getter method returns the value of attribute colour1 of an object of class Car.
     * 
     * @return String value of colour1.
     */
    public String getColour1()
    {
        return colour.get(0);
    }
    
    /**
     * This getter method returns the value of attribute colour2 of an object of class Car.
     * 
     * @return String value of colour2.
     */
    public String getColour2()
    {
        return colour.get(1);
    }
    
    /**
     * This getter method returns the value of attribute colour3 of an object of class Car.
     * 
     * @return String value of colour3.
     */
    public String getColour3()
    {
        return colour.get(2);
    }
    
    /**
     * This getter method returns the value of price of an object of class Car.
     * 
     * @return String value of price.
     */
    public int getPrice()
    {
        return price;
    }
    
    /**
     * This getter method returns the value of year made of an object of class Car.
     * 
     * @return int value of year made.
     */
    public int getYearMade()
    {
        return yearMade;
    }
    
        /**
     * This setter method sets the value of colour1 an object of class Car.
     * 
     * @param colour1 value of colour1.
     */
    public void setColour1(String colour1)
    {
        if ( !colour1.equals(getColour2()) && !colour1.equals(getColour3()))
            colour.set(0, colour1);
    }
    
    /**
     * This setter method sets the value of colour2 an object of class Car.
     * 
     * @param colour2 value of colour2.
     */
    public void setColour2(String colour2)
    {
        if ( !colour2.equals(getColour1()) && !colour2.equals(getColour3()))
            colour.set(1, colour2);
    }
    
    /**
     * This setter method sets the value of colour3 an object of class Car.
     * 
     * @param colour3 value of colour3.
     */
    public void setColour3(String colour3)
    {
        if ( !colour3.equals(getColour1()) && !colour3.equals(getColour1()))
            colour.set(2, colour3);
    }
    
    /**
     * This setter method sets the value of car make of an object of class Car.
     * 
     * @param carMake value of car make.
     */
    public void setCarMake(String carMake)
    {
        if (containsValidCharacters(carMake) && !carMake.isEmpty())
            this.carMake = carMake;
    }
    
    /**
     * This setter method sets the value of car model of an object of class Car.
     * 
     * @param carModel value of car model.
     */
    public void setCarModel(String carModel)
    {
        if (containsValidCharacters(carModel) && !carModel.isEmpty())
            this.carModel = carModel;
    }
    
    /**
     * This setter method sets the value of car registration number of an object of class Car.
     * 
     * @param carRegistrationNumber value of car registration number.
     */
    public void setCarRegistrationNumber(String carRegistrationNumber)
    {
        if ( !carRegistrationNumber.isEmpty() && carRegistrationNumber.length() <= 6 && containsValidCharacters(carRegistrationNumber))
            this.carRegistrationNumber = carRegistrationNumber;
    }
    
    /**
     * This setter method sets the value of price of an object of class Car.
     * 
     * @param price value of price.
     */
    public void setPrice(int price)
    {
        if (price >= 0)
            this.price = price;
    }
    
    /**
     * This setter method sets the value of year made of an object of class Car.
     * 
     * @param yearM value of year made.
     */
    public void setYearMade(int yearMade)
    {
        if (yearMade >= 1900 && yearMade <= Calendar.getInstance().get(Calendar.YEAR))
            this.yearMade = yearMade;
    }
}
