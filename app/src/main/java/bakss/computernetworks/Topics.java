package bakss.computernetworks;

public class Topics {
    private String number;  // номер лекции
    private String name; // название лекции

    public Topics(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
