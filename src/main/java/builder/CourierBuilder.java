package builder;

import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonDeserialize;
import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;

@Getter
@JsonDeserialize(builder = CourierBuilder.Builder.class)

public class CourierBuilder {
    private String login;
    private String password;
    private String firstName;

    private CourierBuilder(Builder builder) {
        this.login = builder.login;
        this.password = builder.password;
        this.firstName = builder.firstName;
    }

    @JsonPOJOBuilder(withPrefix = "with")
    public static class Builder {
        private String login;
        private String password;
        private String firstName;

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CourierBuilder build() {
            return new CourierBuilder(this);
        }

    }

}
