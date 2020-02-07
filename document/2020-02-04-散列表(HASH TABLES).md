# 散列表(HASH TABLES)

[toc]

## 概要

哈希是一个关于平衡“时间和空间”的典型案例

## 一、哈希查询算法由两部分组成

### 1.1 一个哈希函数(a hash function)

> 哈希函数将要查询的键(key)转换成数组的角标。

面对的问题：多个不同的键(key)经过hash之后转换成相同数组角标

### 1.2 冲突解决过程(a collision-resolution process)

有两种方案解决冲突：各自的链(separate chaining)、直线的探测(linear probing)

 ## 二、哈希函数(Hash functions)

哈希函数依赖于键的类型(the key type)。严格地说，对于每种类型的key都需要一个不同的哈希函数。

> 我们寻找的哈希函数，即容易计算，又能让key均匀分布。

- 对于常见类型的key，我们能利用Java提供的默认实现；



> 假设有一个可以容纳M个键值对的数组

### 2.1 模哈希(modular hashing)

用任意一个为正数的键(k)除以数组的长度M，计算得到的余数，作为哈希函数的值。

> 这是一种非常有效的将key平均地分布在数组中（角标在0到M-1之间）

### 2.2 对于字符串

`M`的值为数组的长度，`R`的值是一个质数（例如：31）

```java
    private static int stringHashTest(String str, int M){
        int hash = 0;
        for (int i=0;i<str.length();i++){
            hash = (R+str.charAt(i))%M;
        }
        return hash;
    }
```

### 2.3 Java惯例：`hashCode()`和`equals()`

- 如果a.equals(b)相等，那么a.hashCode()必须等于b.hashCode();
- 如果hashCode()值不同，那么两个对象就不相等；
- 如果hashCode值相同，那么两个对象可能相等，也可能不相等，必须用equals()去决定；

**在好的性能是极其重要的情况下，你应该警觉正在使用的hash函数**



## 三、单独的链（separate chaining）

两个或多个键经过哈希函数得到相同的角标值。将这些碰撞的项链接到单独的链表中，这种方法称之为**单独的链（separate chaining）**

### 3.1 查询过程的两步：

（1） 通过hash函数发现那个列表包含key；

（2）按照顺序列表的顺序查询key；

### 3.2 代码实现

详情在github上：[SeparateChainingHashST](https://github.com/hefrankeleyn/AlgorithmsBook/blob/master/AlgorithmsPro/src/main/java/com/hef/algorithms/chapter3/item34/SeparateChainingHashST.java)

## 四、直线的探查

另一个实现哈表的方法是将N个键值对存放在一个长度为M（M>N）的哈希表中，依靠表中的空元素项去解决冲突。这样的方法被称为**开放寻址(open-addressing)**的哈希方法。

最简单的开发寻址方法被称之为**直线的探查（linear probing）**: 当这儿有一个冲突（通过哈希函数得到的表格角标已经被其他不同的key所占用），那么我们就需要检查表格中下一个项（将角标加1）。

**直线的探查（linear probing）**会遇到三种情况：

- 键等于查询的键：键存在
- 空位置（这个角标上没有键）：没有查到
- 键不等于查询的键：试着查询下一个

该算法的实现思路如下：

> 通过哈希函数计算出键（key0）所在表格的角标，检查查询到的键（key）是否和刚做哈希函数的键（key0）相等，继续(通过增加角标，当达到表格结尾的时候就返回表格的头)直到查到键（key0）或者查到一个空的表格项。

