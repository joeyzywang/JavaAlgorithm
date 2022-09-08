package top.hard;

import java.util.Stack;

public class TrapRainWater_42 {
    //【一句话关键点】： 单调栈： 凹字的形态，栈内是凹字的左边，当前栈顶是凹字的最低点，进来的元素是凹字的最右边，如果栈是空的，没有凹的左边，那么直接break。
    //【单调栈】
    // 找下一个高的，就是单调递增栈，栈顶比栈内要低，当有一个比栈顶元素大的来了，就像一个字 ”凹“， 最低点就是栈顶， 左边就是 第一个比栈顶高的左边的元素，  进来的就是右边的高的地方。 （ min(进来的， 左边的) - 最低点 ）* distance 就是这个bar 能贡献的水。
    public int trap(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < height.length; i ++) {
            while(!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if(stack.isEmpty()) {
                    // 没有左边的那堵墙
                    break;
                }
                int distance = i - stack.peek() - 1;
                int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                res += distance * boundedHeight;
            }
            stack.push(i);
        }

        return res;
    }
}
