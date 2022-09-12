package top.hard;
/**
 if (aMid < bMid) Keep [aRight + bLeft]
 else Keep [bRight + aLeft]
 【一句话】： 关键是根据两个数组的mid的值来判断median在哪里。 上面就是公式。

 首先检查两个数组的长度和，如果为偶数，需要算两个中位数，然后除2，如果为奇数，直接算中位数（长度+1）/2。

 算中位数的辅助方法是分治法。

 **/
public class MedianTwoSortedArrays_4 {
}
