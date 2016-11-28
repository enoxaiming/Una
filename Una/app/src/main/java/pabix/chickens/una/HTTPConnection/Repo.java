package pabix.chickens.una.HTTPConnection;

/**
 * Created by JunHyeok on 2016. 11. 7..
 */

public class Repo {
    private boolean isSuccess;
    private String projectName; //프로젝트 명
    private int id;
    private String launcher; //개설자
    private String contents; //내용
    private String launchDate; //시작날짜
    private String finishDate;
    private boolean isAvailable;
    private int Applicants;
    private String wants;//마감날짜
    private int views; //조회수
    private int likes; //좋아요

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWants() {
        return wants;
    }

    public void setWants(String wants) {
        this.wants = wants;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getApplicants() {
        return Applicants;
    }

    public void setApplicants(int applicants) {
        Applicants = applicants;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getLauncher() {
        return launcher;
    }

    public void setLauncher(String launcher) {
        this.launcher = launcher;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
