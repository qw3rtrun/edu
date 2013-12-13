import checkers.nullness.quals.NonNull;
import checkers.nullness.quals.Nullable;

public class Main {

	public static void f(@NonNull String str)
	{
		System.out.println("Hello, "+str.toString());
	}
	
	public static @NonNull String get1()
	{
		return "S";
	}
	
	public static @Nullable String get2()
	{
		return null;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@NonNull String str = get1();
		f(str);
	}

}
