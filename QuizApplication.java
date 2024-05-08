import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOption;

    public QuizQuestion(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

public class QuizApplication {
    private static List<QuizQuestion> quizQuestions;
    private static int currentQuestionIndex;
    private static int score;
    private static Timer timer;

    public static void main(String[] args) {
        initializeQuiz();

        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : quizQuestions) {
            displayQuestion(question);

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up!");
                    submitAnswer(-1);
                }
            }, 10000
            ); // 10 seconds

            int selectedOption = scanner.nextInt();
            timer.cancel(); // Cancel the timer as the answer has been submitted
            submitAnswer(selectedOption);
        }

        displayResult();
    }

    private static void initializeQuiz() {
        quizQuestions = new ArrayList<>();
        quizQuestions.add(new QuizQuestion("What is the capital of France?",
                List.of("London", "Paris", "Berlin", "Madrid"), 1));
        quizQuestions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                List.of("Earth", "Mars", "Venus", "Mercury"), 1));
        // Add more questions here
        currentQuestionIndex = 0;
        score = 0;
    }

    private static void displayQuestion(QuizQuestion question) {
        System.out.println(question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.print("Your answer (1-" + options.size() + "): ");
    }

    private static void submitAnswer(int selectedOption) {
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);
        if (selectedOption == currentQuestion.getCorrectOption()) {
            System.out.println("Correct!");
            score++;
        } else if (selectedOption == -1) {
            System.out.println("No answer submitted.");
        } else {
            System.out.println("Incorrect!");
        }
        currentQuestionIndex++;
    }

    private static void displayResult() {
        System.out.println("Quiz finished!");
        System.out.println("Your score: " + score + "/" + quizQuestions.size());
    }
}
