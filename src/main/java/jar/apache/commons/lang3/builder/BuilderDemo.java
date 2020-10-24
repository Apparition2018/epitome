package jar.apache.commons.lang3.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.*;

import java.math.BigDecimal;

/**
 * ToStringBuilder
 * <p>
 * https://www.jianshu.com/p/23c36b6fc8af
 * https://www.cnblogs.com/E-star/p/3725916.html
 */
public class BuilderDemo {

    public static void main(String[] args) {
        TaxReturn tr = new TaxReturn("012-68-3242", 1998, "O'Brien", new BigDecimal("43000.00"));
        System.out.println(tr.toString());  // jar.apache.commons.lang.builder.TaxReturn@763d9750[ssn=012-68-3242,year=1998,lastName=O'Brien]
        System.out.println(tr.toString2()); // jar.apache.commons.lang.builder.TaxReturn@763d9750[
                                            //   ssn=012-68-3242
                                            //  year=1998
                                            //  lastName=O'Brien
                                            //  taxableIncome=43000
                                            // ]

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class TaxReturn {
        private String ssn;
        private int year;
        private String lastName;
        private BigDecimal taxableIncome;

        /**
         * ToStringBuilder
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