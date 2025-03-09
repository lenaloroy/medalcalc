import java.util.Scanner;

class Olympics{

    public static void main (String [] a)
    {
        store(); //Change this to a call to the method doing the work
        return;
    } // END main

    public static String input(Scanner scanner, String message){
        System.out.print(message);
        return scanner.nextLine();
    }

    // Method to check if the input is numeric
    public static boolean isNumeric(String str) {
        boolean number = false;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c >= '0' && c <= '9'){
                number = true;
            } else {
                return false;
            }
        }
        return number;
    }
    
    //method to get the name of the country
    public static String name(Scanner scanner){
        String country = input(scanner, "What is the name of the country?");
        return country;
    }
    //method to get the number of competitors with validation
    public static int competitors(Scanner scanner){
        String competitors = "";
        int no_competitors = -1;
            
        while (no_competitors < 0) {
            competitors = input(scanner, "How many athletes competed for them (>0)?");
    
            while (!isNumeric(competitors)) { //checks if integer
                competitors = input(scanner, "How many athletes competed for them (>0)?");
            }
            
            no_competitors = Integer.parseInt(competitors);
        }
        return no_competitors;
    }
    //method to get the number of gold medals with validation
    public static int gold_medals(Scanner scanner, int competitors){
        String gold_medals = "";
        int no_gold_medals = -1;
    
        while (no_gold_medals < 0 || no_gold_medals > (competitors*20)) { //ensures that the number of medals is sensible
            gold_medals = input(scanner, "How many gold medals did they win?");
    
            while (!isNumeric(gold_medals)) { //checks if an integer
                gold_medals = input(scanner, "How many gold medals did they win?");
            }
            no_gold_medals = Integer.parseInt(gold_medals);
        }
        return no_gold_medals;
    }

    
    //creating a space for a countries data to be stored
    public static Country createCountry(String name){
        Country country = new Country();
        country.name = name;
        country.competitors = 0;
        country.gold_medals = 0;
        country.gold_index = 0.0;
        return country;
    }

    //set the values for competitor and gold medals.
    //use value to calculate gold index
    //set gold index
    public static Country setGoldIndex(Country country, int competitors, int gold_medals){
        country.competitors = competitors;
        country.gold_medals = gold_medals;
        country.gold_index = (double) gold_medals/competitors; //allows for calculating double
        return country;
    }

    //use data in record to give the country's score
    public static String convertRecord(Country country){
        double index = Math.round(country.gold_index * 1000) / 1000.0; //for 3 dp x1000 round to nearest int then /1000
        return country.name + " has a gold medal index of " + index;
    }

    // allows user to choose yes or no
    public static String choice(Scanner scanner){
        String choice = "o";
        while (!choice.equals("n") && !choice.equals("y")) { //doesn't let other inputs
            choice = input(scanner, "Another (y/n)?");
        }
        return choice;
    } 

    //run program
    public static void store(){
        Scanner scanner = new Scanner(System.in);
        String choice = "y";

        while(choice.equals("y")){  // runs until the choice is not y
            String name = name(scanner);
            Country new_country = createCountry(name);
            int competitors = competitors(scanner);
            int gold_medals = gold_medals(scanner, competitors);
            setGoldIndex(new_country, competitors, gold_medals);
            System.out.println(convertRecord(new_country));
            choice = choice(scanner);
        }
    }
}