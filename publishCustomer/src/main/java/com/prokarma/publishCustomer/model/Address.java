package com.prokarma.publishCustomer.model;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/** Address */
//@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-06-09T12:49:17.298Z")
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4714754529860580148L;

	@JsonProperty("addressLine1")
	private String addressLine1 = null;

	@JsonProperty("addressLine2")
	private String addressLine2 = null;

	@JsonProperty("street")
	private String street = null;

	public Address addressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
		return this;
	}

	/**
	 * address line1
	 *
	 * @return addressLine1
	 */
	@ApiModelProperty(required = true, value = "address line1")
	@NotNull
	@Pattern(regexp = "^[A-Za-z//d]{1,50}")
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public Address addressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
		return this;
	}

	/**
	 * Get addressLine2
	 *
	 * @return addressLine2
	 */
	@ApiModelProperty(value = "")
	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Address street(String street) {
		this.street = street;
		return this;
	}

	/**
	 * Street name
	 *
	 * @return street
	 */
	@ApiModelProperty(value = "Street name")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Address address = (Address) o;
		return Objects.equals(this.addressLine1, address.addressLine1)
				&& Objects.equals(this.addressLine2, address.addressLine2)
				&& Objects.equals(this.street, address.street);
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, street);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Address {\n");

		sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
		sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
		sb.append("    street: ").append(toIndentedString(street)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
