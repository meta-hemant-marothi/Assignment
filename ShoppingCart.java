import java.util.HashMap;
import java.util.Map;


public class ShoppingCart{
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





