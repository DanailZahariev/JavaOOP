package examPreparation.restaurant.repositories;

import examPreparation.restaurant.entities.tables.interfaces.Table;
import examPreparation.restaurant.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TableRepositoryImpl implements TableRepository<Table> {

    private List<Table> tables;

    public TableRepositoryImpl() {
        this.tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableList(tables);
    }

    @Override
    public void add(Table entity) {
        this.tables.add(entity);
    }

    @Override
    public Table byNumber(int number) {
        return tables.stream().filter(table -> table.getTableNumber() == number).findFirst().orElse(null);
    }
}
