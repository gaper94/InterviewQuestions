
public class Test 
{
	
	public static String find_sequence( String s1, String s2 )
	{
		// Generate lookup table
		int[][] dynamic_table = new int[s1.length() + 1][s2.length() + 1];		
		for( int i = 1; i < dynamic_table.length; i++ )
		{
			for( int j = 1; j < dynamic_table[i].length; j++ )
			{
				if( s1.charAt( i - 1 ) == s2.charAt( j - 1 ) )
				{
					dynamic_table[i][j] = 1 + dynamic_table[i - 1][ j - 1];
				}
				else
				{
					dynamic_table[i][j] = Math.max( dynamic_table[i][j - 1], dynamic_table[i - 1][j] );
				}
			}
		}
		
		// Assemble sequence
		int row_idx = s1.length();
		int column_idx = s2.length();
		String res = "";
		while( column_idx > 0 && row_idx > 0 )
		{
			if( dynamic_table[row_idx][column_idx] != dynamic_table[row_idx][column_idx - 1] )
			{
				res = Character.toString( s2.charAt(column_idx - 1) ) + res;
				row_idx--;
			}
			column_idx--;
		}
		
		return res;
	}
	
	public static void test_case( String s1, String s2, String expectedSequence )
	{
		String result = find_sequence( s1, s2 );
		if( result.equalsIgnoreCase(expectedSequence) )
		{
			System.out.printf( "[OK] LCS of [%s] and [%s] is [%s]\n", s1, s2, result );
		}
		else
		{
			System.out.printf( "[FAIL] LCS of [%s] and [%s], but [%s] is returned\n", s1, s2, result );
		}
	}

	public static void main(String[] args)
	{	
		test_case("A1B2C", "ABC", "ABC");
		test_case("ABDFHGFGH", "ABDFHGFGH", "ABDFHGFGH");
		test_case("ABDFHGFGH", "AQDPQRFS", "ADF");
		test_case("ABDFHGFGH", "AQDPQRFSABDFHGFGH", "ABDFHGFGH");
	}
}
