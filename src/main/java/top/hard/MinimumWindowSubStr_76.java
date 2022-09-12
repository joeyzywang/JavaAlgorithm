package top.hard;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubStr_76 {
    /**
     【滑动窗口 --- 通用模板】
     自己总结的模板。 在读了https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
     之后，有原作者 vs 一个评论者， 思想基本一直。但是原作者就是一个 Map来记录目标字符串的字符数，而评论者是用两个Map， 一个当做final是目标串的快照（不修改）， 另外一个是窗口的快照（不断修改，更新）。

     两者的原理都是一样的， 下面的解法就包括了两个人的。 COMMENT掉的是评论者的，没有comment的我根据原作者的修改的，更好理解点。

     滑动窗口的原理： 1。 先把目标字符串”快照“下来，记录每个字符有多少个。 2. 两个指针，一个开头，一个结尾。结尾先挪动，开头固定。然后，比较当前字符，是否在target里，如果在，就”记录下“（要么更新原字符串快照，要么拿一个窗口Map来更新， 两人的区别）
     3. 如果发现当前的字符已经能”匹配“ / ”覆盖“ 所有的 target的字符了， 就可以开始”收网“了。 两种情况： 要不你的窗口太大了， 要不是”完美的“答案。
     4. 所以在收网的时候， 用while循环，只要当前的窗口还能”覆盖“ 所有的target， 就不断的减小窗口，同时更新”记录“ （要么用一个matches 遍历， 要么是那个 ”窗口快照“）

     **/

    public String minWindow(String s, String t) {
        // corner cases
        if (t.length() > s.length()) {
            return "";
        }

        // build the t char map (check the duplicates)
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        // sliding window (from s left to right)
        int l = 0;
        int r = 0;
        int matches = 0;

        int minLen = s.length() + 1;
        int minR = s.length() - 1;
        int minL = 0;

        while (r < s.length()) {
            // get the char in the right position
            // check tMap to keep matching characters in t
            // keep sliding
            char rChar = s.charAt(r++);
            if (tMap.containsKey(rChar)) {
                tMap.put(rChar, tMap.get(rChar) - 1);
                // get rid of duplicates chars
                if (tMap.get(rChar) == 0)
                    matches++;
            }
            //【错误点】 不是t的长度 而是 tMap的size
            while (matches == tMap.size()) {
                // if the window cover all chars in t update minLen
                if (r - l < minLen) {
                    minLen = (r - l);
                    minR = r;
                    minL = l;
                }
                // get the left char and reduce the window
                char lChar = s.charAt(l++);
                if (tMap.containsKey(lChar)) {
                    tMap.put(lChar, tMap.get(lChar) + 1);

                    if (tMap.get(lChar) > 0)
                        matches--;
                }
            }

        }

        return minLen == s.length() + 1 ? "" : s.substring(minL, minR);
    }
}
