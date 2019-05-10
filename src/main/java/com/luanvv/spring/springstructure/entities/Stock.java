package com.luanvv.spring.springstructure.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "stock", catalog = "mkyong")
public class Stock extends AbstractEntity implements java.io.Serializable {

	@Size(min = 10, max = 20)
	@Column(name = "STOCK_CODE", nullable = false, length = 10)
	private String stockCode;
	
//	@StockNameValid
	@Size(min = 10, max = 20)
	@Column(name = "STOCK_NAME", nullable = false, length = 20)
	private String stockName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
	private Set<StockDailyRecord> stockDailyRecords = new HashSet<>(0);
	
	@CreationTimestamp
	@Column(name = "created_time", updatable = false)
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	@Column(name = "updated_time")
	private LocalDateTime updatedTime;

	public Stock(String stockCode, String stockName) {
		this.stockCode = stockCode;
		this.stockName = stockName;
	}

}
