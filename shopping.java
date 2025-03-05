import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class shopping{
    public static void main(String[] args){
        int id, quant;
        Scanner sc = new Scanner(System.in);
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("Honda", "Bike", 100.0));
        items.add(new Item("Dairymilk", "toffee", 10.2));
        items.add(new Item("Milk", "Source of Calcium", 40.0));
        items.add(new Item("Hero", "Bike", 100.0));
        items.add(new Item("Kitkat", "toffee", 10.2));
        items.add(new Item("Lassi", "Source of Calcium", 40.0));
        items.add(new Item("TVS", "Bike", 100.0));
        items.add(new Item("Crispello", "toffee", 10.2));
        items.add(new Item("Dahi", "Source of Calcium", 40.0));

        shoppingCart shop = new shoppingCart();
        boolean flag = true;
        while(flag){
            System.out.println("#### Choose What Do You Want To Do ####");
            System.out.println("1. Add Items to cart");
            System.out.println("2. Display Quantity");
            System.out.println("3. Update Quantity");
            System.out.println("4. Delete Item");
            System.out.println("5. Display Bill");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            
            switch(choice){
                case 1: 
                    System.out.println(String.format("%8s%15s%50s%15s","Item Id", "Item Name", "Item Desc", "Item Price"));
                    System.out.println("===============================================================================================");
                    for(Item i : items){
                        System.out.println(String.format("%8d%15s%50s%15f",i.getId(), i.getName(), i.getDescription(), i.getPrice()));
                    }
                    System.out.print("Enter the item ID You Want to add: ");
                    id = sc.nextInt();
                    System.out.print("Enter the Quantity of that item You Want to add: ");
                    quant = sc.nextInt();
                    shop.addToCart(items.get(id-1), quant);
                    break;
                case 2:
                    shop.displayCart();
                    break;
                case 3:
                    System.out.println(String.format("%8s%15s%50s%15s","Item Id", "Item Name", "Item Desc", "Item Price"));
                    System.out.println("===============================================================================================");
                    for(Item i : items){
                        System.out.println(String.format("%8d%15s%50s%15f",i.getId(), i.getName(), i.getDescription(), i.getPrice()));
                    }
                    System.out.print("Enter the item ID You Want to update: ");
                    id = sc.nextInt();
                    System.out.print("Enter the Quantity by how much You Want to update: ");
                    quant = sc.nextInt();
                    shop.updateQty(items.get(id-1), quant);
                    break;

                case 4:
                    System.out.println(String.format("%8s%15s%50s%15s","Item Id", "Item Name", "Item Desc", "Item Price"));
                    System.out.println("===============================================================================================");
                    for(Item i : items){
                        System.out.println(String.format("%8d%15s%50s%15f",i.getId(), i.getName(), i.getDescription(), i.getPrice()));
                    }
                    System.out.print("Enter the item ID You Want to delete: ");
                    id = sc.nextInt();
                    shop.deleteItem(items.get(id-1));
                    break;
                
                case 5:
                    shop.displayCart();
                    System.out.println("Total Bill: " + shop.displayBill());
                    break;

                case 6:
                    flag = false;
                    break;
                default: System.out.println("Choose Correct option.");
            }
            System.out.print("\033[H\033[2J"); 
            System.out.flush(); 
    
        }
    }
}

class shoppingCart{
    private Map<Item, Integer> cart = new HashMap<>();
    
    public void addToCart(Item item, Integer quantity){
        cart.put(item, quantity);
        System.out.println(item.getName() + "added to cart");
    }
    public Integer displayQty(Item item){
        System.out.println(cart.get(item));
        return cart.get(item);
    }
    public void updateQty(Item item,Integer quantity){
        if(cart.getOrDefault(item, 0) + quantity >= 0){
            cart.put(item, cart.getOrDefault(item, 0) + quantity);
            System.out.println("--> Items Updated Successfully");
        }else{
            cart.remove(item);
            System.out.println("--> Insufficient Items To Update. Item Removed");
        }
    }
    public void deleteItem(Item item){
        System.out.println("removed");
        cart.remove(item);
    }
    public Double displayBill(){
        Double total = 0.0;
        for(Map.Entry<Item, Integer> entry : cart.entrySet()){
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
    public void displayCart(){
        for(Map.Entry<Item, Integer> entry : cart.entrySet()){
            System.out.println(entry.getKey().getName()+"  " +entry.getValue());
        }
    }

}
class Item{
    static Integer id=1;
    private Integer ItemId;
    private Double Price;
    private String Name;
    private String Description;
    
    public Item(String Name, String Description, Double Price){
        this.ItemId = id++;
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