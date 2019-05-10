package com.luanvv.spring.springstructure.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "stock_daily_record", catalog = "mkyong")
public class StockDailyRecord extends AbstractEntity implements java.io.Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID", nullable = false)
	private Stock stock;
	
	@Column(name = "PRICE_OPEN", precision = 6)
	private Float priceOpen;
	
	@Column(name = "PRICE_CLOSE", precision = 6)
	private Float priceClose;
	
	@Column(name = "PRICE_CHANGE", precision = 6)
	private Float priceChange;
	
	@Column(name = "VOLUME")
	private Long volume;
	
	@Column(name = "DATE", nullable = false, length = 10)
	private LocalDate date;

	public StockDailyRecord(Stock stock, LocalDate date) {
		this.stock = stock;
		this.date = date;
	}

}
