import java.util.Random;
class SharedObject {
        volatile int receive=1;
        volatile int flag=0;
        volatile int send=0;
        synchronized public void increment_sender(){
            send++;
            return ;
        }
        synchronized public void increment_receive(){
            receive++;
            return;
        }
}
class Server implements Runnable{
    SharedObject so;
    public Server(SharedObject so){
        this.so=so;
    }
    public void run(){

        while(true) {
            if(so.flag==0){
                so.increment_sender();
                System.out.println("Sent data packet " + so.send);
            }
            else{
                System.out.println("Resending data packet " + so.send);
            }

            so.flag=1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                synchronized(so){
                   // so.increment_sender();
                    System.out.println("Received Acknowledment before time out ");
                }

            }
        }
    }
}
class Receiver implements Runnable{
    SharedObject so;
    public Receiver(SharedObject so){
        this.so=so;
    }
    public void run(){
        while(true) {
            while (so.flag == 1) {
                Random r = new Random();
                int num = r.nextInt(2);
                if (num == 0) {
                    continue;
                } else {
                    int arr[] = {0, 300};
                    int ind = r.nextInt(2);
                    try {
                        Thread.sleep(arr[ind]);
                    } catch (InterruptedException e) {
                        System.out.println("Receiver thread is interrupted ");
                    }
                    synchronized (so) {
                        System.out.println("Sent acknowledgement for " + so.receive);
                        so.flag = 0;
                        so.increment_receive();
                        //write code to wake up sender thread
                    }

                }
            }
        }


    }
}
public class Prac {

    public static void main(String[] args) {
        SharedObject so= new SharedObject();
        Thread s= new Thread(new Server(so));
        Thread r= new Thread(new Receiver(so));
        s.start();
        r.start();
        try{
            s.join();
            r.join();
        }
        catch(InterruptedException e){
            System.out.println("Main thread ends before the other threads join ");
        }

    }
}