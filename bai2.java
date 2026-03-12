import java.util.Scanner;
import java.util.Random;

public class bai2 {
    public static void main(String[] args) {
        // Cho r = 1, tim pi voi tam O(0,0)
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so luong diem gia dinh: ");
        int n = sc.nextInt();

        // Ban kinh r = 1
        double r = 1;

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

        // dienTich = pi * r**2 => pi = dienTich
        double pi = dienTich;

        // In ket qua
        System.out.println("Gia tri pi xap xi la: " + pi);

        sc.close();
    }
}
