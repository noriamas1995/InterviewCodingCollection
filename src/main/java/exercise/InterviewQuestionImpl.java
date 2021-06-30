package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import model.ListNode;
import model.Node;
import model.TreeNode;

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

  @Override
  public List<Integer> inorderTraversal(TreeNode root) {
    final String note = """
        A typical in-order traversal question.
        We use a wrapper method to complete the recursion.
        The sequence of in-order traversal is left-node-right.
        """;
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    inorderTraversal(root, res);
    return res;
  }

  private void inorderTraversal(TreeNode root, List<Integer> res) {
    if (root.left != null) {
      inorderTraversal(root.left, res);
    }

    if (root != null) {
      res.add(root.val);
    }

    if (root.right != null) {
      inorderTraversal(root.right, res);
    }
  }

  @Override
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    final String note = """
        If we want to traverse by the zig-zag level order then we have to modify the BFS traverse.
        Since the direction of appending numbers is changed at each level, we will also alter the flag for each level.
        Different flag will result in the difference of appending the node values.
        Since we only go through all the tree node once, the overall time complexity is O(N).
        """;
    List<List<Integer>> res = new ArrayList<>();

    if (root == null) {
      return res;
    }

    // traverse the tree using BFS
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    // the direction is left-to-right at the root level
    boolean isFromLeftToRight = true;

    while (!queue.isEmpty()) {
      List<Integer> temp = new ArrayList<>();
      int noOfLeaves = queue.size();
      // we control the direction of appending values on a level basis
      // the size of the current queue = how many elements are there at that depth
      while (noOfLeaves > 0) {
        TreeNode curr = queue.poll();

        if (isFromLeftToRight) {
          temp.add(curr.val);
        } else {
          temp.add(0, curr.val); // append values to the head if reversed direction
        }

        if (curr.left != null) {
          queue.offer(curr.left);
        }

        if (curr.right != null) {
          queue.offer(curr.right);
        }

        noOfLeaves--;
      }

      res.add(temp);
      isFromLeftToRight = !isFromLeftToRight; // invert the flag for each depth
    }
    return res;
  }

  @Override
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    final String note = """
        To understand the question we have to start with the pattern in the respective array,
        preorder is node-left-right and inorder is left-node-right.
        We may find that there is actually a recursive pattern that we can immediately see the root node from preorder,
        after that we look for that number in inorder array and then we can determine what are the elements on the left and right subtrees.
        We repeat this process for all roots in preorder array.
        Since we need to constantly search for the index of value in inorder array, we use a hashmap as the cache.
        The overall time complexity should be O(N) as well as space compexity.
        """;
    return buildTree(preorder, 0, inorder, 0, inorder.length - 1, buildInorderCache(inorder));
  }

  private Map<Integer, Integer> buildInorderCache(int[] inorder) {
    Map<Integer, Integer> res = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      res.put(inorder[i], i);
    }
    return res;
  }

  private TreeNode buildTree(int[] preorder, int preIndex, int[] inorder, int inStart, int inEnd,
      Map<Integer, Integer> cache) {
    if (preIndex > preorder.length - 1 || inStart > inEnd) {
      return null;
    }

    final int val = preorder[preIndex];
    final int indexOfValInInorder = cache.get(val);
    final int noOfNodesOnLeftSubTree = indexOfValInInorder - inStart;

    TreeNode root = new TreeNode(val);

    root.left = buildTree(preorder, preIndex + 1, inorder, inStart, indexOfValInInorder - 1, cache);
    // to get the preIndex of the right subtree, we should start from the original preIndex, skipping all nodes on the left subtree and increment by 1
    root.right = buildTree(preorder, preIndex + noOfNodesOnLeftSubTree + 1, inorder,
        indexOfValInInorder + 1, inEnd, cache);

    return root;
  }

  @Override
  public Node connect(Node root) {
    final String note = """
        Since we need to traverse all the nodes, the time complexity cannot be better than O(N).
        In fact, we can toggle the next pointer on a per level basis which naturally leads to a modified BFS.
        We cannot directly do curr.next = queue.peek() because we are still appending the child nodes into the queue.
        """;
    Queue<Node> queue = new LinkedList<>();
    if (root != null) {
      queue.offer(root);
    }

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Node> temp = new ArrayList<>(); // use a temporary list to hold the nodes within the level
      while (size > 0) {
        Node curr = queue.poll();
        temp.add(curr);
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
        size--;
      }
      connect(temp); // connect the nodes on this level
    }
    return root;
  }

  @Override
  public int kthSmallest(TreeNode root, int k) {
    final String note = """
        Given the tree is a BST, we have to utilize its properties.
        We know that the smallest node is the leave on the bottom left, the 2nd smallest node is its parent and the 3rd one is the parent's right node.
        We found this pattern is exactly the same as in-order traversal of a binary tree.
        Thus we can use a list to hold the traversed values, then the k-th smallest is just list[k-1].
        Also this method can be optimized to directly return when list size reaches k as the nodes behind k-th node won't be needed.
        """;
    List<Integer> nodes = new ArrayList<>();
    kthSmallest(nodes, root, k);
    return nodes.get(k - 1);
  }

  private void kthSmallest(List<Integer> nodes, TreeNode root, final int k) {
    if (nodes.size() >= k) {
      return;
    }
    if (root != null) {
      kthSmallest(nodes, root.left, k);
      nodes.add(root.val);
      kthSmallest(nodes, root.right, k);
    }
  }

  @Override
  public int numIslands(char[][] grid) {
    final String note = """
        The core idea is that for each island cell, we will search the full island and then increse the count by 1.
        When we find the rest of the island cells for that particular island cell, we also change the cell value to water to show that we already searched this cell.
        That is pretty much the terminate condition of the recursive call, we need to make sure the index is within range and the cell is still island not water.
        """;
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          searchGrid(i, j, grid);
          count++;
        }
      }
    }
    return count;
  }

  private void searchGrid(int i, int j, char[][] grid) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
      return;
    }
    grid[i][j] = '0';
    searchGrid(i - 1, j, grid); // search up
    searchGrid(i + 1, j, grid); // search down
    searchGrid(i, j - 1, grid); // search left
    searchGrid(i, j + 1, grid); // search right
  }

  private void connect(List<Node> temp) {
    // connect the nodes in the temporary list in sequence
    for (int i = 0; i < temp.size(); i++) {
      Node node = temp.get(i);
      if (i == temp.size() - 1) {
        node.next = null;
        return;
      }
      node.next = temp.get(i + 1);
    }
  }

  @Override
  public boolean isHappy(int n) {
    final String note = """
        If the number is happy then the sum of digit square will converge to 1 else it will endlessly loop.
        Thus we can cache its sum into a hash set, if we detected a loop i.e. inserting an already-existed sum, it means that the number is not happy.
        """;
    Set<Integer> cache = new HashSet<>();
    int sumOfSquare = 0;
    // break the loop if the sum becomes 1
    while (sumOfSquare != 1) {
      sumOfSquare = calculateSumOfDigitSquare(n);
      // if duplicate result is found then the number is unhappy
      if (!cache.add(sumOfSquare)) {
        return false;
      }
      n = sumOfSquare;
    }
    return true;
  }

  private int calculateSumOfDigitSquare(int n) {
    int sum = 0;
    int num = n;

    while (num > 0) {
      int digit = num % 10;
      sum += (int) Math.pow(digit, 2);
      num /= 10;
    }

    return sum;
  }

  @Override
  public int trailingZeroes(int n) {
    final String note = """
        For e.g. for 10! = 362880 we know that it has 1 trailing zero because it can be divided by 10 without a reminder.
        10 has prime factors of 2 and 5 only. So the answer we are looking for is the number of factors of 10 in n!.
        It also means we should figure out how many 5s and how many 2s are hiding within the sequence of numbers being multiplied together in the calculation for n!.
        However, there too many factors of 2 since each even number will have multiple ones. i.e. we should find Min(# of 5s, # of 2s) = # of 5s.
        The equation for calculating the number of multiples of 5 within n! is n / 5. For example, 10! = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10.
        But we need to be sure to count the additional 5s hiding in numbers like 25, 125, 625... like for 25 we need to add n/25 to the count too.
        Essentially finding counts of n/5^2, n/5^3, n/5^4 etc. is equivalent to divide n by 5 each time and get the count.
        """;
    int count = 0;
    while (n != 0) {
      int temp = n / 5;
      count += temp;
      n = temp;
    }
    return count;
  }
}
