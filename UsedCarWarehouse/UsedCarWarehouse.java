import java.util.*;
import java.io.*;
/**
 * Object of class UsedCarWarehouse is responsible for displaying
 * all the menus, validation of inputs.
 * It creates object of class CarList which is used to store all Car objects.
 * 
 * @author Sharat Bhat
 * @version 1.0 20/10/2017
 */

public class UsedCarWarehouse
{
    private CarList carStorage;
    private boolean exitFlag;
    private Scanner scan;

    /**
     * Constructor for objects of class UsedCarWarehouse
     */
    public UsedCarWarehouse()
    {
        carStorage = new CarList();
        exitFlag = false;
        scan = new Scanner(System.in);
    } 
    
    /**
     * This method adds car to an object of class UsedCarWarehouse.
     * <p>
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'addCar' on a class named 'CarList': {@link CarList#addCar}.
     * Link to a method named 'inputValidCarRegistrationNumber' on this class: {@link #inputValidCarRegistrationNumber}.
     * Link to a method named 'inputValidYear' on this class: {@link #inputValidYear}.
     * Link to a method named 'inputValidCarMakeOrModel' on this class: {@link #inputValidCarMakeOrModel}.
     * Link to a method named 'inputValidCarPrice' on this class: {@link #inputValidCarPrice}.
     */
    private void addCarToCarList()
    {
        Boolean carExists = false;
        System.out.println("Enter the Car Registration Number");
        String carRegistrationNumber = inputValidCarRegistrationNumber();
        if (!carStorage.carRegistrationNumberExists(carRegistrationNumber))
        {
            System.out.println("Enter the Year Made");
            int year = inputValidYear();
            System.out.println("Enter car Colour1");
            String colour1 =scan.nextLine();
            colour1 = colour1.toLowerCase().trim();
            System.out.println("Enter car Colour2");
            String colour2 = scan.nextLine();
            colour2 = colour2.toLowerCase().trim();
            while (colour1.equals(colour2) && !colour2.isEmpty())
            {
                System.out.println("Colour: " + colour1 + "already exists! Please enter another colour");
                colour2 = scan.nextLine();
                colour2 = colour2.toLowerCase().trim();
            }
            System.out.println("Enter car Colour3");
            String colour3 = scan.nextLine();
            colour3 = colour3.toLowerCase().trim();
            while ((colour1.equals(colour3) || colour2.equals(colour3)) && !colour3.isEmpty())
            {
                System.out.println("Colour: " + colour1 + "already exists! Please enter another colour");
                colour3 = scan.nextLine();
                colour3 = colour3.toLowerCase().trim();
            }
            System.out.println("Enter car Make");
            String carMake = inputValidCarMakeOrModel();
            System.out.println("Enter car Model");
            String carModel = inputValidCarMakeOrModel();
            System.out.println("Enter car Price");
            int price = inputValidCarPrice();
            carStorage.addCar(carRegistrationNumber, year, colour1, colour2, colour3, carMake, carModel, price);
        }
        else
            System.out.println("Car with the given registration already exists\n");
    }
    
    /**
     * This method is to edit colour, of a car, used in an object of class UsedCarWarehouse.
     * <p>
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'carColourExists' on a class named 'CarList': {@link CarList#carColourExists}.
     * Link to a method named 'editCarColour' on a class named 'CarList': {@link CarList#editCarColour}.
     * Link to a method named 'inputValidCarRegistrationNumber' on this class: {@link #inputValidCarRegistrationNumber}.
     * Link to a method named 'isValidMenuOption' on this class: {@link #isValidMenuOption}.
     */
    private void carColourEdit()
    {
        char colourMenuOptionLimit = '3';
        System.out.println("Enter Car Registration Number of the car whose Colour has to be edited");
        String carRegistrationNumber = inputValidCarRegistrationNumber();
        if (carStorage.carRegistrationNumberExists(carRegistrationNumber))
        {
            boolean colourDisplayFlag = true;
            while (colourDisplayFlag)
            {
                displayColourMenu();
                String inputOption = scan.nextLine();
                inputOption = inputOption.trim();
                if (isValidMenuOption(inputOption, colourMenuOptionLimit))
                {
                    char indexOption = inputOption.charAt(0);
                    System.out.println("Enter the new colour");
                    String newColour = scan.nextLine().trim();
                    while (carStorage.carColourExists(carRegistrationNumber, newColour, indexOption))
                    {
                        System.out.println("The entered colour: " + newColour + "already exists! Please enter any other colour");
                        newColour = scan.nextLine().trim();
                    }
                    carStorage.editCarColour(carRegistrationNumber, indexOption, newColour);
                    colourDisplayFlag = false;
                }
                else
                    System.out.println("You have entered an invalid Menu option! Please enter any option from 1 to 3\n");
            }  
        }
        else
            System.out.println("car doesn't exist\n");
    }
    
