package is.hi.g.hikersicelands.hikersicelands.APItests;

import com.intuit.karate.junit5.Karate;

class TestRunner {
    @Karate.Test
    Karate testAll() {
        return Karate.run("src/test/java/is/hi/g/hikersicelands/hikersicelands/APItests/").tags("~@ignore");
    }
}

