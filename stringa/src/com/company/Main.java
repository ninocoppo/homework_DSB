package com.company;


import com.google.gson.Gson;

public class Main {

    public static void main(String[] args) {

        String old = "<200,{\"status\":\"success\",\"data\":{\"resultType\":\"vector\",\"result\":[]}},[Content-Type:\"application/json\", Date:\"Sun, 19 Jan 2020 09:18:41 GMT\", Content-Length:\"63\"]>";

        String arrivo = "Response: <200,{\"status\":\"success\",\"data\":{\"resultType\":\"vector\",\"result\":[{\"metric\":{\"Routed_uri\":\"http://myapp:8081/minio/deleteByUserRole/2\",\"http_method\":\"DELETE\",\"instance\":\"api-gateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579688832.334,\"0\"]},{\"metric\":{\"Routed_uri\":\"http://myapp:8081/minio/files\",\"http_method\":\"GET\",\"instance\":\"api-gateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579688832.334,\"0\"]},{\"metric\":{\"Routed_uri\":\"http://myapp:8081/record/check/2\",\"http_method\":\"POST\",\"instance\":\"api-gateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Accepted\",\"response\":\"202 ACCEPTED\"},\"value\":[1579688832.334,\"0\"]},{\"metric\":{\"Routed_uri\":\"http://myapp:8081/record/put\",\"http_method\":\"POST\",\"instance\":\"api-gateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579688832.334,\"0\"]},{\"metric\":{\"Routed_uri\":\"http://myapp:8081/record/showRecord/2\",\"http_method\":\"GET\",\"instance\":\"api-gateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Moved Permanently\",\"response\":\"301 MOVED_PERMANENTLY\"},\"value\":[1579688832.334,\"0\"]},{\"metric\":{\"Routed_uri\":\"http://myapp:8081/user/register\",\"http_method\":\"POST\",\"instance\":\"api-gateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"Internal Server Error\",\"response\":\"500 INTERNAL_SERVER_ERROR\"},\"value\":[1579688832.334,\"0\"]},{\"metric\":{\"Routed_uri\":\"http://myapp:8081/user/register\",\"http_method\":\"POST\",\"instance\":\"api-gateway:8080\",\"job\":\"api_gateway\",\"outcome\":\"OK\",\"response\":\"200 OK\"},\"value\":[1579688832.334,\"0\"]}]}},[Content-Type:\"application/json\", Date:\"Wed, 22 Jan 2020 10:27:12 GMT\", Content-Length:\"1520\"]>";



        String multi[] = arrivo.split("]>"); //Divide le metrics duplicate
        String splitted = multi[0];


        //Trasforma la stringa in una stringa fromato json
        String first = splitted.substring(splitted.indexOf("{"));
        int index1 = first.indexOf(",[Cont");
        String finalString = first.substring(0, index1).replace(",{", "");


        //Splitta ogni singola richiesta http
        String finale = finalString.substring(0, finalString.indexOf("]}}"));
        String tmp1[] = finale.split(":\\[" + "\\{"); //Fa lo split di ":[{"
        String tmp[] = tmp1[1].split("\"metric\"+:");


        int counter = 0;
        while (counter < tmp.length) {
            System.out.println(tmp[counter]);
            counter++;
        }
        System.out.println("Counter" + counter);
        System.out.println("Vector size"+tmp.length);


        if (tmp.length <= counter) {
            System.out.println("Nothing new...");
        } else{
            System.out.println("New request");
        }






        //System.out.println(finalString);
        /*

        Gson g = new Gson();
        String stringInJson = g.toJson(tmp).replace("\\","");
        System.out.println(stringInJson);
        */


    }
}
