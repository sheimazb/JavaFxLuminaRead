package com.melocode.lread;

public class Novella {
    private int id;
    private int pack_id;
    private String title;
    private String description;
    private String img;
    private String content;
    private String progress;
    public Novella (int id, int pack_id, String title, String description, String  img ,String content,String progress ){
        this.id=id;
        this.pack_id=pack_id;
        this.title=title;
        this.description=description;
        this.img=img;
        this.content=content;
        this.progress=progress;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPack_id() {
        return pack_id;
    }

    public void setPack_id(int pack_id) {
        this.pack_id = pack_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
