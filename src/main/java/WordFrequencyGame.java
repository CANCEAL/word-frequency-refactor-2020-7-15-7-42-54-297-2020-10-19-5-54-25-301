import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {
            List<WordInfo> wordInfoList = getWordFrequency(sentence);
            sortWordInfoList(wordInfoList);
            String joiner = formatWordInfo(wordInfoList);
            return joiner;
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

    private String formatWordInfo(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(word -> String.format("%s %d", word.getWord(), word.getWordCount()))
                .collect(Collectors.joining("\n"));
    }
}
