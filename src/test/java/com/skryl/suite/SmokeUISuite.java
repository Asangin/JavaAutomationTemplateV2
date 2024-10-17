package com.skryl.suite;

import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectPackages({
        "com.skryl.ui",
})
//@IncludeTags("smoke")
public class SmokeUISuite {
}
