package tpo.lab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Page {
    protected final WebDriver driver;

    public boolean isFinishedDownloading() {
        return driver.getCurrentUrl()
                     .startsWith(ConfProperties.getProperty("albumPage"));
    }
}
