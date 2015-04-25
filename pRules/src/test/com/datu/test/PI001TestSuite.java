package com.datu.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  PI001_1Test.class,
  PI001_2Test.class,
  PI001_3Test.class,
  PI001_4Test.class,
  PI001_5Test.class,
  PI001_6Test.class,
  PI001_7Test.class,
  PI001_8Test.class,
  PI001_9Test.class,
  PI001_14Test.class,
  PI001_17Test.class
})

public class PI001TestSuite {
  public static void main(String args[]) {
    System.out.println("starting to run PI001TestSuite");
    Result result = JUnitCore.runClasses(PI001TestSuite.class);
    for (Failure failure : result.getFailures()) {
       System.out.println(">> FAILED Testcases :"+failure.toString());
    }
  }
}

