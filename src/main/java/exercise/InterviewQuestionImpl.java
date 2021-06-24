package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.ListNode;

public class InterviewQuestionImpl implements InterviewQuestion {

  @Override
  public List<List<Integer>> threeSum(int[] nums) {
    final String note = """
        We can change the equation A + B + C = 0 to A = -(B + C).
        So we just have to loop for A and use a while loop with 2 pointers to find B and C,
        we can compare the target to the actual sum to see which direction we are moving.
        One important note is that we cannot use List.contains to eliminate the duplicate solutions since it's slow.
        """;
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length < 3) {
      return res;
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      // skip duplicate target number
      // cannot compare for "if nums[i] == nums[i+1] continue" because possible solutions can be missed
      if (i != 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      final int target = -nums[i];
      int j = i + 1;
      int k = nums.length - 1;
      while (j < k) {
        final int sum = nums[j] + nums[k];
        if (sum < target) {
          j++;
        } else if (sum > target) {
          k--;
        } else {
          List<Integer> temp = new LinkedList<>();
          temp.addAll(List.of(nums[i], nums[j], nums[k]));
          res.add(temp);
          j++;
          k--;
          // avoid duplicate solutions, move index to next unique number
          while (j < k && j != i + 1 && nums[j] == nums[j - 1]) {
            j++;
          }
          while (j < k && k != nums.length - 1 && nums[k] == nums[k + 1]) {
            k--;
          }
        }
      }
    }
    return res;
  }

  @Override
  public void setZeroes(int[][] matrix) {
    final String note = """
        We can keep track of an O(MN) space boolean matrix to see which cells are 0, but it is unnecessary work.
        We only need to track the first row and column to know which row/col should we revert to 0, for e.g. 2 boolean arrays.
        If it has to be done in-place with O(1) space, then we can directly modify the original matrix by using the first row and column as a flag.
        """;
    final int row = matrix.length;
    final int col = matrix[0].length;
    boolean firstRowHasZero = false;
    boolean firstColHasZero = false;

    for (int j = 0; j < col; j++) {
      if (matrix[0][j] == 0) {
        firstRowHasZero = true;
        break;
      }
    }

    for (int i = 0; i < row; i++) {
      if (matrix[i][0] == 0) {
        firstColHasZero = true;
        break;
      }
    }

    // loop the rest of numbers so the index starts from 1 not 0
    // if 0 is found then change the flag to 0
    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    // check the first row to set 0 for columns
    // noted that the index should start from 1 since we will deal with [0,0] in the end
    for (int j = 1; j < col; j++) {
      if (matrix[0][j] == 0) {
        makeColAllZero(matrix, j);
      }
    }

    // check the first column to set 0 for the rows
    for (int i = 1; i < row; i++) {
      if (matrix[i][0] == 0) {
        makeRowAllZero(matrix, i);
      }
    }

    // set 0 for first row and column
    if (firstColHasZero) {
      makeColAllZero(matrix, 0);
    }
    if (firstRowHasZero) {
      makeRowAllZero(matrix, 0);
    }
  }

  private void makeRowAllZero(int[][] matrix, int rowIndex) {
    for (int j = 0; j < matrix[0].length; j++) {
      matrix[rowIndex][j] = 0;
    }
  }

  private void makeColAllZero(int[][] matrix, int colIndex) {
    for (int i = 0; i < matrix.length; i++) {
      matrix[i][colIndex] = 0;
    }
  }

  @Override
  public List<List<String>> groupAnagrams(String[] strs) {
    final String note = """
        A very intuitive approach is to use a hashmap whereby the key is the sorted string and value is the list of strings that belong to this category.
        By using this approach, the space complexity will be O(NK) where N is the length of array and K is the length of the longest string.
        The time complexity will be O(N K logK) because sorting requires O(K log K) and we have to do it for N elements.
        """;
    if (strs.length == 0) {
      return new ArrayList<>();
    }
    Map<String, List<String>> map = new LinkedHashMap<>();

    for (int i = 0; i < strs.length; i++) {
      String str = strs[i];
      char[] curr = str.toCharArray();
      Arrays.sort(curr);
      String key = new String(curr);
      map.putIfAbsent(key, new ArrayList<>());
      map.get(key).add(str);
    }
    return new ArrayList<>(map.values());
  }

  @Override
  public int lengthOfLongestSubstring(String s) {
    final String note = """
        A very intuitive way is to directly using sliding-windows methodology.
        We maintain a sliding window of unique characters and use two pointers left and right to move the window.
        If the current character is unique, we need to increase the counter and frequency,
        else we have to keep shrinking the window by increasing the left index until the window contains only unique characters.
        Noted that we also need to maintain the frequency chart and counter when reducing window size.
        """;
    if (s == null || s.length() == 0) {
      return 0;
    }

    int l = 0;
    int r = 0;
    int count = 0;
    int max = Integer.MIN_VALUE;

    // alternatively we can simply use an array[128] to store ASCII characters
    Map<Character, Integer> frequency = new HashMap<>();

    while (r < s.length()) {
      // add current character to the frequency table
      char c = s.charAt(r);
      frequency.putIfAbsent(c, 0);
      frequency.put(c, frequency.get(c) + 1);
      count++;
      // if not unique characters found then need to shrink the window until the window only contains unique characters
      if (frequency.get(c) > 1) {
        while (frequency.get(c) > 1) {
          char left = s.charAt(l);
          l++;
          frequency.put(left, frequency.get(left) - 1);
          count--; // reduce the count when rolling back
        }
      }
      max = Math.max(count, max); // update the max counter
      r++; // right index will increase anyway
    }
    return max;
  }

