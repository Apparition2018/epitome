package knowledge.design.behavioral.state.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 状态模式：允许一个对象在其内部状态改变时改变它的行为。对象看起来似乎修改了它的类
 * Context: 环境角色
 * State: 抽象状态角色
 * ConcreteState: 具体状态角色
 * http://www.cnblogs.com/java-my-life/archive/2012/06/08/2538146.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class StateDemo {
    public static void main(String[] args) {
        VoteManager vm = new VoteManager();
        IntStream.rangeClosed(1, 9).forEach(i -> vm.vote("u1", "A"));
    }
}

interface VoteState {
    /**
     * 处理状态对应的行为
     *
     * @param user        投票人
     * @param voteItem    投票项
     * @param voteManager 投票上下文，用来在实现状态对应的功能处理的时候，
     *                    可以回调上下文的数据
     */
    void vote(String user, String voteItem, VoteManager voteManager);
}

class NormalVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 正常投票，记录到投票记录中
        voteManager.getMapVote().put(user, voteItem);
        System.out.println("恭喜投票成功");
    }

}

class RepeatVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 重复投票，暂时不做处理
        System.out.println("请不要重复投票");
    }

}

class SpiteVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 恶意投票，取消用户的投票资格，并取消投票记录
        String str = voteManager.getMapVote().get(user);
        if (null != str) {
            voteManager.getMapVote().remove(user);
        }
        System.out.println("你有恶意刷屏行为，取消投票资格");
    }

}

class BlackVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 记录黑名单中，禁止登录系统
        System.out.println("进入黑名单，将禁止登录和使用本系统");
    }

}

class VoteManager {
    // 持有状体处理对象
    private VoteState state = null;
    // 记录用户投票的结果，Map<String,String>对应Map<用户名称，投票的选项>
    private Map<String, String> mapVote = new HashMap<>();
    // 记录用户投票次数，Map<String,Integer>对应Map<用户名称，投票的次数>
    private Map<String, Integer> mapVoteCount = new HashMap<>();

    /**
     * 获取用户投票结果的 Map
     */
    public Map<String, String> getMapVote() {
        return mapVote;
    }

    /**
     * 投票
     *
     * @param user     投票人
     * @param voteItem 投票的选项
     */
    public void vote(String user, String voteItem) {
        // 1.为该用户增加投票次数
        // 从记录中取出该用户已有的投票次数
        Integer oldVoteCount = mapVoteCount.get(user);
        if (null == oldVoteCount) {
            oldVoteCount = 0;
        }
        oldVoteCount += 1;
        mapVoteCount.put(user, oldVoteCount);
        // 2.判断该用户的投票类型，就相当于判断对应的状态
        // 到底是正常投票、重复投票、恶意投票还是上黑名单的状态
        if (oldVoteCount == 1) {
            state = new NormalVoteState();
        } else if (oldVoteCount > 1 && oldVoteCount < 5) {
            state = new RepeatVoteState();
        } else if (oldVoteCount >= 5 && oldVoteCount < 8) {
            state = new SpiteVoteState();
        } else if (oldVoteCount > 8) {
            state = new BlackVoteState();
        }
        // 然后转调状态对象来进行相应的操作
        state.vote(user, voteItem, this);
    }
}