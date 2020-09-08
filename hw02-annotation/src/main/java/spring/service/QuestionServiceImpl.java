package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.dao.QuestionDao;
import spring.domain.Question;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    private final Scanner scanner;

    @Override
    public List<Question> getAll() throws IOException {
        return dao.getAllQuestion();
    }

    @Override
    public void start() throws IOException {
        int success = 0, fail = 0;
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your surname: ");
        String surname = scanner.nextLine();
        System.out.println("Answer the questions: ");
        List<Question> questionList = getAll();
        for (Question question : questionList) {
            System.out.println(question.getText());
            if (scanner.nextLine().equals(question.getAnswer())) {
                success++;
            } else {
                fail++;
            }
        }
        System.out.printf("User %s %s finished testing with the result: %s%n", name, surname, getResultTesting(success, fail));
    }

    private String getResultTesting(int success, int fail) {
        if (success > fail) {
            return "Success";
        }
        return "Fail";
    }
}
