package com.nhnent.service.parser;

import com.nhnent.service.model.Contact;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonParser implements Parser<JSONObject>{
    File file;
    Iterator<JSONObject> contacts;

    @Override
    public void init() throws Exception {
        load(file);
    }

    @Override
    public void load(File file) throws IOException, ParseException {
        this.file = file;
        close();
        JSONParser jsonParser = new JSONParser();
        contacts = ((JSONArray) jsonParser.parse(new FileReader(file))).iterator();
    }

    @Override
    public Contact getNextContact() {
        if(!contacts.hasNext()) return null;
        JSONObject jsonObject = contacts.next();
        Contact contact = new Contact();
        contact.name = (String) jsonObject.get("name");
        contact.phoneNumber = (String) jsonObject.get("phoneNumber");
        return contact;
    }

    @Override
    public void close() {
        if(contacts != null) {
            contacts = null;
        }
    }
}
