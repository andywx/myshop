package cn.edu.tju.myshop.model.listener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.PrePersist;

import cn.edu.tju.myshop.model.Customer;

public class CustomerListener {

	@PrePersist
	public void hashPassword(Customer customer) {
		String password = customer.getPlainPassword();
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
		    m.update(password.getBytes());
		    byte s[] = m.digest();
		    String result = "";
		    for (int i = 0; i < s.length; i++) {
		      result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
		    }
		    customer.setPassword(result);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
