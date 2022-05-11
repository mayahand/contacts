package com.nhnent.service;

import com.nhnent.service.model.Contact;
import com.nhnent.service.parser.CsvParser;
import com.nhnent.service.parser.Parser;

import java.io.*;
import java.util.*;

public class Contacts {

    Parser parser;

    public Contacts(){
        parser = new CsvParser();
    }

    public void load(String s) throws Exception {
        parser.load(new File("src/test/resources/" + s));
    }

    public int getUniqueNameCount() throws Exception {
        parser.init();
        Set<String> names = new HashSet<>();
        try(Parser myParser = this.parser){
            Contact contact;
            while((contact = myParser.getNextContact()) != null){
                names.add(contact.name);
            }
            return names.size();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> findByName(String name) throws Exception {
        parser.init();
        List<String> phoneNumbers = new ArrayList<>();

        if(name == null) return phoneNumbers;

        try(Parser myParser = this.parser){
            Contact contact;
            while((contact = myParser.getNextContact()) != null){
                if(name.equals(contact.name)){
                    phoneNumbers.add(contact.phoneNumber);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            phoneNumbers.clear();
        }

        return phoneNumbers;
    }
}
