import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class shopping{

    public static void itemsMenu(ArrayList<Item> items){
        // A Menu of all the items present.
        System.out.println("\n\n===============================================================================================");
        System.out.println(String.format("|%-8s|%-15s|%-35s|%-15s|","Item Id", "Item Name", "Item Desc", "Item Price"));
        System.out.println("===============================================================================================");
        for(Item i : items){
            System.out.println(String.format("|%-8d|%-15s|%-35s|%-15.2f|",i.getId(), i.getName(), i.getDescription(), i.getPrice()));
        }
        System.out.println("===============================================================================================");
    }

    public static void operationsMenu(){
        // A Menu to list all operations that are faciliated by the program.
        System.out.println("#### Choose What Do You Want To Do ####");
        System.out.println("1. Add Items to cart");
        System.out.println("2. Display Quantity");
        System.out.println("3. Update Quantity");
        System.out.println("4. Delete Item");
        System.out.println("5. Display Bill");
        System.out.println("6. Exit");
    }

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
            operationsMenu();
            System.out.print("Enter Your Choice Number: ");
            int choice = sc.nextInt();

            switch(choice){
                case 1: 
                    itemsMenu(items);
                    System.out.print("Enter the item ID You Want to add: ");
                    id = sc.nextInt();
                    System.out.print("Enter the Quantity of that item You Want to add: ");
                    quant = sc.nextInt();
                    shop.addToCart(items.get(id-1), quant);
                    break;

                case 2:
                    shop.displayQty();
                    break;

                case 3:
                    itemsMenu(items);
                    System.out.print("Enter the item ID You Want to update: ");
                    id = sc.nextInt();
                    System.out.print("Enter the Quantity by how much You Want to update: ");
                    quant = sc.nextInt();
                    shop.updateQty(items.get(id-1), quant);
                    break;

                case 4:
                    itemsMenu(items);
                    System.out.print("Enter the item ID You Want to delete: ");
                    id = sc.nextInt();
                    shop.deleteItem(items.get(id-1));
                    break;
                
                case 5:
                    System.out.println("Total Bill for above items: " + shop.displayBill() + "\n\n");
                    break;

                case 6:
                    flag = false;
                    break;
                    
                default: System.out.println("Choose Correct option.");
            }
            
        }
    }
}

class shoppingCart{
    private Map<Item, Integer> cart = new HashMap<>();
    
    public void addToCart(Item item, Integer quantity){
        if(cart.containsKey(item)){
            System.out.println("--> Item already in Cart. Please Use Update Quantity Option");
            return;
        }
        cart.put(item, quantity);
        System.out.println("--> " + item.getName() + " added to cart\n\n");
    }

    public void displayQty(){
        if(cart.isEmpty()){
            System.out.println("--> Your Cart is Empty\n\n");
            return;
        }
        for(Map.Entry<Item, Integer> entry : cart.entrySet()){
            System.out.println("=====================");
            System.out.println("| " + entry.getKey().getName()+"  |  " +entry.getValue() + "   |");
            System.out.println("=====================");
        }
        System.out.println("\n\n");
    }

    public void updateQty(Item item,Integer quantity){
        if(cart.getOrDefault(item, 0) + quantity >= 0){
            cart.put(item, cart.getOrDefault(item, 0) + quantity);
            System.out.println("--> Items Updated Successfully.\n\n");
        }else{
            cart.remove(item);
            System.out.println("--> Insufficient Items To Update. Item Removed.\n\n");
        }
    }

    public void deleteItem(Item item){
        if(cart.containsKey(item)){
            System.out.println("--> " + item.getName() + " is removed.\n\n");
            cart.remove(item);
        }else{
            System.out.println("--> " + item.getName() + " is not in cart. \n\n");
        }
        
    }

    public Double displayBill(){
        Double total = 0.0;
        for(Map.Entry<Item, Integer> entry : cart.entrySet()){
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
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