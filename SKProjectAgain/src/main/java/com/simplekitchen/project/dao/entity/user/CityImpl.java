package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.user.api.City;
import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    private Long uuid;

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
    @OneToMany(mappedBy = "city")
    private List<UserImpl> userList;

    public CityImpl() {
    }

    public CityImpl(String cityName) {
        this.cityName = cityName;
    }

    public CityImpl(String cityName, String regionName, String streetName, Long houseNumber, Long entranceNumber, Long flatNumber, List<UserImpl> userList) {
        this.cityName = cityName;
        this.regionName = regionName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.entranceNumber = entranceNumber;
        this.flatNumber = flatNumber;
        this.userList = userList;
    }

    public CityImpl(Long uuid, String cityName, String regionName, String streetName, Long houseNumber, Long entranceNumber, Long flatNumber, List<UserImpl> userList) {
        this.uuid = uuid;
        this.cityName = cityName;
        this.regionName = regionName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.entranceNumber = entranceNumber;
        this.flatNumber = flatNumber;
        this.userList = userList;
    }

    @Override
    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
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

    public List<UserImpl> getUserList() {
        return userList;
    }

    public void setUserList(List<UserImpl> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityImpl)) return false;
        CityImpl city = (CityImpl) o;
        return Objects.equals(getUuid(), city.getUuid()) && Objects.equals(getCityName(), city.getCityName()) && Objects.equals(getRegionName(), city.getRegionName()) && Objects.equals(getStreetName(), city.getStreetName()) && Objects.equals(getHouseNumber(), city.getHouseNumber()) && Objects.equals(getEntranceNumber(), city.getEntranceNumber()) && Objects.equals(getFlatNumber(), city.getFlatNumber()) && Objects.equals(getUserList(), city.getUserList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getCityName(), getRegionName(), getStreetName(), getHouseNumber(), getEntranceNumber(), getFlatNumber(), getUserList());
    }

    @Override
    public String toString() {
        return "CityImpl{" +
                "uuid=" + uuid +
                ", cityName='" + cityName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                ", entranceNumber=" + entranceNumber +
                ", flatNumber=" + flatNumber +
                ", userList=" + userList +
                '}';
    }

    public static CityImpl.CityImplBuilder builder() {
        return new CityImpl.CityImplBuilder();
    }

    public static class CityImplBuilder {
        private Long uuid;
        private String cityName;
        private String regionName;
        private String streetName;
        private Long houseNumber;
        private Long entranceNumber;
        private Long flatNumber;
        private List<UserImpl> userList;

        CityImplBuilder() {
        }

        public CityImpl.CityImplBuilder uuid(final Long uuid) {
            this.uuid = uuid;
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

        public CityImpl.CityImplBuilder userList(final List<UserImpl> userList) {
            this.userList = userList;
            return this;
        }

        public CityImpl build() {
            return new CityImpl(this.uuid, this.cityName, this.regionName, this.streetName, this.houseNumber, this.entranceNumber, this.flatNumber, this.userList);
        }

        public String toString() {
            return "CityImpl.CityImplBuilder(uuid=" + this.uuid + ", cityName=" + this.cityName + ", regionName=" + this.regionName + ", streetName=" + this.streetName + ", houseNumber=" + this.houseNumber + ", entranceNumber=" + this.entranceNumber + ", flatNumber=" + this.flatNumber + ", userList=" + this.userList + ")";
        }
    }
}
