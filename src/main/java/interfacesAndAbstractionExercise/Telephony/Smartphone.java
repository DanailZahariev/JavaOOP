package interfacesAndAbstractionExercise.Telephony;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Browsable, Callable {

    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile("^[0-9]+$");
        for (String number : numbers) {
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                stringBuilder.append("Calling... ").append(number)
                        .append(System.lineSeparator());
            } else {
                stringBuilder.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return stringBuilder.toString().trim();
    }


    @Override
    public String browse() {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile("^[^0-9]+$");
        for (String url : urls) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                stringBuilder.append("Browsing: ").append(url)
                        .append(System.lineSeparator());
            } else {
                stringBuilder.append("Invalid URL!").append(System.lineSeparator());
            }
        }
        return stringBuilder.toString().trim();
    }
}

