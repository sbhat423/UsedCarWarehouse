import java.util.*;
import java.io.*;

/**
 * The object of class CarList contains the list of object of class Car.
 * It contains methods to search the cars, add cars, delete cars,
 * reading and writing of cars to a file and edit cars.
 * 
 * @author Sharat Bhat
 * @version 1.0 20/10/2017
 */

public class CarList
{
    ArrayList<Car> listOfCars;
    private String path;
    private BufferedReader readingBuffer;
    private FileReader readingFile;
    Scanner scan;
    private FileWriter writingFile;
    private BufferedWriter writingBuffer;
    /**
     * Constructor for objects of class CarList
     */
    public CarList()
    {
        listOfCars = new ArrayList<>();
        path = System.getProperty("user.dir") + "\\usedcars.txt";
        scan = new Scanner(System.in);
    }

    /**
     * This method adds a new car to an object of class CarList.
     * 
     * @param carRegistrationNumber value of car registration number.
     * @param year value of year made of car.
     * @param colour1 value of colour1.
     * @param colour2 value of colour2.
     * @param colour3 value of colour3.
     * @param carMake value of car make.
     * @param carModel value of car model.
     * @param price vaue of price of a car.
     */
    public void addCar( String carRegistrationNumber, int year, String colour1, String colour2, String colour3, String carMake, String carModel, int price)
    {
        Car newCar = new Car(carRegistrationNumber, year, colour1, colour2, colour3, carMake, carModel, price);
        listOfCars.add(newCar);
    }
    
    /**
     * This method checks whether a colour exists for a car with given registration number.
     * 
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getCarRegistrationNumber' on a class named 'Car': {@link Car#getCarRegistrationNumber}.
     * Link to a method named 'getColour1' on a class named 'Car': {@link Car#getColour1}.
     * Link to a method named 'getColour2' on a class named 'Car': {@link Car#getColour2}.
     * Link to a method named 'getColour3' on a class named 'Car': {@link Car#getColour3}.
     * 
     * @param carRegistrationNumber value of car registration number.
     * @param colour value of colour to be checked.
     * @param colourIndex index of the colour.
     * @return boolean true if a colour alreay exists in other indexes else returns false.
     */
    public boolean carColourExists(String carRegistrationNumber, String colour, char colourIndex)
    {
        boolean carFoundFlag = false;
        colour = colour.trim();
        for (Car car : listOfCars)
        {
            if (car.getCarRegistrationNumber().toLowerCase().equals(carRegistrationNumber.toLowerCase()))
            {
                carFoundFlag = true;
                if (colourIndex == '1')
                {
                    if (car.getColour2().toLowerCase().equals(colour.toLowerCase()) || car.getColour3().toLowerCase().equals(colour.toLowerCase()))
                    {
                        return true;
                    }
                }
                else if (colourIndex == '2')
                {
                    if (car.getColour1().toLowerCase().equals(colour.toLowerCase()) || car.getColour3().toLowerCase().equals(colour.toLowerCase()))
                    {
                        return true;
                    }
                }
                else if (colourIndex == '3')
                {
                    if (car.getColour2().toLowerCase().equals(colour.toLowerCase()) || car.getColour1().toLowerCase().equals(colour.toLowerCase()))
                    {
                        return true;
                    }
                }
                else
                    System.out.println("Invalid Colour Index\n");
            }
        }
        if (carFoundFlag == false)
            System.out.println("Car with carReg: " + carRegistrationNumber + "not found\n");
        return false;
    }
    
    /**
     * Checks if a car with given car registration number exists in an object of class CarList.
     * 
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getCarRegistrationNumber' on a class named 'Car': {@link Car#getCarRegistrationNumber}.
     * 
     * @param carRegistrationNumberExists value of car registration number.
     * @return boolean returns true of a car with given car registration number exists else returns false.
     */
    public boolean carRegistrationNumberExists(String carRegistrationNumber)
    {
        for (Car car : listOfCars)
            if (car.getCarRegistrationNumber().toLowerCase().equals(carRegistrationNumber.toLowerCase()))
                return true;    
        return false;
    }

    /**
     * Deletes a car with if a car with given registration number exists from an object of class CarList.
     * Else displays an error message.
     * 
     * @param carRegistrationNumberExists value of car registration number.
     */
    public void deleteCar(String carRegistrationNumber)
    {   
        if (carRegistrationNumberExists(carRegistrationNumber))
        {
            for (Car car : listOfCars)
            {
                if (car.getCarRegistrationNumber().toLowerCase().equals(carRegistrationNumber.toLowerCase()))
                {
                        listOfCars.remove(car);
                        break;
                }
            }
        }
        else
            System.out.println("Car with registration number: " + carRegistrationNumber +" does not exists\n");
    }

    /**
     * This method replaces the colour, of a car with given registration number with a provided colour at a given index.
     * 
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getCarRegistrationNumber' on a class named 'Car': {@link Car#getCarRegistrationNumber}.
     * Link to a method named 'setColour1' on a class named 'Car': {@link Car#setColour1}.
     * Link to a method named 'setColour2' on a class named 'Car': {@link Car#setColour2}.
     * Link to a method named 'setColour3' on a class named 'Car': {@link Car#setColour3}.
     * 
     * @param carRegistrationNumber value of car registration number.
     * @param colourOption index of the colour.
     * @param newColour new colour to be replaced.
     */
    public void editCarColour(String carRegistrationNumber, char colourOption, String newColour)
    {
        for (Car car : listOfCars)
            if (car.getCarRegistrationNumber().toLowerCase().equals(carRegistrationNumber.toLowerCase()))
                if (colourOption == '1')
                    car.setColour1(newColour);
                else if (colourOption == '2')
                    car.setColour2(newColour);
                else if (colourOption == '3')
                    car.setColour3(newColour);
                else
                    System.out.println("Invalid Colour Index\n");
    }

