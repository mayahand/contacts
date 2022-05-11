package com.nhnent.service.parser;

import com.nhnent.service.model.Contact;

import java.io.File;
import java.io.IOException;

public interface Parser extends AutoCloseable {
    void init() throws Exception;
    void load(File file) throws Exception;
    Contact getNextContact() throws IOException;
}
