package other.设计模式.behavioral.state.demo2;

/**
 * 某航空公司的会员积分系统将其会员划分为：普卡（Basic）、银卡（Silver）和金卡（Gold）三个等级。
 * 非会员（NonMember）可以申请成为普卡会员。会员的等级根据其一年内累积的里程数进行调整。
 * 描述会员等级调整的状态图如图6-1 所示。现在采用状态（State）模式实现上述场景，得到如图6-2 所示的类图。
 */
public class StateDemo {
    public static void main(String[] args) {
    }
}

abstract class CState {
    public int flyMiles;    // 里程数
    abstract double travel(int miles, FrequentFlyer context); // 根据累积里程数调整会员等级
}

class CNoCustomer extends CState {  // 非会员
    public double travel(int miles, FrequentFlyer context) {
        System.out.println("Your travel wil not account for points");
        return miles;       // 不累积里程数
    }
}

class CBasic extends CState {       // 普卡会员
    public double travel(int miles, FrequentFlyer context) {
        if (context.flyMiles >= 25000 && context.flyMiles < 50000)
            context.setState(new CSilver());
        if (context.flyMiles >= 50000)
            context.setState(new CGold());
        return miles;
    }
}

class CSilver extends CState {      // 银卡会员
    public double travel(int miles, FrequentFlyer context) {
        if (context.flyMiles < 25000)
            context.setState(new CBasic());
        if (context.flyMiles >= 50000)
            context.setState(new CGold());
        return miles + 0.25 * miles;
    }
}

class CGold extends CState {        // 金卡会员
    public double travel(int miles, FrequentFlyer context) {
        if (context.flyMiles >= 25000 && context.flyMiles < 50000)
            context.setState(new CSilver());
        if (context.flyMiles < 25000)
            context.setState(new CBasic());
        return miles + 0.5 * miles;
    }
}

class FrequentFlyer {
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

