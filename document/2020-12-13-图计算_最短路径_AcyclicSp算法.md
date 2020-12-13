# 图计算：最短路径的AcyclicSP算法(纠正书中的错误)

[toc]

## 一、思想

根据拓扑排序进行遍历，进行“皮筋放松”操作。

算法（第四版）中的AcyclicSP算法代码不完整。导致其只对起点s为拓扑排序的第一个数值时才生效。

稍微修改，便可使用于图的任何顶点。

## 二、具体实现

[详细代码在GitHub上](https://github.com/hefrankeleyn/AlgorithmsBook/blob/master/AlgorithmsPro/src/main/java/com/hef/chapter4/spt/AcyclicSP.java)

```
/**
 * 图计算： 查询最短路径都树
 * @author lifei
 * @since 2020/12/13
 */
public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        Topological topological = new Topological(G);
        boolean beginFlag = false;
        if (topological.isDAG()){
            Iterable<Integer> order = topological.order();
            for (Integer v : order) {
                if (v==s && !beginFlag) {
                    beginFlag = true;
                }
                if (beginFlag) {
                    relax(G, v);
                }
            }
        }
    }

    private void relax(EdgeWeightedDigraph G, int v){
        for (DirectedEdge e : G.adj(v)) {
            if (distTo[e.to()] > distTo[e.from()] + e.weight()){
                distTo[e.to()] = distTo[e.from()] + e.weight();
                edgeTo[e.to()] = e;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e!=null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("tinyDAGEWD.txt");
        try {
            In in = new In(classPathResource.getFile().getCanonicalPath());
            EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
            int s= 5;
            AcyclicSP sp = new AcyclicSP(G, s);
            for (int v = 0; v < G.V(); v++) {
                if (sp.hasPathTo(v)){
                    System.out.print(String.format("%d->%d %.2f : ", s, v, sp.distTo(v)));
                    for (DirectedEdge e : sp.pathTo(v)) {
                        System.out.print(e + " ");
                    }
                    System.out.println();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

示例tinyDAGEWD.txt

```
8
13
5 4 0.35
4 7 0.37
5 7 0.28
5 1 0.32
4 0 0.38
0 2 0.26
3 7 0.39
1 3 0.29
7 2 0.34
6 2 0.40
3 6 0.52
6 0 0.58
6 4 0.93
```

