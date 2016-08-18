//package TestNode;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class TestNode {
    public static void main(String args[]) {
        long st = System.currentTimeMillis();
//        Log.d("Entry", "Start Time = " + st);
        System.out.println("Start Time = " + st);
        for(String name : testNodes) {
            initDataBase();
            List<String> result = new ArrayList<>();
            for(Map.Entry<Node, List<Node>> entry : mDataBase.entrySet()) {
                if(entry.getKey().key.equals(name)) {
                    getNodes(result, entry.getKey());
                    break;
                }
            }
            Collections.sort(result);
            System.out.println("             " + name + " result = " + result);
//            Log.d("Entry", "             " + name + " result = " + result);
        }

        long et = System.currentTimeMillis();
        System.out.println("End Time = " + et + " 耗时 " + (et - st) + "ms");
//        Log.d("Entry", "End Time = " + et + " 耗时 " + (et - st));
    }

    private static void getNodes(List<String> markedNames, Node toCheckNode) {
        if(toCheckNode == null || markedNames == null || toCheckNode.isMarked)
            return;
        markedNames.add(toCheckNode.key);
        toCheckNode.isMarked = true;

        if(toCheckNode.isLeaf) {
//            markedNames.add(toCheckNode.key);
//            toCheckNode.isMarked = true;
            return;
        }
        List<Node> children = mDataBase.get(toCheckNode);
        for(Node child : children) {
            getNodes(markedNames, child);
        }

//        return;
    }

    private static class Node {
        Node() {
            key = "";
            isLeaf = false;
            isMarked = false;
        }
        Node(String key) {
            this.key = key;
            isLeaf = false;
            isMarked = false;
        }
        @Override
        public int hashCode() {
//            Log.d("Entry", "hashCode=" + key.hashCode());
            return key == null ? -1 : key.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            Node des = (Node)o;
            if(des == null || des.key == null  || key == null)
                return false;
            return key.equals(des.key);
        }

        String key;
        boolean isLeaf;
        boolean isMarked;
    }
    private static void initDataBase() {
        mDataBase.clear();
        Node n1 = new Node("P1");
        Node n2 = new Node("P2");
        Node n3 = new Node("P3");
        Node n4 = new Node("P4");
        Node n5 = new Node("P5");
        Node n6 = new Node("P6");
        Node n7 = new Node("P7");

        List<Node> P1 = new ArrayList<>();
        P1.add(n2);
        P1.add(n3);
        mDataBase.put(n1, P1);

        List<Node> P2 = new ArrayList<>();
        P2.add(n4);
        P2.add(n5);
        mDataBase.put(n2, P2);

        List<Node> P3 = new ArrayList<>();
        P3.add(n4);       ///dead cycle
        P3.add(n6);
        P3.add(n7);
        mDataBase.put(n3, P3);

        List<Node> P4 = new ArrayList<>();
        P4.add(n3);
        mDataBase.put(n4, P4);

        List<Node> P5 = new ArrayList<>();
        P5.add(n5);
        mDataBase.put(n5, P5);

        List<Node> P6 = new ArrayList<>();
        P6.add(n6);
        mDataBase.put(n6, P6);

        List<Node> P7 = new ArrayList<>();
        P7.add(n7);
        mDataBase.put(n7, P7);
    }
    private static Map<Node, List<Node>> mDataBase = new HashMap<>();
    private static String[] testNodes = {
            "P1","P2","P3","P4","P5","P6","P7", "P8"
    };
}