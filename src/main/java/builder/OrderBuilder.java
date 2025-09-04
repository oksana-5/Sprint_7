package builder;

import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonDeserialize;
import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;

@Getter
@JsonDeserialize(builder = OrderBuilder.Builder.class)

public class OrderBuilder {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    private OrderBuilder(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.metroStation = builder.metroStation;
        this.phone = builder.phone;
        this.rentTime = builder.rentTime;
        this.deliveryDate = builder.deliveryDate;
        this.comment = builder.comment;
        this.color = builder.color;
    }

    @JsonPOJOBuilder(withPrefix = "with")
    public static class Builder {
        private String firstName;
        private String lastName;
        private String address;
        private String metroStation;
        private String phone;
        private int rentTime;
        private String deliveryDate;
        private String comment;
        private String[] color;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withMetroStation(String metroStation) {
            this.metroStation = metroStation;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withRentTime(int rentTime) {
            this.rentTime = rentTime;
            return this;
        }

        public Builder withDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder withColor(String[] color) {
            this.color = color;
            return this;
        }

        public OrderBuilder build() {
            return new OrderBuilder(this);
        }

    }

}