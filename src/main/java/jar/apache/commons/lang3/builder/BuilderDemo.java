package jar.apache.commons.lang3.builder;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.*;

import java.math.BigDecimal;

/**
 * XxxBuilder
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class BuilderDemo {

    public static void main(String[] args) {
        TaxReturn taxReturn = new TaxReturn().setSsn("012-68-3242").setYear(1998)
                .setLastName("O'Brien").setTaxableIncome(new BigDecimal("43000.00"));
        System.out.println(taxReturn.toString());   // jar.apache.commons.lang.builder.TaxReturn@763d9750[ssn=012-68-3242,year=1998,lastName=O'Brien]
        System.out.println(taxReturn.toString2());  // jar.apache.commons.lang.builder.TaxReturn@763d9750[
        //   ssn=012-68-3242
        //  year=1998
        //  lastName=O'Brien
        //  taxableIncome=43000
        // ]
    }

    @Data
    @Accessors(chain = true)
    static class TaxReturn {
        private String ssn;
        private int year;
        private String lastName;
        private BigDecimal taxableIncome;

        /**
         * ToStringBuilder
         * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/ToStringBuilder.html
         * ToStringBuilder 和 ToStringStyle：https://www.cnblogs.com/E-star/p/3725916.html
         */
        public String toString() {
            return new ToStringBuilder(this)
                    .append("ssn", ssn)
                    .append("year", year)
                    .append("lastName", lastName)
                    .toString();
        }

        String toString2() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }

        /**
         * HashCodeBuilder
         * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/HashCodeBuilder.html
         */
        public int hashCode() {
            return new HashCodeBuilder(3, 7)
                    .append(ssn)
                    .append(year)
                    .toHashCode();
        }

        public int hashCode2() {
            return HashCodeBuilder.reflectionHashCode(this);
        }

        /**
         * EqualsBuilder
         * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/EqualsBuilder.html
         */
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof TaxReturn) {
                TaxReturn bean = (TaxReturn) obj;
                return new EqualsBuilder().append(ssn, bean.ssn).append(year, bean.year).isEquals();
            }
            return false;
        }

        public boolean equals2(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj);
        }

        /**
         * CompareToBuilder
         * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/CompareToBuilder.html
         */
        public int compareTo(TaxReturn obj) {
            return new CompareToBuilder()
                    .append(ssn, obj.getSsn())
                    .append(year, obj.getYear())
                    .toComparison();
        }

        public int compareTo2(Object obj) {
            return CompareToBuilder.reflectionCompare(this, obj);
        }
    }
}
