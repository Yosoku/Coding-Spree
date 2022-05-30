package com.rocket.src.viewcomponents;

import com.rocket.src.R;

public class ResultData {
    private final String[] levels = {"Garbage", "Intern", "Junior", "Senior"};
    private final String[] summaries = {"A garbage can is widely used for waste disposal. They function similarly in software engineering. They usually can't hold an engineering job for over 2 months  because of the knowledge gap between them and the worst intern.  Don't get discouraged though,it's called garbage-can,not garbage-cannot. You can do better. Keep working.",

            "An engineering intern is a student or recent graduate of an engineering program who is gaining hands-on experience in their field through an internship program. Interns in most fields, including engineering, are paid very little and sometimes work only for academic credit.",

            "Junior software engineers are entry-level members of a software development team. They assist the team with basic tasks under the supervision of their seniors,such as learning base code and writing simple code, and debugging existing software.",

            "Senior Software Engineer will develop information systems by studying operations; designing, developing and installing software solutions; support and develop software team. The Senior Software Engineer will lead a team of developers responsible for building new and support existing websites."};
    private final String[] greetings = {"Oh no", "Keep working", "Good job",
            "Congratulations"};
    public String LEVEL; // garbage,intern,junior,senior
    public String SUMMARY; // summary about level
    public String GREETING; // good job,congrats,keep working,oh no
    public String NAME;
    public int IMAGEID;
    public float PERCENTILE;


    private ResultData(int i) {
        LEVEL = levels[i];
        GREETING = greetings[i];
        SUMMARY = summaries[i];
        switch (i) {
            case 0:
                IMAGEID = R.drawable.ollie;
                break;
            case 1:
                IMAGEID = R.drawable.angela;
                break;
            case 2:
                IMAGEID = R.drawable.tyrell;
                break;
            case 3:
                IMAGEID = R.drawable.elliot;
                break;
        }
    }

    public static ResultData FromPercentile(float percentile) {
        if (percentile < 0.25) return new ResultData(0);
        if (percentile < 0.5) return new ResultData(1);
        if (percentile < 0.75) return new ResultData(2);
        return new ResultData(3);
    }
}
