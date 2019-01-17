package de.drumcat.riotapichallengefx.domain;

import javax.persistence.*;

@Entity
@Table(name = "PROPERTIES")
public class PropertiesDto {
    @Id
    @Column
    private String key;
    @Column
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
