package com.codechallenge.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;

/*
 * DTO for any transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1472542000597487191L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Max( value=Integer.MAX_VALUE )
    private Long id;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "created_timestamp")
	private long timestamp;
	
	public Transaction() {}
	
	public Transaction(double amount, long timestamp) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", timestamp=" + timestamp + "]";
	}
	
}
