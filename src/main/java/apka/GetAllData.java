package apka;

/**
 * Created by MICHALLL on 17.02.2017.
 */
public class GetAllData {
    class Sat {
        private String sat_in_view;
        private String sat_used;

        public String getSat_in_view() {
            return sat_in_view;
        }

        public void setSat_in_view(String sat_in_view) {
            this.sat_in_view = sat_in_view;
        }

        public String getSat_used() {
            return sat_used;
        }

        public void setSat_used(String sat_used) {
            this.sat_used = sat_used;
        }
    }
    class pos{
        private float latitude_seconds;
        private float longitude_seconds;
        private float longitude;
        private float longitude_minutes;
        private float latitude;
        private float latitude_minutes;

        public float getLatitude_seconds() {
            return latitude_seconds;
        }

        public void setLatitude_seconds(float latitude_seconds) {
            this.latitude_seconds = latitude_seconds;
        }

        public float getLongitude_seconds() {
            return longitude_seconds;
        }

        public void setLongitude_seconds(float longitude_seconds) {
            this.longitude_seconds = longitude_seconds;
        }

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }

        public float getLongitude_minutes() {
            return longitude_minutes;
        }

        public void setLongitude_minutes(float longitude_minutes) {
            this.longitude_minutes = longitude_minutes;
        }

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public float getLatitude_minutes() {
            return latitude_minutes;
        }

        public void setLatitude_minutes(float latitude_minutes) {
            this.latitude_minutes = latitude_minutes;
        }
    }
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
