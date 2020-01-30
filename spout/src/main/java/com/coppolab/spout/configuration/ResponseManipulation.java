package com.coppolab.spout.configuration;




import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseManipulation {

    public Map<String,String> manipulateRepsonse(String response){

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

                int myIndex = tmp[i].indexOf("}");
                tmp[i] = tmp[i].substring(0,myIndex)+""+tmp[i].substring(myIndex+1);
                //metrics.add(0,tmp[i]);
                metrics.add(tmp[i]);

            }
            for(String m: metrics){
                System.out.println(m);
            }
            JSONParser parser = new JSONParser();
            //JSONObject json = (JSONObject) parser.parse(stringToParse);
            Map<String,String> metricsMap = new HashMap<>();
            for (int j=0;j<metrics.size();j++){
                JSONObject json = (JSONObject) parser.parse(metrics.get(j));
                String time = json.get("time").toString();
                metricsMap.put(time,metrics.get(j));
            }



            return metricsMap;

        }catch(IndexOutOfBoundsException | ParseException e){
            System.out.println("No metrics available yet");
        }
        return null;
    }

}
