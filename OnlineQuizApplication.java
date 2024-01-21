import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswer;

    public Question(String questionText, List<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private List<Question> questions;
    private int userScore;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.userScore = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();

            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            int userAnswer;
            do {
                System.out.print("Enter your answer (1-" + options.size() + "): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // consume the invalid input
                }
                userAnswer = scanner.nextInt();
            } while (userAnswer < 1 || userAnswer > options.size());

            if (userAnswer == question.getCorrectAnswer()) {
                System.out.println("Correct!\n");
                userScore++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getCorrectAnswer() + "\n");
            }
        }

        double percentage = ((double) userScore / questions.size()) * 100;
        System.out.println("Quiz complete. Your score: " + userScore + "/" + questions.size());
        System.out.println("Your score percentage: " + percentage + "%");

        scanner.close();
    }
}

class OnlineQuizApplication {
    public static void main(String[] args) {
        // Create questions
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", List.of("Paris", "Berlin", "London"), 1));
        questions.add(new Question("Which planet is known as the Red Planet?", List.of("Venus", "Mars", "Jupiter"), 2));
        questions.add(new Question("What is the largest mammal?", List.of("Elephant", "Blue Whale", "Giraffe"), 2));
        questions.add(new Question("In which year did World War II end?", List.of("1943", "1945", "1947"), 2));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", List.of("William Shakespeare", "Charles Dickens", "Jane Austen"), 1));
        questions.add(new Question("What is the currency of Japan?", List.of("Yuan", "Yen", "Won"), 2));
        questions.add(new Question("Which country is known as the 'Land of the Rising Sun'?", List.of("China", "Japan", "South Korea"), 2));
        questions.add(new Question("What is the largest ocean on Earth?", List.of("Atlantic Ocean", "Indian Ocean", "Pacific Ocean"), 3));
        questions.add(new Question("Who developed the theory of relativity?", List.of("Isaac Newton", "Albert Einstein", "Galileo Galilei"), 2));
        questions.add(new Question("What is the chemical symbol for gold?", List.of("Au", "Ag", "Cu"), 1));

        // Shuffle the questions for variety
        Collections.shuffle(questions);

        // Create quiz
        Quiz quiz = new Quiz(questions);

        // Start the quiz
        quiz.startQuiz();
    }
}
