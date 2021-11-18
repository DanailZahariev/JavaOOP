package ReflectionExercise.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        Field[] declaredFields = RichSoilLand.class.getDeclaredFields();

        while (!"HARVEST".equals(input = scanner.nextLine())) {
            String modifier = input;
            Field[] fields = Arrays.stream(declaredFields).filter(field -> Modifier.toString(field.getModifiers()).equals(modifier))
                    .toArray(Field[]::new);
            if (fields.length == 0) {
                printFields(declaredFields);
            } else {
                printFields(fields);
            }
        }
    }

    private static void printFields(Field[] fields) {
        Arrays.stream(fields).forEach(field ->
                System.out.printf("%s %s %s%n", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));
    }
}

