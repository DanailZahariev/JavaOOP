package InterfacesAndAbstractionExercise.Telephony;

import java.util.List;

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
        boolean validNumber = true;
        for (String number : numbers) {
            char[] chars = number.toCharArray();
            for (char symbol : chars) {
                if (!Character.isDigit(symbol)) {
                    stringBuilder.append("Invalid number!").append(System.lineSeparator());
                    validNumber = false;
                    break;
                }
            }
            if (validNumber) {
                stringBuilder.append("Calling... ").append(number).append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }


    @Override
    public String browse() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean validUrl = true;
        for (String url : urls) {
            char[] chars = url.toCharArray();
            for (char symbol : chars) {
                if (Character.isDigit(symbol)) {
                    stringBuilder.append("Invalid URL!").append(System.lineSeparator());
                    validUrl = false;
                    break;
                }
            }
            if (validUrl) {
                stringBuilder.append("Browsing: ").append(url).append("!").append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }
}