    /**
     * This method is used to edit the price, of a car, used in an object of a class CarList
     * <p>
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'editCarPrice' on a class named 'CarList': {@link CarList#editCarPrice}.
     * Link to a method named 'inputValidCarRegistrationNumber' on this class: {@link #inputValidCarRegistrationNumber}.
     */
    private void carPriceEdit()
    {
        System.out.println("Enter the car Registration Number");
        String carRegistrationNumber = inputValidCarRegistrationNumber();
        System.out.println("Enter new car price");
        int newCarPrice = inputValidCarPrice();
        carStorage.editCarPrice(carRegistrationNumber, newCarPrice);
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
     * This method deletes car from an object of class CarList.
     * <p>
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'deleteCar' on a class named 'CarList': {@link CarList#deleteCar}.
     * Link to a method named 'inputValidCarRegistrationNumber' on this class: {@link #inputValidCarRegistrationNumber}.
     */
    private void deleteCarFromCarList()
    {
        System.out.println("Enter the registration number of the car to be deleted");
        String carRegistrationNumber = inputValidCarRegistrationNumber();
        carStorage.deleteCar(carRegistrationNumber);
    }
    
    /**
     * This method prints the menu on the screen.
     */
    private void displayMenu()
    {
        System.out.println();
        System.out.println("--------- Main Menu -------------");
        System.out.println("(1) Search Cars");
        System.out.println("(2) Add Car");
        System.out.println("(3) Delete Car");
        System.out.println("(4) Exit System");
        System.out.println("(5) Edit Cars");
    }
    
    /**
     * This method prints the colour menu on the screen.
     */
    private void displayColourMenu()
    {
        System.out.println();
        System.out.println("------------- Colour Menu ---------------");
        System.out.println("(1) To edit Colour1");
        System.out.println("(2) To edit Colour2");
        System.out.println("(3) To edit Colour3");
    }
    
    /**
     * This method prints the edit menu on the screen.
     */
    private void displayEditMenu()
    {
       System.out.println();
       System.out.println("--------- Edit Menu ------------");
       System.out.println("(1) Edit Car Colour");
       System.out.println("(2) Edit Car Price");
       System.out.println("(3) Back to Main Menu");
    }
    
    /**
     * This method is used to edit either the colour or the price of the car.
     * <p>
     * Link to a method named 'carColourEdit' on this class: {@link #carColourEdit}.
     * Link to a method named 'carPriceEdit' on this class: {@link #carPriceEdit}.
     */
    private void editCar()
    {
        char editMenuOptionLimit = '4';
        boolean editDisplayFlag = true;
        while (editDisplayFlag)
        {
            displayEditMenu();
            String inputOption = scan.nextLine();
            inputOption = inputOption.trim();
            if (isValidMenuOption(inputOption, editMenuOptionLimit))
            {
                char option = inputOption.charAt(0);
                switch(option)
                {
                    case '1' :  carColourEdit();
                                break;
                    case '2' :  carPriceEdit();
                                break;
                    case '3' :  editDisplayFlag = false;
                                break;
                }
            }
            else
                System.out.println("You have entered an invalid Menu option! Please enter any option from 1 to 3\n");
        }
    }
    
    /**
     * This method gets a valid car age from the user.
     * <p>
     * Gets input from the user and checks if the input is a number and is only two digit.
     * If the input is invalid, asks the user for input till a valid input is entered.
     * 
     * Link to a method named 'containsOnlyNumbers' on this class: {@link #containsOnlyNumbers}.
     * 
     * @return int returns a valid number.
     */
    private int inputValidCarAge()
    {
        String carAge = scan.nextLine();
        carAge = carAge.trim();
        while (carAge.length() > 2 || !containsOnlyNumbers(carAge))
        {
            if (!containsOnlyNumbers(carAge))
                System.out.println("Age should not contain any signs or symbols, it should only be a number. Enter the age again");
            else if (carAge.trim().length() > 2)
                System.out.println("Age should not be greater than 99. Enter the age again");
            else if (Integer.parseInt(carAge) < 0)
                System.out.println("Age should not be negative. Enter the age again");
            carAge = scan.nextLine();
        }
        return Integer.parseInt(carAge);
    }
    
    /**
     * This method gets a valid car Make or Model from the user.
     * <p>
     * Gets input from the user and checks if the input is a valid car make or model.
     * A valid car make or model should not be empty and should contain only alphabets or number.
     * If the input is invalid, asks the user for input till a valid input is entered.
     * 
     * Link to a method named 'containsValidCharacters' on this class: {@link #containsValidCharacters}.
     * 
     * @return String returns a valid car make or model.
     */
    private String inputValidCarMakeOrModel()
    {
        String carMakeOrModel = scan.nextLine();
        carMakeOrModel = carMakeOrModel.trim();
        while (carMakeOrModel.isEmpty() || !containsValidCharacters(carMakeOrModel))
        {
            if (carMakeOrModel.isEmpty())
                System.out.println("Car Make or Model should not be empty! Enter Car Make or Model again");
            else if (!containsValidCharacters(carMakeOrModel))
                System.out.println("Car Make or Model contains invalid characters! Enter Car Make or Model again");
            carMakeOrModel = scan.nextLine();
        }
        return carMakeOrModel;
    }
    
    /**
     * This method gets a valid car price from the user.
     * <p>
     * Gets input from the user and checks if the input is a valid car price.
     * A valid car price should not be empty and should always be a number.
     * If the input is invalid, asks the user for input till a valid input is entered.
     * 
     * Link to a method named 'containsOnlyNumbers' on this class: {@link #containsOnlyNumbers}.
     * 
     * @return int returns a valid car price.
     */
    private int inputValidCarPrice()
    {
        String carPrice = scan.nextLine();
        carPrice = carPrice.trim();
        while (carPrice.isEmpty() || !containsOnlyNumbers(carPrice))
        {
            if (carPrice.isEmpty())
                System.out.println("Car price cannot be empty! Enter the price again");
            else if (!containsOnlyNumbers(carPrice))
                System.out.println("Invalid car price. Car price should contain only positive number. Enter the price agian");
            carPrice = scan.nextLine();
        }
        return Integer.parseInt(carPrice);
    }
    
    /**
     * This method gets a valid car registration number from the user.
     * <p>
     * Gets input from the user and checks if the input is a valid car registration number or not.
     * Car registration number should not be empty and should contain maximum of 6 characters, and alphabets or numbers.
     * If the input is invalid, asks the user for input till a valid input is entered.
     * 
     * Link to a method named 'containsValidCharacters' on this class: {@link #containsValidCharacters}.
     *      
     * @return String returns a valid car registration number.
     */
    private String inputValidCarRegistrationNumber()
    {
        boolean validCarRegistrationNumber = false;
        String carRegistrationNumber = scan.nextLine();
        carRegistrationNumber = carRegistrationNumber.trim();
        while (carRegistrationNumber.isEmpty() || !validCarRegistrationNumber)
        {
            carRegistrationNumber = carRegistrationNumber;
            int carRegistrationNumberLength = carRegistrationNumber.length();
            if (carRegistrationNumberLength <= 6 && containsValidCharacters(carRegistrationNumber))
                validCarRegistrationNumber = true;
            else
            {
                validCarRegistrationNumber = false;
                System.out.println("You have entered an invalid car registration number! Please enter a car registration number again");
                carRegistrationNumber = scan.nextLine();
            }
        }
        return carRegistrationNumber;
    }
    
    /**
     * This method gets a valid year from the user.
     * <p>
     * Gets input from the user and checks if the input is a valid year or not.
     * A valid year should not be empty and should contain 4 digits, and should be greater than or equal to 1900.
     * If the input is invalid, asks the user for input till a valid input is entered.
     * 
     * Link to a method named 'containsOnlyNumbers' on this class: {@link #containsOnlyNumbers}.
     *      
     * @return int returns a valid year.
     */
    private int inputValidYear()
    {
        String year = scan.nextLine();
        year = year.trim();
        while (!(year.length() == 4 && Integer.parseInt(year) <= Calendar.getInstance().get(Calendar.YEAR) && containsOnlyNumbers(year) &&
                Integer.parseInt(year) >= 1900))
        {
            System.out.println("Enter a valid year");
            year = scan.nextLine();
        }
        return Integer.parseInt(year);
    }
    
    /**
     * This method checks if the menu option is valid.
     * 
     * @param option menu option entered by th user.
     * @param optionLimit maximum limit of the menu options.
     * @return boolean returns true if the option is valid else returns false.
     */
    private boolean isValidMenuOption(String option, char optionLimit)
    {
        option = option.trim();
        if (option.length() == 1 && option.charAt(0) >= '1' && option.charAt(0) <= optionLimit)
            return true;
        else 
            return false;
    }
    
    /**
     * This method searches the car by age.
     * <p>
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'searchCarByAge' on a class named 'CarList': {@link CarList#searchCarByAge}.
     * Link to a method named 'inputValidCarAge' on this class: {@link #inputValidCarAge}.
     */
    private void searchByCarAge()
    {
        System.out.println("Enter Car Age.Car age should be a non negative number");
        int carAge = inputValidCarAge();
        carStorage.searchCarByAge(carAge);
    }
    
    /**
     * This method searches the car by car make and model.
     * <p>
     * This method searches for the car make first. Then it searches for the car model.
     * If the car model entered is 'ANY', it searches for all the car models under the given car make.
     * 
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'searchCarByMakeAndModel' on a class named 'CarList': {@link CarList#searchCarByMakeAndModel}.
     * Link to a method named 'inputValidCarMakeOrModel' on this class: {@link #inputValidCarMakeOrModel}.
     */
    private void searchByCarMakeAndModel()
    {
        System.out.println("Enter Car Make");
        String carMake = inputValidCarMakeOrModel();
        System.out.println("Enter Car Model");
        String carModel = inputValidCarMakeOrModel();
        carStorage.searchCarByMakeAndModel(carMake, carModel);
    }
    
    /**
     * This method searches the car by car price.
     * <p>
     * This method takes minimum car price and maximum car price as input.
     * And searches for all the cars whose price falls under that price range.
     * 
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'searchCarByPrice' on a class named 'CarList': {@link CarList#searchCarByPrice}.
     * Link to a method named 'inputValidCarPrice' on this class: {@link #inputValidCarPrice}.
     */
    private void searchByCarPrice()
    {
        System.out.println("Enter minimum Car Price");
        int minimumPrice = inputValidCarPrice();
        System.out.println("Enter maximum Car Price");
        int maximumPrice = inputValidCarPrice();
        while (maximumPrice < minimumPrice)
        {
            System.out.println("Maximum Price should be greater than or equal to the Minimum Price. Enter Maximum Price again");
            maximumPrice = inputValidCarPrice();
        }
        carStorage.searchCarByPrice(minimumPrice, maximumPrice);
    }
    
    /**
     * This method searches the car by car registration number.
     * <p>
     * This method takes car registration  as input.
     * And searches for the car with given car registration number.
     * 
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'searchCarByRegistrationNumber' on a class named 'CarList': {@link CarList#searchCarByRegistrationNumber}.
     * Link to a method named 'inputValidCarRegistrationNumber' on this class: {@link #inputValidCarRegistrationNumber}.
     */
    private void searchByCarRegistrationNumber()
    {
        System.out.println("Enter the car Registration Number");
        String carRegistrationNumber = inputValidCarRegistrationNumber();
        carStorage.searchCarByRegistrationNumber(carRegistrationNumber);
    }
    
    /**
     * This method is responsible for searching cars.
     * 
     * Link to a method named 'isValidMenuOption' on this class: {@link #isValidMenuOption}.
     * Link to a method named 'searchByCarRegistrationNumber' on this class: {@link #searchByCarRegistrationNumber}.
     * Link to a method named 'searchByCarMakeAndModel' on this class: {@link #searchByCarMakeAndModel}.
     * Link to a method named 'searchByCarAge' on this class: {@link #searchByCarAge}.
     * Link to a method named 'searchByCarPrice' on this class: {@link #searchByCarPrice}.
     */
    private void searchCar()
    {
        char searchMenuOptionLimit = '5';
        boolean searchDisplayFlag = true;
        while (searchDisplayFlag)
        {
            searchDisplay();
            String inputOption = scan.nextLine();
            inputOption = inputOption.trim();
            if (isValidMenuOption(inputOption, searchMenuOptionLimit))
            {
                char option = inputOption.charAt(0);
                switch(option)
                {
                    case '1' :  searchByCarRegistrationNumber();
                                break;
                    case '2' :  searchByCarMakeAndModel();
                                break;
                    case '3' :  searchByCarAge();
                                break;
                    case '4' :  searchByCarPrice();
                                break;
                    case '5' :  searchDisplayFlag = false;
                                break;
                }
            }
            else
                System.out.println("You have entered an invalid Menu option! Please enter any option from 1 to 5");
        }
    }
    
    /**
     * This method prints the search menu.
     */
    private void searchDisplay()
    {
        System.out.println();
        System.out.println("------------ Search Menu ----------------");
        System.out.println("(1) By Registration Number");
        System.out.println("(2) By Car Make and Car Model");
        System.out.println("(3) By Age");
        System.out.println("(4) By Price (range)");
        System.out.println("(5) Back to Main Menu");
    }
    
    /**
     * This is the starting method of the program.
     * 
     * Link to a class named 'CarList': {@link CarList}.
     * Link to a method named 'readCarsFromFile' on a class named 'CarList': {@link CarList#readCarsFromFile}.
     * Link to a method named 'writeCarsToFile' on a class named 'CarList': {@link CarList#writeCarsToFile}.
     * Link to a method named 'isValidMenuOption' on this class: {@link #isValidMenuOption}.
     * Link to a method named 'searchCar' on this class: {@link #searchCar}.
     * Link to a method named 'addCarToCarList' on this class: {@link #addCarToCarList}.
     * Link to a method named 'deleteCarFromCarList' on this class: {@link #deleteCarFromCarList}.
     * Link to a method named 'editCar' on this class: {@link #editCar}.
     */
    public void startWarehouse()
    {
        char startMenuOptionLimit = '5';
        carStorage.readCarsFromFile();
        while (!exitFlag)
        {
            displayMenu();
            String inputOption = scan.nextLine();
            inputOption = inputOption.trim();
            if (isValidMenuOption(inputOption, startMenuOptionLimit))
            {
                char option = inputOption.charAt(0); 
                switch (option)
                {
                    case '1' :  searchCar();
                                break;
                    case '2' :  addCarToCarList();
                                break;
                    case '3' :  deleteCarFromCarList();
                                break;
                    case '4' :  carStorage.writeCarsToFile();
                                exitFlag = true;
                                break;
                    case '5' :  editCar();
                                break;
                }
            }
            else
                System.out.println("You have entered an invalid menu option. Please entere a number from 1 to 5");
        }
    }
}
