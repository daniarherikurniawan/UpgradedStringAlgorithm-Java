import java.util.Scanner;
import java.util.Vector;

public class NewAlgorithm {

	   private String Pattern = new String();
	   private String Text = new String();
	   private Vector<Integer> border = 
		new Vector<Integer>();
	   private int IdxMatch; // relatif thd nol
	   public int numberOfCompare;

	   public NewAlgorithm() {
	      IdxMatch = -1;
	      numberOfCompare = 0;
	   }

	   public void setPattern(String myString) {
	      Pattern = myString;
	      createBorderFunction();
	   }

	   public void setText(String myString) {
	      Text = myString;
	   }

	   public void createBorderFunction() {
	      for (int i = Pattern.length() - 1; i > 0; i--) {
	         String pref = Pattern.substring(i, 
	               Pattern.length());
	         System.out.println(pref);
	         if (Pattern.matches("^" + pref + "\\w*")) {
	            border.add(Pattern.length() - i);
	            System.out.println(Pattern.length() - i);
	         }
	      }
	   }
	   /*
	    * function createBorderFunction() -> array of int
	    * KAMUS	:
	    * 	int [] ArrInt ;
	    * ALGORITMA :
	    * for (semua suffix pada pattern){
	    * 	if (suffix ke-i juga berupa preffix) then
	    * 		ArrInt.add(panjang suffix tersebut);
	    * }
	    * return ArrInt;
	    */
	   private int getLongestPrefSuff(int Idx) {
	      int NumberOfMatch = Pattern.length() - Idx - 1;
	      int i = 0;
	      while (i < border.size()) {
	         int length = border.elementAt(i);
	         if (length <= NumberOfMatch) {
	            return length;
	         }
	         i++;
	      }
	      return 0;
	   }
	   
	   /*
	    * function getLongestPrefSuff(int Idx) -> integer
	    * KAMUS :
	    * 	Int NumberOfMatch
	    * 
	    * ALGORITMA :
	    * 	for ( semua Border ke-i yang terurut mengecil)
	    * 		if (panjang Border ke-i <= NumberOfMatch ) then
	    * 			return panjangnya ;
	    * 		}
	    * 	}
	    * 	return 0;
	    */

	   private int getIdxLastOccurence(String myString, 
	         char myChar) {
	      for (int i = myString.length() - 1; i >= 0; i--) {
	         if (myString.charAt(i) == myChar)
	            return i;
	      }
	      return -1;
	   }
	   
   /* function getIdxLastOccurence(String myString,
    * 		char myChar) -> Integer
    * 
    * KAMUS :
    * 	integer i ;
    * ALGORITMA :
    * for(setiap char ke - i yang ada di myString dimulai dari belakang)
    * 	if ( char ke - i sama dengan myChar) then
    * 		return i;
    * 	endif
    * endfor
    * return -1;
    */
	   
	   public void startNewAlgorithm() {
	      if (Pattern.length() <= Text.length()) {
	         // Panjang pattern < panjang text
	         int i = Pattern.length() - 1; // iterator untuk text
	         int j = Pattern.length() - 1; // iterator untuk pattern
	         int length = 0;
	         int markIdx = -99;
	         boolean match = false;
	         while (i < Text.length() && !match) {
	            if (Text.charAt(i) != Pattern.charAt(j)) {
	               length = getLongestPrefSuff(j);
	               String subPattern = 
	                     Pattern.substring(length, j + 1);

	               int idxChar = 
	                     getIdxLastOccurence(subPattern,
	                     Text.charAt(i));

	               if (idxChar != -1) { // char ada di pattern
	                  idxChar += length; // ditambah marker
	                  markIdx = i; // menandai idx yang pasti sama
	                  i += Pattern.length() - (idxChar + 1);
	                  j = Pattern.length() - 1;
	                  length = getLongestPrefSuff(idxChar);
	               } else { // char tidak ada pada sisa pattern
	                  // menghilangkan tanda idx yang pasti sama
	                  markIdx = -99;
	                  int NumberOfMatch = Pattern.length() - j - 1;
	                  //merubah i
	                  if (length != 0) {
	                     i = Pattern.length() + 
	                        i + NumberOfMatch - length;
	                  } else
	                     i = Pattern.length() + 
	                        i + NumberOfMatch;
	                  //merubah j
	                  j = Pattern.length() - 1;
	               }
	            } else {
	               j--;  i--;
	            }
	            if (i == markIdx) { // tidak membandingkan 2 kali
	               i--;  j--;
	            }
	            if (j == (-1 + length)) { 
	               match = true;
	               IdxMatch = i + 1 - length;
	            }
	            numberOfCompare++;
	         }
	      }
	   }
	   /* if (j == (-1 + length)) { 
	    *   match = true;
	    *   IdxMatch = i + 1 - length;
	    * }
	    */
	 //aadaabbbcbb
	 //bbbcbb
	 //     bbbcbb

	// abacaabacaabcaabaabb
	// abcaab
	public void printBorderFunction() {
		System.out.println("Text    : " + Text);
		System.out.println("Pattern : " + Pattern);
	}

	// bacbababaabcba
	// ababa
	public static void main(String[] args) {
		NewAlgorithm BM = new NewAlgorithm();
		// BM.setPattern("ababababca");
		 //BM.setPattern("abcab");
		 //BM.setText("abacaabadcabcabaabb");

		 //BM.setText("a pattern matching algorithm");
		 //BM.setPattern("rithm");

		 //BM.setPattern("abcab");
		 //BM.setText("abacaabacaabcaabaabb");
		
		BM.setPattern("bbbcbb");
		BM.setText("aadaabbbcbb");
		//BM.setPattern("ccbcab");
		//BM.setText("aadaabccbcab");
		 //BM.setPattern("abacab");
		 //BM.setText("abacaabaccabacabaabb");

		 //BM.setPattern("abacab");
		 //BM.setText("abacaabadcabacabbaabb");

		// BM.setPattern("ababa");
		// BM.setText("bacbababaabcba");

		 //BM.setPattern("baaaaa");
		 //BM.setText("aaaaaaaaa");

		// BM.setText("algoritma string matching baru");
		// BM.setPattern("baru");

		//BM.setPattern("abcaba");
		//BM.setText("afcabctabcabaxadcba");

		BM.printBorderFunction();

		BM.startNewAlgorithm();

		System.out.println("Indeks pertama : " + BM.IdxMatch);
		System.out.println("Jumlah perbandingan : " + BM.numberOfCompare);

	}
}
