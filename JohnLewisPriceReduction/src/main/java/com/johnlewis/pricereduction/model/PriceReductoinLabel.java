package com.johnlewis.pricereduction.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PriceReductoinLabel {
	
	public String wasNow;
	public String wasThenNow;
	public String percentDiscount;
	public String getWasNow() {
		return wasNow;
	}
	public void setWasNow(String wasNow) {
		this.wasNow = wasNow;
	}
	public String getWasThenNow() {
		return wasThenNow;
	}
	public void setWasThenNow(String wasThenNow) {
		this.wasThenNow = wasThenNow;
	}
	public String getPercentDiscount() {
		return percentDiscount;
	}
	public void setPercentDiscount(String percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

}
