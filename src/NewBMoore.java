import java.util.Scanner;
import java.util.Vector;

public class NewBMoore {
	
	private String Pattern = new String();
	private String Text = new String();
	private Vector<Integer> border = new Vector<Integer>();
	private int IdxMatch ;// relatif thd nol
	
	public int numberOfCompare;
	
	public NewBMoore(){
		IdxMatch = -1;
		numberOfCompare = 0;
	}
	
	public void setPattern(String myString){
		Pattern = myString;
		createBorderFunction();
	}
	
	public void setText(String myString){
		Text = myString;
	}
	
	public void createBorderFunction(){
		for (int i = Pattern.length()-2 ; i >= 0; i--){
			String pref = Pattern.substring(i+1,Pattern.length());
			//
			//System.out.println(Pattern.matches("^"+pref+"\\w*"));
			if(Pattern.matches("^"+pref+"\\w*")){
				border.add(Pattern.length()-i-1);
				//System.out.println("ini : "+pref);
			}
		}
	}
	
	private int getLongestPrefSuff(int Idx){
		int NumberOfMatch = Pattern.length() - Idx - 1;
		System.out.println("numberOfMatch : "+NumberOfMatch);
		int i = 0;
		while (i < border.size()){
			int length = border.elementAt(i);
			if(length <= NumberOfMatch){
				return length; 
			}
			i++;
		}
		return 0;
	}
	
	private int getIdxLastOccurence(String myString, char myChar){
		for (int i = myString.length()-1; i >= 0 ; i--){
			if(myString.charAt(i)==myChar)
				return i;
		}
		return -1;
	}
	
	public void startNewBM(){
		if(Pattern.length() <= Text.length()){
			// Panjang pattern < panjang text
			int i = Pattern.length()-1; // iterator untuk text
			int j = Pattern.length()-1; // iterator untuk pattern
			int length = 0;
			int markIdx = -99;
			boolean match = false;
			while ( i < Text.length() && !match){
				if(Text.charAt(i)!=Pattern.charAt(j)){
					length = getLongestPrefSuff(j);
					String subPattern = Pattern.substring(length,j+1);
					System.out.println("subpatern : "+subPattern);
					System.out.println(Text);
					for(int k= 0 ; k <= i - Pattern.length() ; k ++){
						System.out.print(" ");
					}
					System.out.println(Pattern);
					
					int idxChar = getIdxLastOccurence(subPattern,Text.charAt(i));
					System.out.println("  t  i : "+i+"  j : "+j);
					System.out.println("indexLastOcc : "+idxChar);
					Scanner scan =  new Scanner(System.in);
					//int temp = scan.nextInt();
					
					if(idxChar!= -1){
						//ditambah marker
						idxChar += length;
						
						// menandai idx yang pasti sama
						markIdx = i;
						
						// char ada di pattern
						i += Pattern.length() - (idxChar+1);
						j = Pattern.length()-1;
						length = getLongestPrefSuff(idxChar);
						System.out.println(idxChar+" niiih : " +length);
					}else{
						// menghilangkan tanda idx yang pasti sama
						markIdx = -99;
						
						int NumberOfMatch = Pattern.length() - j - 1;
						
						int delta = 0;
						if(length != 0)
							delta= NumberOfMatch - length;
						
						System.out.println(" delta "+delta+ "  length : "+length);
						//char tidak ada pada sisa pattern
						if(length != 0)
							i = Pattern.length()+i + delta;
						else
							i = Pattern.length()+i + delta + NumberOfMatch;
						j = Pattern.length()-1;
						System.out.println("ni  i : "+i+"  j : "+j);
					}
				}else{
					j--;
					i--;
				}
				if(i==markIdx){
					i --;
					j --;

					System.out.println("Masukd "+markIdx);
				}
				if(j == (-1 + length)){
					System.out.println("hereee "+i);
					match = true;
					IdxMatch = i+1 - length;
				}
				//System.out.println( Text.charAt(i)+"  === "+Pattern.charAt(j));
				//Not compare twice
				
				
				System.out.println(Text);
				for(int k= 0 ; k <= i - Pattern.length() ; k ++){
					System.out.print(" ");
				}
				System.out.println(Pattern);
				System.out.println("i : "+i+"  j : "+j);
				numberOfCompare++;
				System.out.println("  Masuk "+numberOfCompare);
			}
			//System.out.println("Masugfdbk "+j);
		}
	}
	
	//abacaabacaabcaabaabb
	// abcaab
	public void printBorderFunction(){
		System.out.println("Text    : "+Text);
		System.out.println("Pattern : "+Pattern);
		//System.out.println("border function : ");
		//for(int i = 1;i < border.size() ; i++){
			//System.out.println(i+". "+border.elementAt(i));
		//}
	}
	//bacbababaabcba
	//    ababa
	public static void main (String [] args){
		NewBMoore BM = new NewBMoore();
		//BM.setPattern("ababababca");
		//BM.setPattern("abcab");
		//BM.setText("abacaabadcabcabaabb");
		
		//BM.setText("a pattern matching algorithm");
		//BM.setPattern("rithm");
		
		//BM.setPattern("abcaab");
		//BM.setText("abacaabacaabcaabaabb");
		
		BM.setPattern("abcaba");
		BM.setText("afcabctabcabaxadcba");
		
		//BM.setPattern("abacab");
		//BM.setText("abaccsdikcnksdnccsdc[po[pkpcsdaaabbaababababacabacbabcabcbabcaaabadcabacabaabb");
		
		//BM.setPattern("ababa");
		//BM.setText("bacbababaabcba");
		
		//BM.setPattern("baaaaa");
		//BM.setText("aaaaaaaaa");
		
		//BM.setText("mencoba algoritma string matching yang baru");
		//BM.setPattern("baru");

		//BM.setPattern("abcaba");
		//BM.setText("afcabctabcabaxadcba");
		
		BM.printBorderFunction();
		
		BM.startNewBM();
		
		System.out.println("Indeks pertama : "+BM.IdxMatch);
		System.out.println("Jumlah perbandingan : "+BM.numberOfCompare);
		
	}
}
