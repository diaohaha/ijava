package algorithm;

// 滑动窗口

public class SlideWindow {

}

/**
 * 
 */
class Solution {
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
}
