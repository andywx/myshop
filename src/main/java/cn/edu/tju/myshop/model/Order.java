package cn.edu.tju.myshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="customer_id")
	private String customerId;

	@Column(name="invoice_item")
	private String invoiceItem;

	@Column(name="invoice_title")
	private String invoiceTitle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="place_time")
	private Date placeTime;

	private BigDecimal price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="send_time")
	private Date sendTime;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="order")
	private List<Item> items;

	//bi-directional many-to-one association to OrderStatus
	@ManyToOne
	@JoinColumn(name="status_id")
	private OrderStatus orderStatus;

	//bi-directional many-to-one association to PaymentMethod
	@ManyToOne
	@JoinColumn(name="payment_method_id")
	private PaymentMethod paymentMethod;

	//bi-directional many-to-one association to SendMethod
	@ManyToOne
	@JoinColumn(name="send_method_id")
	private SendMethod sendMethod;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getInvoiceItem() {
		return this.invoiceItem;
	}

	public void setInvoiceItem(String invoiceItem) {
		this.invoiceItem = invoiceItem;
	}

	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public Date getPlaceTime() {
		return this.placeTime;
	}

	public void setPlaceTime(Date placeTime) {
		this.placeTime = placeTime;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setOrder(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setOrder(null);

		return item;
	}

	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public SendMethod getSendMethod() {
		return this.sendMethod;
	}

	public void setSendMethod(SendMethod sendMethod) {
		this.sendMethod = sendMethod;
	}

}