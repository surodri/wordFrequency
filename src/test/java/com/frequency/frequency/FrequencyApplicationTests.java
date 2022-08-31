package com.frequency.frequency;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FrequencyApplicationTests {


	@Test
	void splitByWordsReturnsWords() {
		FrequencyService frequencyService = new FrequencyService();

		String[] result = frequencyService.splitByWords("the cat is in the bag");

		String [] expected = new String[]{"the", "cat", "is", "in", "the", "bag"};
		for(int i = 0; i< expected.length; i++){
			assertEquals(expected[i], result[i]);
		}
	}

	@Test
	void splitByWordsReturnsWordsWithDashesUnderscores() {
		FrequencyService frequencyService = new FrequencyService();

		String[] result = frequencyService.splitByWords("the cat_holder is in the-bag");

		String [] expected = new String[]{"the", "cat_holder", "is", "in", "the-bag"};
		for(int i = 0; i< expected.length; i++){
			assertEquals(expected[i], result[i]);
		}
	}

	@Test
	void splitByWordsReturnsWordsWithAposterphy() {
		FrequencyService frequencyService = new FrequencyService();

		String[] result = frequencyService.splitByWords("the cat's toy is on the shelf");

		String [] expected = new String[]{"the", "cat's", "toy", "is", "on", "the", "shelf"};
		for(int i = 0; i< expected.length; i++){
			assert(expected[i].equals(result[i]));
		}
	}
	@Test
	void splitByWordsGivenBlankReturnsEmpty() {
		FrequencyService frequencyService = new FrequencyService();

		String[] result = frequencyService.splitByWords("   ");

		assert(result.length==0);
	}

	@Test
	void splitByWordsGivenEmptyReturnsEmpty() {
		FrequencyService frequencyService = new FrequencyService();

		ArrayList<String[]> result = frequencyService.getWordsFrequencies("");

		assert(result.size() == 0);
	}

	@Test
	void splitByWordsGivenEmptyReturnsBlank() {
		FrequencyService frequencyService = new FrequencyService();

		ArrayList<String[]> result = frequencyService.getWordsFrequencies("  ");

		assert(result.size() == 0);
	}

	@Test
	void getWordsFrequenciesReturnsInDecreasingOrder(){
		FrequencyService frequencyService = new FrequencyService();

		ArrayList<String[]> result = frequencyService.getWordsFrequencies("the cat is in the bag");

		String [] freq1 = new String[]{"2", "the"};
		String [] freq2 = new String[]{"1", "in"};
		String [] freq3 = new String[]{"1", "cat"};
		String [] freq4 = new String[]{"1", "bag"};
		String [] freq5 = new String[]{"1", "is"};
		ArrayList<String[]> expected = new ArrayList<String[]>(List.of(freq1, freq2, freq3, freq4, freq5));
		assert(result.size() == 5);
		for(int i = 0; i< expected.size(); i++){
			assertEquals(expected.get(i)[0],result.get(i)[0]);
			assertEquals(expected.get(i)[1],result.get(i)[1]);
		}
	}

	@Test
	void getWordsFrequenciesReturnsInDecreasingOrderGivenMultipleFreq(){
		FrequencyService frequencyService = new FrequencyService();

		ArrayList<String[]> result = frequencyService.getWordsFrequencies("the cat is in the bag is is, is bag bag is");

		String [] freq1 = new String[]{"5", "is"};
		String [] freq2 = new String[]{"3", "bag"};
		String [] freq3 = new String[]{"2", "the"};
		String [] freq4 = new String[]{"1", "in"};
		String [] freq5 = new String[]{"1", "cat"};
		ArrayList<String[]> expected = new ArrayList<String[]>(List.of(freq1, freq2, freq3, freq4, freq5));
		assert(result.size() == 5);
		for(int i = 0; i< expected.size(); i++){
			assertEquals(expected.get(i)[0],result.get(i)[0]);
			assertEquals(expected.get(i)[1],result.get(i)[1]);
		}
	}

	@Test
	void getWordsFrequenciesReturnsInDecreasingOrderGivenOtherFormats(){
		FrequencyService frequencyService = new FrequencyService();

		ArrayList<String[]> result = frequencyService.getWordsFrequencies(" All the cat's toys' test_holders are in the bag. The 1-10");
		assert(result.size() == 10);
	}
}
