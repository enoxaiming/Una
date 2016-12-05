package pabix.chickens.una.HTTPConnection;

/**
 * Created by JunHyeok on 2016. 11. 7..
 */

public class Repo {
    private int project_idx; //고유숫자
    private String wants; //태그
    private int view_count; //조회수
    private String launcher; //개설자
    private String projectName; //프로젝트 명
    private int like_count; //좋아요
    private String subscriber; //구독자
    private int isAvaliable; //사용가능
    private String finishDate; //마감날짜
    private String launchDate; //시작날짜
    private String id; //id
    private String contents; //내용
    private int Applicants; //참가자

    public int getIsAvaliable() {
        return isAvaliable;
    }

    public void setIsAvaliable(int isAvaliable) {
        this.isAvaliable = isAvaliable;
    }

    public int getProject_idx() {
        return project_idx;
    }

    public void setProject_idx(int project_idx) {
        this.project_idx = project_idx;
    }

    public String getWants() {
        return wants;
    }

    public void setWants(String wants) {
        this.wants = wants;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getLauncher() {
        return launcher;
    }

    public void setLauncher(String launcher) {
        this.launcher = launcher;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public int isAvaliable() {
        return isAvaliable;
    }

    public void setAvaliable(int avaliable) {
        isAvaliable = avaliable;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getApplicants() {
        return Applicants;
    }

    public void setApplicants(int applicants) {
        Applicants = applicants;
    }
}
