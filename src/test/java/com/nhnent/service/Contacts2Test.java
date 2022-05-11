package com.nhnent.service;

import com.nhnent.service.parser.CsvParser;
import com.nhnent.service.parser.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class Contacts2Test {
	Contacts2 contacts2;

	@Before
	public void before() {
		contacts2 = new Contacts2();
	}

	@Test
	public void Csv포맷() throws Exception {
		contacts2.load("/row-3-dup.csv", new CsvParser());

		List<String> result = contacts2.findByName("Hong Gildong");

		assertThat(result.size(), is(4));
		assertEquals("010-5555-5678", result.get(0));
		assertEquals("010-4444-5678", result.get(1));
		assertEquals("010-1111-2222", result.get(2));
		assertEquals("010-1234-5678", result.get(3));
	}

	@Test
	public void Json포맷() throws Exception {
		contacts2.load("/row-3-dup.json", new JsonParser());

		List<String> result = contacts2.findByName("Hong Gildong");

		assertThat(result.size(), is(4));
		assertEquals("010-5555-5678", result.get(0));
		assertEquals("010-4444-5678", result.get(1));
		assertEquals("010-1111-2222", result.get(2));
		assertEquals("010-1234-5678", result.get(3));
	}

	@Test
	public void 같은_이름에_다른_전화번호_개수가_많은_이름_역순으로_Sort() throws Exception {
		contacts2.load("/row-7-sort.json", new JsonParser());
		List<String> result = contacts2.getNamesBySorting();

		assertThat(result.size(), is(4));
		assertEquals("Hong Gildong", result.get(0));
		assertEquals("Ha Nada", result.get(1));
		assertEquals("Im Kkeokjeong", result.get(2));
		assertEquals("Han Seokgyu", result.get(3));
	
	}
}
