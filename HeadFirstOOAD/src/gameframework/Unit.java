package gameframework;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Unit {
    private final int id;
    private String name;
    private String type;
    private List weapons;
    private Map properties;

    public Unit(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void addWeapon(Weapon weapon) {
        if (weapons == null) {
            weapons = new LinkedList();
        }
        weapons.add(weapon);
    }

    public List getWeapons() {
        return weapons;
    }
    
    public void setProperty(String propertyName, Object value) {
        if (properties == null) {
            properties = new HashMap();
        }
        properties.put(propertyName, value);
    }

    public Object getProperty(String propertyName) {
        if (properties == null) {
            return null;
        }
        return properties.get(propertyName);
    }
}
