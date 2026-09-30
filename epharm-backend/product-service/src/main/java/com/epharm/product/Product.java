package com.epharm.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    private String id;

    private String sku;
    private String brandName;
    private String genericName;
    private String category;
    private String dosageForm;
    private String strength;
    private String manufacturer;
    private Double unitPrice;
    private Integer reorderLevel;
    private Boolean prescriptionRequired;
    private String storageCondition;
    private String description;
    private String activeIngredients;
    private String status;
    private String packageSize;

    public Product() {
    }

    public Product(
            String id,
            String sku,
            String brandName,
            String genericName,
            String category,
            String dosageForm,
            String strength,
            String manufacturer,
            Double unitPrice,
            Integer reorderLevel,
            Boolean prescriptionRequired,
            String storageCondition,
            String description,
            String activeIngredients,
            String status,
            String packageSize) {

        this.id = id;
        this.sku = sku;
        this.brandName = brandName;
        this.genericName = genericName;
        this.category = category;
        this.dosageForm = dosageForm;
        this.strength = strength;
        this.manufacturer = manufacturer;
        this.unitPrice = unitPrice;
        this.reorderLevel = reorderLevel;
        this.prescriptionRequired = prescriptionRequired;
        this.storageCondition = storageCondition;
        this.description = description;
        this.activeIngredients = activeIngredients;
        this.status = status;
        this.packageSize = packageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Boolean getPrescriptionRequired() {
        return prescriptionRequired;
    }

    public void setPrescriptionRequired(Boolean prescriptionRequired) {
        this.prescriptionRequired = prescriptionRequired;
    }

    public String getStorageCondition() {
        return storageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        this.storageCondition = storageCondition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActiveIngredients() {
        return activeIngredients;
    }

    public void setActiveIngredients(String activeIngredients) {
        this.activeIngredients = activeIngredients;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }
}