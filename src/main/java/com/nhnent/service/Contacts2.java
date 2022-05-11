package com.nhnent.service;

import com.nhnent.service.model.Contact;
import com.nhnent.service.parser.Parser;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Contacts2 extends Contacts {

    File file;

    public void load(String s, Parser parser) throws Exception {
        file = new File("src/test/resources/" + s);
        this.parser = parser;
        this.parser.load(file);
    }

    public List<String> getNamesBySorting() {
        try(Parser myParser = this.parser){
            Map<String, Integer> names = new HashMap<>();
            Contact contact;
            while((contact = myParser.getNextContact()) != null){
                Integer count = names.get(contact.name);
                if(count == null){
                    names.put(contact.name, 1);
                    continue;
                }

                count += 1;
                names.put(contact.name, count);
            }
            List<String> list = names.entrySet().stream().sorted((o1, o2) -> {
                int comp = Integer.compare(o1.getValue(), o2.getValue());
                if(comp == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return comp;
            }).map(item -> item.getKey()).collect(Collectors.toList());
            Collections.reverse(list);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
