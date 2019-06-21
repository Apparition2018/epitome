package other.设计模式.structural.decorator.demo2;

/**
 * 某发票（Invoice）由抬头（Head）部分、正文部分和脚注（Foot）部分构成。
 * 现采用装饰（Decorator）模式实现打印发票的功能，得到如图6-1 所示的类图。
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        Invoice t = new Invoice();
        Invoice ticket;

        ticket = new FootDecorator((new HeadDecorator(t)));
        ticket.printInvoice();

        System.out.println("--------------------");

        ticket = new FootDecorator((new HeadDecorator(new Decorator(null))));
        ticket.printInvoice();
    }
}

class Invoice {
    public void printInvoice() {
        System.out.println("This is the content of the invoice");
    }
}

class Decorator extends Invoice {
    protected Invoice ticket;

    Decorator(Invoice t) {
        ticket = t;
    }

    public void printInvoice() {
        if (ticket != null) {
            ticket.printInvoice();
        }
    }
}

class HeadDecorator extends Decorator {

    HeadDecorator(Invoice t) {
        super(t);
    }

    public void printInvoice() {
        System.out.println("This is the header of the invoice!");
        ticket.printInvoice();
    }
}

class FootDecorator extends Decorator {

    FootDecorator(Invoice t) {
        super(t);
    }

    public void printInvoice() {
        ticket.printInvoice();
        System.out.println("This is the footnote of the invoice!");
    }
}