  @Override
  public String longestPalindrome(String s) {
    final String note = """
        The very naive idea is to use a sliding-window but in that case to maintain a palindromic window is troublesome.
        It then comes the idea of expanding the string from center,
        so we will loop the string for the center of palindrome, but there are two cases, odd length or even length.
        If the palindrome has odd length then it means we expand from 1 character, vice versa for even length.
        By this way we can easily know the length of the longest palindrome, while it is still difficult to directly return the substring directly.
        The best way is to compute the start and end index of the substring found according to its maximum length.
        """;
    if (s == null || s.length() == 0) {
      return "";
    }

    int start = 0, end = 0; // hold substring index
    for (int center = 0; center < s.length(); center++) {
      // odd length palindrome substring
      int longestOdd = getLengthExpandedFromCenter(center, center, s);
      // even length palindrome substring
      int longestEven = getLengthExpandedFromCenter(center, center + 1, s);

      int length = longestOdd > longestEven ? longestOdd : longestEven;

      // the computation of the substring index
      if (length > end - start) {
        start = center - (length - 1) / 2;
        end = center + length / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  private int getLengthExpandedFromCenter(int start, int end, String s) {
    // very important point here is we cannot directly use the passed value of start and end here.
    // Since we are going to modify the value of index, it will affect the main loop without using new variables.
    int left = start, right = end;
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }
    // by right it is right - left + 1 but we already move too much so need to - 2 too
    return right - left - 1;
  }

  @Override
  public boolean increasingTriplet(int[] nums) {
    final String note = """
        The essential idea is to keep tracking a smaller number and a larger number until a number that is larger than both is found.
        """;
    int smaller = Integer.MAX_VALUE;
    int bigger = Integer.MAX_VALUE;
    for (int num : nums) {
      // num < smaller < bigger
      if (num <= smaller) {
        smaller = num;
        // smaller < num < bigger
      } else if (num <= bigger) {
        bigger = num;
        // smaller < bigger < num
      } else {
        return true;
      }
    }
    return false;
  }

  @Override
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    final String note = """
        Since the numbers stored in the linked list are in reverse order, we can directly loop from the beginning.
        When we loop we sum up the values and keep tracking the digit and carrier until both l1 and l2 reach the end.
        Noted that we have to add a new node in the end if the sum of last digit exceeds 10.
        The time complexity will be O(max(m,n)) where m, n are the length of l1 and l2.
        """;
    // create the head of new linked list first since l1 and l2 are guaranteed to have first element
    int sum = l1.val + l2.val;
    int digit = sum % 10;
    int carrier = sum / 10;
    /* alternatively can use a dummy head:
    *  ListNode dummyHead = new ListNode(0);
       ListNode curr = dummyHead; */
    ListNode head = new ListNode(digit);
    ListNode curr = head;
    l1 = l1.next;
    l2 = l2.next;
    // the stop condition should be when both l1 and l2 reach the end
    while (l1 != null || l2 != null) {
      // 3 cases according to the l1 and l2 status
      sum =
          l1 == null ? l2.val + carrier : l2 == null ? l1.val + carrier : l1.val + l2.val + carrier;
      digit = sum % 10;
      carrier = sum / 10;
      curr.next = new ListNode(digit);
      curr = curr.next;

      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
    }
    // if after both nodes reach the end but carrier still > 0 then add a new node
    if (carrier > 0) {
      curr.next = new ListNode(carrier);
    }
    return head;
  }

  @Override
  public ListNode oddEvenList(ListNode head) {
    final String note = """
        Since the requirement is O(1) space and O(N) time, we can only loop through the list once without any other data structures.
        The idea is to break the odd and even nodes into 2 lists and eventually add head of even list to the tail of odd list.
        It is better to use the even node as the terminate condition since it is always after the odd node.
        """;
    // case when there are 0, 1 and 2 node(s) in the list
    if (head == null || head.next == null || head.next.next == null) {
      return head;
    }

    ListNode odd = head;
    ListNode even = head.next;
    ListNode evenHead = even;

    // the terminate condition cannot be && since if even is null, even.next will throw NullPointerException
    while (even != null && even.next != null) {
      // change the pointer and advance to the next node
      odd.next = even.next;
      odd = odd.next;
      even.next = odd.next;
      even = even.next;
    }
    // add the head of even node to the tail of odd nodes
    odd.next = evenHead;
    return head;
  }

  @Override
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    final String note = """
        If two linked lists have intersection, when we loop till the end node, they must share the same node by reference.
        However, in order to find the intersection node, we have to know how long are the linked lists first.
        We have to make them equally long by moving the head pointer of the longer list ahead for K times where K is their difference in length.
        After we trim the longer list, we can start the loop from the beginning because we know that at a certain point they are guaranteed to intersect.
        We traverse both linked lists so the time complexity is O(M + N) where M and N are their length.
        """;
    // compute length and check if they intersect
    ListNode currA = headA;
    int lengthA = 1;
    while (currA.next != null) {
      lengthA++;
      currA = currA.next;
    }

    ListNode currB = headB;
    int lengthB = 1;
    while (currB.next != null) {
      lengthB++;
      currB = currB.next;
    }

    // if the last node not equal then no intersection
    if (currA != currB) {
      return null;
    }

    ListNode longer = lengthA > lengthB ? headA : headB;
    ListNode shorter = lengthA > lengthB ? headB : headA;

    // move the pointer forward for the difference in length
    for (int i = 0; i < Math.abs(lengthA - lengthB); i++) {
      longer = longer.next;
    }

    // start moving together from the beginning until they intersect
    while (longer != shorter) {
      longer = longer.next;
      shorter = shorter.next;
    }
    return longer; // return either node
  }
}
