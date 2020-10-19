import java.util.*;

public class WordFrequencyGame {
    private static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {

        if (sentence.split(WHITE_SPACE).length==1) {
            return sentence + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = getWordFrequency(sentence);
                sortWordInfoList(wordInfoList);
                StringJoiner joiner = getStringJoiner(wordInfoList);
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> getWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE));

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int count = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, count));
        }
        return wordInfoList;
    }

    private void sortWordInfoList(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private StringJoiner getStringJoiner(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : wordInfoList) {
            String combineWordInfo = String.format("%s %d", wordInfo.getWord(), wordInfo.getWordCount());
            joiner.add(combineWordInfo);
        }
        return joiner;
    }
}
