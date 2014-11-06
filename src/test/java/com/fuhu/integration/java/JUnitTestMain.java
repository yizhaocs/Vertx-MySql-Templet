package com.fuhu.integration.java;

import org.junit.runner.RunWith;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({ JUnitOfStatusCheck.class, JUnitOfPerPackage.class, JUnitOfPerPackageAndUser.class, JunitOfFinalCheck.class })
public class JUnitTestMain {

}
