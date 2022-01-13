package com.maximalibri.bookdescription.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class BookDescriptionService {

    /** returns the description of a book parsed from the book's page on goodreads */
    public String getBookDescription(String isbn) {
        StringBuffer description = null;
        try {
            URL oracle = new URL("https://www.goodreads.com/search?q="+isbn);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            Boolean intoDescriptionDiv = false;
            Boolean passedFirstSpan = false;
            while ((inputLine = in.readLine()) != null){
                if(inputLine.contains("id=\"description\"")) intoDescriptionDiv=true;
                if(inputLine.contains("<span") && intoDescriptionDiv && passedFirstSpan) description = new StringBuffer(inputLine);
                if(inputLine.contains("<span") && intoDescriptionDiv) passedFirstSpan=true;
                if(intoDescriptionDiv && inputLine.contains("</div>")) intoDescriptionDiv=false;
            }
            in.close();
            if(description!=null) {
                Integer first = description.indexOf(">");
                description.delete(0, first + 1);
                first = description.indexOf("<br />");
                while (first != -1) {
                    description.delete(first, first + 6);
                    description.insert(first, "\n\n");
                    first = description.indexOf("<br />");
                }
                first = description.indexOf("<i>");
                while(first!=-1) {
                    Integer second = description.indexOf("</i>");
                    description.delete(first,second+4);
                    first = description.indexOf("<i>");
                }

                first = description.indexOf("</span>");
                description.delete(first, first + 7);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(description!=null) return description.toString();
        else return "";
    }
}
