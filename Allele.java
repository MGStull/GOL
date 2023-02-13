import java.util.ArrayList;

public class Allele {
	String name;
	ArrayList<NTCombination[]> genes;
	
	Allele(ArrayList<NTCombination[]> genes,String Name){
		this.setGenes(genes);
		this.setName(Name);
		
	}
	Allele(ArrayList<NTCombination[]> genes){
		this.setGenes(genes);
	}
	
	
	// GC mult divide
	// AT + -
	double geneReader(NTCombination[] readMe)
	{
		double sum=0;
		for(int i=0;i<readMe.length;i++)
		{
			if(readMe[i].getFirst()=="A")
			{
				sum++;
			}
			else if(readMe[i].getFirst()=="T")
			{
				sum--;
			}
			else if(readMe[i].getFirst() == "G")
			{
				sum*=sum;
			}
			else if(readMe[i].getFirst() == "C" && sum != 0)
			{
				sum /= sum;
			}
		}
		return sum;
	}
	
	ArrayList<NTCombination[]> getGenes(){return genes;}
	String getName(){return this.name;}
	void setGenes(ArrayList<NTCombination[]> a){genes=a;}
	void setName(String n){this.name=n;}
}
