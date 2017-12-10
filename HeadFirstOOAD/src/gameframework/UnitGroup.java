package gameframework;

import java.util.*;

public class UnitGroup {
    private Map units;

    public UnitGroup(List<Unit> unitList) {
        units = new HashMap();
        for (Unit unit : unitList) {
            units.put(unit.getId(), unit);
        }
    }

    public UnitGroup() {
        this(new LinkedList<>());
    }

    public void addUnit(Unit unit) {
        units.put(unit.getId(), unit);
    }

    public void removeUnit(int id) {
        units.remove(id);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit.getId());
    }

    public Unit getUnit(int id) {
        return (Unit) units.get(id);
    }

    public List getUnits() {
        List unitList = new LinkedList();
        for (Iterator iterator = units.entrySet().iterator(); iterator.hasNext(); ) {
            unitList.add(iterator.next());
        }
        return unitList;
    }
}
