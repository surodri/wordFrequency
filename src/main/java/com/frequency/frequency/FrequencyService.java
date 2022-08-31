package com.frequency.frequency;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class FrequencyService {

    public ArrayList<String[]> getWordsFrequencies(String text){
        ArrayList<String[]> response = new ArrayList<String[]>();
        if(text.isEmpty() || text.isBlank()){
            return response;
        }

        String[] words = splitByWords(text);
        HashMap<String, Long> frequencies = getFrequencies(words);
        TreeMap<Long, ArrayList<String>> listsByFrequencyDecreasingOrder = getDecreasingOrder(frequencies);

        prepOutput(response, listsByFrequencyDecreasingOrder);
        return response;
    }

    private static TreeMap<Long, ArrayList<String>> getDecreasingOrder(HashMap<String, Long> frequencies) {
        TreeMap<Long, ArrayList<String>> listsByFrequencyDecreasingOrder = new TreeMap<Long, ArrayList<String>>(Collections.reverseOrder());
        for(Map.Entry<String, Long> entry: frequencies.entrySet()){
            if(!entry.getKey().isBlank() & !entry.getKey().isEmpty()){
                ArrayList<String> sameFreqWords;
                if(listsByFrequencyDecreasingOrder.containsKey(entry.getValue())){
                    sameFreqWords = listsByFrequencyDecreasingOrder.get(entry.getValue());
                    sameFreqWords.add(entry.getKey());
                }else {
                    sameFreqWords = new ArrayList<>(List.of(entry.getKey()));
                }
                listsByFrequencyDecreasingOrder.put(entry.getValue(), sameFreqWords);
            }
        }
        return listsByFrequencyDecreasingOrder;
    }

    private static HashMap<String, Long> getFrequencies(String[] words) {
        HashMap<String, Long> frequencies = new HashMap<>();
        for(String word: words){
            if(!word.isEmpty() && !word.isBlank()){
                if(frequencies.containsKey(word)){
                    Long incrementFrequency= frequencies.get(word) + 1L;
                    frequencies.put(word, incrementFrequency);
                }else {
                    frequencies.put(word, 1L);
                }
            }
        }
        return frequencies;
    }

    private static void prepOutput(ArrayList<String[]> response, TreeMap<Long, ArrayList<String>> listsByFrequency) {
        //Note this is only copied into an array of strings and returned as response for testing
        for(Map.Entry<Long, ArrayList<String>> entry: listsByFrequency.entrySet()){
            for(String word: entry.getValue()){
                String frequency = entry.getKey().toString();
                System.out.println(frequency + " " +  word);
                String [] frequencyWord = new String[]{frequency, word};
                response.add(frequencyWord);
            }
        }
    }

    protected String [] splitByWords(String text){
        return text.split("([^a-zA-Z0-9\'_\\-\'\\’+]+)'*\\1*");
    }

    public String readFile(String filePath) throws IOException {
            File file = new ClassPathResource(filePath).getFile();
            String text = new String(Files.readAllBytes(file.toPath()));
            return text;
    }
}
