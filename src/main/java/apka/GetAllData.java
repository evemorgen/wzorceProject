package apka;

public class GetAllData{
    private GetSatInfo sat;

    public GetSatInfo getSat() { return this.sat; }

    public void setSat(GetSatInfo sat) { this.sat = sat; }

    private GetPosInfo pos;

    public GetPosInfo getPos() { return this.pos; }

    public void setPos(GetPosInfo pos) { this.pos = pos; }

    private String timestamp;

    public String getTimestamp() { return this.timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
