package io.dudek.florystyka.controller;

import io.dudek.florystyka.dao.QuestionDAO;
import io.dudek.florystyka.model.Question;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Controller
public class PdfController {

    @Autowired
    private QuestionDAO questDAO;


    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public String genPDFContent(Model model) {
        List<Question> questions = new ArrayList<>();
        Random random = new Random();
        int max = questDAO.getMaxId();
        int[] qid = new int[40];
        int[] exclude = new int[18];
        exclude[0] = 117;
        exclude[1] = 229;
        exclude[2] = 241;
        exclude[3] = 259;
        exclude[4] = 260;
        exclude[5] = 261;
        exclude[6] = 266;
        exclude[7] = 267;
        exclude[8] = 275;
        exclude[9] = 277;
        exclude[10] = 293;
        exclude[11] = 310;
        exclude[12] = 353;
        exclude[13] = 474;
        exclude[14] = 480;
        exclude[15] = 483;
        exclude[16] = 499;
        exclude[17] = 543;
        int z = 0;
        int tmp;
        boolean isOK = true;
        while (z < 40) {
            isOK = true;
            tmp = random.nextInt(max) + 1;
            for (int y = 0; y < z; y++) {
                if (tmp == qid[y]) {
                    isOK = false;
                    break;
                }
            }
            for (int y = 0; y < 18; y++) {
                if (tmp == exclude[y]) {
                    isOK = false;
                    break;
                }
            }
            if (isOK == true) {
                qid[z] = tmp;
                z++;
            }
        }
        questions = questDAO.generateQuestionsList(qid);
        // questions.add(questDAO.findQuestionById(269));
        JSONArray pdfContent = new JSONArray();

        for (int i = 0; i < questions.size(); i++) {
            pdfContent.add(generateQuestion(questions.get(i), i + 1));
        }
        ;

        pdfContent.add(generateAnswersTable(questions));
        model.addAttribute("pdfContent", pdfContent);
        return "pdf";
    }


    //Replace html tags for PDFMake format
    private JSONArray switchHTMLTags(String  content) {

        if(content.contains("<table>")) {
            content = content.replaceAll("<table>", "\u00E0").replaceAll("</table>", "\u00E1");
            String tmp = content.substring(0, content.indexOf("\u00E0"));
            if(content.indexOf("\u00E1") < content.length()-1) {
                content = tmp + content.substring(content.indexOf("\u00E1") +1);
            } else {
                content = tmp;
            }
        }

        JSONArray contentForPDF = new JSONArray();
        if (content.contains("<i>")) {

            final String ITALIC_START = "\u00EC";
            final String ITALIC_END = "\u00ED";


            String tmpContent = content.replaceAll("<i>", ITALIC_START).replaceAll("</i>", ITALIC_END);


            int italicStartIndex = tmpContent.indexOf(ITALIC_START);
            int italicEndIndex = tmpContent.indexOf(ITALIC_END);

            JSONObject tagged;

            while (tmpContent.indexOf(ITALIC_START) >= 0) {

                if (italicStartIndex > 0) {
                    contentForPDF.add(tmpContent.substring(0, italicStartIndex));
                }

                tagged = new JSONObject();
                tagged.put("text", tmpContent.substring((italicStartIndex + 1), (italicEndIndex)));
                tagged.put("italics", true);
                contentForPDF.add(tagged);
                tmpContent = tmpContent.substring(italicEndIndex + 1);
                italicStartIndex = tmpContent.indexOf(ITALIC_START);
                italicEndIndex = tmpContent.indexOf(ITALIC_END);
            }

            if (tmpContent.length() > 0) {
                contentForPDF.add(tmpContent);
            }

        } else {
            contentForPDF.add(content);
        }

        return contentForPDF;
    }



