package pages.userdetails;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

public class AvatarPage extends BaseUserDetails {

    public final SelenideElement alertOnPageTopCssSelector = $("div.alert.alert-error");
    public final SelenideElement avatarTextOnPageHeaderCssSelector = $("div.page-header h2");
    public final SelenideElement imageCssSelector = $("div.avatar.avatar-48 img");
    public final SelenideElement removeImageButtonCssSelector = $("div.form-actions a.btn.btn-red");
    public final SelenideElement textAboveFileInputCssSelector = $("div.sidebar-content h3");
    public final SelenideElement imageInputCssSelector = $("input#form-avatar");
    public final SelenideElement submitButtonCssSelector = $("button.btn.btn-blue");


    // This function checks if the image exists.
    // But if no profile photo has been uploaded yet
    // The default is a big letter made in <div>
    public boolean doesAvatarImageExist()
    {
        if(imageCssSelector.exists()){
            return imageCssSelector.isImage();
        }else {
            return false;
        }
    }
    public void clickRemoveImageButton()
    {
        if(removeImageButtonCssSelector.exists())
        {
            removeImageButtonCssSelector.click();
        }
    }
    public void uploadImage(File file){
        imageInputCssSelector.uploadFile(file);
        submitButtonCssSelector.click();
    }

    public void removeFile(){
        if(removeImageButtonCssSelector.exists()){
            removeImageButtonCssSelector.click();
        }
    }


    // Files to be used in tests
    public enum TestFile {
        PROFILE_IMAGE("src/test/resources/images/Profile.jpg"),
        BIG_IMAGE("src/test/resources/images/30mbImage.jpg"),
        SMALL_IMAGE("src/test/resources/images/50kbImage.jpg"),
        EMPTY_TEXT_FILE("src/test/resources/images/EmptyTextFile.txt");

        private final String filePath;

        TestFile(String filePath) {
            this.filePath = filePath;
        }

        public File getFile() {
            return new File(filePath);
        }

        public String getFilePath() {
            return filePath;
        }
    }
}
