import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CatatanPenjualanToko{
	public static void main(String[] args) {		
		ArrayList<String> list = new ArrayList<String>(); //deklarasi arraylist masukkan barang		
		ArrayList<Integer> banyakList = new ArrayList<Integer>(); //deklarasi arraylist banyaknya masukkan barang	
		ArrayList<Integer> hargaList = new ArrayList<Integer>(); //deklarasi arraylist harga barang persatuan	
		ArrayList<String> riwayat = new ArrayList<String>(); //deklarasi arraylist riwayat
		long pendapatanWarung = 0;		

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
			String pilih = scan.nextLine();
			
			System.out.println();

			switch(pilih) {
				case "1":
					boolean cek2 = true;
					do{
						System.out.println("1. Masukkan barang");
						System.out.println("2. Exit");

						System.out.print("Pilih: ");
						pilih = scan.nextLine();
						System.out.println();

						if(pilih.equals("1")){
							System.out.print("Nama barang: ");
							String inputBarang = scan.nextLine().toLowerCase();
							System.out.print("Banyaknya: ");						
							String banyakInput = scan.nextLine();
							System.out.print("Harga per pcs atau per kg: ");
							String hargaBarang = scan.nextLine();
							
							if (!isAllDigit(banyakInput) || !isAllDigit(hargaBarang)) {
								System.out.println("\nMasukan BANYAK BARANG dan HARGA BARANG Harus Berupa ANGKA dan LEBIH DARI NOL (0)!!!\n");
							} else if (inputBarang.length() > 20) {
								System.out.println("\nNama Barang TIDAK BOLEH lebih dari 20 karakter\n");
							} else if (banyakInput.length() > 6) {
								System.out.println("\nJumlah barang TIDAK BOLEH MELEBIHI 999.999!!!\n");
							} else if (hargaBarang.length() > 6) {
								System.out.println("\nHarga barang TIDAK BOLEH MELEBIHI 999.999!!!\n");
							} else {
								list.add(inputBarang.toLowerCase());
								banyakList.add(Integer.parseInt(banyakInput));
								hargaList.add(Integer.parseInt(hargaBarang));

								System.out.println("\nProses BERHASIL!\n");
							}
						} else if (pilih.equals("2")){
							cek2 = false;
						} else{						
							System.out.println("\nPilihan yang Anda Masukan TIDAK BENAR!!!\n");
						}									
					}while(cek2);
				break;

				case "2":
					cek2 = true;
					String barangTerjual;
					String banyakTerjual;
					if (list.size() == 0) {
						System.out.println("\nMasukan barang terlebih dahulu pada Menu 1\n");
						break;
					}

					do {
						System.out.println("1. Masukkan barang terjual");
						System.out.println("2. Exit");

						System.out.print("Pilih: ");
						pilih = scan.nextLine();
						System.out.println();

						switch(pilih) {
							case "1":
								System.out.print("Nama barang terjual: ");
								barangTerjual = scan.nextLine().toLowerCase();
								System.out.print("Banyak terjualnya: ");
								banyakTerjual = scan.nextLine();

								if (!isAllDigit(banyakTerjual)) {
									System.out.println("\nBanyak terjual harus Berupa ANGKA!!!\n");
								} else if (!list.contains(barangTerjual)) {
									System.out.println("\nBarang tidak tersedia\n");
								} else if (banyakTerjual.length() > 6) {
									System.out.println("\nJumlah barang yang dimasukan TERLALU BANYAK!!!\n");
								} else if (banyakList.get(list.indexOf(barangTerjual)) < Integer.parseInt(banyakTerjual)) {
									System.out.println("\nJumlah Barang MELEBIHI JUMLAH STOCK!!!\n");
								} else {
									int cekIndex = list.indexOf(barangTerjual);
									int hargaSatuan = hargaList.get(cekIndex);
									int kuranginStok = banyakList.get(cekIndex) - Integer.parseInt(banyakTerjual); 
									banyakList.set(cekIndex, kuranginStok);
									
									pendapatanWarung += (long)hargaSatuan * (long)Integer.parseInt(banyakTerjual);
									String riwayatPenjualan = String.format("|%s| %-20s | %-6s | %-9s | %-13s|", waktu, barangTerjual, banyakTerjual, "Rp " + hargaSatuan, "Rp " + ((long)hargaSatuan * (long)Integer.parseInt(banyakTerjual)) );
									riwayat.add(riwayatPenjualan);	

									
									System.out.println("\nCatatan berhasil\n");
								}
							break;
							
							case "2":
								cek2 = false;
							break;

							default:
								System.out.println("\nPilihan yang Anda Masukan TIDAK BENAR!!!\n");
							break;
						}
						

						
							
					}while(cek2);
				break;

				case "3":
					System.out.println("Stok saat ini: ");
					System.out.println("\n--------------------------------------------------------------------------------");
					System.out.println("| No |      Nama Barang     | Jumlah Barang | Harga Satuan | Total Harga Barang|");
					System.out.println("--------------------------------------------------------------------------------");

					Long hargaStock = (long)0;
					for(int i = 0; i < list.size(); i++){	
						hargaStock += ((long)banyakList.get(i) * (long)hargaList.get(i));
						System.out.println(String.format("| %-2d | %-20s | %-13d | %-12s | %-17s |", (i+1), list.get(i), banyakList.get(i), "Rp " + hargaList.get(i), "Rp "+ ((long)banyakList.get(i) * (long)hargaList.get(i)) ));				
					}
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("\t\t\tTotal Harga Stock \t Rp " + hargaStock);
					System.out.println("--------------------------------------------------------------------------------\n");
					System.out.println();
				break;

				case "4":
					System.out.println("Riwayat barang terjual: ");
					System.out.println("\n--------------------------------------------------------------------------------");
					System.out.println("|   Waktu Terjual   |      Nama Barang     | jumlah | harga/pcs |  total hrg.  |");
					System.out.println("--------------------------------------------------------------------------------");
					for(int i = 0; i < riwayat.size(); i++){					
						System.out.println(riwayat.get(i));					
					}
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("\t\t\tPendapatan Warung \t Rp " + pendapatanWarung);
					System.out.println("--------------------------------------------------------------------------------\n");
				break;

				case "5":
					System.out.println("\nTerima kasih telah menggunakan program kami :)\n");
					cek = false;
				break;
				
				default:
					System.out.println("\nPilihan yang Anda Masukan TIDAK BENAR!!!\n");
				break;
			}									
		}while(cek);
	
	}

	private static boolean isAllDigit(String word) {
		word = word.toLowerCase();
		char[] arrChar = word.toCharArray();

		for (int i = 0; i < arrChar.length; i++) {
			if (arrChar[i] < 48 || arrChar[i] > 57) {
				return false;
			}
		}

		return true;
	}	
}


