package com.skeet.consul.provider.mine.algoruthm;

import com.skeet.consul.provider.mine.algoruthm.entity.Node;
import com.skeet.consul.provider.mine.algoruthm.util.NodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL
 *
 * @author chengsj
 * @date 2020/10/15 19:22
 */
public class NodeNextToRight {

    public static void main(String[] args) {
        Integer[] nodes = new Integer[] {1,2,3,4,5,6,7};
        Node node = NodeUtil.buildNode(nodes);
        connect(node);
        System.out.println();
    }

    /**
     * 个人解
     *
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        Node firstNode = root;
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(root);
        while(firstNode != null && firstNode.left != null) {
            List<Node> nodeTempList = new ArrayList<>();
            for (int i = 0; i < nodeList.size(); i++) {
                Node fst = nodeList.get(i);
                fst.left.next = fst.right;

                if (i + 1 < nodeList.size()) {
                    Node scd = nodeList.get(i + 1);
                    fst.right.next = scd.left;
                }

                nodeTempList.add(fst.left);
                nodeTempList.add(fst.right);
            }

            nodeList.clear();
            nodeList.addAll(nodeTempList);
            firstNode = nodeList.get(0);
        }

        return root;
    }

    /**
     * 最优解-递归版
     *
     * @param root
     * @return
     */
    public static Node connect_Best(Node root) {
        if(root == null || root.left == null)
            return root;
        root.left.next = root.right;
        if(root.next != null){
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    /**
     * 最优解-迭代版
     *
     * @param root
     * @return
     */
    public static Node connect_Best2(Node root) {
        if (root == null) {
            return root;
        }

        Node pre = root;
        Node cur = null;

        while (pre.left != null) {
            cur = pre;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }

                cur = cur.next;
            }

            pre = pre.left;
        }

        return root;
    }
}
