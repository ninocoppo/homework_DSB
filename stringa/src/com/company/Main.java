package com.company;


import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        String old = "<200,{\"status\":\"success\",\"data\":{\"resultType\":\"vector\",\"result\":[]}},[Content-Type:\"application/json\", Date:\"Sun, 19 Jan 2020 09:18:41 GMT\", Content-Length:\"63\"]>";


        String arrivo = "(1,<200,{\"status\":\"success\",\"data\":{\"resultType\":\"vector\",\"result\":[{\"metric\":{\"Routed_uri\":\"null/record/check/4\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Accepted\",\"response\":\"202 ACCEPTED\"},\"value\":[1579100617.399,\"0.450710869\"]},{\"metric\":{\"Routed_uri\":\"null/record/put\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579100617.399,\"0.332547022\"]},{\"metric\":{\"Routed_uri\":\"null/record/put\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Unauthorized\",\"response\":\"401 UNAUTHORIZED\"},\"value\":[1579100617.399,\"0.526352693\"]},{\"metric\":{\"Routed_uri\":\"null/user/register\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579100617.399,\"0\"]}]}},[Content-Type:\"application/json\", Date:\"Wed, 15 Jan 2020 15:03:37 GMT\", Content-Length:\"843\"]>)\n" +
                "(1,<200,{\"status\":\"success\",\"data\":{\"resultType\":\"vector\",\"result\":[{\"metric\":{\"Routed_uri\":\"null/record/check/4\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Accepted\",\"response\":\"202 ACCEPTED\"},\"value\":[1579100627.407,\"0.450710869\"]},{\"metric\":{\"Routed_uri\":\"null/record/put\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579100627.407,\"0.332547022\"]},{\"metric\":{\"Routed_uri\":\"null/record/put\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Unauthorized\",\"response\":\"401 UNAUTHORIZED\"},\"value\":[1579100627.407,\"0.526352693\"]},{\"metric\":{\"Routed_uri\":\"null/user/register\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579100627.407,\"0\"]}]}},[Content-Type:\"application/json\", Date:\"Wed, 15 Jan 2020 15:03:47 GMT\", Content-Length:\"843\"]>)\n" +
                "(1,<200,{\"status\":\"success\",\"data\":{\"resultType\":\"vector\",\"result\":[{\"metric\":{\"Routed_uri\":\"null/record/check/4\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Accepted\",\"response\":\"202 ACCEPTED\"},\"value\":[1579100637.412,\"0.450710869\"]},{\"metric\":{\"Routed_uri\":\"null/record/put\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579100637.412,\"0\"]},{\"metric\":{\"Routed_uri\":\"null/record/put\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Unauthorized\",\"response\":\"401 UNAUTHORIZED\"},\"value\":[1579100637.412,\"0\"]},{\"metric\":{\"Routed_uri\":\"null/user/register\",\"http_method\":\"POST\",\"instance\":\"apigateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579100637.412,\"0\"]}]}},[Content-Type:\"application/json\", Date:\"Wed, 15 Jan 2020 15:03:57 GMT\", Content-Length:\"823\"]>)";


        String multi[] = arrivo.split("]>"); //Divide le metrics duplicate
        String splitted = multi[0];


        //Trasforma la stringa in una stringa fromato json
        String first = splitted.substring(splitted.indexOf("{"));
        int index1 = first.indexOf(",[Cont");
        String finalString = first.substring(0,index1).replace(",{","");





        //Splitta ogni singola richiesta http
        String finale = finalString.substring(0,finalString.indexOf("]}}"));
        String tmp[] = finale.split("\"metric\"+:");


        String metrics[] = tmp;
        for(int i = 0; i < tmp.length-1; i++){

            metrics[i] = metrics[i+1];
        }
        metrics[metrics.length-1] = "";
        for(String m: metrics){
            System.out.println(m);
        }




        //System.out.println(finalString);
        /*
        Gson g = new Gson();
        String stringInJson = g.toJson(finalString);
        System.out.println(stringInJson);
        //System.out.println(str.replace("\\",""));

         */
    }
}
