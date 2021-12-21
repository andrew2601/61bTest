import com.sun.jdi.connect.Connector;

public class pokemon {
    public String name;
    public int level;

    public pokemon(String name, int level){
        this.name = name;
        this.level = level;
    }

    public static void main (String[] args) {
        pokemon p = new pokemon("Pikachu", 17);
        int level = 100;
        change(p, level);
        System.out.println("Name:" + p.name + ",Level" + p.level);
    }
    public static void change(pokemon poke, int level){
        poke.level = level;
        level = 50;
        poke = new pokemon("Gengar", 1);
    }

}
