package cn.edu.tju.myshop.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String detail;

	private BigDecimal discount;

	private String image;

	@Column(name="is_on_sale")
	private byte isOnSale;

	@NotNull
	@Column(name="market_price")
	private BigDecimal marketPrice;

	@NotNull
	private String name;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="pub_date")
	private Date pubDate;

	@NotNull
	@Column(name="sale_count")
	private int saleCount;

	@NotNull
	@Column(name="stock_count")
	private int stockCount;

	@NotNull
	private String summary;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="product")
	private List<Item> items;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public byte getIsOnSale() {
		return this.isOnSale;
	}

	public void setIsOnSale(byte isOnSale) {
		this.isOnSale = isOnSale;
	}

	public BigDecimal getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPubDate() {
		return this.pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public int getSaleCount() {
		return this.saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public int getStockCount() {
		return this.stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setProduct(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setProduct(null);

		return item;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}