package pabix.chickens.una.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by JunHyeok on 2016. 10. 20..
 */

public class ProjectVO extends RealmObject {
    @PrimaryKey
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

    public ProjectVO() {
    }

    public ProjectVO(int project_idx,String wants,int view_count,String launcher,String projectName,int like_count,String subscriber,int isAvaliable,String finishDate,String launchDate,String id,String contents,int applicants) {
        this.project_idx = project_idx;
        this.view_count = view_count;
        this.launcher = launcher;
        this.projectName = projectName;
        this.like_count = like_count;
        this.subscriber = subscriber;
        this.isAvaliable = isAvaliable;
        this.finishDate = finishDate;
        this.launchDate = launchDate;
        this.id = id;
        this.contents = contents;
        this.Applicants = applicants;
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
