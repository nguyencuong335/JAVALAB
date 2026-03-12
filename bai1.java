import java.util.Scanner;
import java.util.Random;

public class bai1 {
    public static void main(String[] args) {
        // Chi cho duy nhat r, tim dien tich hinh tron tam O(0,0).
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so luong diem gia dinh: ");
        int n = sc.nextInt();

        System.out.print("Nhap ban kinh hinh tron: ");
        double r = sc.nextDouble();

        // Random n diem nam trong hinh vuong canh 2r
        Random rand = new Random();
        int count = 0;

        for (int i = 0; i < n; i++) {
            double x = -r + rand.nextDouble() * (r + r);
            double y = -r + rand.nextDouble() * (r + r);
            
            if (x * x + y * y <= r * r) {
                count++;
            }
        }

        double canh = r + r;
        // Tinh dien tich
        double dienTich = ((double) count / n) * canh * canh;

        // In ket qua
        System.out.println("Dien tich hinh tron tam O(0,0) ban kinh " + r + " la: " + dienTich);

        sc.close();
    }
}
