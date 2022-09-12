package top.hard;

import java.util.Stack;

public class LargestRectangleHistogram_84 {
    // 木桶原理！！最短的那个板子决定了能装多少水。
    // 假设在找第i块板子能构成的最大的长方形。其实就是找i左边和右边第一个比它小（矮）的块的位置，l 和 r
    // 那么在l 和 r 之间的所有块的高度都是>=i的高度的， 那么木桶原理，用 高度i x （ r-l-1 ） 就是i能构造的最大的长方形。
    // 要实现这个，其实就是用到了单调栈，找 “i位置的下一个比” 它矮的位置

    /*
    用一个单调减栈，栈顶比栈内的元素都要大，从栈底到顶，像一个上升的楼梯。
    这样做的目的是，栈顶元素的Left 第一个比它矮的元素，就是第二个元素，而即将入栈的元素就是Right第一个比它矮的元素。
    所以，栈顶的 i 能构成的最大的Rectangle就一下能计算出来了。
     */

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxSize = Integer.MIN_VALUE;
        //错误1： 注意循环到到界外。
        for(int i = 0; i <= heights.length; i ++) {
            int currHeight;
            //错误1： 到界外之后，入栈一个最小元素。
            if(i == heights.length) {
                currHeight = -1;
            } else {
                currHeight = heights[i];
            }
            //错误2： 栈内都是index，所以最后需要用heights 去取真实的元素。
            while(!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int heightTemp = heights[stack.pop()];
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                maxSize = Math.max(maxSize, (i - leftIndex - 1) *  heightTemp);
            }
            stack.push(i);
        }
        return maxSize;
    }
}
