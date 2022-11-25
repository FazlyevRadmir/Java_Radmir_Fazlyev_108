package ru.itis.fazlyev.radmir.model;

public class History {

    private String usName;
    private String city;
    private String time;

    public History() {
        super();
    }

    public History(String usName, String city, String time) {
        this.usName = usName;
        this.city = city;
        this.time = time;
    }

    public String getUsName() {
        return usName;
    }

    public String getCity() {
        return city;
    }

    public String getTime() {
        return time;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
