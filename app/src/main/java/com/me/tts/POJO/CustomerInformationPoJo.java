package com.me.tts.POJO;

public class CustomerInformationPoJo {

    String question;
    int id;
    String date;
    String time;
    String category;


    public CustomerInformationPoJo(String question,int id,String date,String time,String category)
    {
        this.category=category;
        this.question=question;
        this.date=date;
        this.id=id;
        this.time=time;
    }


    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getTime() {
        if(time.charAt(6)=='A')
        {
            return time.substring(0,time.length()-2);
        }
        String t=time.substring(0,2);
        int hours=Integer.parseInt(t);
        hours+=12;

        time= hours+time.substring(2);
        return time.substring(0,time.length()-2);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
