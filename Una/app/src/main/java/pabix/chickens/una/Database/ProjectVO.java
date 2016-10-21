package pabix.chickens.una.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by JunHyeok on 2016. 10. 20..
 */

public class ProjectVO extends RealmObject {
    @PrimaryKey
    private String name; //프로젝트 명
    private String host; //개설자
    private String contents; //내용
    private int startDate; //시작날짜
    private int finishDate; //마감날짜
    //TODO Realm StringList
    /*
    private String[] wants; //원하는 분야
    private String[] participants; //지원자
    */
    private int views; //조회수
    private int likes; //좋아요

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(int finishDate) {
        this.finishDate = finishDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
