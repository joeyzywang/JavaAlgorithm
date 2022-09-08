package top.hard;

public class RegularExpressMatching_10 {
    //dp[i][j] 代表以s[i] 和 p[j]结尾的字符匹配，找出与下面三个的关系。考虑 . 和 *的作用。
    // dp[i-1]dp[j-1]
    // dp[i][j-1]
    // dp[i-1][j]

    // 分析p的j个字符的情况， 两种大的情况，*  和 不是*。
    // 不是* 比较容易分析，只有 相等（包括 . 的情况） 和 不等。
    // * 比较复杂，因为它可以换成3种情况，空， 1  和多。所以比较复杂。 然后，这三种请要结合j前面的那个字符是否和i相等的请， 因为* 有控制前导符的能力。

    // 由于有*的前导的功能，所以，会用到[j-2]的元素，所以初始化的时候为了不越界，需要首先考虑加一行一列，然后考虑初始化j = 1的情况
    /**
     1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
     2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
     3, If p.charAt(j) == '*':
     here are two sub conditions:
     1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
     2   if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == '.':
     dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
     or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
     or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     **/
    public boolean isMatch(String s, String p) {
        if(p.isEmpty()) return s.isEmpty();
        int sLength = s.length();
        int pLength = p.length();
        boolean[][] dp = new boolean[sLength + 1][pLength + 1];
        dp[0][0] = true;

        // 先初始化第0行 （也就是空字符串 与 pattern比较的情况， 不用想明白也行）
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }

        // 根据初始化的第0行，从底到上的构建这个dp，最后得到结果！需要从0， 0 开始。
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                } else {
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                        dp[i+1][j+1] = dp[i][j];
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        RegularExpressMatching_10 regularExpressMatching_10 = new RegularExpressMatching_10();
        System.out.println(regularExpressMatching_10.isMatch("ab", "ab*"));
    }
}
