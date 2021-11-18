package ReflectionExercise.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Scanner scanner = new Scanner(System.in);

        Class<BlackBoxInt> blackBox = BlackBoxInt.class;
        Constructor<BlackBoxInt> declaredConstructor = blackBox.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        BlackBoxInt blackBoxInt = declaredConstructor.newInstance();

        Field innerValue = blackBox.getDeclaredField("innerValue");
        innerValue.setAccessible(true);
        String input = scanner.nextLine();

        while (!input.equals("END")) {
            String[] tokens = input.split("_");
            String methodName = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            Method method = blackBox.getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);

            method.invoke(blackBoxInt, value);

            System.out.println(innerValue.get(blackBoxInt));

            input = scanner.nextLine();

        }

    }
}
