package cn.edu.tju.myshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the send_method database table.
 * 
 */
@Entity
@Table(name="send_method")
@NamedQuery(name="SendMethod.findAll", query="SELECT s FROM SendMethod s")
public class SendMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="sendMethod")
	private List<Order> orders;

	public SendMethod() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setSendMethod(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setSendMethod(null);

		return order;
	}

}