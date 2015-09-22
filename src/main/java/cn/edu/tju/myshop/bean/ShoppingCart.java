package cn.edu.tju.myshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cn.edu.tju.myshop.model.Customer;
import cn.edu.tju.myshop.model.Item;
import cn.edu.tju.myshop.model.Product;
import cn.edu.tju.myshop.model.Rank;

@ManagedBean
@SessionScoped
public class ShoppingCart implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Map<Integer, Item> pidToItemMap;

	private List<Item> items;
	
	@ManagedProperty("#{userInfo}")
	private UserInfo user;
	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public ShoppingCart() {
		
	}
	
	@PostConstruct
	public void initialize() {
		items = new LinkedList<Item>();
		pidToItemMap = new HashMap<Integer, Item>();		
	}
	
	/**
	 * Clear the shopping cart
	 *
	 */
	public void clear() {
		items.clear();
		pidToItemMap.clear();
	}
	
	/**
	 * Add product
	 * @param p
	 */
	public void addProduct(Product product) {
		Integer pid = product.getId();
		Item item = pidToItemMap.get(pid);
		if (item == null) {
			item = new Item();
			item.setProduct(product);
			item.setCount(0);
			item.setPrice(BigDecimal.valueOf(0.0));
			items.add(item);
			pidToItemMap.put(pid, item);
		}
		
		// Add the product count in the item
		item.setCount(item.getCount() + 1);
		
		// Update the total price in the item
		updateItemPrice(item);
	}
	
	/**
	 * Update the total price in the item
	 * @param item
	 */
	private void updateItemPrice(Item item) {
		// Calculate the total price in the item
		// total_price = product_price * discount * count
		double price = 0.0;
		Product product = item.getProduct();
		double marketPrice = product.getMarketPrice().doubleValue();
		Rank rank = user.getUser().getRank();
		double discount = rank.getDiscount().doubleValue();
		int count = item.getCount();
		price = marketPrice * discount * count;
		item.setPrice(BigDecimal.valueOf(price));
	}
	
	/**
	 * Remove a product
	 * @param p
	 */
	public void removeProduct(Product product) {
		Integer pid = product.getId();
		Item item = pidToItemMap.remove(pid);
		items.remove(item);
	}
	
	/**
	 * Get the list of items
	 * @return
	 */
	public List<Item> getItems() {
		return items;
	}
	
	/**
	 * Get the Customer object
	 * @return
	 */
	public Customer getCustomer() {
		return user.getUser();
	}
	
	/**
	 * Get the total price of the products in the shopping cart
	 * @return
	 */
	public double getTotalPrice() {
		double total = 0.0;
		for (Item item : items) {
			total += item.getPrice().doubleValue();
		}
		return total;
	}
	
	/**
	 * Get the size of the items
	 * @return
	 */
	public int getSize() {
		return items.size();		
	}
	
	/**
	 * Set the count of the product in the specified item
	 * @param index
	 * @param newCount
	 */
	public void setItemCount(int index, short newCount) {
		Item item = items.get(index);		
		item.setCount(newCount);
		updateItemPrice(item);		
	}
	
	/**
	 * Get the price of the specified item
	 * @param index
	 * @return
	 */
	public double getItemPrice(int index) {
		Item item = items.get(index);
		return item.getPrice().doubleValue();
	}
}
