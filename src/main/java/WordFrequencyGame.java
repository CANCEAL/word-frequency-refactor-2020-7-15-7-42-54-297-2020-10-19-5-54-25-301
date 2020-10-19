import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    private static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence){

        if (sentence.split(WHITE_SPACE).length==1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfoList = getWordFrequency(sentence);

                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : wordInfoList) {
                    String combineWordInfo = String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount());
                    joiner.add(combineWordInfo);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> getWordFrequency(String sentence) {
        String[] words = sentence.split(WHITE_SPACE);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordInfo>> wordInfoMap = getListMap(wordInfoList);

        List<WordInfo> distinctWordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()){
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            distinctWordInfos.add(wordInfo);
        }
        return wordInfoList = distinctWordInfos;
    }

    private Map<String,List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }

            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }


        return map;
    }


}
