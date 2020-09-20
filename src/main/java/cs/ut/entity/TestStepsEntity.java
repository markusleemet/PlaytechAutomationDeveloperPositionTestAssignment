package cs.ut.entity;

import java.util.List;

public class TestStepsEntity {
    List<String> testSteps;

    public TestStepsEntity(List<String> testSteps) {
        this.testSteps = testSteps;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        for (String step : testSteps) {
            int index = testSteps.indexOf(step);
            stringBuilder.append(String.format("\t%d-> %s\n", index + 1, step));
        }
        return stringBuilder.toString();
    }
}
