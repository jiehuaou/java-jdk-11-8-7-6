package hello;

public class Test01 {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer(10);
        for (int i = 0; i < 10; i++) {
            if(i!=0){
                sb.append(",");
            }
            sb.append(i);
        }

        System.out.println(sb.toString());
    }
}
