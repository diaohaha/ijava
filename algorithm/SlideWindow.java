package algorithm;

import java.util.Arrays;

// 滑动窗口
// 滑动窗口用来处理 连续子序列 连续子数组 问题 并且有滑动时机
// 将嵌套的循环问题，转换为单循环问题，降低时间复杂度。

public class SlideWindow {
    public static void main(String[] args) {
        int[] arr = { 1, 3, -4, 2, 4, -1, 2 };
        Solution s = new Solution();
        // int sum = s.maxSubstrings(arr);
        int sum = s.maxSubseqSum(arr);
        System.out.println(sum);

        System.out.println("===============");

        System.out.println(String.join(",", Arrays.toString(arr)));
        sum = s.maxKsubseqSum(arr, 3);
        System.out.println(sum);
    }
}

/**
 */
class Solution {
    /**
     * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
     * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
     */
    public int numberOfSubstrings(String s) {
        int answer = 0;
        int[] counts = new int[3];
        char[] chars = s.toCharArray();
        for (int left = 0, right = 0; right < chars.length; right++) {

            counts[chars[right] - 'a']++;
            while (counts[0] > 0 && counts[1] > 0 && counts[2] > 0) {
                counts[chars[left++] - 'a']--;
                answer += chars.length - right;
            }
        }
        return answer;
    }

    // int 是基本数据类型，int 变量存储的是数值。

    // 最长子序列之和 不能使用滑动窗口
    // dynm
    // 当窗口扩大的时候可能遇到负数，窗口中的值也就可能增加也可能减少，这种情况下不知道什么时机去收缩左侧窗口
    public int maxSubseqSum(int[] arr) {
        int answer = 0;
        int[] result = new int[arr.length];

        answer = arr[0];
        result[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            result[i] = Math.max(result[i - 1] + arr[i], arr[i]);
            answer = Math.max(answer, result[i]);
        }
        return answer;
    }

    // 给定一个整数数组，计算长度为 'k' 的连续子数组的最大总和。
    public int maxKsubseqSum(int[] arr, int k) {
        int answer = 0;
        if (arr.length < k) {
            return 0;
        }

        int left = 0, right = k - 1;
        int nowAnswer = Arrays.stream(Arrays.copyOfRange(arr, left, right + 1)).sum();
        answer = nowAnswer;

        // 虽然这个是固定窗口 也用可变窗口的方式写

        // 右窗口先滑
        for (; right < arr.length; right++) {
            //
            while (right - left > k - 1) {
                nowAnswer = nowAnswer + arr[right] - arr[left++];
                answer = Math.max(answer, nowAnswer);
            }
        }

        return answer;
    }
}
