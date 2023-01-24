package com.simplekitchen.project.dao.entity.city;

import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.entity.city.api.City;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "city")
public class CityImpl implements City {

    /**
     * уникальный идентификатор города
     */
    @Id
    @Column
    @GeneratedValue
    private Long id;

    /**
     * название города
     * */
    @Column
    private String cityName;

    /**
     * название области
     * */
    @Column
    private String regionName;

    /**
     * название улицы
     * */
    @Column
    private String streetName;

    /**
     * номер дома
     * */
    @Column
    private Long houseNumber;

    /**
     * номер подъезда
     * */
    @Column
    private Long entranceNumber;

    /**
     * номер квартиры
     * */
    @Column
    private Long flatNumber;


    /**
     *
     * */
    @OneToOne(mappedBy = "city")
    private UserImpl user;

    public CityImpl() {
    }

    public CityImpl(String cityName) {
        this.cityName = cityName;
    }

    public CityImpl(String cityName, String regionName, String streetName, Long houseNumber, Long entranceNumber, Long flatNumber, UserImpl user) {
        this.cityName = cityName;
        this.regionName = regionName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.entranceNumber = entranceNumber;
        this.flatNumber = flatNumber;
        this.user = user;
    }

    public CityImpl(Long id, String cityName, String regionName, String streetName, Long houseNumber, Long entranceNumber, Long flatNumber, UserImpl user) {
        this.id = id;
        this.cityName = cityName;
        this.regionName = regionName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.entranceNumber = entranceNumber;
        this.flatNumber = flatNumber;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long uuid) {
        this.id = uuid;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public Long getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Long houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public Long getEntranceNumber() {
        return entranceNumber;
    }

    public void setEntranceNumber(Long entranceNumber) {
        this.entranceNumber = entranceNumber;
    }

    @Override
    public Long getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Long flatNumber) {
        this.flatNumber = flatNumber;
    }

    public UserImpl getUser() {
        return user;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityImpl)) return false;
        CityImpl city = (CityImpl) o;
        return Objects.equals(getId(), city.getId()) && Objects.equals(getCityName(), city.getCityName()) && Objects.equals(getRegionName(), city.getRegionName()) && Objects.equals(getStreetName(), city.getStreetName()) && Objects.equals(getHouseNumber(), city.getHouseNumber()) && Objects.equals(getEntranceNumber(), city.getEntranceNumber()) && Objects.equals(getFlatNumber(), city.getFlatNumber()) && Objects.equals(getUser(), city.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCityName(), getRegionName(), getStreetName(), getHouseNumber(), getEntranceNumber(), getFlatNumber(), getUser());
    }

    @Override
    public String toString() {
        return "CityImpl{" +
                "uuid=" + id +
                ", cityName='" + cityName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                ", entranceNumber=" + entranceNumber +
                ", flatNumber=" + flatNumber +
                ", user=" + user +
                '}';
    }

    public static CityImpl.CityImplBuilder builder() {
        return new CityImpl.CityImplBuilder();
    }

    public static class CityImplBuilder {
        private Long id;
        private String cityName;
        private String regionName;
        private String streetName;
        private Long houseNumber;
        private Long entranceNumber;
        private Long flatNumber;
        private UserImpl user;

        CityImplBuilder() {
        }

        public CityImpl.CityImplBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public CityImpl.CityImplBuilder cityName(final String cityName) {
            this.cityName = cityName;
            return this;
        }

        public CityImpl.CityImplBuilder regionName(final String regionName) {
            this.regionName = regionName;
            return this;
        }

        public CityImpl.CityImplBuilder streetName(final String streetName) {
            this.streetName = streetName;
            return this;
        }

        public CityImpl.CityImplBuilder houseNumber(final Long houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public CityImpl.CityImplBuilder entranceNumber(final Long entranceNumber) {
            this.entranceNumber = entranceNumber;
            return this;
        }

        public CityImpl.CityImplBuilder flatNumber(final Long flatNumber) {
            this.flatNumber = flatNumber;
            return this;
        }

        public CityImpl.CityImplBuilder user(final UserImpl user) {
            this.user = user;
            return this;
        }

        public CityImpl build() {
            return new CityImpl(this.id, this.cityName, this.regionName, this.streetName, this.houseNumber, this.entranceNumber, this.flatNumber, this.user);
        }

        public String toString() {
            return "CityImpl.CityImplBuilder(uuid=" + this.user + ", cityName=" + this.cityName + ", regionName=" + this.regionName + ", streetName=" + this.streetName +
                   ", houseNumber=" + this.houseNumber + ", entranceNumber=" + this.entranceNumber + ", flatNumber=" + this.flatNumber + ", user=" + this.user + ")";
        }
    }
}
