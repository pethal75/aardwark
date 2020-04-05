package com.paragon;

import org.junit.jupiter.api.Test;

class ParagonMainTest {

	@Test
	void test() {
		String args1[] = { "data/paragon1.txt" };
		
		ParagonMain.main(args1);

		String args2[] = { "data/paragon-err1.txt" };
		
		ParagonMain.main(args2);

		String args3[] = { "data/paragon2.txt" };
		
		ParagonMain.main(args3);

		String args4[] = { "data/paragon-err2.txt" };
		
		ParagonMain.main(args4);

		String args5[] = { };
		
		ParagonMain.main(args5);

		String args6[] = { "data/paragon4.txt" };
		
		ParagonMain.main(args6);

	}

}
