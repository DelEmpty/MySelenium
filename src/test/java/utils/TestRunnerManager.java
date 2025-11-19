package utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunnerManager {
    public static String getTags() throws IOException {
        String filepath = "src/test/resources/ExcelRun/RunExecution.xlsx";
        String sheetname = "Foglio1";

        List<String> testsToRun = ExcelUtils.getTestsFromExcel(filepath, sheetname);

        if (testsToRun.isEmpty()) {
            return "@NoTest"; //Tag da inserire di default se si vogliono eseguire i test dall'excel
        }

        // Giunzione dei vari tag secondo questa formattazione "@ + tag + or"
        return testsToRun.stream()
                .map(tag -> "@" + tag)
                .collect(Collectors.joining(" or "));
    }
}
