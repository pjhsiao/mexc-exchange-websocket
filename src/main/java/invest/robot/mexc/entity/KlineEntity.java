package invest.robot.mexc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 Name	Type	    Description
 -------------------------------
 k	    object	    klineInfo
 > T	long	    endTime
 > a	bigDecimal	volume
 > c	bigDecimal	closingPrice
 > h	bigDecimal	highestPrice
 > i	interval	interval
 > l	bigDecimal	lowestPrice
 > o	bigDecimal	openingPrice
 > t	long	    stratTime
 > v	bigDecimal	quantity
 s	    string	    symbol
 t	    long	    eventTime
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KlineEntity implements Serializable {
    private String c;
    private String s;
    private DataDetails d;
    @JsonProperty("t")
    private long t;

    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class DataDetails {
        private CandlestickData k;
        private String e;

    }

    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class CandlestickData {
    @JsonProperty("T")
    private long endTime;
    private BigDecimal a;
    private BigDecimal c;
    private BigDecimal h;
    private String i;
    private BigDecimal l;
    private BigDecimal o;
    @JsonProperty("t")
    private long stratTime;
    private BigDecimal v;
    }
}
