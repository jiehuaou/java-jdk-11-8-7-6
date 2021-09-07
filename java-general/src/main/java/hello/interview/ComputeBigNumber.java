package hello.interview;

public class ComputeBigNumber {

    // sum ( n1, n2 )
    public static void main(String[] args) {
        String s1 = "1000000000000000000000000000000000000000000000000000000345";
        String s2 =           "789000000000000000000000000000000000000000000700";
        int len1 = s1.length();
        int len2 = s2.length();
        int maxLen = Math.max(len1, len2);
        String s3 = "";
        int next = 0;
        for (int i = 0; i < maxLen; i++) {
            int a1 = 0;
            int a2 = 0;
            if((len1-1)>= i){
                char c = s1.toCharArray()[len1-i-1];
                a1 = c - '0';
            }
            if((len2-1)>=i){
                char c = s2.toCharArray()[len2-i-1];
                a2 = c - '0';
            }
            int add  = a1 + a2 + next;
            if(add<=9){
                String c = String.valueOf(add);
                s3 = c + s3;
                next = 0;
            }else{
                int x1 = add % 10;  // 13 -> 3
                int x2 = (int) add / 10 ; // 13/10 -> 1
                next =x2;
                String c = String.valueOf(x1);
                s3 = c + s3;
            }
        }
        if(next>0){
            String  c= String.valueOf(next);
            s3 = c + s3;
        }

        System.out.println("final=" + s3);

    }
}
