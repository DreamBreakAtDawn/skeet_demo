package com.skeet.consul.provider.mine.algoruthm.util;

import com.google.common.collect.Maps;
import com.skeet.consul.provider.mine.algoruthm.entity.Node;
import com.skeet.consul.provider.util.MapKit;

import java.util.Map;

/**
 * Desc:
 *
 * @author chengsj
 * @date 2020/10/15 19:25
 */
public class NodeUtil {
    public static Node buildNode(Integer[] nodes) {
        Map<Integer, Node> nodeMap = Maps.newHashMap();
        Node result = null;

        int length = nodes.length;
        for (int i = 0; i < length; i++) {
            Integer prt, left, right;
            Node prtNode = null;
            if ((prt = nodes[i]) != null) {
                prtNode = MapKit.putIfAbsent(nodeMap, i, new Node(prt));

                if (i == 0) {
                    result = prtNode;
                }
            }

            int leftIndex = i * 2 + 1;
            if (leftIndex < length && (left = nodes[leftIndex]) != null) {
                Node leftNode = MapKit.putIfAbsent(nodeMap, leftIndex, new Node(left));
                if (prtNode != null) {
                    prtNode.left = leftNode;
                }
            }

            int rightIndex = (i + 1) * 2;
            if (rightIndex < length && (right = nodes[rightIndex]) != null) {
                Node rightNode = MapKit.putIfAbsent(nodeMap, rightIndex, new Node(right));
                prtNode.right = rightNode;
            }
        }

        return result;
    }
}
