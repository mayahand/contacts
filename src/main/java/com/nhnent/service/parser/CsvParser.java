package com.nhnent.service.parser;

import com.nhnent.service.model.Contact;

import java.io.*;

public class CsvParser implements Parser{
    File file;
    BufferedReader bufferedReader;

    @Override
    public void init() throws Exception {
        load(file);
    }

    @Override
    public void load(File file) throws Exception {
        this.file = file;
        close();
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    @Override
    public Contact getNextContact() throws IOException {
        String contact = bufferedReader.readLine();
        if(contact == null) return null;

        Contact c = new Contact();
        String[] data = contact.trim().split(",");
        c.name = data[0];
        c.phoneNumber = data[1];
        return c;
    }

    @Override
    public void close() throws Exception {
        if(bufferedReader != null) {
            bufferedReader.close();
            bufferedReader = null;
        }
    }
}
