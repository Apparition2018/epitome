package knowledge.design.pattern.gof.behavioral.state.demo3;

/**
 * 一个屏幕放大镜工具，其具体功能如下：
 * <pre>
 * 1 第一次点击'放大镜'，屏幕放大一倍
 * 2 第二次点击'放大镜'，屏幕再放大一倍
 * 3 第三次点击'放大镜'，屏幕还原到默认大小
 * </pre>
 *
 * @author ljh
 * @since 2022/1/25 1:38
 */
public class StateDemo3 {

    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.onClick();
        screen.onClick();
        screen.onClick();
    }

    /**
     * Context & StateManager
     */
    private static class Screen {
        private State currentState;
        private final State normalState;
        private final State doubleState;
        private final State quadrupleState;

        public Screen() {
            this.normalState = new NormalState();
            this.doubleState = new DoubleState();
            this.quadrupleState = new QuadrupleState();
            this.currentState = normalState;
            this.currentState.display();
        }

        public void setState(State state) {
            this.currentState = state;
        }

        public void onClick() {
            if (this.currentState == normalState) {
                this.setState(doubleState);
                this.currentState.display();
            } else if (this.currentState == doubleState) {
                this.setState(quadrupleState);
                this.currentState.display();
            } else if (this.currentState == quadrupleState) {
                this.setState(normalState);
                this.currentState.display();
            }
        }
    }

    private static abstract class State {
        protected abstract void display();
    }

    private static class NormalState extends State {
        @Override
        public void display() {
            System.out.println("正常大小");
        }
    }

    private static class DoubleState extends State {
        @Override
        public void display() {
            System.out.println("双倍大小");
        }
    }

    private static class QuadrupleState extends State {
        @Override
        public void display() {
            System.out.println("四倍大小");
        }
    }
}
