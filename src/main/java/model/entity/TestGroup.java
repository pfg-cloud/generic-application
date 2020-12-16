package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
public class TestGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_uuid")
    private String groupUuid;

    @Column(name = "status")
    @Convert(converter = Status.Converter.class)
    private Status status;

    @Getter
    @AllArgsConstructor
    public enum Status {
        INACTIVE("I"), ACTIVE("A"), COMPLETE("C"), UNKNOWN("U");

        private final String code;

        public static class Converter implements AttributeConverter<Status, String> {

            @Override
            public String convertToDatabaseColumn(Status status) {
                return status.getCode();
            }

            @Override
            public Status convertToEntityAttribute(String s) {
                for (final Status status : values()) {
                    if (status.getCode().equalsIgnoreCase(s)) {
                        return status;
                    }
                }
                return Status.UNKNOWN;
            }
        }
    }
}
