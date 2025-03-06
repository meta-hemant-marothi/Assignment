import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Shopping{

    public static void itemsMenu(ArrayList<Item> items){
        // A Menu of all the items present in the shop.
        System.out.println("\n\n==============================================================================");
        System.out.println(String.format("|%-8s|%-15s|%-35s|%-15s|","Item Id", "Item Name", "Item Desc", "Item Price"));
        System.out.println("==============================================================================");
        for(Item i : items){
            System.out.println(String.format("|%-8d|%-15s|%-35s|%-15.2f|",i.getId(), i.getName(), i.getDescription(), i.getPrice()));
        }
        System.out.println("==============================================================================");
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
        System.out.println("#######################################");
    }

    public static int getNumInput(Scanner sc, int min, int max){
        //A Function to get a valid integer input in the given range.
        int num=0;
        while(true){
            try{
                num = sc.nextInt();
                sc.nextLine();
                if(num>=min && num<=max)return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            }catch(Exception e){
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args){

        int item_id;
        int item_quantity;
        int quantity_limit = 500;
        Scanner sc = new Scanner(System.in);

        // Items created in memory
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

        //A cart object for the customer
        ShoppingCart shop_cart = new ShoppingCart();
        boolean flag = true;

        // Will run until exited by customer
        while(flag){
            operationsMenu();
            System.out.print("Enter Your Choice Number: ");
            int choice = getNumInput(sc, 1, 6);

            // According to the customers choice, operations will be performed
            switch(choice){
                case 1: 
                    itemsMenu(items);
                    System.out.print("Enter the item ID You Want to add: ");
                    item_id = getNumInput(sc, 1, items.size());
                    System.out.print("Enter the Quantity of that item You Want to add: ");
                    item_quantity = getNumInput(sc, -1*quantity_limit, quantity_limit);
                    shop_cart.addToCart(items.get(item_id-1), item_quantity);
                    break;

                case 2:
                    shop_cart.displayQty();
                    break;

                case 3:
                    itemsMenu(items);
                    System.out.print("Enter the item ID You Want to add: ");
                    item_id = getNumInput(sc, 1, items.size());
                    System.out.print("Enter the Quantity of that item You Want to add: ");
                    item_quantity = getNumInput(sc, -1*quantity_limit, quantity_limit);
                    shop_cart.updateQty(items.get(item_id-1), item_quantity);
                    break;

                case 4:
                    shop_cart.displayQty();
                    System.out.print("Enter the item ID You Want to delete: ");
                    item_id = getNumInput(sc, 1, items.size());
                    shop_cart.deleteItem(items.get(item_id-1));
                    break;
                
                case 5:
                    System.out.println("Total Bill for above items: " + shop_cart.displayBill() + "\n\n");
                    break;

                case 6:
                    flag = false;
                    break;
                    
                default: System.out.println("!! Invalid input. Please Choose Correct option.");
            }
            
        }
    }
}





class ShoppingCart{
    private Map<Item, Integer> cart = new HashMap<>();
    
    /** 
       *Adds the item with provided quantity into the cart.
    */
    public void addToCart(Item item, Integer quantity){
        
        if(cart.containsKey(item)){
            System.out.println("--> Item already in Cart. Please Use Update Quantity Option");
            return;
        }
        cart.put(item, quantity);
        System.out.println("--> " + item.getName() + " added to cart\n\n");
    }

    /** 
       *Displays the item and its corresponding quantity added in the cart.
    */
    public void displayQty(){
        if(cart.isEmpty()){
            System.out.println("--> Your Cart is Empty\n\n");
            return;
        }
        for(Map.Entry<Item, Integer> entry : cart.entrySet()){
            System.out.println("=================================");
            System.out.println("| " + entry.getKey().getId() + " |"+ entry.getKey().getName()+"  |  " +entry.getValue() + "   |");
            System.out.println("=================================");
        }
        System.out.println("\n\n");
    }

    /** 
       *Updates the quantity of item in the cart by increasing or decreasing the amount.
    */
    public void updateQty(Item item,Integer quantity){
        if(cart.getOrDefault(item, 0) + quantity >= 0){
            cart.put(item, cart.getOrDefault(item, 0) + quantity);
            System.out.println("--> Items Updated Successfully.\n\n");
        }else{
            cart.remove(item);
            System.out.println("--> Insufficient Items To Update. Item Removed.\n\n");
        }
    }

    /** 
       *Deletes the provided item if present in cart.
    */
    public void deleteItem(Item item){
        if(cart.containsKey(item)){
            System.out.println("--> " + item.getName() + " is removed.\n\n");
            cart.remove(item);
        }else{
            System.out.println("--> " + item.getName() + " is not in cart. \n\n");
        }
        
    }

    /** 
       *Calculates and return the total bill of the cart.
    */
    public Double displayBill(){
        Double total = 0.0;
        for(Map.Entry<Item, Integer> entry : cart.entrySet()){
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}





class Item{

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