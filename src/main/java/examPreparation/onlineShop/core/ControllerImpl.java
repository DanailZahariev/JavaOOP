package examPreparation.onlineShop.core;

import examPreparation.onlineShop.common.constants.ExceptionMessages;
import examPreparation.onlineShop.common.constants.OutputMessages;
import examPreparation.onlineShop.core.interfaces.Controller;
import examPreparation.onlineShop.models.products.components.*;
import examPreparation.onlineShop.models.products.computers.Computer;
import examPreparation.onlineShop.models.products.peripherals.*;
import onlineShop.models.products.components.*;
import examPreparation.onlineShop.models.products.computers.DesktopComputer;
import examPreparation.onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Map<Integer, Computer> computersMap;
    private Map<Integer, Component> componentsMap;
    private Map<Integer, Peripheral> peripheralMap;

    public ControllerImpl() {
        this.computersMap = new LinkedHashMap<>();
        this.componentsMap = new LinkedHashMap<>();
        this.peripheralMap = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        if (computersMap.containsKey(id)) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
        }

        Computer computer;
        if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else if (computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }
        computersMap.put(id, computer);
        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        Peripheral peripheral;
        checkComputerId(computerId);

        if (peripheralMap.containsKey(id)) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
        }

        switch (peripheralType) {
            case "Headset":
                peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Keyboard":
                peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Monitor":
                peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            case "Mouse":
                peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }
        computersMap.get(computerId).addPeripheral(peripheral);
        peripheralMap.put(peripheral.getId(), peripheral);
        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        Peripheral peripheral = computersMap.get(computerId).removePeripheral(peripheralType);

        peripheralMap.remove(peripheral.getId());

        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        checkComputerId(computerId);

        if (componentsMap.containsKey(computerId)) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
        }

        Component component;
        switch (componentType) {
            case "CentralProcessingUnit":
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "Motherboard":
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "PowerSupply":
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "RandomAccessMemory":
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "SolidStateDrive":
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                break;
            case "VideoCard":
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }
        componentsMap.put(component.getId(), component);
        computersMap.get(computerId).addComponent(component);
        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        checkComputerId(computerId);

        Component component = computersMap.get(computerId).removeComponent(componentType);

        componentsMap.remove(component.getId());

        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        checkComputerId(id);
        Computer computer = computersMap.remove(id);
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> filteredComputers = computersMap.values().stream().filter(c -> c.getPrice() <= budget)
                .sorted(Comparator.comparing(Computer::getOverallPerformance).reversed())
                .collect(Collectors.toList());
        if (filteredComputers.isEmpty()) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        }
        Computer computer = filteredComputers.get(0);
        componentsMap.remove(computer.getId());
        return computer.toString();
    }

    @Override
    public String getComputerData(int id) {
        checkComputerId(id);
        return computersMap.get(id).toString();
    }

    private void checkComputerId(int id) {
        if (!this.computersMap.containsKey(id)) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
    }
}
