package reflectionExercise.barracksWars.core;

import reflectionExercise.barracksWars.core.commands.Command;
import reflectionExercise.barracksWars.interfaces.Repository;
import reflectionExercise.barracksWars.interfaces.Runnable;
import reflectionExercise.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Engine implements Runnable {

    private static String COMMANDS_PATH_NAME = "barracksWars.core.commands.";
    private static String EXECUTE_METHOD_NAME = "execute";

    private Repository repository;
    private UnitFactory unitFactory;

    public Engine(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpretCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException | ExecutionControl.NotImplementedException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: refactor for problem 4
    private String interpretCommand(String[] data, String commandName) throws ExecutionControl.NotImplementedException {
        String result = "";
        try {
            String commandClassName = commandName.substring(0, 1).toUpperCase() + commandName.substring(1);
            Class clazz = Class.forName(COMMANDS_PATH_NAME + commandClassName);
            Constructor<Command> constructor = clazz.getDeclaredConstructor(String[].class, Repository.class, UnitFactory.class);
            Command commandInstance = constructor.newInstance(data, repository, unitFactory);
            Method executeMethod = clazz.getDeclaredMethod(EXECUTE_METHOD_NAME);
            try {
                result = (String) executeMethod.invoke(commandInstance);
            } catch (InvocationTargetException e) {
                System.out.print(e.getCause().getMessage());
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }


//        switch (commandName) {
//            case "add":
//                Add add = new Add(data, repository, unitFactory);
//                result = add.execute();
//                break;
//            case "report":
//                Report report = new Report(data, repository, unitFactory);
//                result = report.execute();
//                break;
//            case "fight":
//                Fight fight = new Fight(data, repository, unitFactory);
//                result = fight.execute();
//                break;
//            default:
//                throw new RuntimeException("Invalid command!");
//        }
//        return result;
}

