package dev.mazurkiewicz.florystyka.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by md on 6/5/17.
 */
public class QuestionDrawer {

    public static Set<Integer> drawQuestions(Integer max, Integer count) {
        Random random = new Random();
        Set<Integer> ids = new HashSet<>();
        while(ids.size() != count) {
            ids.add(random.nextInt(max) + 1);
        }

        return ids;
    }

}
