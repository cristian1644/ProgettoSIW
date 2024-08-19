package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Carrello {
	
private List<CartItem> items = new ArrayList<>();
    
    public void addPizza(Pizza pizza, int quantity) {
        for (CartItem item : items) {
            if (item.getPizza().equals(pizza)) { //se la pizza è gia presente nel carrello
                item.setQuantity(item.getQuantity() + quantity); //ne aumento la quantità
                return;
            }
        }
        items.add(new CartItem(pizza, quantity)); //altrimenti aggiungo un nuovo oggetto al carrello con la quantità specificata
    }

    public void removePizza(Pizza pizza) {
        items.removeIf(item -> item.getPizza().equals(pizza)); //scorro nella lista di pizza e rimuovo quella scelta
    }
    
    public void removePizzaQuantity(Pizza pizza, int quantity) {
        for (CartItem item : items) {
            if (item.getPizza().equals(pizza)) {
                int newQuantity = item.getQuantity() - quantity;
                if (newQuantity > 0) {
                    item.setQuantity(newQuantity);
                } else {
                    items.remove(item);
                }
                return;
            }
        }
    }

    public void svuota() {
        this.items.clear();
    }
    
    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getPizza().getPrezzo() * item.getQuantity()).sum();
    }
    
    public List<CartItem> getItems() {
        return items;
    }

    
    
}
