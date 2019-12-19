import java.util.*;

public class LeeCode {
    /**
     *  前K个高频元素
     *
     *  使用优先级队列将元素进行排序，队列首部是最小的元素，每次进行比较当前元素和队列首部的元素的出现的次数
     *  不断的更新队列的元素  直到元素完全遍历
     *  最后队列中只有K个元素 就是要求的元素 ，为了保证他的顺序，进行逆序
     * @param nums 非空整形数组
     * @param k 频率
     * @return 链表
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
    }
    //小的次序在前面
    PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return map.get(o1) - map.get(o2);
        }
    });
        //保持第K大的
        for(int key :map.keySet()){
            queue.add(key);
            if(queue.size() > k){
                queue.poll();
            }
        }
        List<Integer> res= new ArrayList<>();
        while(!queue.isEmpty()){
            res.add(queue.poll());
        }
        Collections.reverse(res);
        return res;
}
    /**
     * A表示两个长度相同的字符串中相同位置的相同字符的个数，B表示不同位置相同字符的个数
     *
     * 使用数组表示当前字符出现的次数
     * A表示出现的A的次数，B表示出现的总次数，B-A表示B出现的次数
     * @param secret    原字符串
     * @param guess     目的字符串
     * @return  A的个数+A +B的个数+B
     */
    public String getHint(String secret, String guess) {
        int arr1[]={0,0,0,0,0,0,0,0,0,0};
        int arr2[]={0,0,0,0,0,0,0,0,0,0};
        int A = 0,B = 0;
        for(int i=0;i<guess.length();i++){
            if(guess.charAt(i)==secret.charAt(i)){
                A++;
            }
            arr1[guess.charAt(i)-'0']++;
            arr2[secret.charAt(i)-'0']++;
        }
        for(int i=0;i<10;i++){
            B+= (arr1[i]<arr2[i] ? arr1[i] : arr2[i]);
        }
        return A+"A"+(B-A)+"B";
    }
    public static void main(String[] args) {
        LeeCode l = new LeeCode();
        System.out.println(l.getHint("1122","2211"));
        System.out.println(l.topKFrequent(new int[]{1,1,1,2,2,3,},2));
    }
}
