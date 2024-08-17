package it.uniroma3.siw.model;

public class CartItem {
	
	private Pizza pizza;
    private int quantity;

    public CartItem(Pizza pizza, int quantity) {
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
