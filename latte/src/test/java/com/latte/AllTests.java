package com.latte;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	LatteFreeTextTest.class, 
	LatteVarTest.class, 
	LatteForeachTest.class, 
	LatteIncludeTest.class, 
	LatteCommentTest.class, 
	LatteIfTest.class })
public class AllTests {

}
