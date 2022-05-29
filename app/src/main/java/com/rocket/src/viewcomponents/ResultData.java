package com.rocket.src.viewcomponents;

public class ResultData {
    public String LEVEL; // garbage,intern,junior,senior
    public String SUMMARY; // summary about level
    public String GREETING; // good job,congrats,keep working,oh no
    public String NAME;
    public float PERCENTILE;
    private String[] levels = {"Garbage","Intern","Junior","Senior"};
    private String[] summaries = {"Garbage summary youre trash","Intern " +
            "fdfsadfsasf",
            "Junior sandwich","Senior pls sensei lala"};
    private String[] greetings = {"Oh no","Keep working", "Good job",
            "Congratulations"};


    private ResultData(int i){
        LEVEL = levels[i];
        GREETING = greetings[i];
        SUMMARY = summaries[i];
    }

    public static ResultData FromPercentile(float percentile){
        if(percentile<0.25) return new ResultData(0);
        if(percentile<0.5) return new ResultData(1);
        if(percentile<0.75) return new ResultData(2);
        return new ResultData(3);
    }
}
