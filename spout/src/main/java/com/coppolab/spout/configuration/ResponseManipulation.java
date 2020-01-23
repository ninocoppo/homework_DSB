package com.coppolab.spout.configuration;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ResponseManipulation {

    public ArrayList<String> manipulateRepsonse(String response){

        ArrayList<String> metrics = new ArrayList<>();

        //System.out.println("Response: "+response);

        String multi[] = response.split("]>"); //Divide le metrics duplicate
        String splitted = multi[0];


        //Trasforma la stringa in una stringa fromato json
        String first = splitted.substring(splitted.indexOf("{"));
        int index1 = first.indexOf(",[Cont");
        String finalString = first.substring(0, index1).replace(",{", "");


        //Splitta ogni singola richiesta http
        String finale = finalString.substring(0, finalString.indexOf("]}}"));
        try {
            String tmp1[] = finale.split(":\\[" + "\\{"); //Fa lo split di ":[{"
            String tmp[] = tmp1[1].split("\"metric\"+:");


            for(int i = 1; i < tmp.length; i++){
                metrics.add(tmp[i]);
            }
            for(String m: metrics){
                System.out.println(m);
            }
            return metrics;

        }catch(IndexOutOfBoundsException e){
            System.out.println("No metrics available yet");
        }
        return metrics;
    }

}
