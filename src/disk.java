import java.lang.reflect.Array;
import java.util.*;

public class disk {
    ArrayList<Integer> queue = new ArrayList<>();
    int head;
    int max;

    disk(int h, int m) {
        head = h;
        max = m;
    }

    public void initialize(int n) {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            int x = s.nextInt();
            queue.add(x);
        }
        Collections.sort(queue);
    }

    public int less_than(int ind) {
        int left_values = queue.get(ind) - head;
        System.out.println("abs(  "+queue.get(ind)+"- "+head+" )");
        left_values = Math.abs(left_values);
        for (int i = ind; i > 0; i--) {
            int diffrence = queue.get(i) - queue.get(i - 1);
            System.out.println("abs(  "+queue.get(i)+"- "+queue.get(i - 1)+" )");
            diffrence = Math.abs(diffrence);
            left_values += diffrence;
        }
        left_values += queue.get(0);
        System.out.println("abs(  "+queue.get(0)+"- 0  )");
        return left_values;
    }

    public int greater_than(int ind) {
        int right_values = queue.get(ind) - 0;
        System.out.println("abs(  "+queue.get(ind)+"- 0  )");
        for (int i = ind + 1; i < queue.size(); i++) {
            int diffrence = queue.get(i) - queue.get(i - 1);
            System.out.println("abs(  "+queue.get(i)+"- "+queue.get(i - 1)+"  )");
            diffrence = Math.abs(diffrence);
            right_values += diffrence;
        }
        return right_values;
    }

    public int scan() {
        int total_movement = 0;
        System.out.println("Scan algorithm Starts ::");
        for (int i = queue.size() - 1; i >= 0; i--) {
            if (queue.get(i) <= head) {
                total_movement = less_than(i);
                int x=total_movement;
                total_movement += greater_than(i + 1);
               int y=total_movement-x;
                System.out.println("Total movement = "+x+"  +  "+y+"  = "+ total_movement);
                break;
            }
        }
        return total_movement;
    }

    public int right_values_for_c_scan(int ind) {


        System.out.println(" (  "+queue.get(ind)+" - "+head +" )");
      int right_values = queue.get(ind) -head;

        for (int  i =ind+1;i < queue.size() ; i++) {
            int diffrence = queue.get(i) - queue.get(i - 1);
            System.out.println("abs(  "+queue.get(i)+"  - "+queue.get(i - 1)+"  )");
            diffrence = Math.abs(diffrence);
            right_values += diffrence;
        }
        System.out.println("abs(  "+max+"- "+queue.get(queue.size() - 1)+"  )");
        right_values+=max-queue.get(queue.size()-1);
        return right_values;

    }
    public int less_than_c_scn(int m){
        System.out.println("abs(  "+max+"- 0   )");
        int left_movement=max;
        System.out.println("abs(  "+queue.get(0)+"- 0  )");
        left_movement+=queue.get(0);
        for(int i=1;i<=m;i++){
            int diffrence = queue.get(i) - queue.get(i - 1);
            System.out.println("abs(  "+queue.get(i)+"  - "+queue.get(i - 1)+"  )");
            diffrence = Math.abs(diffrence);
            left_movement += diffrence;
        }
        return left_movement;
    }

    public int c_scan() {
        int total_movement = 0;
        System.out.println("c-Scan algorithm Starts ::");
        for (int i = queue.size() - 1; i >= 0; i--) {
            if (queue.get(i) <= head) {
                total_movement =right_values_for_c_scan(i + 1);
                int x=total_movement;
                total_movement +=less_than_c_scn(i);
                int y=total_movement-x;
                System.out.println("Total movement = "+x+"  +  "+y+"  = "+ total_movement);

                break;
            }
        }
        return total_movement;
    }

    int new_algo() {
        System.out.println("new optimized  algorithm Starts ::");
        int total_movement = queue.get(0);
        System.out.println("( = "+queue.get(0)+"  - 0 )" );

        for (int i = 1; i < queue.size(); i++) {
            int difference = queue.get(i) - queue.get(i - 1);
            System.out.print("abs(  "+queue.get(i)+"  - "+queue.get(i - 1)+"  )"+" + ");
            difference = Math.abs(difference);
            total_movement += difference;
        }
        System.out.println("  = "+total_movement );

        return total_movement;
    }

    public void optimal_solution(int x, int y, int z) {
        if (x < y && x < z) {
            System.out.println("The optimal solution is to use the new optimized algorithm  algorithm");
        } else if (y < x && y < z) {
            System.out.println("The optimal solution is to use the s-scan algorithm");

        } else
            System.out.println("The optimal solution is to use scan algorithm");
    }

    public static void main(String[] args) {
        int head, max;
        System.out.println("Enter the head number and max value ");
        Scanner s = new Scanner(System.in);
        head = s.nextInt();
        max = s.nextInt();
        disk d = new disk(head, max);
        System.out.println("Enter the size of the queue");
        int queue_size = s.nextInt();
        d.initialize(queue_size);
        int x = d.new_algo();
        int y = d.scan();
        int z = d.c_scan();
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        d.optimal_solution(x, y, z);
    }
}