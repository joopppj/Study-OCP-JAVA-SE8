package test;
public class workingWithGenerics<T>{
	private T contents;
	public T emptyCrate(){
		return contents;
	}
	public void packCrate(T contents){
		this.contents=contents;
	}
}
