package com.mimmimji.jsonformatter.examples;

import com.mimmimji.jsonformatter.JsonFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonFormatterEx1 {
    public static void main(String[] args) {
        JSONObject surveyObject = new JSONObject();

        // 기본 필드 추가
        surveyObject.put("surveyName", "Customer Satisfaction Survey");
        surveyObject.put("surveyDesc", "A survey to gather customer feedback on our services");

        // "questions" 배열 생성
        JSONArray questionsArray = new JSONArray();

        // 첫 번째 질문 추가
        JSONObject question1 = new JSONObject();
        question1.put("questionName", "How satisfied are you with our service?");
        question1.put("questionDesc", "Please rate your satisfaction from 1 to 5");
        question1.put("questionType", "SHORT_ANSWER");
        question1.put("isRequired", true);
        question1.put("options", new JSONArray()); // 빈 options 배열
        questionsArray.put(question1);

        // 두 번째 질문 추가
        JSONObject question2 = new JSONObject();
        question2.put("questionName", "What additional services would you like us to offer?");
        question2.put("questionDesc", "Please share your suggestions");
        question2.put("questionType", "LONG_ANSWER");
        question2.put("isRequired", false);
        question2.put("options", new JSONArray()); // 빈 options 배열
        questionsArray.put(question2);

        // 세 번째 질문 추가
        JSONObject question3 = new JSONObject();
        question3.put("questionName", "How satisfied are you with our service?");
        question3.put("questionDesc", "Rate your satisfaction from 1 to 5");
        question3.put("questionType", "SINGLE_CHOICE");
        question3.put("isRequired", true);

        // options 배열 추가
        JSONArray optionsArray = new JSONArray();
        for (int i = 1; i <= 5; i++) {
            JSONObject option = new JSONObject();
            option.put("optionValue", String.valueOf(i));
            optionsArray.put(option);
        }
        question3.put("options", optionsArray);

        // 세 번째 질문을 questions 배열에 추가
        questionsArray.put(question3);

        // "questions" 배열을 최상위 객체에 추가
        surveyObject.put("questions", questionsArray);

        // 결과 출력 (Pretty Print)
        System.out.println(JsonFormatter.prettyPrint(surveyObject.toString()));
        System.out.println(JsonFormatter.minify(surveyObject.toString()));
        System.out.println(JsonFormatter.sortByKeys(surveyObject.toString()));
        System.out.println(JsonFormatter.validate(surveyObject.toString()));
        System.out.println(JsonFormatter.syntaxHighlight(surveyObject.toString()));
    }
}
