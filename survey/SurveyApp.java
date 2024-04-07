import java.util.Scanner;
interface Database {
    void saveResponse(String response);
}
class SQLDatabase implements Database {
    @Override
    public void saveResponse(String response) {
        System.out.println("Response saved to SQL database: " + response);
    }
}

class NoSQLDatabase implements Database {
    @Override
    public void saveResponse(String response) {
        System.out.println("Response saved to NoSQL database: " + response);
    }
}
class Survey {
    private String title;
    private String[] questions;
    private Database database;

    public Survey(String title, String[] questions, Database database) {
        this.title = title;
        this.questions = questions;
        this.database = database;
    }

    public void conductSurvey() {
        System.out.println("Welcome to the survey: " + title);
        Scanner scanner = new Scanner(System.in);
        try {
            for (int i = 0; i < questions.length; i++) {
                System.out.println("Q" + (i + 1) + ". " + questions[i]);
                System.out.print("Enter your response: ");
                String response = scanner.nextLine();
                database.saveResponse(response);
            }
        } finally {
            scanner.close(); 
        }
        System.out.println("Thank you for participating in the survey!");
    }
}
public class SurveyApp {
    public static void main(String[] args) {
        Database database = new SQLDatabase(); 
        String title = "Customer Satisfaction Survey";
        String[] questions = {"How satisfied are you with our product/service?",
                              "What improvements would you like to see?",
                              "Would you recommend our product/service to others?"};
        Survey survey = new Survey(title, questions, database);
        survey.conductSurvey();
    }
}
