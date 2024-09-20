package knowledge.design.pattern.gof.behavioral.state.demo;

import knowledge.design.pattern.gof.behavioral.state.demo3.StateDemo3;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 状态模式：允许一个对象在其内部状态改变时行为也发行改变，使其看起来像是改变了对象所属的类
 * <p>使用场景：行为随状态的改变而改变
 * 使用实例：
 * <pre>
 * 1 工作流、游戏、电商订单
 * 2 <a href="https://gupaoedu-tom.blog.csdn.net/article/details/121354228">org.springframework.statemachine</a>
 * </pre>
 * 角色：
 * <pre>
 * 上下文 Context：持有 State 的引用，并提供一个设置器用于接收新的 State
 * 状态部分：接收 Context 的引用，从而可以获取 Context 的信息，和触发 State 转移
 * 抽象状态 State：声明特定 State 的行为
 * 具体状态 ConcreteState
 * </pre>
 * 优点：符合单一职责原则<br/>
 * 缺点：一定程度违反开闭原则，扩展新状态只需增加 ConcreteState，但其它 ConcreteState 转移到新状态需要修改代码
 * <p>状态转换方式：
 * <pre>
 * 1 由 ConcreteState 负责状态之间的转换
 * 2 由 Context 负责状态之间的转换，此时相当于还充当了 StateManager 的角色 {@link StateDemo3}
 * </pre>
 * 有限状态机：Finite State Machine
 * <pre>
 * 1 状态 State
 * 2 事件 Event / 转移条件 Transition Condition：事件触发 State 的转移及 Action 的执行
 * 3 动作 Action：不是必须的
 * </pre>
 * 有限状态机实现方法：
 * <pre>
 * 1 分支逻辑法 (if-else)：可读性和可维护性差
 * 2 查表法：建议在 State 比较多，Action 简单的情况下使用
 *   定义：一维表示 State，二维表示 Event，交值表示 State 经过 Event 之后转移到的新 Sate 和需要执行的 Action
 *   缺点：只能表示 Action 非常简单的状态机
 * 3 状态模式：建议在 State 不多，Action 复杂的情况下使用
 * </pre>
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/state">State</a>
 * @see <a href="http://c.biancheng.net/view/1388.html">Java设计模式</a>
 * @see <a href="">设计模式之美：状态模式：游戏、工作流引擎中常用的状态机是如何实现的？</a>
 * @since 2020/9/26 2:51
 */
public class StateDemo {

    /**
     * <a href="https://refactoringguru.cn/design-patterns/state/java/example">媒体播放器根据当前的状态表现不同的控制行为</a>
     */
    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }

    /**
     * State
     */
    private static abstract class State {
        protected Player player;

        private State(Player player) {
            this.player = player;
        }

        protected abstract String onLock();

        protected abstract String onPlay();

        protected abstract String onNext();

        protected abstract String onPrevious();
    }

    /**
     * ConcreteState
     */
    private static class LockedState extends State {

        LockedState(Player player) {
            super(player);
            player.setPlaying(false);
        }

        @Override
        public String onLock() {
            if (player.isPlaying()) {
                player.changeState(new ReadyState(player));
                return "Stop playing";
            } else {
                return "Locked...";
            }
        }

        @Override
        public String onPlay() {
            player.changeState(new ReadyState(player));
            return "Ready";
        }

        @Override
        public String onNext() {
            return "Locked...";
        }

        @Override
        public String onPrevious() {
            return "Locked...";
        }
    }

    /**
     * ConcreteState
     */
    private static class ReadyState extends State {

        public ReadyState(Player player) {
            super(player);
        }

        @Override
        public String onLock() {
            player.changeState(new LockedState(player));
            return "Locked...";
        }

        @Override
        public String onPlay() {
            String action = player.startPlayback();
            player.changeState(new PlayingState(player));
            return action;
        }

        @Override
        public String onNext() {
            return "Locked...";
        }

        @Override
        public String onPrevious() {
            return "Locked...";
        }
    }

    /**
     * ConcreteState
     */
    private static class PlayingState extends State {

        PlayingState(Player player) {
            super(player);
        }

        @Override
        public String onLock() {
            player.changeState(new LockedState(player));
            player.setCurrentTrackAfterStop();
            return "Stop playing";
        }

        @Override
        public String onPlay() {
            player.changeState(new ReadyState(player));
            return "Paused...";
        }

        @Override
        public String onNext() {
            return player.nextTrack();
        }

        @Override
        public String onPrevious() {
            return player.previousTrack();
        }
    }

    /**
     * Context
     */
    private static class Player {
        private State state;
        private boolean playing = false;
        private final List<String> playlist = new ArrayList<>();
        private int currentTrack = 0;

        public Player() {
            this.state = new ReadyState(this);
            setPlaying(true);
            IntStream.rangeClosed(1, 12).forEach(i -> playlist.add("Track " + i));
        }

        public void changeState(State state) {
            this.state = state;
        }

        public State getState() {
            return state;
        }

        public void setPlaying(boolean playing) {
            this.playing = playing;
        }

        public boolean isPlaying() {
            return playing;
        }

        public String startPlayback() {
            return "Playing " + playlist.get(currentTrack);
        }

        public String nextTrack() {
            currentTrack++;
            if (currentTrack > playlist.size() - 1) {
                currentTrack = 0;
            }
            return "Playing " + playlist.get(currentTrack);
        }

        public String previousTrack() {
            currentTrack--;
            if (currentTrack < 0) {
                currentTrack = playlist.size() - 1;
            }
            return "Playing " + playlist.get(currentTrack);
        }

        public void setCurrentTrackAfterStop() {
            this.currentTrack = 0;
        }
    }

    private record UI(Player player) {
        private static final JTextField textField = new JTextField();

        public void init() {
            JFrame frame = new JFrame("Test player");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel context = new JPanel();
            context.setLayout(new BoxLayout(context, BoxLayout.Y_AXIS));
            frame.getContentPane().add(context);
            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
            context.add(textField);
            context.add(buttons);

            JButton play = new JButton("Play");
            play.addActionListener(e -> textField.setText(player.getState().onPlay()));
            JButton stop = new JButton("Stop");
            stop.addActionListener(e -> textField.setText(player.getState().onLock()));
            JButton next = new JButton("Next");
            next.addActionListener(e -> textField.setText(player.getState().onNext()));
            JButton prev = new JButton("Prev");
            prev.addActionListener(e -> textField.setText(player.getState().onPrevious()));
            frame.setVisible(true);
            frame.setSize(300, 100);
            buttons.add(play);
            buttons.add(stop);
            buttons.add(next);
            buttons.add(prev);
        }
    }
}
