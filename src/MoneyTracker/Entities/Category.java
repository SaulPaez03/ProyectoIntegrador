package MoneyTracker.Entities;

public class Category {
    int id;
    String name;
    public Category(String name, int id){
        this.name = name;
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
