package cn.edu.tju.myshop.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.tju.myshop.model.listener.CustomerListener;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
@EntityListeners({CustomerListener.class})
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Size(min = 1, max = 20)
	private String username;

	private String address;

	@NotNull
	private String answer;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String city;

	private String county;
    
	@NotNull
    @NotEmpty
	@Email
	private String email;
	
	@NotNull
	private String gender;

    @Size(min = 11, max = 11)
    @Digits(fraction = 0, integer = 11)
	private String mobile;

	@NotNull
	@Size(min = 6, max = 20)
	@Transient private String plainPassword;
	
	private String password;

	private String province;

	@NotNull
	private String question;

	@NotNull
	@Size(min = 1, max = 20)
	@Column(name="real_name")
	private String realName;

	private int score;

    @Size(min = 6, max = 6)
    @Digits(fraction = 0, integer = 6)
	private String zipcode;

	//bi-directional many-to-one association to Rank
	@ManyToOne
	private Rank rank;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	private List<UserRole> userRoles;

	public Customer() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPlainPassword() {
		return this.plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Rank getRank() {
		return this.rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setCustomer(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setCustomer(null);

		return userRole;
	}

}