package com.bytesvc.provider.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_account_one")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "identifier", nullable = false)
	private String identifier;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "frozen", nullable = false)
	private double frozen;

}
