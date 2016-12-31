public enum EnumsAndNestClass{
	WINTER,SPRING,SUMMER,FALL;
	public static void main(String[] args){
		for(EnumsAndNestClass i:EnumsAndNestClass.values()) //value() return list of all values 
		System.out.println(i.name()+""+i.ordinal());  // name() and ordinal() return enums'names and their indexes
	}
}