    private JSONObject generateQuestion(Question question, int number) {

        JSONObject pdf = new JSONObject();

        JSONObject quest = new JSONObject();
        quest.put("style", "table");
        quest.put("layout", "noBorders");

        JSONObject table = new JSONObject();
        table.put("headerRows", 1);
        table.put("keepWithHeaderRows", 1);

        JSONArray body = new JSONArray();

        JSONArray questBody = new JSONArray();
        JSONArray questContentBody = new JSONArray();

        JSONObject questContent = new JSONObject();
        String content = number + ". " + question.getContent();
        content = content.replaceAll("<br />", "\n");
        if(!content.contains("colspan")) {
            content = content.replaceAll("<table>", "");
            content = content.replaceAll("</table>", "");
            content = content.replaceAll("<tr><td>", "");
            content = content.replaceAll("</td></tr>", "\n");
            content = content.replaceAll("</td><td>", "\t");

        }

        if (content.contains("<i>") || content.contains("<table>")) {
            questContent.put("text", switchHTMLTags(content));
        } else {
            questContent.put("text", content);
        }

        questContent.put("style", "content");
        questContentBody.add(questContent);

        JSONObject answers = new JSONObject();

        String answersString = "A. " + question.getAnswerContent(0) + "\n" +
                "B. " + question.getAnswerContent(1) + "\n" +
                "C. " + question.getAnswerContent(2) + "\n" +
                "D. " + question.getAnswerContent(3) + "\n";

        if (answersString.contains("<i>") || answersString.contains("<b>") || answersString.contains("<u>")) {
            answers.put("text", switchHTMLTags(answersString));

        } else {
            answers.put("text", answersString);
        }



        answers.put("style", "answer");
        questBody.add(answers);
        body.add(questContentBody);

        //adding table





        body.add(questBody);

        table.put("body", body);

        pdf.put("table", table);
        pdf.put("style", "table");
        pdf.put("layout", "noBorders");

        return pdf;
    }

    private Map<String, String> getImgMap() {
        Map<String, String> images = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File f = new File(classLoader.getResource("resources/images.b64").getFile());
        String[] line;
        String tmp;
        try {
            Scanner file = new Scanner(f);
            while (file.hasNext()) {
                tmp = file.next();
                line = tmp.split(";");
                if (line.length > 1) {
                    images.put(line[0], line[1]);
                }

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return images;
    }

    private Map<String, String> getTableMap() {
        Map<String, String> images = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File f = new File(classLoader.getResource("resources/tables.b64").getFile());
        String[] line;
        String tmp;
        try {
            Scanner file = new Scanner(f);
            while (file.hasNext()) {
                tmp = file.next();
                line = tmp.split(";");
                if (line.length > 1) {
                    images.put(line[0], line[1]);
                }

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return images;
    }

    private JSONArray generateAnswersTable(List<Question> questions) {
        JSONArray result = new JSONArray();
        JSONObject header = new JSONObject();
        header.put("text", "Klucz poprawnych odpowiedzi");
        header.put("fontSize", 14);
        JSONArray margin = new JSONArray();
        margin.add(0);
        margin.add(20);
        header.put("margin", margin);
        header.put("alignment", "center");
        header.put("bold", true);
        header.put("pageBreak", "before");
        JSONObject answers = new JSONObject();
        answers.put("style", "answerKey");
        JSONObject table = new JSONObject();
        table.put("headerRows", 1);
        table.put("keepWithHeaderRows", 1);
        JSONArray body = new JSONArray();
        JSONArray firstRow = new JSONArray();
        JSONObject questNumber = new JSONObject();
        questNumber.put("text", "Pytanie");
        questNumber.put("style", "singleKey");
        questNumber.put("bold", true);
        JSONObject correctAnswer = new JSONObject();
        correctAnswer.put("text", "Odpowiedź");
        correctAnswer.put("style", "singleKey");
        correctAnswer.put("bold", true);

        firstRow.add(questNumber);
        firstRow.add(correctAnswer);
        firstRow.add(questNumber);
        firstRow.add(correctAnswer);

        body.add(firstRow);

        for (int i = 0; i < 20; i++) {
            JSONArray row = new JSONArray();
            JSONObject q1 = new JSONObject();
            q1.put("text", i + 1);
            q1.put("style", "singleKey");
            q1.put("bold", true);
            JSONObject a1 = new JSONObject();
            a1.put("text", questions.get(i).getCorrect().toString());
            a1.put("style", "singleKey");

            JSONObject q2 = new JSONObject();
            q2.put("text", i + 21);
            q2.put("style", "singleKey");
            q2.put("bold", true);
            JSONObject a2 = new JSONObject();
            a2.put("text", questions.get(i + 20).getCorrect().toString());
            a2.put("style", "singleKey");

            row.add(q1);
            row.add(a1);
            row.add(q2);
            row.add(a2);
            body.add(row);

        }

        table.put("body", body);
        answers.put("table", table);

        result.add(header);
        result.add(answers);

        return result;
    }
}
