package knowledge.design.structural.adapter.demo2;

import l.demo.Demo;

/**
 * 某软件系统中，已设计并实现了用于显示地址信息的类 Address，如图6-1 所示。
 * 现要求提供基于 Dutch 语言的地址信息显示接口，为了实现该要求并考虑到以后可能还会出现新的语言接口，
 * 决定采用适配器（Adapter）模式实现该要求。
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class AdapterDemo extends Demo {
    public static void main(String[] args) {
        Address address = new Address();
        DutchAddress adapter = new DutchAddressAdapter(address);
        adapter.street();
        adapter.postcode();
        adapter.city();
    }

    // 源角色
    private static class Address {
        public void block() {
            p("block");
        }

        public void zip() {
            p("zip");
        }

        public void plat() {
            p("plat");
        }
    }

    // 目标角色
    private static class DutchAddress {
        public void street() {
            p("street");
        }

        public void postcode() {
            p("street");
        }

        public void city() {
            p("city");
        }
    }

    // 适配器角色
    private static class DutchAddressAdapter extends DutchAddress {
        private Address address;

        DutchAddressAdapter(Address address) {
            this.address = address;
        }

        public void street() {
            address.block();
        }

        public void postcode() {
            address.zip();
        }

        public void city() {
            address.plat();
        }
    }
}
