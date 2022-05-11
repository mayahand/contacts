package com.nhnent.service;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ContactsTest {
    Contacts contacts;

    @Before
    public void before() {
        contacts = new Contacts();
    }

    @Test
    public void 데이터가_1개_인것() throws Exception {
        // 데이터 파일을 읽어들입니다.
        contacts.load("/row-1.csv");

        // 주소록에 있는 사람 수를 반환합니다.
        assertEquals(1, contacts.getUniqueNameCount());
        assertEquals("010-1111-2222", contacts.findByName("Hong Gildong").get(0));
    }

    @Test
    public void 동명이인이_있는_경우()  throws Exception {
        contacts.load("/row-3-dup.csv");

        // 주소록에 있는 사람 수를 반환합니다.
        // 동명이인은 한 사람으로 간주합니다.
        assertEquals(2, contacts.getUniqueNameCount());

        List<String> result;
        result = contacts.findByName("Hong Gildong");
        assertEquals(4, result.size()); // "Hong Gildong" 이름을 가지고 있는 사람이 4명입니다.
        assertEquals("010-5555-5678", result.get(0));
        assertEquals("010-4444-5678", result.get(1));
        assertEquals("010-1111-2222", result.get(2));
        assertEquals("010-1234-5678", result.get(3));

        result = contacts.findByName("Im Kkeokjeong");
        assertEquals(1, result.size());
        assertEquals("010-2222-3333", result.get(0));
    }

    @Test
    public void 주소록에_없는_사용자를_요청했을때() throws Exception {
        contacts.load("/row-3-dup.csv");

        List<String> result;
        result = contacts.findByName("없는사용자이름");
        assertEquals(0, result.size()); // 주소록에 없는 사용자를 요청했을 때 result는 null이 아닙니다.
    }
}
