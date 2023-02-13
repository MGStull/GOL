
public class NTCombination {
	String first;
	String second;
	//A with T, and G with C
	NTCombination(String a){
		this.setFirst(a);
		if(a=="T")
			this.setSecond("A");
		else if(a=="A")
			this.setSecond("T");
		else if(a=="G")
			this.setSecond("C");
		else if(a=="C")
			this.setSecond("G");
		else
			throw new RuntimeException("Type mismatch Allele + non capital AGTC letter");
	}
	NTCombination(String a, String b){
		this.setFirst(a);
		this.setSecond(b);
	}

	String getFirst() {return this.first;}
	String getSecond(){return this.second;}
	
	void setFirst(String a){this.first=a;}
	void setSecond(String a){this.second=a;}
	

}
