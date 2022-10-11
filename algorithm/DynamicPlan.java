package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态回归算法
 * 
 * 1.动态规划算法通常用于求解最优解问题； 先求解子问题，并把子问题结果保存下来，避免重复计算；
 * 2.采用动态规划算法的问题必须满足 最优化原理 和 无后效性
 * 2.1 最优化原理 一个最优策略的子策略一定是最优的；
 * 2.2 无后效性 每个状态都是对历史结果的“完整”总结
 */

public class DynamicPlan {
    public static void main(String[] args) {
        DynamicPlanSolution solution = new DynamicPlanSolution();

        System.out.println("test Q32");
        String s = "()()))((()))(()((";
        int res = solution.longestValidPartentheses(s);
        System.out.println(String.format("longest is: %d", res));

        System.out.println("test Q300");
        int[] nums300 = { 1, 3, 2, 5, 1 };
        int res300 = solution.longestIncreSubSeqLength(nums300);
        System.out.println(String.format("longest is: %d", res300));

        System.out.println("test Q508");
        int[] coins = { 5, 2, 1 };
        int res508 = solution.exchangeCash(coins, 8);
        System.out.println(String.format("change plan counts is: %d", res508));

        System.out.println("test Q647");
        String s647 = "abacaba";
        int res647 = solution.countSubStrings(s647);
        System.out.println(String.format("count sub string is: %d", res647));

        System.out.println("test Q64");
        int[][] nums64 = {
                { 1, 3, 1 },
                { 1, 5, 1 },
                { 4, 2, 1 },
        };
        int res64 = solution.computeShortestPath(nums64);
        System.out.println(String.format("shortest path is: %d", res64));

        System.out.println("test Q139");
        String s139 = "helloword";
        String[] words = { "hello", "word" };
        boolean res139 = solution.IsStringCanSplit(s139, words);
        System.out.println(String.format("is string can split: %s", res139));

        // test subString
        String tests = "abcdef";
        System.out.println(String.format("%s sub 2,4 is %s", tests, tests.substring(2, 4)));

        System.out.println("test 01 backpack");
        int cap = 10;
        int[] W = { 2, 3, 4, 5 };
        int[] V = { 1, 2, 10, 4 };
        int res01back = solution.backpack(W, V, cap);
        System.out.println(String.format("pack max value is: %s", res01back));

    }

}

class DynamicPlanSolution {
    /**
     * Q32 最长有效括号
     * 一个只包含()两种字符的字符串，找出最长有效括号；
     */
    public int longestValidPartentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length()];
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 > 0 ? dp[i - 2] + 2 : 2);
                } else if (s.charAt(i - 1) == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * Q300 最长递增子序列
     * 一个数组，找出最长递增子序列的长度
     */
    public int longestIncreSubSeqLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(dp[i], res);
        }

        return res;
    }

    /**
     * 508 零钱兑换
     * 给你一个整数数组coins表示不同面额的硬币，另给一个amount表示总额； 返回凑成总额的组合数
     * 这种类型的状态转移步数不固定 dp[i-coin]
     */
    public int exchangeCash(int[] coins, int amount) {
        if (coins.length == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1; // import

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 647 回文子串
     * 给一个字符串s 统计其中回文子串的数量
     * 
     * 二维的状态转移方程。 dp[i][j]不代表结果。 表示一个可取项
     */
    public int countSubStrings(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }

        // dp[i][j] 表示 s[i]-s[j] 是否为回文子串
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        // 单个字符也算回文串
        int result = s.length();
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 从右下角开始遍历 因为 dp[i][j] 依赖 dp[i+1][j-1]
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        // 相邻时不需要转移公示
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    result += 1;
                }
            }
        }

        return result;
    }

    /**
     * 118 杨辉三角
     * 给一个num 生成杨辉三角的前num行
     * 
     * 最基础的动态规划思想。 一步一步求解
     */
    public List<List<Integer>> generateYanhui(int nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums; i++) {
            // List列表的操作 add get
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 && j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1), ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }

    /***
     * 64 最小路径和
     * 给一个m*n的矩阵， 找出从左上角到右下角的最短路径和
     */
    public int computeShortestPath(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        // 二维数组里状态转移的依赖关系 决定了遍历顺序
        // dp[i][j] 依赖 dp[i-1][j] & dp[i][j-1] 时， 按正常的 0-m 0-n遍历即可

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 139 单词拆分
     * 给定一个字符串s,和一个字符串列表words； 计算s是否可以由words中的单词组成；
     * 
     * 状态定义 bool dp[i] = s[0-i]是否可以被组成
     * 状态转移方程 dp[i] = {word in words} or dp[i-word.length] && dp[i-word.length-i]
     * == word
     * begin: dp[0] = True
     */
    public boolean IsStringCanSplit(String s, String[] words) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (String word : words) {
                if (i - word.length() >= 0) {
                    String subString = s.substring(i - word.length(), i);
                    if (subString.equals(word)) {
                        dp[i] = dp[i - word.length()];
                    }
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }

        return dp[s.length()];
    }

    /**
     * 01背包问题
     * 假设背包容量为capacity; w[i]为物品重量 v[i]未物品价值; 求最多能装多少价值物品
     * 
     * 状态转移 dp[i][j] 前i(0-N)件商品 到背包限重为j(0-cap)时； 获得的最大的价值
     */
    public int backpack(int[] W, int[] V, int cap) {
        int[][] dp = new int[W.length + 1][cap + 1];

        // max
        // dp[i][j] = dp[i-1][j] 第i件商品不放背包
        // dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i]] + V[i]) 第i件商品放背包

        // dp[0][x] 表示一件也没有放 价值为0
        for (int i = 1; i <= W.length; i++) {
            for (int j = 0; j <= cap; j++) {
                int a = dp[i - 1][j];
                if (j - W[i - 1] < 0) {
                    dp[i][j] = a;
                } else {
                    int b = dp[i - 1][j - W[i - 1]] + V[i - 1];
                    dp[i][j] = Math.max(a, b);
                }
            }
        }
        printMatrix(dp);

        return dp[W.length][cap];
    }

    private void printMatrix(int[][] matrix) {
        for (int[] rows : matrix) {
            System.out.println(Arrays.toString(rows));
        }
        return;
    }

}
