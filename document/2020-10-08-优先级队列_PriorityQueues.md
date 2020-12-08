# 优先级队列

[toc]

## 一、数据结构：二叉堆（binary heap）

一个二叉堆是一个keys集合，这个keys集合是由一个完整的顺序堆的二叉树组成，按照等级顺序在数组中进行表示（不使用数组的第一项元素）。

> **Definition.** A binary heap is a collection of keys arranged in a complete heap-ordered binary tree, represented in level order in an array(not using the first entry).

为了简洁，从现在开始，当提起一个二叉堆（binary heap）的时候，删除“二叉（binary）”修饰符，使用术语堆（heap），

### 1.1 上下移动（travel up and down）

在一个堆中，一个角标为k的节点，它的父节点的位置是$\lfloor k/2 \rfloor$，相反两个孩子节点的角标分别为：2k和2k+1。通过在数组角标上做简单的算术运算，我们能向上和向下移动：为了从a[k]向上移动，设置k 等于 k/2，为了向下移动，设置k等于 2*k 或者 2 * k +1  。

完整的二叉树用数组（heaps 堆）表示是不易改变的数据结构，但是这已经足够的灵活，让我们实现高效的优先级队列。确切地说，我们将用这个数据结构开发“对数时间”（logarithmic-time）的插入（insert）和删除（remove）最大值的实现。

### 1.2 完全两叉树的属性

由于完全二叉树的属性，这个算法能够利用在树路径上上下移动的能力，不使用指针，还能保证对数的性能：

> 一个大小为N的完全二叉树，它的高为$\lfloor lgN \rfloor$

## 二、在堆中的算法（Algorithms  on heaps)

我们用一个长度为N+1的私有数组`pq[]`来表示一个大小为N的堆，`pq[0]`不使用，使用`pq[1]`到`pq[N]`。

### 2.1 对比和交换

```java
    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j])<0;
    }
    
    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
```

### 2.2  从下向上移动（恢复堆的状态）swim

```java
    /** bottom-up restoring heap order */
    private void swim(int k){
        while (k>1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }
```

### 2.3 从上向下移动（恢复堆的状态）sink

```java
    /** top-down restoring heap order */
    private void sink(int k){
        while (2*k<=N){
            int j = 2*k;
            if (j<N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    } 
```

### 2.4 插入一个值 Insert

```java
    /** insert a key into the priority queue */
    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }
```

### 2.5 移除一个最大值

```java
    /** return and remove the largest key */
    public Key delMax(){
        Key m = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        return m;
    }
```



