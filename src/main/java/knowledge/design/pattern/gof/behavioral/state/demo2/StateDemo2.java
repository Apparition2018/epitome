package knowledge.design.pattern.gof.behavioral.state.demo2;

/**
 * 某航空公司的会员积分系统将其会员划分为：普卡（Basic）、银卡（Silver）和金卡（Gold）三个等级。
 * 非会员（NonMember）可以申请成为普卡会员。会员的等级根据其一年内累积的里程数进行调整。
 * 描述会员等级调整的状态图如图6-1所示。现在采用状态（State）模式实现上述场景，得到如图6-2所示的类图。
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class StateDemo2 {

    /**
     * State
     */
    private static abstract class CState {
        // 里程数
        private int flyMiles;

        // 根据累积里程数调整会员等级
        protected abstract double travel(int miles, FrequentFlyer context);
    }

    /**
     * ConcreteState
     * 非会员
     */
    private static class CNoCustomer extends CState {
        public double travel(int miles, FrequentFlyer context) {
            // 不累积里程数
            System.out.println("Your travel wil not account for points");
            return miles;
        }
    }

    /**
     * ConcreteState
     * 普卡会员
     */
    private static class CBasic extends CState {
        public double travel(int miles, FrequentFlyer context) {
            if (context.flyMiles >= 25000 && context.flyMiles < 50000)
                context.setState(new CSilver());
            if (context.flyMiles >= 50000)
                context.setState(new CGold());
            return miles;
        }
    }

    /**
     * ConcreteState
     * 银卡会员
     */
    private static class CSilver extends CState {
        public double travel(int miles, FrequentFlyer context) {
            if (context.flyMiles < 25000)
                context.setState(new CBasic());
            if (context.flyMiles >= 50000)
                context.setState(new CGold());
            return miles + 0.25 * miles;
        }
    }

    /**
     * ConcreteState
     * 金卡会员
     */
    private static class CGold extends CState {
        public double travel(int miles, FrequentFlyer context) {
            if (context.flyMiles >= 25000 && context.flyMiles < 50000)
                context.setState(new CSilver());
            if (context.flyMiles < 25000)
                context.setState(new CBasic());
            return miles + 0.5 * miles;
        }
    }

    /**
     * Context
     */
    private static class FrequentFlyer {
        CState state;
        double flyMiles;

        FrequentFlyer() {
            state = new CNoCustomer();
            flyMiles = 0;
            setState(state);
        }

        public void setState(CState state) {
            this.state = state;
        }

        public void travel(int miles) {
            double bonusMiles = state.travel(miles, this);
            flyMiles += bonusMiles;
        }
    }
}
