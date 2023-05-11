package ExperimentOne;

public class DoWhileTest {
    public static void main(String[] args) {
        int i=1;
        do
        {
            if(i%2==0)       //（1）
                System.out.print (i+" ");
            i++;
        }while(i<=20);        //（2）
    }
}