    /**
     * Edits the price, of the car with given registration number.
     * 
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getCarRegistrationNumber' on a class named 'Car': {@link Car#getCarRegistrationNumber}.
     * Link to a method named 'setPrice' on a class named 'Car': {@link Car#setPrice}.
     * 
     * @param carRegistrationNumber value of car registration number.
     * @param newCarPrice new car price to be replaced.
     */
    public void editCarPrice(String carRegistrationNumber, int newCarPrice)
    {
     for (Car car : listOfCars)
            if (car.getCarRegistrationNumber().toLowerCase().equals(carRegistrationNumber.toLowerCase()))
                car.setPrice(newCarPrice);
    }
    
    /**
     *  Searches a car from an object of class CarList based on the car age.
     *  
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getYearMade' on a class named 'Car': {@link Car#getYearMade}.
     *  
     * @param carAge age of the car.
     */
    public void searchCarByAge(int carAge)
    {
        boolean carFoundFlag = false;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (Car car : listOfCars)
            if (car.getYearMade() >= currentYear - carAge)
            {
                car.display();
                carFoundFlag = true;
            }
        if (carFoundFlag == false)
            System.out.println("Cars with age: " + carAge + " are not found in the warehouse.");
    }

    /**
     * Searches a car from an object of class CarList based on the car make and model.
     *  
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getCarMake' on a class named 'Car': {@link Car#getCarMake}.
     * Link to a method named 'getCarModel' on a class named 'Car': {@link Car#getCarModel}.
     * Link to a method named 'display' on a class named 'Car': {@link Car#display}.
     * 
     * @param carMake make of the car.
     * @param carModel model of the car.
     */
    public void searchCarByMakeAndModel(String carMake, String carModel)
    {
        boolean carFoundFlag = false;
        if (!carModel.toLowerCase().equals("any"))
            for (Car car : listOfCars)
            {
                if (car.getCarMake().toLowerCase().equals(carMake.toLowerCase()) && car.getCarModel().toLowerCase().equals(carModel.toLowerCase()))
                {
                    car.display();
                    carFoundFlag = true;
                }
            }
        else
            for (Car car : listOfCars)
            {
                if (car.getCarMake().toLowerCase().equals(carMake.toLowerCase()))
                {
                    car.display();
                    carFoundFlag = true;
                }
            }
        if (carFoundFlag == false)
            System.out.println("Car with make: " + carMake + " and model: " + carModel + "is not found in the warehouse.");
    }

    /**
     * Searches a car from an object of class CarList based on the range of price.
     *  
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getPrice' on a class named 'Car': {@link Car#getPrice}.
     * Link to a method named 'display' on a class named 'Car': {@link Car#display}.
     *  
     *  @param minimumPrice minimum value of the price range.
     *  @param maximumPrice maximum value of the price range.
     */
    public void searchCarByPrice(int minimumPrice, int maximumPrice)
    {
        boolean carFoundFlag = false;
        for (Car car : listOfCars)
            if (car.getPrice() >= minimumPrice && car.getPrice() <= maximumPrice)
            {
                car.display();
                carFoundFlag = true;
            }
        if (carFoundFlag == false)
            System.out.println("Car between the price range $" + minimumPrice + " to $" + maximumPrice + " is not found in the warehouse.");
    }

    /**
     * Searches a car from an object of class CarList based on the car registration number.
     *  
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getCarRegistrationNumber' on a class named 'Car': {@link Car#getCarRegistrationNumber}.
     * Link to a method named 'display' on a class named 'Car': {@link Car#display}.
     *  
     *  @param carRegistrationNumber value of car registration number.
     */
    public void searchCarByRegistrationNumber(String carRegistrationNumber)
    {
        boolean carFound = false;
        for (Car car : listOfCars)
            if (car.getCarRegistrationNumber().toLowerCase().equals(carRegistrationNumber.toLowerCase()))
            {
                car.display();
                carFound = true;
            }
        if (carFound == false)
            System.out.println("Car with the given registration number not found\n");
    }
    
    /**
     * This method reads all the cars from the text userCars.txt  file present in this programs folder and
     * adds them to an object of class CarList.
     */
    public void readCarsFromFile()
    {
        try
        {
            readingFile = new FileReader(path);
            readingBuffer = new BufferedReader(readingFile);
            String readLine = "";
            while ((readLine = readingBuffer.readLine()) != null) 
            {
                String[] carAttributeValues = readLine.split(",");
                Car newCar = new Car(carAttributeValues[0], Integer.parseInt(carAttributeValues[1]), carAttributeValues[2], carAttributeValues[3], 
                carAttributeValues[4], carAttributeValues[5], carAttributeValues[6], Integer.parseInt(carAttributeValues[7]));
                listOfCars.add(newCar);
            }
            try
            {
                readingBuffer.close();
                readingFile.close();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * This method writes all the cars present in an object of class CarList 
     * to a text file carUsers.txt present in the folder of this program.
     * 
     * Link to a class named 'Car': {@link Car}.
     * Link to a method named 'getCarDetails' on a class named 'Car': {@link Car#getCarDetails}.
     */
    public void writeCarsToFile()
    {    
        try
        {
            writingFile = new FileWriter(path, false);
            writingBuffer = new BufferedWriter(writingFile);
            for (Car car : listOfCars)
            {
                writingBuffer.write(car.getCarDetails());
                writingBuffer.newLine();
            }
            try
            {
                writingBuffer.close();
                writingFile.close();
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
