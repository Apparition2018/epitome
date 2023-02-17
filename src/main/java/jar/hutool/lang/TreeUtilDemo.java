package jar.hutool.lang;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://hutool.cn/docs/#/core/语言特性/树结构/树结构工具-TreeUtil">TreeUtil</a> 树结构工具
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/lang/tree/TreeUtil.html">TreeUtil api</a>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/lang/tree/TreeNode.html">TreeNode api</a>
 *
 * @author ljh
 * @since 2020/11/19 22:31
 */
public class TreeUtilDemo {

    /**
     * 结构：
     * 系统管理
     * |- 用户管理
     * |- 添加用户
     * 店铺管理
     * |- 商品管理
     * |- 添加商品
     * <p>
     * 数据库：
     * id	parentId    name	    weight
     * 1	    0	    系统管理	    5
     * 11	    1	    用户管理	    10
     * 111	    1	    用户添加	    11
     * 2	    0	    店铺管理	    5
     * 21	    2	    商品管理	    10
     * 221	    2	    添加添加	    11
     */
    public static void main(String[] args) {
        List<TreeNode<String>> treeNodeList = new ArrayList<>();
        treeNodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
        treeNodeList.add(new TreeNode<>("11", "1", "用户管理", 10));
        treeNodeList.add(new TreeNode<>("111", "11", "添加用户", 11));
        treeNodeList.add(new TreeNode<>("2", "0", "店铺管理", 5));
        treeNodeList.add(new TreeNode<>("21", "2", "商品管理", 10));
        treeNodeList.add(new TreeNode<>("221", "2", "添加商品", 11));

        List<Tree<String>> treeList = TreeUtil.build(treeNodeList, "0");
        System.out.println(treeList);


        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setIdKey("rid");
        // 最大深度
        treeNodeConfig.setDeep(3);

        List<Tree<String>> treeList2 = TreeUtil.build(treeNodeList, "0", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getWeight());
            tree.setName(treeNode.getName());
            // 扩展属性
            tree.putExtra("extraField", 666);
            tree.putExtra("other", new Object());
        });
        System.out.println(treeList2);
    }

}
