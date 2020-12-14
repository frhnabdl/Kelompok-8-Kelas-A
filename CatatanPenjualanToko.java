import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CatatanPenjualanToko{
	public static void main(String[] args) {		
		ArrayList<String> list = new ArrayList<String>(); //deklarasi arraylist masukkan barang		
		ArrayList<Integer> banyakList = new ArrayList<Integer>(); //deklarasi arraylist banyaknya masukkan barang		
		ArrayList<String> riwayat = new ArrayList<String>(); //deklarasi arraylist riwayat		
		
		Scanner scan = new Scanner(System.in); //deklarasi scanner		
		LocalDateTime dateTime = LocalDateTime.now(); //deklarasi waktu sekarang
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); //deklarasi format waktu
		
		String waktu = dateTime.format(formatter);					
		
		boolean cek = true;

		do{
			System.out.println("1. Masukkan barang");
			System.out.println("2. Catat barang terjual");
			System.out.println("3. Cek stok barang");
			System.out.println("4. Riwayat terjual");
			System.out.println("5. Exit");
			
			System.out.print("Pilih: ");
			byte pilih = scan.nextByte();
			
			System.out.println();
			
				if(pilih == 5){
					System.out.println("Terima kasih telah menggunakan program kami :)");
					cek = false;
				}
				else if(pilih == 1){				
					boolean cek2 = true;
					do{
						System.out.println("1. Masukkan barang");
						System.out.println("2. Exit");

						System.out.print("Pilih: ");
						pilih = scan.nextByte();
						System.out.println();

						if(pilih == 2){
							cek2 = false;
						}
						else{						
							System.out.print("Nama barang: ");
							String inputBarang = scan.next();
							System.out.print("Banyaknya: ");						
							int banyakInput = scan.nextInt();
							
							list.add(inputBarang);
							banyakList.add(banyakInput);
						}									
					}while(cek2);
				}
				else if(pilih == 2){
					System.out.print("Nama barang terjual: ");
					String barangTerjual = scan.next();
					System.out.print("Banyak terjualnya: ");
					int banyakTerjual = scan.nextInt();

					if(list.contains(barangTerjual)){
						int cekIndex = list.indexOf(barangTerjual);
						int kuranginStok = banyakList.get(cekIndex) - banyakTerjual; 
						banyakList.set(cekIndex, kuranginStok);
						
						riwayat.add("( " + waktu + " ) " + barangTerjual + " terjual sebanyak " + banyakTerjual + "pcs");	
						System.out.println("Catatan berhasil\n");
					}
					else{
						System.out.println("Barang tidak tersedia\n");
					}
				}
				else if(pilih == 3){
					System.out.println("Stok saat ini: ");
					for(int i = 0; i < list.size(); i++){					
						System.out.println((i+1) + ". " + list.get(i) + ": " + banyakList.get(i) + "pcs");					
					}
					System.out.println();
				}
				else if(pilih == 4){
					System.out.println("Riwayat barang terjual: ");
					for(int i = 0; i < riwayat.size(); i++){					
						System.out.println((i+1) + ". " + riwayat.get(i));					
					}
					System.out.println();
				}									
		}while(cek);
	
	}	
}

