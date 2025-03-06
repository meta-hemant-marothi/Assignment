
public class Item{

    private static Integer unique_id=1;
    private Integer ItemId;
    private Double Price;
    private String Name;
    private String Description;
    
    public Item(String Name, String Description, Double Price){
        this.ItemId = unique_id++;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
    }


    public Integer getId(){
        return ItemId;
    }
    public Double getPrice(){
        return Price;
    }
    public String getDescription(){
        return Description;
    }
    public String getName(){
        return Name;
    }
}