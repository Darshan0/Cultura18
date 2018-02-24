package in.co.cultura.cultura18.Model;

public class SponsorObject {
    private String type;
    private String logoUrl;
    private String level;

    SponsorObject(String type, String logoUrl, String level){
        this.level = level;
        this.logoUrl = logoUrl;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getLevel() {
        return level;
    }
}
