package top.hard;

public class FirstMissingNumb {
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        int i = 0;
        while (i < length){
            // left move
            if(nums[i] == i + 1){
                i++;
            }else if(nums[nums[i] - 1] == nums[i] || nums[i] > length || nums[i] < i + 1){
                /*
                 当前数在本该在的位置上已经存在了，可以override 删除掉
                 nums[nums[i] - 1] == nums[i]

                 nums[i] > length
                 当前的数太大了，超过了数组的长度，最小的正整数可以用当前的index表示

                 nums[i] < i + 1
                 当前的数比当前的index还要小，不用存，因为左边的index （之前的）已经表示了这个最小的正整数
                 比如： [1, 2, 1] 最后那个1，不用存了，因为之前的 [1, 2] 的index已经表示1， 2 都存在了。


                 删除的方法是用最后的那个元素来override当前元素，然后长度-1.
                 */
                // 使用index 来保存当前的数 i
                // delete (override) and right move
                nums[i] = nums[length - 1];
                length--;
            }else{
                // 没有超过边界 （既不比当前的index小，又没有超过最大，并且在"正确位置"上并不存在。
                // 将这个数和它本该在的位置对调。

                // swap
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        FirstMissingNumb firstMissingNumb = new FirstMissingNumb();
        int[] arr = {1,2,5,3,3,3,5};
        System.out.println(firstMissingNumb.firstMissingPositive(arr));
    }
}
