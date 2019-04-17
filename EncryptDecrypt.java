import java.util.Scanner;

public class EncryptDecrypt {

	public static String encrypt(String pass) {
		
		String encrypted = new String();
		int[] ascii = new int[pass.length()];
		int index = 1;
		
		for (int i = 0; i < pass.length(); i++) {
			ascii[i] = (int)(pass.charAt(i));
		}	
		ascii[0]++;
		
		for (int i = 1; i < ascii.length; i++) {
			ascii[i] += ascii[i - 1];
		}
		
		while (index != pass.length()) {
			ascii[index] -= 26;
			
			if (ascii[index] >= 97 && ascii[index] <= 122)
				index++;
		}
		
		for (int i = 0; i < ascii.length; i++) {
			encrypted += Character.toString((char)ascii[i]);
		}
		return encrypted;
	}
	
	public static String decrypt(String encryptedPass) {
		
		String decrypted = new String();
		int[] ascii = new int[encryptedPass.length()];
		int index = 1;
		
		for (int i = 0; i < ascii.length; i++) {
			ascii[i] = (int)(encryptedPass.charAt(i));
		}
		
		for (int i = (ascii.length - 1); i > 0; i--) {
			ascii[i] -= ascii[i -1];
		}
		ascii[0]--;
		
		while (index < ascii.length) {
			ascii[index] += 26;
			
			if (ascii[index] >= 97 && ascii[index] <= 122) {
				index++;
			}
		}
		
		for (int i = 0; i < ascii.length; i++) {
			decrypted += Character.toString((char)(ascii[i]));
		}
		return decrypted;
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String pass = new String();
		boolean onlyLetters = false;
		
		System.out.print("Enter a message to encrypt (must contain only lowercase letters,\n"
				+ "uppercase letters will be automatically converted to lowercase letters): ");
		
		while (!onlyLetters) {
			pass = input.next();
			
			for (int i = 0; i < pass.length(); i++) {
				if (!Character.isLetter(pass.charAt(i))) {
					System.out.print("You need to enter only letters. Try again: ");
					onlyLetters = false;
					break;
				}
				else
					onlyLetters = true;
			}		
		}
		input.close();
		
		pass = pass.toLowerCase();
		
		System.out.println("Encrypted message: " + encrypt(pass));
		System.out.println("Decrypted message: " + decrypt(encrypt(pass)));
	}
}
